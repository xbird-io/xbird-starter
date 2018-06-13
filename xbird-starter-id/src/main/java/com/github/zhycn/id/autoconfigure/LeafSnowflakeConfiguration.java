package com.github.zhycn.id.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.zhycn.id.factory.LeafSnowflakeFactory;
import com.github.zhycn.id.service.LeafSnowflakeID;

@Configuration
@ConditionalOnProperty(name = "xbird.id.snowflake.enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(LeafSnowflakeProperties.class)
public class LeafSnowflakeConfiguration {

  @Autowired
  private LeafSnowflakeProperties leafSnowflakeProperties;

  @Bean
  @ConditionalOnMissingBean
  public LeafSnowflakeID createLeafSnowflakeFactory() {
    int workerId = leafSnowflakeProperties.getWorkerId();
    int dataCenterId = leafSnowflakeProperties.getDataCenterId();
    return new LeafSnowflakeFactory(workerId, dataCenterId);
  }

}
