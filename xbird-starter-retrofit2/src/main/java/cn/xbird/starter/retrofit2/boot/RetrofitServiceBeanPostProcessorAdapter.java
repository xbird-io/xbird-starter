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
package cn.xbird.starter.retrofit2.boot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.Assert;

import cn.xbird.starter.retrofit2.annotations.RetrofitClient;
import cn.xbird.starter.retrofit2.context.RetrofitContext;

/**
 * @author zhycn
 * @since 1.0.0 2018-02-02
 */
public class RetrofitServiceBeanPostProcessorAdapter
    extends InstantiationAwareBeanPostProcessorAdapter
    implements
      BeanFactoryAware,
      PriorityOrdered {

  static final String BEAN_NAME = "retrofitServiceBeanPostProcessorAdapter";
  private BeanFactory beanFactory;
  private RetrofitServiceFactory retrofitServiceFactory;

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE - 1;
  }

  @Override
  public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
      throws BeansException {
    Object ret = null;

    if (beanClass.isAnnotationPresent(RetrofitClient.class)) {
      RetrofitClient annotation = beanClass.getAnnotation(RetrofitClient.class);
      String identity = annotation.value();
      ret = getRetrofitServiceFactory().createServiceInstance(beanClass, identity);
    }

    return ret;
  }

  private RetrofitServiceFactory getRetrofitServiceFactory() {
    Assert.notNull(beanFactory, "BeanFactory may not be null");

    if (retrofitServiceFactory == null) {
      RetrofitContext context = beanFactory.getBean(RetrofitContext.class);
      retrofitServiceFactory = new RetrofitServiceFactory(context);
    }

    return retrofitServiceFactory;
  }
}
