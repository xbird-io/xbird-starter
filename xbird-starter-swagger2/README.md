# xbird-starter-swagger2

[Swagger](https://swagger.io/) 号称世界上最流行的API工具，是一款让你更好地管理和书写API文档的框架。其官方定义是：

> Swagger is the world’s largest framework of API developer tools for the OpenAPI Specification(OAS), enabling development across the entire API lifecycle, from design and documentation, to test and deployment.

随着互联网技术的发展，越来越多的网站在使用前后端分离的架构。API成为前端和后端交互的必然方式，为了促进前端和后端开发人员的协作，管理和书写API文档变得越来越重要。在这样的应用背景下，Swagger 提供了一套完整的API文档解决方案，用于生成、描述、调用和可视化的管理界面。其目标是：

_为REST APIs定义一个标准的、语言无关的接口，使人和计算机在看不到源码或者看不到文档或者不通过网络流量检测的情况下就能发现和理解各种服务的功能。通过使用 Swagger 的定义，消费者就能通过少量的实现逻辑与远程的服务进行交互，去掉了调用服务时的很多猜测，让协作变得更加简单高效。_

## 快速开始

本项目是基于 [SpringFox](https://springfox.github.io/springfox/) 的集成，在你的 Spring Boot 项目中添加依赖配置：

```
<dependency>
  <groupId>com.github.zhycn</groupId>
  <artifactId>xbird-starter-swagger2</artifactId>
</dependency>
```

然后，在应用程序的属性文件中添加相关配置：

> 我们建议你在开发和测试环境中启用，若用于生产环境，请配置相关的安全策略。

| 属性 | 描述 | 默认值 |
|:---|:---|:---|
| xbird.swagger2.enabled | （可选）是否启用。为了安全起见，默认不开启。 | false |
| xbird.swagger2.title | （可选）标题 | API Documentation |
| xbird.swagger2.description | （可选）描述 | The Best APIs are Built with Swagger Tools |
| xbird.swagger2.terms-of-service-url | （可选）服务条款 |  |
| xbird.swagger2.license | （可选）监听协议 |  |
| xbird.swagger2.license-url | （可选）监听协议网址 |  |
| xbird.swagger2.version | （可选）接口版本 | 1.0 |
| xbird.swagger2.contact.name | （可选）联系人 |  |
| xbird.swagger2.contact.url | （可选）联系网址 |  |
| xbird.swagger2.contact.email | （可选）联系邮箱 |  |
