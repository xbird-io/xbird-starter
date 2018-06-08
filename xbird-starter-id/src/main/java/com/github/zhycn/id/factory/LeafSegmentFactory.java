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
package com.github.zhycn.id.factory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

import com.github.zhycn.id.boot.DefaultLeafSegmentService;
import com.github.zhycn.id.boot.LeafSegment;
import com.github.zhycn.id.boot.LeafSegmentAware;
import com.github.zhycn.id.boot.LeafSegmentService;
import com.github.zhycn.id.domain.LeafSegmentEntity;
import com.github.zhycn.id.repository.LeafSegmentRepository;

/**
 * @author qizhaohong@lakala.com
 * @since 2.2.0 2018-06-08
 */
public class LeafSegmentFactory implements LeafSegmentID {

  private static final String REGEXP = "^[a-zA-Z0-9_-]{3,64}$";
  private static ReentrantLock lock = new ReentrantLock();
  private static ConcurrentMap<String, LeafSegmentService> bizTagMap = new ConcurrentHashMap<>();
  private LeafSegmentRepository repository;
  private boolean asynLoading;

  public LeafSegmentFactory(LeafSegmentRepository repository, boolean asynLoading) {
    this.repository = repository;
    this.asynLoading = asynLoading;
  }

  private void check(String bizTag) {
    if (!Pattern.matches(REGEXP, bizTag)) {
      throw new IllegalArgumentException("Unsupported format: bizTag=" + bizTag);
    }
  }

  @Override
  public Long getId(String bizTag) {
    this.check(bizTag);

    if (bizTagMap.containsKey(bizTag)) {
      return bizTagMap.get(bizTag).getId();
    }

    try {
      lock.lock();
      LeafSegmentService leafSegmentService = new DefaultLeafSegmentService(new LeafSegmentAware() {

        @Override
        public LeafSegment loadAndUpdate() {
          if (!repository.existsById(bizTag)) {
            throw new IllegalArgumentException("bizTag[" + bizTag + "] not exists.");
          }
          LeafSegmentEntity entity = repository.findById(bizTag).get();
          final LeafSegment currentSegment = new LeafSegment(entity.getMaxId(), entity.getStep());
          final Long newMaxId = currentSegment.getMaxId() + currentSegment.getStep();
          entity.setMaxId(newMaxId);
          repository.save(entity);
          return new LeafSegment(newMaxId, currentSegment.getStep());
        }
      }, asynLoading);
      bizTagMap.putIfAbsent(bizTag, leafSegmentService);
    } finally {
      lock.unlock();
    }

    return bizTagMap.get(bizTag).getId();
  }

  @Override
  public Long getId(String bizTag, Long userId, Integer dbSize) {
    Long prefixOrderId = this.getId(bizTag);
    int leftMoveBit = this.leftMoveBit(dbSize);
    Long lastOrderId = ((prefixOrderId << leftMoveBit) | (userId % dbSize)); // 加入userId基因
    return lastOrderId;
  }

  @Override
  public void init(String bizTag, long step) {
    this.init(bizTag, 0, step);
  }

  @Override
  public void init(String bizTag, long startId, long step) {
    this.init(bizTag, startId, step, null);
  }

  @Override
  public void init(String bizTag, long startId, long step, String desc) {
    this.check(bizTag);

    if (!repository.existsById(bizTag)) {
      LeafSegmentEntity entity = new LeafSegmentEntity();
      entity.setBizTag(bizTag);
      entity.setDescription(desc);
      entity.setMaxId(startId);
      entity.setStep(step);
      repository.save(entity);
    }
  }

  private int leftMoveBit(Integer dbSize) {
    return Integer.toBinaryString(dbSize).length() - 1;
  }

}
