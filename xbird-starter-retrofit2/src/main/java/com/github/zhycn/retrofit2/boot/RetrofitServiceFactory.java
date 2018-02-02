package com.github.zhycn.retrofit2.boot;

import com.github.zhycn.retrofit2.context.RetrofitContext;

import retrofit2.Retrofit;

/**
 * 服务工厂
 * 
 * @author zhycn
 * @since 1.0.0 2018-02-02
 */
public class RetrofitServiceFactory {

  private final RetrofitContext retrofitContext;

  public RetrofitServiceFactory(RetrofitContext retrofitContext) {
    this.retrofitContext = retrofitContext;
  }

  public <T> T createServiceInstance(Class<T> serviceClass, String identity) {
    Retrofit retrofit = getConfiguredRetrofit(identity);
    return retrofit.create(serviceClass);
  }

  private Retrofit getConfiguredRetrofit(String identity) {
    return retrofitContext.get(identity).orElseThrow(() -> new RuntimeException(
        "Cannot obtain [" + identity + "] Retrofit in your application configuration file."));
  }

}
