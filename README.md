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

### 部署到私有仓库

企业用户如果建有Maven私有仓库（nexus），可将本项目部署到私有仓库：

第一步：下载

```
git clone https://github.com/zhycn/xbird-starter.git
```

第二步：私有仓库配置

在本地Maven的settings.xml文件中配置用户认证信息：

```
<server>
  <id>nexus-releases</id>
  <username>admin</username>
  <password>admin123</password>
</server>

<server>
  <id>nexus-snapshots</id>
  <username>admin</username>
  <password>admin123</password>
</server>
```

在POM.xml中配置部署构件：

```
<distributionManagement>
  <repository>
    <id>nexus-releases</id>
    <name>Internal Releases</name>
    <url>http://10.7.3.2/nexus/content/repositories/releases/</url>
  </repository>
  <snapshotRepository>
    <id>nexus-snapshots</id>
    <name>Internal snapshots</name>
    <url>http://10.7.3.2/nexus/content/repositories/snapshots/</url>
  </snapshotRepository>
</distributionManagement>
```

第三步：部署安装

```
mvn deploy
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