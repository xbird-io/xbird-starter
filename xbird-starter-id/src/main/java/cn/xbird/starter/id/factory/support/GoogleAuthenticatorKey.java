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

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhycn
 * @since 2.2.0 2018-06-15
 */
public final class GoogleAuthenticatorKey {

  private final GoogleAuthenticatorConfig config;
  private final String key;
  private final int verificationCode;
  private final List<Integer> scratchCodes;

  private GoogleAuthenticatorKey(GoogleAuthenticatorConfig config, String key, int verificationCode,
      List<Integer> scratchCodes) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }

    if (config == null) {
      throw new IllegalArgumentException("Configuration cannot be null");
    }

    if (scratchCodes == null) {
      throw new IllegalArgumentException("Scratch codes cannot be null");
    }

    this.config = config;
    this.key = key;
    this.verificationCode = verificationCode;
    this.scratchCodes = new ArrayList<>(scratchCodes);
  }

  public List<Integer> getScratchCodes() {
    return scratchCodes;
  }

  public GoogleAuthenticatorConfig getConfig() {
    return config;
  }

  public String getKey() {
    return key;
  }

  public int getVerificationCode() {
    return verificationCode;
  }

  public static class Builder {
    private GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig();
    private String key;
    private int verificationCode;
    private List<Integer> scratchCodes = new ArrayList<>();

    public Builder(String key) {
      this.key = key;
    }

    public GoogleAuthenticatorKey build() {
      return new GoogleAuthenticatorKey(config, key, verificationCode, scratchCodes);
    }

    public Builder setConfig(GoogleAuthenticatorConfig config) {
      this.config = config;
      return this;
    }

    public Builder setKey(String key) {
      this.key = key;
      return this;
    }

    public Builder setVerificationCode(int verificationCode) {
      this.verificationCode = verificationCode;
      return this;
    }

    public Builder setScratchCodes(List<Integer> scratchCodes) {
      this.scratchCodes = scratchCodes;
      return this;
    }
  }
}
