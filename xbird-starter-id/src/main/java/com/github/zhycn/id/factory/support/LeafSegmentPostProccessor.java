/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.zhycn.id.factory.support;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 美团 Leaf-segment 数据库方案的具体实现
 * 
 * @author qizhaohong@lakala.com
 * @since 2.2.0 2018-06-08
 */
public class LeafSegmentPostProccessor {

  private static Logger logger = LoggerFactory.getLogger(LeafSegmentPostProccessor.class);
  private static ReentrantLock lock = new ReentrantLock();
  private static ExecutorService taskExecutor = Executors.newSingleThreadExecutor();
  private volatile LeafSegment[] segments = new LeafSegment[2]; // 采用双buffer
  private LeafSegmentAware leafSegmentAware; // 外部注入数据
  private boolean asynLoading; // 是否为异步加载buffer
  private AtomicLong currentId; // 当前ID值
  private volatile boolean sw; // 控制开关，切换buffer

  public LeafSegmentPostProccessor(LeafSegmentAware leafSegmentAware, boolean asynLoading) {
    this.sw = false;
    this.asynLoading = asynLoading;
    this.leafSegmentAware = leafSegmentAware;
    this.segments[index()] = leafSegmentAware.loadAndUpdate();
    this.currentId = new AtomicLong(segments[index()].getMinId());
    logger.debug("Leaf-segment init load segment[0]: {}", segments[index()].toString());
    logger.debug("Leaf-segment asynLoading: {}", asynLoading);
    logger.debug("Leaf-segment currentId: {}", currentId);
    logger.info("Leaf-segment init success.");
  }

  /**
   * 基于异步加载策略来获取ID
   * 
   * @return ID
   */
  private Long asyncGetId() {
    if (isMiddleId() || isMaxId()) {
      try {
        lock.lock();
        FutureTask<Boolean> futureTask = null;
        if (isMiddleId()) {
          futureTask = new FutureTask<>(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
              segments[reIndex()] = leafSegmentAware.loadAndUpdate();
              logger.debug("Leaf-segment asyn load segment[{}]: {}", reIndex(),
                  segments[reIndex()].toString());
              return true;
            }
          });
          taskExecutor.submit(futureTask);
        }

        if (isMaxId()) {
          try {
            if (futureTask.get()) {
              this.setSw(); // 切换buffer
              currentId = new AtomicLong(segments[index()].getMinId());
            }
          } catch (Exception e) {
            // 强制同步切换
            logger.warn("Leaf-segment asyn load error", e);
            segments[reIndex()] = leafSegmentAware.loadAndUpdate();
            logger.debug("Leaf-segment sync load segment[{}]: {}", reIndex(),
                segments[reIndex()].toString());
            this.setSw(); // 切换buffer
            currentId = new AtomicLong(segments[index()].getMinId());
          }
        }
      } finally {
        lock.unlock();
      }
    }
    return currentId.getAndIncrement();
  }

  /**
   * 获取一个ID值
   * 
   * @return ID
   */
  public Long getId() {
    return asynLoading ? asyncGetId() : syncGetId();
  }

  /**
   * 当前 buffer 索引（用于获取当前ID，由控制开关来实现切换）
   * 
   * @return 当前的索引值
   */
  private int index() {
    if (sw) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * 当前ID是否为缓存的最大ID值
   * 
   * @return true for maxId
   */
  private boolean isMaxId() {
    return segments[index()].getMaxId().equals(currentId.longValue());
  }

  /**
   * 当前ID是否为缓存的中间ID值
   * 
   * @return true for middleId
   */
  private boolean isMiddleId() {
    return segments[index()].getMiddleId().equals(currentId.longValue());
  }

  /**
   * 反转 buffer 索引（用于加载另一个 buffer数据，由控制开关来实现切换）
   * 
   * @return 反转后的索引值
   */
  private int reIndex() {
    if (sw) {
      return 0;
    } else {
      return 1;
    }
  }

  /**
   * 切换
   */
  private void setSw() {
    logger.info("Switch index from {} to {}", index(), reIndex());
    this.sw = !sw;
  }

  /**
   * 基于同步加载策略来获取ID
   * 
   * @return ID
   */
  private Long syncGetId() {
    if (isMiddleId() || isMaxId()) {
      try {
        lock.lock();
        if (isMiddleId()) {
          segments[reIndex()] = leafSegmentAware.loadAndUpdate();
          logger.debug("Leaf-segment sync load segment[{}]: {}", reIndex(),
              segments[reIndex()].toString());
        }

        if (isMaxId()) {
          this.setSw(); // 切换
          currentId = new AtomicLong(segments[index()].getMinId());
        }
      } finally {
        lock.unlock();
      }
    }
    return currentId.getAndIncrement();
  }

}
