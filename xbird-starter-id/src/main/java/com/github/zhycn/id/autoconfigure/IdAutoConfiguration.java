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
package com.github.zhycn.id.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.zhycn.id.factory.ILeafSegment;
import com.github.zhycn.id.factory.LeafSegmentFactory;
import com.github.zhycn.id.repository.LeafSegmentRepository;

/**
 * ID Auto-configuration
 * 
 * @author zhycn
 * @since 2.2.0 2018-06-08
 */
@Configuration
@ConditionalOnBean(LeafJPAConfiguration.class)
public class IdAutoConfiguration {

  @Autowired
  private LeafSegmentRepository repository;

  @Bean
  public ILeafSegment createLeafSegmentFactory() {
    return new LeafSegmentFactory(repository, false);
  }
  
}
