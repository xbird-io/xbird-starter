package com.github.zhycn.retrofit2.context;

import java.util.Optional;

import retrofit2.Retrofit;

/**
 * The K,V store for retrofit instance, because the retrofit instance is immutable, and we couldn't
 * get some useful identify from it's public method.
 * 
 * In order to support multiply base url endpoint instance, we must create and store them
 * separately.
 *
 * @author zhycn
 * @since 1.0.0 2018-02-02
 */
public interface RetrofitContext {

  Retrofit register(String identity, Retrofit retrofit);

  boolean unregister(String identity);

  Optional<Retrofit> find(String identity);

  boolean exists(String identity);

}
