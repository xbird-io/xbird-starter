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
package cn.xbird.starter.id.factory.support;

import java.util.concurrent.TimeUnit;

/**
 * @author zhycn
 * @since 2.2.0 2018-06-15
 */
public class GoogleAuthenticatorConfig {

  private long timeStepSizeInMillis = TimeUnit.SECONDS.toMillis(30);
  private int windowSize = 3;
  private int codeDigits = 6;
  private int keyModulus = (int) Math.pow(10, codeDigits);
  private KeyRepresentation keyRepresentation = KeyRepresentation.BASE32;
  private HmacHashFunction hmacHashFunction = HmacHashFunction.HmacSHA1;

  public int getKeyModulus() {
    return keyModulus;
  }

  public KeyRepresentation getKeyRepresentation() {
    return keyRepresentation;
  }

  public int getCodeDigits() {
    return codeDigits;
  }

  public long getTimeStepSizeInMillis() {
    return timeStepSizeInMillis;
  }

  public int getWindowSize() {
    return windowSize;
  }

  public HmacHashFunction getHmacHashFunction() {
    return hmacHashFunction;
  }

  public static class GoogleAuthenticatorConfigBuilder {
    private GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig();

    public GoogleAuthenticatorConfig build() {
      return config;
    }

    public GoogleAuthenticatorConfigBuilder setCodeDigits(int codeDigits) {
      if (codeDigits <= 0) {
        throw new IllegalArgumentException("Code digits must be positive.");
      }

      if (codeDigits < 6) {
        throw new IllegalArgumentException("The minimum number of digits is 6.");
      }

      if (codeDigits > 8) {
        throw new IllegalArgumentException("The maximum number of digits is 8.");
      }

      config.codeDigits = codeDigits;
      config.keyModulus = (int) Math.pow(10, codeDigits);
      return this;
    }

    public GoogleAuthenticatorConfigBuilder setTimeStepSizeInMillis(long timeStepSizeInMillis) {
      if (timeStepSizeInMillis <= 0) {
        throw new IllegalArgumentException("Time step size must be positive.");
      }

      config.timeStepSizeInMillis = timeStepSizeInMillis;
      return this;
    }

    public GoogleAuthenticatorConfigBuilder setWindowSize(int windowSize) {
      if (windowSize <= 0) {
        throw new IllegalArgumentException("Window number must be positive.");
      }

      config.windowSize = windowSize;
      return this;
    }

    public GoogleAuthenticatorConfigBuilder setKeyRepresentation(
        KeyRepresentation keyRepresentation) {
      if (keyRepresentation == null) {
        throw new IllegalArgumentException("Key representation cannot be null.");
      }

      config.keyRepresentation = keyRepresentation;
      return this;
    }

    public GoogleAuthenticatorConfigBuilder setHmacHashFunction(HmacHashFunction hmacHashFunction) {
      if (hmacHashFunction == null) {
        throw new IllegalArgumentException("HMAC Hash Function cannot be null.");
      }

      config.hmacHashFunction = hmacHashFunction;
      return this;
    }
  }
}
