# xbird-starter-dubbo

Dubbo是一个开源的分布式服务治理框架，现已成功Apache基金会的一个孵化项目。

- 官网：http://dubbo.apache.org/
- 用户手册：http://dubbo.apache.org/books/dubbo-user-book/
- Zookeeper 配置中心：http://dubbo.apache.org/books/dubbo-user-book/references/registry/zookeeper.html
- dubbo-spring-boot-starter：https://github.com/alibaba/dubbo-spring-boot-starter
- 简单示例项目：https://github.com/zhycn/dubbo-samples

本项目以实际生产应用出发，使用 Zookeeper 作为注册中心，已添加 Zookeeper 客户端依赖，你只须配置 Zookeeper 服务地址即可。

## 发布服务

作为服务生产者，首先在你的 Spring Boot 项目中添加依赖配置：

```
<dependency>
  <groupId>com.github.zhycn</groupId>
  <artifactId>xbird-starter-dubbo</artifactId>
</dependency>
```

然后，在项目的属性文件中添加必要的配置参数：

```
# 应用名称，等价于：spring.application.name
spring.dubbo.application.name=dubbo-samples-provider
# 应用组织名称
spring.dubbo.application.organization=github
# 应用所有者
spring.dubbo.application.owner=zhycn

# 注册中心地址（必须）
spring.dubbo.registry.address=10.7.112.125:2181,10.7.112.126:2181,10.7.112.127:2181
# 注册协议（必须）
spring.dubbo.registry.protocol=zookeeper
# 客户端
spring.dubbo.registry.client=zkclient

# 协议名称，默认值：dubbo
spring.dubbo.protocol.name=dubbo
# 协议地址，默认值：20880
spring.dubbo.protocol.port=20880
```

接下来，在 Spring Boot Application上添加注解`@EnableDubboConfiguration`, 表示开启服务。

```
@SpringBootApplication
@EnableDubboConfiguration
public class DubboProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(DubboProviderApplication.class, args);
  }
}
```

编写你的Dubbo服务，在要发布的服务实现上添加 `@Service`注解（import com.alibaba.dubbo.config.annotation.Service），其中interfaceClass是要发布服务的接口。

```
@Component
@Service(interfaceClass = BookService.class)
public class BookServiceImpl implements BookService {

}
```

启动Spring Boot应用，在控制台可以看到dubbo启动及注册的相关信息。

## 消费服务

作为服务消费者，首先在你的 Spring Boot 项目中添加依赖配置：

```
<dependency>
  <groupId>com.github.zhycn</groupId>
  <artifactId>xbird-starter-dubbo</artifactId>
</dependency>
```

然后，在项目的属性文件中添加必要的配置参数：

```
# 应用名称，等价于：spring.application.name
spring.dubbo.application.name=dubbo-samples-consumer
# 应用组织名称
spring.dubbo.application.organization=github
# 应用所有者
spring.dubbo.application.owner=zhycn

# 注册中心地址（必须）
spring.dubbo.registry.address=10.7.112.125:2181,10.7.112.126:2181,10.7.112.127:2181
# 注册协议（必须）
spring.dubbo.registry.protocol=zookeeper
# 客户端
spring.dubbo.registry.client=zkclient
```

服务消费者与生产者的区别：

> 1. 服务消费者无需关心生产者的协议名称及端口，只要配置相同的注册地址即可。
> 2. 如果服务消费者既是生产者，按照服务生产者配置即可。

接下来，在 Spring Boot Application上添加注解`@EnableDubboConfiguration`, 表示开启服务。

```
@SpringBootApplication
@EnableDubboConfiguration
public class DubboConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(DubboConsumerApplication.class, args);
  }
}
```

通过@Reference注入需要使用的interface：

```
@Component
public class BookConsumer {

  @Reference
  private BookService bookService;
 
}
```

启动Spring Boot应用，调用相关服务就可以获取到接口返回信息。

> 

## 建议

