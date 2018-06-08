package com.github.zhycn.id.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "xbird.id.leaf.segment")
public class LeafSegmentProperties {

  private Boolean asynLoading = false;

  public Boolean getAsynLoading() {
    return asynLoading;
  }

  public void setAsynLoading(Boolean asynLoading) {
    this.asynLoading = asynLoading;
  }
  
}
