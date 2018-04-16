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
package com.github.zhycn.resteasy.boot;

import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletContainerInitializer;

import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters;
import org.jboss.resteasy.plugins.servlet.ResteasyServletInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

/**
 * This class is the Spring Boot equivalent of {@link ResteasyServletInitializer}, which implements
 * the Servlet API {@link ServletContainerInitializer} interface to find all JAX-RS Application,
 * Provider and Path classes in the classpath.
 *
 * As we all know, in Spring Boot we use an embedded servlet container. However, the Servlet spec
 * does not support embedded containers, and many portions of it do not apply to embedded
 * containers, and ServletContainerInitializer is one of them.
 *
 * This class fills in this gap.
 *
 * Notice that the JAX-RS Application classes are found in this RESTEasy starter by class
 * ResteasyEmbeddedServletInitializer, and that is done by scanning the classpath.
 *
 * The Path and Provider annotated classes are found by using Spring framework (instead of scanning
 * the classpath), since it is assumed those classes are ALWAYS necessarily Spring beans (this
 * starter is meant for Spring Boot applications that use RESTEasy as the JAX-RS implementation)
 *
 * @author Fabio Carvalho (facarvalho@paypal.com or fabiocarvalho777@gmail.com)
 */
public class ResteasyApplicationBuilder {

  public static final String BEAN_NAME = "JaxrsApplicationServletBuilder";
  private static Logger logger = LoggerFactory.getLogger(ResteasyApplicationBuilder.class);

  public ServletRegistrationBean<Servlet> build(String applicationClassName, String path,
      Set<Class<?>> resources, Set<Class<?>> providers) {
    Servlet servlet = new HttpServlet30Dispatcher();

    ServletRegistrationBean<Servlet> servletRegistrationBean =
        new ServletRegistrationBean<>(servlet);

    servletRegistrationBean.setName(applicationClassName);
    servletRegistrationBean.setLoadOnStartup(1);
    servletRegistrationBean.setAsyncSupported(true);
    servletRegistrationBean.addInitParameter("javax.ws.rs.Application", applicationClassName);

    if (path != null) {
      String mapping = path;
      if (!mapping.startsWith("/")) mapping = "/" + mapping;
      String prefix = mapping;
      if (!"/".equals(prefix) && prefix.endsWith("/"))
        prefix = prefix.substring(0, prefix.length() - 1);
      if (mapping.endsWith("/"))
        mapping += "*";
      else
        mapping += "/*";
      // resteasy.servlet.mapping.prefix
      servletRegistrationBean.addInitParameter("resteasy.servlet.mapping.prefix", prefix);
      servletRegistrationBean.addUrlMappings(mapping);
    }

    if (resources.size() > 0) {
      StringBuilder builder = new StringBuilder();
      boolean first = true;
      for (Class<?> resource : resources) {
        if (first) {
          first = false;
        } else {
          builder.append(",");
        }

        builder.append(resource.getName());
      }
      servletRegistrationBean.addInitParameter(ResteasyContextParameters.RESTEASY_SCANNED_RESOURCES,
          builder.toString());
    }

    if (providers.size() > 0) {
      StringBuilder builder = new StringBuilder();
      boolean first = true;
      for (Class<?> provider : providers) {
        if (first) {
          first = false;
        } else {
          builder.append(",");
        }
        builder.append(provider.getName());
      }
      servletRegistrationBean.addInitParameter(ResteasyContextParameters.RESTEASY_SCANNED_PROVIDERS,
          builder.toString());
    }

    logger.debug(
        "ServletRegistrationBean has just bean created for JAX-RS class " + applicationClassName);

    return servletRegistrationBean;
  }

}