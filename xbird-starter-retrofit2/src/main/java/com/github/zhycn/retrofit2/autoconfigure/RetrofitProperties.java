package com.github.zhycn.retrofit2.autoconfigure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Retrofit Configuration properties.
 * 
 * @author zhycn
 * @since 1.0.0 2018-04-12
 */
@ConfigurationProperties(prefix = "xbird.retrofit2")
public class RetrofitProperties {

  private Long readTimeout = 8000L;
  private Long writeTimeout = 8000L;
  private Long connectTimeout = 5000L;
  private Integer maxIdle = 5;
  private Integer keepAlive = 120; 
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
