# 企业级开发指南

在企业级应用开发中，通常会使用企业内部的私有仓库和应用模块。为了更好地助力企业集成、构建和发布应用模块，我们提供了 `xbird-starter-bom` 来帮助企业提升管理效能。

## 开发向导

企业开发者可以通过简单的几步操作来定制自己的版本管理平台。

### 1. 安装

下载 Xbird Starter 项目到本地目录：

```
git clone https://github.com/zhycn/xbird-starter.git
```

### 2. 集成

作为开发者，如果你需要集成企业内部的依赖包或者 Xbird Starter 项目未能提供的依赖包时：

1）打开 `xbird-starter/pom.xml` 文件，启用 `xbird-starter-bom` 模块：

```
<modules>
    <!-- 去掉 xbird-starter-bom 模块的注释 -->
	<module>xbird-starter-bom</module>
</modules>
```

2）开发者只需关心`xbird-starter-bom` 模块中的 `pom.xml` 文件，在这里添加所需的依赖包即可。

例如，我们提供的一个演示：

```
<properties>
	<squareup.pollexor.version>2.0.4</squareup.pollexor.version>
	<!-- 添加你的依赖包版本 -->
</properties>

<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>com.squareup</groupId>
			<artifactId>pollexor</artifactId>
			<version>${squareup.pollexor.version}</version>
		</dependency>
		<!-- 添加你的依赖包 -->
	</dependencies>
</dependencyManagement>
```
3）你可以配置模块的版本信息：

> 我们不建议你做过多的修改，除非你能熟炼地使用本项目。

```
<version>1.0.0</version>
```

### 3. 构建

集成所需之后，开始构建：

```
# 通过打包来测试项目是否成功
mvn clean package
```

在发布之前，你应当尽可能多的做一次测试，以确保无误。


### 4. 发布

首先，你需要配置本地仓库信息。以Nexus为例，这里不再赘述，不懂的请移步：[Maven私服仓库管理： Nexus 3.0](https://segmentfault.com/a/1190000005966312)

配置好之后，通过命令发布到本地仓库：

```
mvn deploy
```

## 使用向导

部署到本地仓库后，就可以使用了，方法与 Xbird Starter 基本一致。

1）在你的 Spring Boot 项目中作为父项目依赖：

```
<parent>
    <groupId>com.github.zhycn</groupId>
    <artifactId>xbird-starter-bom</artifactId>
    <version>2.3.0</version>
    <relativePath />
</parent>
```

2）非 Spring Boot 项目可以添加依赖管理：

```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.github.zhycn</groupId>
            <artifactId>xbird-starter-bom</artifactId>
            <version>2.3.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```