package com.github.zhycn.id.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.zhycn.id.factory.TwitterSnowflakeFactory;
import com.github.zhycn.id.service.SnowflakeID;

@Configuration
@ConditionalOnProperty(name = "xbird.id.snowflake.enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(SnowflakeProperties.class)
public class SnowflakeConfiguration {

  @Autowired
  private SnowflakeProperties leafSnowflakeProperties;

  @Bean
  @ConditionalOnMissingBean
  public SnowflakeID createLeafSnowflakeFactory() {
    int workerId = leafSnowflakeProperties.getWorkerId();
    int dataCenterId = leafSnowflakeProperties.getDataCenterId();
    return new TwitterSnowflakeFactory(workerId, dataCenterId);
  }

}
