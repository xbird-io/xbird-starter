package com.github.zhycn.retrofit2.autoconfigure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "xbird.retrofit2")
public class RetrofitProperties {

  private Connection connection = new Connection();

  private List<EndPoint> endpoints = new ArrayList<>();

  public static class EndPoint {

    private String identity;

    private String baseUrl;

    public String getIdentity() {
      return identity;
    }

    public String getBaseUrl() {
      return baseUrl;
    }

    public void setIdentity(String identity) {
      this.identity = identity;
    }

    public void setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
    }
  }

  public static class Connection {

    private Long readTimeout = 10000L;

    private Long writeTimeout = 10000L;

    private Long connectTimeout = 10000L;

    private Integer maxIdleConnections = 5;

    private Integer keepAliveDuration = 5;

    private int retries = 0;

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

  }

  public Connection getConnection() {
    return connection;
  }

  public List<EndPoint> getEndpoints() {
    return endpoints;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  public void setEndpoints(List<EndPoint> endpoints) {
    this.endpoints = endpoints;
  }

}
