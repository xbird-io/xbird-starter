# xbird-starter-dubbo-resteasy

[RESTEasy](http://resteasy.jboss.org/) 是 Jboss 开源的比较成熟的 REST 框架，是 JAX-RS 规范的一种实现。本项目通过集成 RESTEasy 框架实现 Dubbo 的 RESTful 风格的远程调用。

### 使用须知

1. 本项目基于 Spring Boot 2 版本构建，由于在 Spring Boot 2 中 autoconfigure 包的包名发生变更，与 Spring Boot 1.x 不兼容。如果使用低版本的Spring Boot，可参考：[paypal/resteasy-spring-boot](https://github.com/paypal/resteasy-spring-boot)

2. 由于在测试的过程中，发现官方提供的最新稳定版 `3.5.0.Final` 中，`resteasy-client` 模块的Maven打包存在问题，故采用了 RESTEasy 3.5.0.RC4 版本。（后续会持续跟进这个问题）

3. JAX-RS 与 Spring MVC 的不可共用性，决定了在使用本项目时，意味着项目中 Spring MVC 相关的Web请求将会生效。

4. 本项目适用于服务提供者，作为服务消费都可集成`xbird-starter-dubbo`依赖，无需集成 RESTEasy 框架。

5. 本项目是 `xbird-starter-dubbo` 模块的增强，如果不需要对外提供 RESTful 服务，则无需使用本项目。

## 快速开始

> 请先阅读使用须知。

第一步，你应该按照 [xbird-starter-dubbo](../xbird-starter-dubbo/) 模块的文档完成配置。

第二步，作为服务生产者，在你的 Spring Boot 2.x 项目中添加依赖配置：

```
<dependency>
  <groupId>com.github.zhycn</groupId>
  <artifactId>xbird-starter-dubbo</artifactId>
</dependency>
```

修改为：

```
<dependency>
  <groupId>com.github.zhycn</groupId>
  <artifactId>xbird-starter-dubbo-resteasy</artifactId>
</dependency>
```

第三步，你可以在属性配置文件中指定协议、端口和运行容器（可选）：

```
# 协议名称，默认值：dubbo
spring.dubbo.protocol.name=rest
# 协议地址，默认值：28080
spring.dubbo.protocol.port=28081
# 运行容器名称，支持tomcat,jetty等。
spring.dubbo.protocol.server=tomcat
```

第四步，在服务生产者项目中指定访问根路径（可选）：

```
@Component
@ApplicationPath("services")
public class DubboApplicationPath extends Application {

}
```

第五步，在你的接口定义（api）项目中添加依赖配置：

```
<dependency>
	<groupId>javax.ws.rs</groupId>
	<artifactId>javax.ws.rs-api</artifactId>
	<version>2.1</version>
</dependency>
```

依赖包提供了REST常用注解，使用请参考：http://blog.csdn.net/github_35758702/article/details/52614282

第六步，在接口上添加REST注解描述：

```
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BookService {

  @GET
  @Path("books")
  List<Map<String, Object>> getBooks();
}
```

> REST 注解也可以在实现类中设置，虽然可以正常调用，但在调用时会打印错误日志。所以建议 REST 注解统一定义在接口类的方法上，方便管理和维护。

第七步，启动服务生产者，即可通过HTTP访问服务：

```
http://localhost:9001/services/books
```

> 作为服务消费者，如果不对外提供服务的话，只需按照 `xbird-starter-dubbo` 模块文档配置即可，不需要做任何修改。