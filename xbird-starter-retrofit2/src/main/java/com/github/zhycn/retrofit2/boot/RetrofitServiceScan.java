package com.github.zhycn.retrofit2.boot;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RetrofitServiceFactoryBeanRegistrar.class)
public @interface RetrofitServiceScan {

  String[] value() default {};

  String[] basePackages() default {};

  Class<?>[] basePackageClasses() default {};
}
