package com.github.zhycn.retrofit2.boot;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.github.zhycn.retrofit2.annotations.RetrofitClient;

public class RetrofitServiceComponentProvider extends ClassPathScanningCandidateComponentProvider {

  private RetrofitServiceComponentProvider() {
    super(false);
    addIncludeFilter(new AnnotationTypeFilter(RetrofitClient.class, true, true));
  }

  @Override
  protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
    return beanDefinition.getMetadata().isInterface();
  }

  static RetrofitServiceComponentProvider getInstance() {
    return new RetrofitServiceComponentProvider();
  }

}
