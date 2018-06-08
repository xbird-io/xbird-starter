package com.github.zhycn.id.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Leaf-segment Configuration Properties
 * 
 * @author qizhaohong@lakala.com
 * @since 2.2.0 2018-06-08
 */
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
