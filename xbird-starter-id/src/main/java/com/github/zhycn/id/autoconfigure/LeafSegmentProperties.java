package com.github.zhycn.id.autoconfigure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Leaf-segment Configuration Properties
 * 
 * @author qizhaohong@lakala.com
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
