package com.github.zhycn.swagger2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

/**
 * 该注解支持 Spring Data Rest。
 * 
 * @author zhycn
 * @since 1.0.0 2018-01-30
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SpringDataRestConfiguration.class})
public @interface EnableSwagger2SpringDataRest {

}
