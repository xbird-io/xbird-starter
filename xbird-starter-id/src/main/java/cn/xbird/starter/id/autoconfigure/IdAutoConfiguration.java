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
package cn.xbird.starter.id.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.xbird.starter.id.autoconfigure.SnowflakeProperties.Strategy;
import cn.xbird.starter.id.factory.DefaultSnowflakeFactory;
import cn.xbird.starter.id.factory.OneTimePasswordFactory;
import cn.xbird.starter.id.factory.RandomFactory;
import cn.xbird.starter.id.factory.TwitterSnowflakeFactory;
import cn.xbird.starter.id.factory.UrlShortenerFactory;
import cn.xbird.starter.id.factory.support.GoogleAuthenticator;
import cn.xbird.starter.id.factory.support.GoogleAuthenticatorConfig;
import cn.xbird.starter.id.service.OneTimePasswordID;
import cn.xbird.starter.id.service.RandomID;
import cn.xbird.starter.id.service.SnowflakeID;
import cn.xbird.starter.id.service.UrlShortenerID;

/**
 * ID Auto-configuration
 * 
 * @author zhycn
 * @since 2.2.0 2018-06-08
 */
@Configuration
@EnableConfigurationProperties({SnowflakeProperties.class, TotpProperties.class})
public class IdAutoConfiguration {

  @Autowired
  private SnowflakeProperties snowflakeProperties;

  @Autowired
  private TotpProperties totpProperties;

  @Bean
  @ConditionalOnMissingBean
  public OneTimePasswordID createOneTimePasswordFactory() {
    GoogleAuthenticatorConfig config =
        new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
            .setCodeDigits(totpProperties.getCodeDigits())
            .setHmacHashFunction(totpProperties.getHmacHashFunction())
            .setKeyRepresentation(totpProperties.getKeyRepresentation())
            .setTimeStepSizeInMillis(totpProperties.getTimeStepSizeInMillis())
            .setWindowSize(totpProperties.getWindowSize()).build();
    return new OneTimePasswordFactory(new GoogleAuthenticator(config));
  }

  @Bean
  @ConditionalOnMissingBean
  public RandomID createRandomFactory() {
    return new RandomFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  public SnowflakeID createSnowflakeFactory() {
    int workerId = snowflakeProperties.getWorkerId();
    int dataCenterId = snowflakeProperties.getDataCenterId();
    
    if (snowflakeProperties.getStrategy() == Strategy.twitter) {
      return new TwitterSnowflakeFactory(workerId, dataCenterId);
    }
    
    return new DefaultSnowflakeFactory(workerId);
  }

  @Bean
  @ConditionalOnMissingBean
  public UrlShortenerID createUrlShortenerFactory() {
    return new UrlShortenerFactory();
  }

}
