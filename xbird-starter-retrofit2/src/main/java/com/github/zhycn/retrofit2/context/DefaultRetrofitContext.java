package com.github.zhycn.retrofit2.context;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Retrofit;

public class DefaultRetrofitContext extends ConcurrentHashMap<String, Retrofit>
    implements
      RetrofitContext {

  private static final long serialVersionUID = 1L;

  @Override
  public Retrofit register(String identity, Retrofit retrofit) {
    return put(identity, retrofit);
  }

  @Override
  public boolean unregister(String identity) {
    return remove(identity) != null;
  }

  @Override
  public Optional<Retrofit> get(String identity) {
    return Optional.ofNullable(super.get(identity));
  }

  @Override
  public boolean contains(String identity) {
    return containsKey(identity);
  }

  @Override
  public boolean empty() {
    clear();
    return true;
  }

}
