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
package com.github.zhycn.swagger2.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 Auto-configuration
 * 
 * @author zhycn
 * @since 1.0.0 2018-01-30
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "xbird.swagger2", name = "enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(Swagger2Properties.class)
public class Swagger2AutoConfiguration {

  @Autowired
  private Swagger2Properties properties;

  @Bean
  @ConditionalOnMissingBean
  public Docket defaultDocketApi() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(properties.build()).select()
        .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
  }

}
