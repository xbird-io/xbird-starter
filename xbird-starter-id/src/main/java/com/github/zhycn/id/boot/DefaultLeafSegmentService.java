package com.github.zhycn.id.boot;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultLeafSegmentService implements LeafSegmentService {

  private static ReentrantLock lock = new ReentrantLock();
  private static ExecutorService taskExecutor = Executors.newSingleThreadExecutor();
  private volatile LeafSegment[] segments = new LeafSegment[2]; // 这两段用来存储每次拉升之后的最大值
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
