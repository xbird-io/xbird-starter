# xbird-starter-resteasy

[RESTEasy](http://resteasy.jboss.org/) 是 Jboss 开源的比较成熟的 REST 框架，是 JAX-RS 规范的一种实现。本项目基于 Spring Boot 2.x 版本构建，旨在简化 RESTEasy 与 Spring Boot 2.x 的快速集成。

> 由于在 Spring Boot 2.x 中 autoconfigure 模块的包名发生变更，与 Spring Boot 1.x 不兼容。如果使用低版本的Spring Boot，可参考：[paypal/resteasy-spring-boot](https://github.com/paypal/resteasy-spring-boot) 项目。

特别说明：由于在测试的过程中，发现官方提供的最新稳定版 `3.5.0.Final` 中，`resteasy-client` 模块的Maven打包存在问题，故采用了 RESTEasy 3.5.0.RC4 版本（后续会持续跟进这个问题）。

## 快速开始

在你的 Spring Boot 2.x 项目中添加依赖配置：

```
<dependency>
  <groupId>com.github.zhycn</groupId>
  <artifactId>xbird-starter-resteasy</artifactId>
</dependency>
```

然后，设置访问根目录（可选）：

```
@Component
@ApplicationPath("/services/")
public class JaxrsApplicationPath extends Application {
}
```

接下来，在你的接口定义项目（api）中添加依赖配置：

```
<dependency>
  <groupId>javax.ws.rs</groupId>
  <artifactId>javax.ws.rs-api</artifactId>
  <version>2.1</version>
</dependency>
```

> 该依赖包提供了REST常用注解，使用请参考：http://blog.csdn.net/github_35758702/article/details/52614282

在接口上添加REST注解描述：

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

配置完成后，启动服务。在浏览器中输入访问地址（验证）：

```
http://[HOST]:[PORT]/services/books
```

## 注意事项

1. 本项目中支持JSON和XML格式输入和输出。
2. 如果你在 Spring MVC 项目中引入本项目，则会覆盖 Spring MVC 提供的服务。