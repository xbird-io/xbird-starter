# xbird-starter

A cohesive, versioned platform for enterprise applications with Spring Boot.

**Compatible**

- [Spring IO platform - Brussels-SR6](http://platform.spring.io/platform/)
- [Spring Boot - 1.5.9.RELEASE](https://projects.spring.io/spring-boot/)
- [Spring Cloud - Edgware.SR1](http://projects.spring.io/spring-cloud/)

## Quick Start

The recommended way to get started using `xbird-starter` in your project is with a dependency management system – the snippet below can be copied and pasted into your build: 

```
<parent>
  <groupId>com.github.zhycn</groupId>
  <artifactId>xbird-starter</artifactId>
  <version>1.0.0.RELEASE</version>
  <relativePath />
</parent>
```

Or: 

```
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>com.github.zhycn</groupId>
      <artifactId>xbird-starter</artifactId>
      <version>1.0.0.RELEASE</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

### Using the Platform

The Spring IO platform provides versions of the various Spring projects and their dependencies. With the configuration shown above added to your build script, you're ready to declare your dependencies without having to worry about version numbers:

### Maven

```
<dependency>
  <groupId>org.apache.commons</groupId>
  <artifactId>commons-lang3</artifactId>
</dependency>
```

## Dependency versions

| Group | Artifact | Version | Website |
|:---|:---|:---|:---|
| io.spring.platform | platform-bom | Brussels-SR6 | [Official](http://platform.spring.io/platform/) | 
| org.springframework.boot | spring-boot-starter-parent | 1.5.9.RELEASE | [Official](https://projects.spring.io/spring-boot/) | 
| org.springframework.cloud | spring-cloud-dependencies | Edgware.SR1 |[Official](http://projects.spring.io/spring-cloud/) | 
| org.apache.commons | commons-lang3 | 3.7 | [Official](http://commons.apache.org/proper/commons-lang/) | 
| com.alibaba | fastjson | 1.2.44 | [GitHub](https://github.com/alibaba/fastjson) | 
| com.alibaba | druid-spring-boot-starter | 1.1.6 | [GitHub](https://github.com/alibaba/druid) | 
