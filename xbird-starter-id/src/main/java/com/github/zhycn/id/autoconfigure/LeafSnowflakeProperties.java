package com.github.zhycn.id.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Leaf-snowflake Configuration Properties
 * 
 * @author qizhaohong@lakala.com
 * @since 2.2.0 2018-06-08
 */
@ConfigurationProperties(prefix = "xbird.id.snowflake")
public class LeafSnowflakeProperties {

  private boolean enabled;

  /**
   * workerId: 0~31
   */
  private Integer workerId = 0;

  /**
   * dataCenterId: 0~31
   */
  private Integer dataCenterId = 0;

  public Integer getDataCenterId() {
    return dataCenterId;
  }

  public Integer getWorkerId() {
    return workerId;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setDataCenterId(Integer dataCenterId) {
    this.dataCenterId = dataCenterId;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setWorkerId(Integer workerId) {
    this.workerId = workerId;
  }

}
