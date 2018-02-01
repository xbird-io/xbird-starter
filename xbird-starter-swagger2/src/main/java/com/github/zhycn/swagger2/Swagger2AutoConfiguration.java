package com.github.zhycn.swagger2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2AutoConfiguration
 * 
 * @author zhycn
 * @since 1.0.0 2018-01-30
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "xbird.swagger2.enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(Swagger2Properties.class)
public class Swagger2AutoConfiguration {

  private static final Logger LOGGER = LoggerFactory.getLogger(Swagger2AutoConfiguration.class);

  @Autowired
  private Swagger2Properties properties;

  @Bean
  @ConditionalOnMissingBean
  public Docket defaultDocketApi() {
    LOGGER.info("Init Swagger2.");
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(properties.build()).select()
        .apis(StringUtils.isEmpty(properties.getBasePackage())
            ? RequestHandlerSelectors.any()
            : RequestHandlerSelectors.basePackage(properties.getBasePackage()))
        .paths(PathSelectors.any()).build();
  }

}
