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
package cn.xbird.starter.id.autoconfigure;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;

import cn.xbird.starter.id.factory.support.HmacHashFunction;
import cn.xbird.starter.id.factory.support.KeyRepresentation;

/**
 * TOTP Configuration Properties
 * 
 * @author zhycn
 * @since Better.SR1 2018-08-07
 */
@ConfigurationProperties(prefix = "xbird.id.totp")
public class TotpProperties {

  private long timeStepSizeInMillis = TimeUnit.SECONDS.toMillis(30);
  private int windowSize = 3;
  private int codeDigits = 6;
  private KeyRepresentation keyRepresentation = KeyRepresentation.BASE32;
  private HmacHashFunction hmacHashFunction = HmacHashFunction.HmacSHA1;

  public long getTimeStepSizeInMillis() {
    return timeStepSizeInMillis;
  }

  public void setTimeStepSizeInMillis(long timeStepSizeInMillis) {
    this.timeStepSizeInMillis = timeStepSizeInMillis;
  }

  public int getWindowSize() {
    return windowSize;
  }

  public void setWindowSize(int windowSize) {
    this.windowSize = windowSize;
  }

  public int getCodeDigits() {
    return codeDigits;
  }

  public void setCodeDigits(int codeDigits) {
    this.codeDigits = codeDigits;
  }

  public KeyRepresentation getKeyRepresentation() {
    return keyRepresentation;
  }

  public void setKeyRepresentation(KeyRepresentation keyRepresentation) {
    this.keyRepresentation = keyRepresentation;
  }

  public HmacHashFunction getHmacHashFunction() {
    return hmacHashFunction;
  }

  public void setHmacHashFunction(HmacHashFunction hmacHashFunction) {
    this.hmacHashFunction = hmacHashFunction;
  }

}
