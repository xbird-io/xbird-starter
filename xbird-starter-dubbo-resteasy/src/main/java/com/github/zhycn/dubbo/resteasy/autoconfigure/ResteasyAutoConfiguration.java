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
package com.github.zhycn.dubbo.resteasy.autoconfigure;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jboss.resteasy.core.AsynchronousDispatcher;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.core.ResourceMethodRegistry;
import org.jboss.resteasy.core.SynchronousDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ListenerBootstrap;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.spi.Registry;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.zhycn.dubbo.resteasy.boot.ResteasyApplicationBuilder;
import com.github.zhycn.dubbo.resteasy.boot.ResteasyEmbeddedServletInitializer;

/**
 * This is the main class that configures this Resteasy Sring Boot starter
 *
 * @author Fabio Carvalho (facarvalho@paypal.com or fabiocarvalho777@gmail.com)
 */
@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties
public class ResteasyAutoConfiguration {

  private static Logger logger = LoggerFactory.getLogger(ResteasyAutoConfiguration.class);

  @Bean
  @Qualifier("ResteasyProviderFactory")
  public static BeanFactoryPostProcessor springBeanProcessor() {
    ResteasyProviderFactory resteasyProviderFactory = new ResteasyProviderFactory();
    ResourceMethodRegistry resourceMethodRegistry =
        new ResourceMethodRegistry(resteasyProviderFactory);

    SpringBeanProcessor springBeanProcessor = new SpringBeanProcessor();
    springBeanProcessor.setProviderFactory(resteasyProviderFactory);
    springBeanProcessor.setRegistry(resourceMethodRegistry);

    logger.debug("SpringBeanProcessor has been created");

    return springBeanProcessor;
  }

  /**
   * This is a modified version of {@link ResteasyBootstrap}
   *
   * @return a ServletContextListener object that configures and start a ResteasyDeployment
   */
  @Bean
  public ServletContextListener resteasyBootstrapListener(
      @Qualifier("ResteasyProviderFactory") final BeanFactoryPostProcessor beanFactoryPostProcessor) {
    ServletContextListener servletContextListener = new ServletContextListener() {

      private SpringBeanProcessor springBeanProcessor =
          (SpringBeanProcessor) beanFactoryPostProcessor;

      protected ResteasyDeployment deployment;

      public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ListenerBootstrap config = new ListenerBootstrap(servletContext);

        ResteasyProviderFactory resteasyProviderFactory = springBeanProcessor.getProviderFactory();
        ResourceMethodRegistry resourceMethodRegistry =
            (ResourceMethodRegistry) springBeanProcessor.getRegistry();

        deployment = config.createDeployment();

        deployment.setProviderFactory(resteasyProviderFactory);
        deployment.setRegistry(resourceMethodRegistry);

        if (deployment.isAsyncJobServiceEnabled()) {
          AsynchronousDispatcher dispatcher =
              new AsynchronousDispatcher(resteasyProviderFactory, resourceMethodRegistry);
          deployment.setDispatcher(dispatcher);
        } else {
          SynchronousDispatcher dispatcher =
              new SynchronousDispatcher(resteasyProviderFactory, resourceMethodRegistry);
          deployment.setDispatcher(dispatcher);
        }

        deployment.start();

        servletContext.setAttribute(ResteasyProviderFactory.class.getName(),
            deployment.getProviderFactory());
        servletContext.setAttribute(Dispatcher.class.getName(), deployment.getDispatcher());
        servletContext.setAttribute(Registry.class.getName(), deployment.getRegistry());
      }

      public void contextDestroyed(ServletContextEvent sce) {
        if (deployment != null) {
          deployment.stop();
        }
      }
    };

    logger.debug("ServletContextListener has been created");

    return servletContextListener;
  }

  @Bean(name = ResteasyApplicationBuilder.BEAN_NAME)
  public ResteasyApplicationBuilder resteasyApplicationBuilder() {
    return new ResteasyApplicationBuilder();
  }

  @Bean
  public static ResteasyEmbeddedServletInitializer resteasyEmbeddedServletInitializer() {
    return new ResteasyEmbeddedServletInitializer();
  }

}
