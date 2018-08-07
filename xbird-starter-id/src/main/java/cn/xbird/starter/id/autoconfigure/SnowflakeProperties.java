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
package cn.xbird.starter.id.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Leaf-snowflake Configuration Properties
 * 
 * @author zhycn
 * @since 2.2.0 2018-06-08
 */
@ConfigurationProperties(prefix = "xbird.id.snowflake")
public class SnowflakeProperties {

  public enum Strategy {
    twitter, leaf;
  }

  /**
   * Strategy: Twitter, MTLeaf
   */
  private Strategy strategy = Strategy.leaf;

  /**
   * workerId: 0~31
   */
  private Integer workerId = 0;

  /**
   * dataCenterId: 0~31
   */
  private Integer dataCenterId = 0;

  public Integer getDataCenterId() {
    return dataCenterId;
  }

  public Strategy getStrategy() {
    return strategy;
  }

  public Integer getWorkerId() {
    return workerId;
  }

  public void setDataCenterId(Integer dataCenterId) {
    this.dataCenterId = dataCenterId;
  }

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  public void setWorkerId(Integer workerId) {
    this.workerId = workerId;
  }

}
