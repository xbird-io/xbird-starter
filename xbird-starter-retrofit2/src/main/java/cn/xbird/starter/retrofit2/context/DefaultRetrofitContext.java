/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.xbird.starter.retrofit2.context;

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
