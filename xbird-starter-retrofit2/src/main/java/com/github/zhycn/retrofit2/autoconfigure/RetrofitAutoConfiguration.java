package com.github.zhycn.retrofit2.autoconfigure;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static java.util.concurrent.TimeUnit.MINUTES;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zhycn.retrofit2.context.DefaultRetrofitContext;
import com.github.zhycn.retrofit2.context.RetrofitContext;

import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConditionalOnClass(Retrofit.class)
@EnableConfigurationProperties(RetrofitProperties.class)
public class RetrofitAutoConfiguration {

  Logger LOGGER = LoggerFactory.getLogger(RetrofitAutoConfiguration.class);

  private final List<Converter.Factory> converterFactories;

  private final OkHttpClient okHttpClient;

  private final RetrofitProperties retrofitProperties;

  @Autowired
  public RetrofitAutoConfiguration(List<Converter.Factory> converterFactories,
      OkHttpClient okHttpClient, RetrofitProperties retrofitProperties) {
    this.converterFactories = converterFactories;
    this.okHttpClient = okHttpClient;
    this.retrofitProperties = retrofitProperties;
    checkConfiguredUrl(this.retrofitProperties);
  }

  @Configuration
  @ConditionalOnClass(OkHttpClient.class)
  public static class OkHttpClientConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ConnectionPool connectionPool(RetrofitProperties properties) {
      return new ConnectionPool(properties.getMaxIdleConnections(),
        properties.getKeepAliveDuration(), MINUTES);
    }

    @Bean
    @ConditionalOnMissingBean
    public OkHttpClient okHttpClient(RetrofitProperties properties, ConnectionPool connectionPool,
        List<Interceptor> interceptors) {

      OkHttpClient.Builder builder =
          new OkHttpClient.Builder().readTimeout(properties.getReadTimeout(), TimeUnit.MILLISECONDS)
              .writeTimeout(properties.getWriteTimeout(), TimeUnit.MILLISECONDS)
              .connectTimeout(properties.getConnectTimeout(), TimeUnit.MILLISECONDS)
              .connectionPool(connectionPool);

      for (Interceptor interceptor : interceptors) {
        builder.addInterceptor(interceptor);
      }

      return builder.build();
    }
  }

  @Configuration
  @ConditionalOnClass(JacksonConverterFactory.class)
  public static class JacksonConverterFactoryConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper mapper() {
      ObjectMapper mapper = new ObjectMapper();
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      mapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
      return mapper;
    }

    @Bean
    @ConditionalOnMissingBean
    public JacksonConverterFactory jacksonConverterFactory(ObjectMapper mapper) {
      mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
      return JacksonConverterFactory.create(mapper);
    }
  }

  @Bean
  @ConditionalOnMissingBean
  public RetrofitContext retrofitContext() {
    Builder builder = new Builder().validateEagerly(true);
    converterFactories.forEach(builder::addConverterFactory);
    if (okHttpClient != null) {
      builder.client(okHttpClient);
    }
    RetrofitContext context = new DefaultRetrofitContext();
    Map<String, String> endpoints = retrofitProperties.getEndpoints();
    endpoints.keySet().forEach(key -> {
      context.register(key, builder.baseUrl(endpoints.get(key)).build());
    });
    return context;
  }

  private void checkConfiguredUrl(RetrofitProperties properties) {
    Map<String, String> endpoints = properties.getEndpoints();
    endpoints.keySet().forEach(key -> {
      String url = endpoints.get(key);
      Assert.isTrue(ResourceUtils.isUrl(url), url + " is not a valid url");
      if (!url.endsWith("/")) {
        LOGGER.warn(
            "The [{}] didn't end with \"/\". This means a relative base url, end with / would be better.",
            url);
      }
    });
  }
}
