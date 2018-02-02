package com.github.zhycn.retrofit2.interceptors;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求重试拦截器
 * 
 * @author zhycn
 * @since 1.0.0 2018-02-02
 */
public class RetryInterceptor implements Interceptor {

  Logger LOGGER = LoggerFactory.getLogger(RetryInterceptor.class);

  private final int retries;

  public RetryInterceptor(int retries) {
    this.retries = retries;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    Response response = doRequest(chain, request);
    int tryCount = 0;
    while (response == null && tryCount < retries) {
      Request newRequest = request.newBuilder().build(); // Avoid the cache
      response = doRequest(chain, newRequest);
      tryCount++;
      LOGGER.warn("Request failed, retry to acquire a new connection, {} in {} retries", tryCount,
          retries);
    }
    if (response == null) { // Important ,should throw an exception here
      throw new IOException();
    }
    return response;
  }

  private Response doRequest(Chain chain, Request request) {
    Response response = null;
    try {
      response = chain.proceed(request);
    } catch (Exception e) {
      LOGGER.error("doRequest error", e);
    }
    return response;
  }

}
