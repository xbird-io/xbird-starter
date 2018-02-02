package com.github.zhycn.retrofit2.autoconfigure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "xbird.retrofit2")
public class RetrofitProperties {

  private Long readTimeout = 10000L;
  private Long writeTimeout = 10000L;
  private Long connectTimeout = 10000L;
  private Integer maxIdle = 5;
  private Integer keepAlive = 5;
  private Map<String, String> endpoints = new HashMap<>();

  public Long getConnectTimeout() {
    return connectTimeout;
  }

  public Map<String, String> getEndpoints() {
    return endpoints;
  }

  public Integer getKeepAlive() {
    return keepAlive;
  }

  public Integer getMaxIdle() {
    return maxIdle;
  }

  public Long getReadTimeout() {
    return readTimeout;
  }

  public Long getWriteTimeout() {
    return writeTimeout;
  }

  public void setConnectTimeout(Long connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  public void setEndpoints(Map<String, String> endpoints) {
    this.endpoints = endpoints;
  }

  public void setKeepAlive(Integer keepAlive) {
    this.keepAlive = keepAlive;
  }

  public void setMaxIdle(Integer maxIdle) {
    this.maxIdle = maxIdle;
  }

  public void setReadTimeout(Long readTimeout) {
    this.readTimeout = readTimeout;
  }

  public void setWriteTimeout(Long writeTimeout) {
    this.writeTimeout = writeTimeout;
  }

}
