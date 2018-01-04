# xbird-starter

基于Spring Boot的企业级项目依赖版本管理。

### 快速开始

> 使用须知：本项目完美兼容Spring Boot项目，并在此基础上添加了部分国内外优质项目依赖包。

在Spring Boot项目中，作为父项目依赖：

```
<parent>
  <groupId>org.xbird.starter</groupId>
  <artifactId>xbird-starter</artifactId>
  <version>1.0.0</version>
  <relativePath />
</parent>
```

或者，作为依赖管理添加到项目中：

```
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.xbird.starter</groupId>
      <artifactId>xbird-starter</artifactId>
      <version>1.0.0</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

### 依赖版本

| Group | Artifact | Version |
|:---|:---|:---|
| io.spring.platform | platform-bom | Brussels-SR6 |
| org.springframework.cloud | spring-cloud-dependencies | Dalston.SR4 |
| de.codecentric | spring-boot-admin-server | 2.7.0 |
| de.codecentric | spring-boot-admin-server-ui | 2.7.0 |
| de.codecentric | spring-boot-admin-starter-client | 2.7.0 |
| com.alibaba | fastjson | 1.2.38 |
| com.alibaba | druid | 1.1.3 |
| org.apache.commons | commons-lang3 | 3.6 |