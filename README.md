# xbird-starter

Xbird Starter 是一个基于 [Spring IO Platform](http://platform.spring.io/platform/) 打造的、开源的企业级版本管理平台。以 [Spring Boot](https://projects.spring.io/spring-boot/) 和 [Spring Cloud](http://projects.spring.io/spring-cloud/) 为载体，集成国产优质开源项目，致力于快速构建企业级应用程序。

> Xbird Starter 继承 Spring IO Platform 的全部特征，完全兼容 Spring Boot 和 Spring Cloud 服务生态。鉴于 Spring Framework 5 已发布，且最低版本要求为 JDK 8。为适应未来的开发技术，Xbird Starter 以 Java 8 为基础开发环境。

## 快速开始

我们推荐你在项目中使用 Xbird Starter 作为统一的版本依赖管理。它提供了 Spring 所有项目的依赖版本以及常用的第三方依赖包，这使得项目开发者无需花费时间去关心依赖包的版本和兼容性问题。你也可以使用 [Spring IO Platform](http://platform.spring.io/platform/) 项目作为统一的版本依赖管理，所有的依赖包都经过兼容性测试，可放心使用。

1）在你的 Spring Boot 项目中作为父项目依赖：

```
<parent>
    <groupId>com.github.zhycn</groupId>
    <artifactId>xbird-starter</artifactId>
    <version>Better.SR1</version>
    <relativePath />
</parent>
```

2）非 Spring Boot 项目可以添加依赖管理：

```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.github.zhycn</groupId>
            <artifactId>xbird-starter</artifactId>
            <version>Better.SR1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

添加好依赖配置后，在项目中使用依赖包而不需要添加版本号：

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
    <commons-lang3.version>3.7</commons-lang3.version>
</properties>
```

## 附录：依赖版本

以下是 Xbird Starter 已集成的依赖包及版本信息：

> 得益于 Spring IO Platform 项目提供的依赖版本管理，请查看：[Spring IO Platform - Dependency versions](https://docs.spring.io/platform/docs/Cairo-SR3/reference/htmlsingle/#appendix-dependency-versions)

- [Spring IO Platform - Cairo-SR3](http://platform.spring.io/platform/)
- [Spring Boot - 2.0.4.RELEASE](https://projects.spring.io/spring-boot/)
- [Spring Cloud - Finchley.SR1](http://projects.spring.io/spring-cloud/)
- [Alibaba Fastjson - 1.2.47](https://github.com/alibaba/fastjson)
- [Alibaba Druid - 1.1.10](https://github.com/alibaba/druid)
- [Alibaba Dubbo - 2.0.0](https://github.com/alibaba/dubbo-spring-boot-starter)
- [Apache POI - 3.17](https://poi.apache.org/)
- [Google Guava - 25.1-jre](https://github.com/google/guava)
- [Google Gson - 2.8.5](https://github.com/google/gson)
- [Hutool - 4.1.2](http://www.hutool.cn/)
- [JBoss RESTEasy - 3.5.0.CR4](https://resteasy.github.io/)
- [Retrofit - 2.3.0](http://square.github.io/retrofit/)
- [Spring Boot Admin - 2.0.1](https://github.com/codecentric/spring-boot-admin)
- [Springfox Swagger2 - 2.8.0](https://springfox.github.io/springfox/)