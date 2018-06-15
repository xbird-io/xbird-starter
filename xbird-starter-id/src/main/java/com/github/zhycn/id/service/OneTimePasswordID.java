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

  boolean authorize(String secret, int code, long timestamp) throws GoogleAuthenticatorException;

  boolean authorize(TOTP totp) throws GoogleAuthenticatorException;

  TOTP getTotpPassword();

  TOTP getTotpPassword(long timestamp);

}
