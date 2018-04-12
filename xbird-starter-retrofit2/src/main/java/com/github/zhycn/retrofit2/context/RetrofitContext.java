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
