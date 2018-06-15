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
package com.github.zhycn.id.service;

/**
 * 生成短链接接口
 * 
 * @author zhycn
 * @since 2.2.0 2018-06-08
 */
public interface UrlShortenerID {

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @return 返回生成短网址数组，选其一作为短网址（默认6位长度）
   */
  String[] create(String url);

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @param length 指定生成短链接的长度，取值范围：1~8
   * @return 返回生成短网址数组，选其一作为短网址
   */
  String[] create(String url, int length);

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @param secret 和实际网址混合生成短网址的掩码
   * @param length 指定生成短链接的长度，取值范围：1~8
   * @return 返回生成短网址数组，选其一作为短网址
   */
  String[] create(String url, String secret, int length);

}
