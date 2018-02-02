package com.github.zhycn.retrofit2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 服务标记
 * 
 * @author zhycn
 * @since 1.0.0 2018-02-02
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface RetrofitClient {

  String name() default "";

  String value() default "default";

  String retrofit() default "";

}
