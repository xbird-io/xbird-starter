package com.github.zhycn.retrofit2.context;

import java.util.Optional;

import retrofit2.Retrofit;

public interface RetrofitContext {

  Retrofit register(String identity, Retrofit retrofit);

  boolean unregister(String identity);

  Optional<Retrofit> get(String identity);

  boolean contains(String identity);

  boolean empty();

}
