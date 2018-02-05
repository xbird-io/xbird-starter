# xbird-starter-retrofit2

Retrofit 是一个 RESTful 的 HTTP 网络请求框架。其官方定义是：

```
A type-safe HTTP client for Android and Java
```

- 官方文档：http://square.github.io/retrofit/
- 简明教程：http://blog.csdn.net/carson_ho/article/details/73732076

## 快速开始

在你的 Spring Boot 项目中添加依赖配置：

```
<dependency>
  <groupId>com.github.zhycn</groupId>
  <artifactId>xbird-starter-retrofit2</artifactId>
</dependency>
```

配置 `@RetrofitClientScan`注解，用于扫描 Retrofit 服务接口包：

```
@SpringBootApplication
@RetrofitClientScan({"com.github.xxx"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
```

然后，在应用程序的属性文件中添加相关配置：

| 属性 | 描述 | 默认值 |
|:---|:---|:---:|
| xbird.retrofit2.connect-timeout | 连接超时时间，默认值：5秒 | 5000 |
| xbird.retrofit2.read-timeout | 读取超时时间，默认值：10秒 | 10000 |
| xbird.retrofit2.write-timeout | 写入超时时间，默认值：10秒 | 10000 |
| xbird.retrofit2.keep-alive | 连接池活跃时间，默认值：300秒 | 300 |
| xbird.retrofit2.max-idle | 连接池闲置连接数，默认值：5个 | 5 |
| xbird.retrofit2.endpoints.* | 设置服务节点 |  |

## 使用向导

第一步：定义服务接口和节点名称

```
@RetrofitClient("github")
public interface GitHubService {
  @GET("users/{user}/repos")
  Call<List<Repo>> listRepos(@Path("user") String user);
}
```

第二步：在属性文件中添加配置参数

```
# github: @RetrofitClient 注解定义的节点名称
# https://api.github.com/: BaseURL，须以 `/` 结尾
xbird.retrofit2.endpoints.github=https://api.github.com/
```

第三步：调用服务

```
@Autowired
private GitHubService service;

# 调用
Call<List<Repo>> repos = service.listRepos("octocat");
```