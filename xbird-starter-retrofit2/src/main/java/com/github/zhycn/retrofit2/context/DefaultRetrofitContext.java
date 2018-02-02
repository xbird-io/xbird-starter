package com.github.zhycn.retrofit2.context;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Retrofit;

/**
 * @author zhycn
 * @since 1.0.0 2018-02-02
 */
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
  public Optional<Retrofit> find(String identity) {
    return Optional.ofNullable(get(identity));
  }

  @Override
  public boolean exists(String identity) {
    return containsKey(identity);
  }

}
