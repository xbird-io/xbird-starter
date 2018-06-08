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
 * SMS ID Generator
 * 
 * @author zhycn
 * @since 2.2.0 2018-04-18
 */
public abstract class SmsID {

  /**
   * 随机生成6位长度的短信验证码
   * 
   * @return 6位长度短信验证码
   */
  public static String random() {
    return random(6);
  }

  /**
   * 随机生成指定长度的短信验证码
   * 
   * @param count 指定长度
   * @return 指定长度短信验证码
   */
  public static String random(int count) {
    return RandomID.randomInt(count);
  }

}
