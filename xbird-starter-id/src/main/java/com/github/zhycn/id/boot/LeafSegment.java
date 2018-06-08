package com.github.zhycn.id.boot;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class LeafSegment implements InitializingBean {

  private Long minId;
  private Long maxId;
  private Long middleId;
  private Long step;

  public LeafSegment(Long maxId, Long step) {
    Assert.notNull(maxId, "maxId must be not null.");
    Assert.notNull(step, "step must be not null.");
    this.maxId = maxId;
    this.step = step;
    try {
      afterPropertiesSet();
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  public Long getMaxId() {
    return maxId;
  }

  public Long getMiddleId() {
    return middleId;
  }

  public Long getMinId() {
    return minId;
  }

  public Long getStep() {
    return step;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    this.middleId = this.maxId - (long) Math.round(this.step / 2);
    this.minId = this.maxId - this.step;
  }

}
