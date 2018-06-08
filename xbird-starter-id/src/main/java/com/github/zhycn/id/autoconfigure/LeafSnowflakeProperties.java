package com.github.zhycn.id.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "xbird.id.leaf.snowflake")
public class LeafSnowflakeProperties {

  private Integer workerId = 0;
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
