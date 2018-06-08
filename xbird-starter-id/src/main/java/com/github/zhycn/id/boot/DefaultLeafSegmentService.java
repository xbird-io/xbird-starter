/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.zhycn.id.boot;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 默认的 Leaf-segment 实现
 * 
 * @author qizhaohong@lakala.com
 * @since 2.2.0 2018-06-08
 */
public class DefaultLeafSegmentService implements LeafSegmentService {

  private static ReentrantLock lock = new ReentrantLock();
  private static ExecutorService taskExecutor = Executors.newSingleThreadExecutor();
  private volatile LeafSegment[] segments = new LeafSegment[2];
  private boolean asynLoading;
  private AtomicLong currentId;
  private volatile boolean sw;
  private LeafSegmentAware leafSegmentAware;

  public DefaultLeafSegmentService(LeafSegmentAware leafSegmentAware, boolean asynLoading) {
    this.leafSegmentAware = leafSegmentAware;
    this.setSw(false);
    this.setAsynLoading(asynLoading);
    this.segments[index()] = leafSegmentAware.loadAndUpdate();
    this.currentId = new AtomicLong(segments[index()].getMinId());
  }

  private boolean isSw() {
    return sw;
  }

  private void setSw(boolean sw) {
    this.sw = sw;
  }

  public boolean isAsynLoading() {
    return asynLoading;
  }

  public void setAsynLoading(boolean asynLoading) {
    this.asynLoading = asynLoading;
  }

  private int index() {
    if (isSw()) {
      return 1;
    } else {
      return 0;
    }
  }

  private int reIndex() {
    if (isSw()) {
      return 0;
    } else {
      return 1;
    }
  }

  public Long getId() {
    return asynLoading ? asyncGetId() : syncGetId();
  }

  public Long syncGetId() {
    if (segments[index()].getMiddleId().equals(currentId.longValue())
        || segments[index()].getMaxId().equals(currentId.longValue())) {
      try {
        lock.lock();
        if (segments[index()].getMiddleId().equals(currentId.longValue())) {
          segments[reIndex()] = leafSegmentAware.loadAndUpdate();
        }

        if (segments[index()].getMaxId().equals(currentId.longValue())) {
          this.setSw(!isSw()); // 切换
          currentId = new AtomicLong(segments[index()].getMinId());
        }
      } finally {
        lock.unlock();
      }
    }

    return currentId.getAndIncrement();
  }

  public Long asyncGetId() {
    if (segments[index()].getMiddleId().equals(currentId.longValue())
        || segments[index()].getMaxId().equals(currentId.longValue())) {
      try {
        lock.lock();
        FutureTask<Boolean> futureTask = null;
        if (segments[index()].getMiddleId().equals(currentId.longValue())) {
          futureTask = new FutureTask<>(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
              segments[reIndex()] = leafSegmentAware.loadAndUpdate();
              return true;
            }

          });
          taskExecutor.submit(futureTask);
        }

        if (segments[index()].getMaxId().equals(currentId.longValue())) {
          try {
            if (futureTask.get()) {
              this.setSw(!isSw()); // 切换
              currentId = new AtomicLong(segments[index()].getMinId());
            }
          } catch (Exception e) {
            e.printStackTrace();
            // 强制同步切换
            segments[reIndex()] = leafSegmentAware.loadAndUpdate();
            this.setSw(!isSw());
            currentId = new AtomicLong(segments[index()].getMinId());
          }
        }
      } finally {
        lock.unlock();
      }
    }

    return currentId.getAndIncrement();
  }

}
