package com.github.zhycn.retrofit2.autoconfigure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "xbird.retrofit2")
public class RetrofitProperties {

  private Long readTimeout = 10000L;

  private Long writeTimeout = 10000L;

  private Long connectTimeout = 10000L;

  private Integer maxIdleConnections = 5;

  private Integer keepAliveDuration = 5;

  private int retries = 0;
  
  private Map<String, String> endpoints = new HashMap<>();

  public Long getReadTimeout() {
    return readTimeout;
  }

  public Long getWriteTimeout() {
    return writeTimeout;
  }

  public Long getConnectTimeout() {
    return connectTimeout;
  }

  public Integer getMaxIdleConnections() {
    return maxIdleConnections;
  }

  public Integer getKeepAliveDuration() {
    return keepAliveDuration;
  }

  public int getRetries() {
    return retries;
  }

  public Map<String, String> getEndpoints() {
    return endpoints;
  }

  public void setReadTimeout(Long readTimeout) {
    this.readTimeout = readTimeout;
  }

  public void setWriteTimeout(Long writeTimeout) {
    this.writeTimeout = writeTimeout;
  }

  public void setConnectTimeout(Long connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  public void setMaxIdleConnections(Integer maxIdleConnections) {
    this.maxIdleConnections = maxIdleConnections;
  }

  public void setKeepAliveDuration(Integer keepAliveDuration) {
    this.keepAliveDuration = keepAliveDuration;
  }

  public void setRetries(int retries) {
    this.retries = retries;
  }

  public void setEndpoints(Map<String, String> endpoints) {
    this.endpoints = endpoints;
  }

}
