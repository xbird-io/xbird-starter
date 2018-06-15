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
package com.github.zhycn.id.autoconfigure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Leaf-segment Configuration Properties
 * 
 * @author zhycn
 * @since 2.2.0 2018-06-08
 */
@ConfigurationProperties(prefix = "xbird.id.segment")
public class LeafSegmentProperties {

  private boolean enabled;
  private boolean asynLoading;
  private Map<String, LeafSegmentData> endpoints = new HashMap<>();

  public boolean getAsynLoading() {
    return asynLoading;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setAsynLoading(boolean asynLoading) {
    this.asynLoading = asynLoading;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Map<String, LeafSegmentData> getEndpoints() {
    return endpoints;
  }

  public void setEndpoints(Map<String, LeafSegmentData> endpoints) {
    this.endpoints = endpoints;
  }

  public static class LeafSegmentData {

    private String bizTag;
    private Long startId;
    private Long step;
    private String description;

    public String getBizTag() {
      return bizTag;
    }

    public void setBizTag(String bizTag) {
      this.bizTag = bizTag;
    }

    public Long getStartId() {
      return startId;
    }

    public void setStartId(Long startId) {
      this.startId = startId;
    }

    public Long getStep() {
      return step;
    }

    public void setStep(Long step) {
      this.step = step;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }
  }

}
