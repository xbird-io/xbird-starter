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

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author qizhaohong@lakala.com
 * @since 2.2.0 2018-06-08
 */
public class LeafSegment implements InitializingBean {

  private Long minId;
  private Long maxId;
  private Long middleId;
  private Long step;

  public LeafSegment(Long maxId, Long step) {
    Assert.notNull(maxId, "maxId must be not null.");
    Assert.notNull(step, "step must be not null.");
    this.maxId = maxId;
    this.step = step;
    try {
      afterPropertiesSet();
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  public Long getMaxId() {
    return maxId;
  }

  public Long getMiddleId() {
    return middleId;
  }

  public Long getMinId() {
    return minId;
  }

  public Long getStep() {
    return step;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    this.middleId = this.maxId - (long) Math.round(this.step / 2);
    this.minId = this.maxId - this.step;
  }

}
