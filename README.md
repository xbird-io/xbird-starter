# xbird-starter

Xbird Starter 是一个基于 [Spring IO Platform](http://platform.spring.io/platform/) 打造的、开源的企业级版本管理平台。以 [Spring Boot](https://projects.spring.io/spring-boot/) 和 [Spring Cloud](http://projects.spring.io/spring-cloud/) 为载体，集成国产优质开源项目，致力于快速构建企业级应用程序。

> Xbird Starter 继承 Spring IO Platform 的全部特征，完全兼容 Spring Boot 和 Spring Cloud 服务生态。鉴于 Spring Framework 5 已发布，且最低版本要求为 JDK 8。为适应未来的开发技术，Xbird Starter 确定以 JDK 8 为基础开发环境。

## 快速开始

我们推荐你在项目中使用 Xbird Starter 作为统一的版本依赖管理。它提供了 Spring 所有项目的依赖版本以及常用的第三方依赖包，这使得项目开发者无需花费时间去关心依赖包的版本和兼容性问题。

> 如果你对本项目心存疑虑，我们推荐你使用 [Spring IO Platform](http://platform.spring.io/platform/) 项目作为统一的版本依赖管理，所有的依赖包都做过兼容性测试，可放心使用。

1）在你的 Spring Boot 项目中作为父项目依赖：

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>your-application</artifactId>
    <version>1.0.0-SNAPSHOT</version>

    <parent>
        <groupId>com.github.zhycn</groupId>
        <artifactId>xbird-starter</artifactId>
        <version>2.0.0</version>
        <relativePath />
    </parent>

    <!-- Dependency declarations -->

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

2）非 Spring Boot 项目可以添加依赖管理：

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>your-application</artifactId>
    <version>1.0.0-SNAPSHOT</version>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.github.zhycn</groupId>
                <artifactId>xbird-starter</artifactId>
                <version>2.0.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <!-- Dependency declarations -->

</project>
```

添加好依赖配置后，在项目中新增依赖包则不需要添加版本号：

```
<dependency>
  <groupId>org.apache.commons</groupId>
  <artifactId>commons-lang3</artifactId>
</dependency>
```

## 覆盖版本

如果需要升级或降级依赖版本，可以修改你项目中 `pom.xml` 文件的属性 `<properties>` 来设置。首先，你需要查看父项目的属性名称定义，然后修改为你需要的版本：

```
<properties>
    <apache.commons-lang3.version>3.7</apache.commons-lang3.version>
</properties>
```

## 附录：依赖版本

以下是 Xbird Starter 已集成的依赖包及版本信息：

> 得益于 Spring IO Platform 项目提供的依赖版本管理，请查看：[Spring IO Platform - Dependency versions](https://docs.spring.io/platform/docs/Brussels-SR6/reference/htmlsingle/#appendix-dependency-versions)

| Group | Artifact | Version | Website |
|:---|:---|:---|:---:|
| io.spring.platform | platform-bom | Cairo-RELEASE | [Official](http://platform.spring.io/platform/) | 
| org.springframework.boot | spring-boot-starter-parent | 2.0.1.RELEASE | [Official](https://projects.spring.io/spring-boot/) | 
| org.springframework.cloud | spring-cloud-dependencies | Edgware.SR3 |[Official](http://projects.spring.io/spring-cloud/) | 
| org.apache.commons | commons-lang3 | 3.7 | [Official](http://commons.apache.org/proper/commons-lang/) | 
| com.alibaba | fastjson | 1.2.44 | [GitHub](https://github.com/alibaba/fastjson) | 
| com.alibaba | druid-spring-boot-starter | 1.1.9 | [GitHub](https://github.com/alibaba/druid) | 
| io.springfox | springfox-swagger2 | 2.8.0 | [Official](https://springfox.github.io/springfox/) | 
| io.springfox | springfox-swagger-ui | 2.8.0 | [Official](https://springfox.github.io/springfox/) | 
| io.springfox | springfox-data-rest | 2.8.0 | [Official](https://springfox.github.io/springfox/) | 
| io.springfox | springfox-bean-validators | 2.8.0 | [Official](https://springfox.github.io/springfox/) | 
| com.squareup.retrofit2 | retrofit | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | adapter-guava | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | adapter-java8 | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | adapter-rxjava | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | adapter-rxjava2 | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | converter-gson | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | converter-guava | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | converter-jackson | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | converter-java8 | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | converter-jaxb | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | converter-moshi | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | converter-protobuf | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | converter-simplexml | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| com.squareup.retrofit2 | converter-wire | 2.3.0 | [Official](http://square.github.io/retrofit/) | 
| cn.hutool | hutool-all | 4.0.9 | [Official](http://www.hutool.cn/) |
| com.alibaba.spring.boot | dubbo-spring-boot-starter | 2.0.0 | [Official](https://github.com/alibaba/dubbo-spring-boot-starter) |
| org.apache.zookeeper | zookeeper | 3.4.11 | [Official](http://zookeeper.apache.org/) |
| com.101tec | zkclient | 0.10 | [Official](https://github.com/sgroschupf/zkclient) |
| javax.ws.rs | javax.ws.rs-api | 2.1 | [Official](https://github.com/jax-rs) |
