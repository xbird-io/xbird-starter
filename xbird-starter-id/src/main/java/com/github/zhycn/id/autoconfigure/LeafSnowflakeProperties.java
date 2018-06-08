package com.github.zhycn.id.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Leaf-snowflake Configuration Properties
 * 
 * @author qizhaohong@lakala.com
 * @since 2.2.0 2018-06-08
 */
@ConfigurationProperties(prefix = "xbird.id.leaf.snowflake")
public class LeafSnowflakeProperties {

  /**
   * workerId: 0~31
   */
  private Integer workerId = 0;
  
  /**
   * dataCenterId: 0~31
   */
  private Integer dataCenterId = 0;

  public Integer getWorkerId() {
    return workerId;
  }

  public Integer getDataCenterId() {
    return dataCenterId;
  }

  public void setWorkerId(Integer workerId) {
    this.workerId = workerId;
  }

  public void setDataCenterId(Integer dataCenterId) {
    this.dataCenterId = dataCenterId;
  }

}
