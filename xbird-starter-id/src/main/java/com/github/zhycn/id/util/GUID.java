/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.zhycn.id.util;

/**
 * GUID Generator
 * 
 * @author zhycn
 * @since 2.2.0 2018-04-18
 */
public abstract class GUID {

  /**
   * 随机生成一个GUID字符串
   * 
   * @return GUID字符串
   */
  public static String random() {
    return UUID.random().replaceAll("-", "");
  }

  /**
   * 根据指定字符生成一个GUID字符串
   * 
   * @param name 指定字符
   * @return GUID字符串
   */
  public static String random(String name) {
    return UUID.random(name).replaceAll("-", "");
  }

  /**
   * 随机生成一个GUID字符串，并转换为大写字母
   * 
   * @return GUID字符串
   */
  public static String randomUpperCase() {
    return random().toUpperCase();
  }

  /**
   * 根据指定字符生成一个GUID字符串，并转换为大写字母
   * 
   * @param name 指定字符
   * @return GUID字符串
   */
  public static String randomUpperCase(String name) {
    return random(name).toUpperCase();
  }

}