1. 如果你对Dubbo不够熟悉，请阅读[Dubbo用户手册](http://dubbo.apache.org/books/dubbo-user-book/references/registry/zookeeper.html)，了解Dubbo的原理、协议和配置等相关信息。
2. 你也可以下载 [dubbo-samples](https://github.com/zhycn/dubbo-samples) 演示项目，感受一下。

## 更多配置参考

Dubbo的配置非常灵活，大多数配置参数都是可选的，这里我们介绍一些主要的配置参数。如果你感兴趣的话，最好花点时间看看Dubbo的源码，你会受益良多。

同时，我们建议你更多的关注Dubbo提供的 `@Service` 和 `@Reference` 注解，大多数配置都可以通过注解来完成。

```
## ApplicationConfig（可选）
## 应用配置：用于配置当前应用信息，不管该应用是提供者还是消费者。
# 应用名称（可选，推荐设置）
spring.dubbo.application.name=dubbo-samples-provider
# 应用组织/公司名（可选）
spring.dubbo.application.organization=github
# 应用所有者/部门（可选）
spring.dubbo.application.owner=zhycn
# 应用版本（可选，如果提供者指定版本，则消费者也必须指定版本）
spring.dubbo.application.version=1.0.0
# 应用环境（可选，如果提供者指定环境，则消费者也必须指定环境）
spring.dubbo.application.environment=dev

## RegistryConfig （必须）
## 注册中心配置：用于配置连接注册中心相关信息。
# 注册中心地址（必填，多个地址用逗号`,`隔开）
spring.dubbo.registry.address=10.7.112.125:2181
# 指定服务注册/消费的组名（可选）
spring.dubbo.registry.group=TZ
# 注册中心协议（可选，默认值：zookeeper）
spring.dubbo.registry.protocol=zookeeper
# 注册中心请求超时时间（可选，单位：毫秒）
spring.dubbo.registry.timeout=5000
# 注册中心会话超时时间（可选，单位：毫秒）
spring.dubbo.registry.session=
# 指定服务的版本号（可选，版本号可在多个地方指定，不建议重复设置）
spring.dubbo.registry.version=
# 注册中心登录用户名（可选）
spring.dubbo.registry.username=
# 注册中心登录密码（可选）
spring.dubbo.registry.password=

## ProtocolConfig （可选）
## 协议配置：用于配置提供服务的协议信息，协议由提供方指定，消费方被动接收。
## Dubbo支持多种协议：不同协议都给出了默认配置方案。
# 指定协议名称（可选，默认值：dubbo）
spring.dubbo.protocol.name=dubbo
# 指定端口号（可选，默认值：20880）
spring.dubbo.protocol.port=20880
# 最大请求数据长度（可选，默认值：8M）
spring.dubbo.protocol.payload=

## MonitorConfig （可选）
## 监控中心配置：用于配置连接监控中心相关信息。
## 配置要求：项目中部署了dubbo-admin服务
# 监控中心地址
spring.dubbo.monitor.address=
# 服务分组（可选）
spring.dubbo.monitor.group=
# 监控中心登录用户名
spring.dubbo.monitor.username=
# 监控中心登录密码
spring.dubbo.monitor.password=
# 协议（可选）
spring.dubbo.monitor.protocol=
# 版本号设置（可选）
spring.dubbo.monitor.version=

## ProviderConfig （可选）
## 服务提供者缺省配置：当ProtocolConfig和ServiceConfig某属性没有配置时，采用此缺省值。

## MethodConfig （可选）
## 方法配置：用于ServiceConfig和ReferenceConfig指定方法级的配置信息。

## ConsumerConfig （可选）
## 服务消费者缺省配置：当ReferenceConfig某属性没有配置时，采用此缺省值。
# 检查服务提供者是否存在（默认值：false）spring.dubbo.consumer.check=false
# 是否加载时即刻初始化（可选）
spring.dubbo.consumer.init=
# 是否延迟创建连接（可选）
spring.dubbo.consumer.lazy=
# 指定调用版本号（可选）
spring.dubbo.consumer.version=
# 指定调用服务分组（可选）
spring.dubbo.consumer.group=
# 连接数限制，0表示共享连接，否则为该服务独享连接数（可选）
spring.dubbo.consumer.connections=
# 远程调用超时时间（可选，单位：毫秒）
spring.dubbo.consumer.timeout=
# 重试次数（可选）
spring.dubbo.consumer.retries=
# 最大并发调用（可选）
spring.dubbo.consumer.actives=
# 配置负载均衡策略（可选）
spring.dubbo.consumer.loadbalance=
# 是否异步调用（可选）
spring.dubbo.consumer.async=
# 异步发送是否等待发送成功（可选）
spring.dubbo.consumer.sent=
```

## Dubbo + RestEasy 构建 RESTful 风格的远程调用

RestEasy 是 JBoss 开源的比较成熟的 REST 框架，是 JAX-RS 规范的一种实现。为了支持基于 Dubbo 构建的分布式系统服务对外提供 RESTful 风格的远程调用，我们通过集成 RESTEasy 框架来解决这个问题。

### 开始集成

首先完成 Dubbo 的配置，然后在服务提供者项目中集成 `xbird-starter-resteasy`：

```
<dependency>
  <groupId>com.github.zhycn</groupId>
  <artifactId>xbird-starter-resteasy</artifactId>
</dependency>
```

配置请查看：[xbird-starter-resteasy](../xbird-starter-resteasy/) 项目。

接下来，你可以在属性配置文件中指定协议、端口和运行容器（可选）：

```
# 协议名称，默认值：dubbo
spring.dubbo.protocol.name=rest
# 协议地址，默认值：20880
spring.dubbo.protocol.port=20881
# 运行容器名称，支持tomcat,jetty等。
spring.dubbo.protocol.server=tomcat
```

运行服务，可通过HTTP请求服务。