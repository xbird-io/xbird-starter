# xbird-starter-id

> A Distributed ID Generator for Spring Boot.

本项目是基于 [Leaf——美团点评分布式ID生成系统](https://tech.meituan.com/MT_Leaf.html) 的开源实现，提供复杂分布式系统ID生成的解决方案。

1. RandomID
2. UrlShortenerID
3. OneTimePasswordID
4. SnowflakeID
5. LeafSegmentID

## 快速开始

在你的 Spring Boot 项目中添加依赖配置：

```
<dependency>
  <groupId>com.github.zhycn</groupId>
  <artifactId>xbird-starter-id</artifactId>
</dependency>
```

### 1. RandomID

随机ID生成器，可快速生成 UUID、GUID 和 Random 字符串。

```
// 在项目中使用（自动注入）
@Autowired
private RandomID randomID;
```

RandomID 接口定义如下：

```
public interface RandomID {

  /**
   * 随机生成一个UUID字符串
   * 
   * @return UUID 字符串
   */
  String uuid();

  /**
   * 根据指定字符生成一个UUID字符串
   * 
   * @param name 指定字符
   * @return UUID 字符串
   */
  String uuid(String name);

  /**
   * 随机生成一个UUID字符串，并转换为大写字母
   * 
   * @return UUID 字符串
   */
  String uuidUpperCase();

  /**
   * 根据指定字符生成一个UUID字符串，并转换为大写字母
   * 
   * @param name 指定字符
   * @return UUID 字符串
   */
  String uuidUpperCase(String name);

  /**
   * 随机生成一个GUID字符串
   * 
   * @return GUID 字符串
   */
  String guid();

  /**
   * 根据指定字符生成一个GUID字符串
   * 
   * @param name 指定字符
   * @return GUID 字符串
   */
  String guid(String name);

  /**
   * 随机生成一个GUID字符串，并转换为大写字母
   * 
   * @return GUID 字符串
   */
  String guidUpperCase();

  /**
   * 根据指定字符生成一个GUID字符串，并转换为大写字母
   * 
   * @param name 指定字符
   * @return GUID 字符串
   */
  String guidUpperCase(String name);

  /**
   * 随机生成指定长度的字符串，由大小写字母（数字）组成
   * 
   * @param count 指定长度
   * @return 随机字符串
   */
  String random(int count);

  /**
   * 随机生成指定长度的字符串，由大小写字母（数字）组成
   * 
   * @param count 指定长度
   * @param onlyNumbers 是否为纯数字
   * @return 随机字符串
   */
  String random(int count, boolean onlyNumbers);

  /**
   * 自定义字符集生成指定长度的字符串
   * 
   * @param count 指定长度
   * @param chars 指定字符集
   * @return 随机字符串
   */
  String random(int count, char... chars);

  /**
   * 自定义字符集生成指定长度的字符串
   * 
   * @param count 指定长度
   * @param chars 指定字符集
   * @return 随机字符串
   */
  String random(int count, String chars);
}
```

### 2. UrlShortenerID

短网址生成器。

```
// 在项目中使用（自动注入）
@Autowired
private UrlShortenerID urlShortenerID;
```

UrlShortenerID 接口定义如下：

```
public interface UrlShortenerID {

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @return 返回生成短网址数组，选其一作为短网址（默认6位长度）
   */
  String[] create(String url);

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @param length 指定生成短链接的长度，取值范围：1~8
   * @return 返回生成短网址数组，选其一作为短网址
   */
  String[] create(String url, int length);

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @param secret 和实际网址混合生成短网址的掩码
   * @param length 指定生成短链接的长度，取值范围：1~8
   * @return 返回生成短网址数组，选其一作为短网址
   */
  String[] create(String url, String secret, int length);

}
```

### 3. OneTimePasswordID

基于时间的一次性密码（TOTP）生成器。

```
// 在项目中使用（自动注入）
@Autowired
private OneTimePasswordID oneTimePasswordID;
```

OneTimePasswordID 接口定义如下：

```
public interface OneTimePasswordID {

  /**
   * 验证码是否有效
   * 
   * @param secret 安全码
   * @param code 验证码
   * @param timestamp 生成码的时间
   * @return true for success
   * @throws GoogleAuthenticatorException
   */
  boolean authorize(String secret, int code, long timestamp) throws GoogleAuthenticatorException;

  /**
   * 验证码是否有效
   * 
   * @param totp
   * @return true for success
   * @throws GoogleAuthenticatorException
   */
  boolean authorize(TOTP totp) throws GoogleAuthenticatorException;

  /**
   * 获取一个验证码（系统当前时间）
   * 
   * @return 验证码
   */
  TOTP getTotpPassword();

  /**
   * 获取一个特定时间的验证码
   * 
   * @return 验证码
   */
  TOTP getTotpPassword(long timestamp);
}
```

### 4. SnowflakeID

基于 Twitter-snowflake 方案的实现。

该接口提供了两个实现类，分别是 LeafSnowflakeFactory 和 TwitterSnowflakeFactory。它们的区别是：前者只接收一个参数 `workerId`，而后者接收两个参数 `workerId` 和 `dataCenterId`。

```
# 通过属性文件配置参数
xbird.id.snowflake.worker-id=0
xbird.id.snowflake.data-center-id=0
```

接口的默认配置是 LeafSnowflakeFactory ：

```
@Autowired
private SnowflakeProperties snowflakeProperties;

@Bean
@ConditionalOnMissingBean
public SnowflakeID createLeafSnowflakeFactory() {
  int workerId = snowflakeProperties.getWorkerId();
  return new LeafSnowflakeFactory(workerId);
}
```

此时，workerId 的取值范围是：0~1023，dataCenterId 设置则无效。如果想使用 TwitterSnowflakeFactory 提供的服务，只需要在项目中添加配置即可：

```
@Autowired
private SnowflakeProperties snowflakeProperties;

@Bean
public SnowflakeID createTwitterSnowflakeFactory() {
  int workerId = snowflakeProperties.getWorkerId();
  int dataCenterId = snowflakeProperties.getDataCenterId();
  return new TwitterSnowflakeFactory(workerId, dataCenterId);
}
```

此时，workerId 和 dataCenterId 的取值范围都是：0~31。

> 注意：如果不是多机房部署环境，使用默认即可。另外，后续会增加 ZK 注册中心，以实现自动生成 workerId。

```
// 在项目中使用（自动注入）
@Autowired
private SnowflakeID snowflakeID;
```

SnowflakeID 接口定义如下：

```
public interface SnowflakeID {

  /**
   * 获取一个由 Twitter-snowflake 算法生成的18位长度的ID
   * 
   * @return 18位长度的ID
   */
  Long getId();

}
```

### 5. LeafSegmentID

基于美团 Leaf-segment 数据库方案的实现。

> 在使用时，需要配置一个数据源。如果是多数据源，则使用 @Primary 标记的数据源将作为默认数据源。

由于依赖数据库的缘故，需要手动打开配置 `@EnableLeafSegment`：

```
@SpringBootApplication
@EnableLeafSegment // 打开配置
public class DemoApplication {

}
```

同时，在 POM 文件中添加 JPA 依赖配置：

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

然后，在数据源对应的数据库中创建表：

```
-- 基于美团 Leaf-Segment 数据库方案的表结构设计
CREATE TABLE `leaf_segment` (
	`biz_tag` VARCHAR(128) NOT NULL COMMENT '业务标签',
	`max_id` BIGINT(20) NOT NULL DEFAULT '1' COMMENT '当前被分配的最大值',
	`step` INT(11) NOT NULL COMMENT '每次分配的号段长度',
	`description` VARCHAR(256) NULL DEFAULT NULL COMMENT '描述信息',
	`created` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
	`last_modified` TIMESTAMP NULL DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY (`biz_tag`)
)
COMMENT='Leaf-Segment'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
```

> 以上是 MySQL 的数据库脚本，其他数据库则作相应的修改即可。

接下来，在项目中配置业务标签：

```
# 是否使用异步加载（默认值：True）
xbird.id.segment.asyn-loading=true

# 设置业务标签（支持设置多个业务标签）
xbird.id.segment.endpoints.one.biz-tag=test
xbird.id.segment.endpoints.one.start-id=1
xbird.id.segment.endpoints.one.step=20
xbird.id.segment.endpoints.one.description=Test Description

# 业务标签二（startId 和 description 不是必须的）
xbird.id.segment.endpoints.two.biz-tag=order_test
xbird.id.segment.endpoints.two.step=20
```

- startId 默认值为 1。
- description 默认值为 null。

配置完成后，即可在项目中使用：

```
// 在项目中使用（自动注入）
@Autowired
private LeafSegmentID leafSegmentID;
```

LeafSegmentID 接口定义如下：

```
public interface LeafSegmentID {

  /**
   * 初始化一个业务配置
   * 
   * @param bizTag 业务标签
   * @param step 步长
   */
  void init(String bizTag, long step);

  /**
   * 初始化一个业务配置
   * 
   * @param bizTag 业务标签
   * @param startId 起始值
   * @param step 步长
   */
  void init(String bizTag, long startId, long step);

  /**
   * 初始化一个业务配置
   * 
   * @param bizTag 业务标签
   * @param startId 起始值
   * @param step 步长
   * @param desc 业务描述信息
   */
  void init(String bizTag, long startId, long step, String desc);

  /**
   * 获取一个由 Leaf-segment 算法生成的ID
   * 
   * @param bizTag 业务标签
   * @return ID
   */
  Long getId(String bizTag);

  /**
   * 获取一个由 Leaf-segment 算法生成的ID。为了更好的支持分库分表的应用场景，增加了用户基因算法的实现。
   * 
   * @param bizTag 业务标签
   * @param userId 用户标识
   * @param dbSize 数据库大小
   * @return
   */
  Long getId(String bizTag, long userId, int dbSize);

}
```