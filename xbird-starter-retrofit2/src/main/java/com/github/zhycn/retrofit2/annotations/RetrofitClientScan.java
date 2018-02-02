package com.github.zhycn.retrofit2.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.github.zhycn.retrofit2.boot.RetrofitServiceFactoryBeanRegistrar;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RetrofitServiceFactoryBeanRegistrar.class)
public @interface RetrofitClientScan {

  String[] value() default {};

  Class<?>[] basePackageClasses() default {};
}
