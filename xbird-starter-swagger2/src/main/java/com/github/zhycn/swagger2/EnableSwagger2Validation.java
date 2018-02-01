package com.github.zhycn.swagger2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

/**
 * 该注解支持JSR-303的部分验证注解
 * 
 * @author zhycn
 * @since 1.0.0 2018-01-30
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({BeanValidatorPluginsConfiguration.class})
public @interface EnableSwagger2Validation {

}
