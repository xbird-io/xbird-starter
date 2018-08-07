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
package cn.xbird.starter.id.factory;

import org.springframework.util.Assert;

import cn.xbird.starter.id.factory.support.GoogleAuthenticatorException;
import cn.xbird.starter.id.factory.support.GoogleAuthenticatorKey;
import cn.xbird.starter.id.factory.support.IGoogleAuthenticator;
import cn.xbird.starter.id.factory.support.TOTP;
import cn.xbird.starter.id.service.OneTimePasswordID;

/**
 * TOTP 实现工厂
 * 
 * @author zhycn
 * @since 2.2.0 2018-06-15
 */
public class OneTimePasswordFactory implements OneTimePasswordID {

  private IGoogleAuthenticator googleAuthenticator;

  public OneTimePasswordFactory(IGoogleAuthenticator googleAuthenticator) {
    this.googleAuthenticator = googleAuthenticator;
  }

  @Override
  public boolean authorize(String secret, int code, long timestamp)
      throws GoogleAuthenticatorException {
    return googleAuthenticator.authorize(secret, code, timestamp);
  }

  @Override
  public boolean authorize(TOTP totp) throws GoogleAuthenticatorException {
    Assert.notNull(totp, "TOTP cannot be null.");
    return authorize(totp.getSecret(), totp.getCodeAsInt(), totp.getTimestamp());
  }

  @Override
  public TOTP getTotpPassword() {
    return getTotpPassword(System.currentTimeMillis());
  }

  @Override
  public TOTP getTotpPassword(long timestamp) {
    GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
    String code = googleAuthenticator.getTotpPassword(key.getKey(), timestamp);
    return new TOTP(key.getKey(), code, key.getScratchCodes(), timestamp);
  }

}
