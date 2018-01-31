# 企业级开发指南

在企业级应用开发中，通常都有企业内部的私有仓库和应用模块。为了更好地助力企业集成、构建和发布应用模块，我们提供了 `xbird-starter-bom` 模块来帮助和提升管理效能。

## 开发向导

企业开发者可以通过简单的几步操作来定制自己的版本管理平台。

### 1. 安装

下载 Xbird Starter 项目到本地目录：

```
git clone https://github.com/zhycn/xbird-starter.git
```

### 2. 集成

作为开发者，可以只关心 `xbird-starter-bom` 模块中的 `pom.xml` 文件。使用编辑工具打开 `pom.xml` 文件，在这里添加企业内部的依赖包或者 Xbird Starter 项目未能提供的依赖包。

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

你也可以修改项目的信息，例如坐标和版本号。需要注意的是，在你修改了坐标和版本号之后，建议将子模块的引用一起修改。

### 3. 构建

项目编辑完成后，开始构建：

```
# 通过打包来测试项目是否成功
mvn clean package
```

在确定项目打包成功后，就可以准备发布了。

### 4. 发布

在发布之前，你需要配置好本地仓库信息。

> 以Nexus为例，这里不再赘述，不懂的请移步：[Maven私服仓库管理： Nexus 3.0](https://segmentfault.com/a/1190000005966312)

配置好之后，通过命令发布到本地仓库：

```
mvn deploy
```

## 使用向导

部署到本地仓库后，就可以使用定制服务了，使用方法 Xbird Starter 基本一致。

> 需要注意的是：使用自定义的坐标和版本信息。

假如新的坐标和版本信息为：

```
groupId: com.zhycn.platform
artifactId: xbird-starter-bom
version: 1.0.0
```

1）在你的 Spring Boot 项目中作为父项目依赖：

```
<parent>
    <groupId>com.zhycn.platform</groupId>
    <artifactId>xbird-starter-bom</artifactId>
    <version>1.0.0</version>
    <relativePath />
</parent>
```

2）非 Spring Boot 项目可以添加依赖管理：

```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.zhycn.platform</groupId>
            <artifactId>xbird-starter-bom</artifactId>
            <version>1.0.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## 升级

我们建议你在集成企业级开发时，只修改 `xbird-starter-bom/pom.xml` 文件的内容，当需要升级时，只需下载新的版本，然后将 `xbird-starter-bom/pom.xml` 文件覆盖即可。