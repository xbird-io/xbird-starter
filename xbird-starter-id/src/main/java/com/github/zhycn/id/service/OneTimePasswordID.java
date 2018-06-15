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

import com.github.zhycn.id.factory.support.GoogleAuthenticatorException;
import com.github.zhycn.id.factory.support.TOTP;

/**
 * the Time-based One-time Password (TOTP) from Google Authenticator.
 * 
 * @author zhycn
 * @since 2.2.0 2018-06-14
 */
public interface OneTimePasswordID {

  /**
   * 验证码是否有效
   * 
   * @param secret 安全码
   * @param code 验证码
   * @param timestamp 生成码的时间
   * @return true for success
   * @throws GoogleAuthenticatorException
   */
  boolean authorize(String secret, int code, long timestamp) throws GoogleAuthenticatorException;

  /**
   * 验证码是否有效
   * 
   * @param totp
   * @return true for success
   * @throws GoogleAuthenticatorException
   */
  boolean authorize(TOTP totp) throws GoogleAuthenticatorException;

  /**
   * 获取一个验证码（系统当前时间）
   * 
   * @return 验证码
   */
  TOTP getTotpPassword();

  /**
   * 获取一个特定时间的验证码
   * 
   * @return 验证码
   */
  TOTP getTotpPassword(long timestamp);

}
