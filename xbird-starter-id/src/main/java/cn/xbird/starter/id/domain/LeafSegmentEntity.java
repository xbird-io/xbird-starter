/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.xbird.starter.id.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Leaf-segment Entity
 * 
 * @author zhycn
 * @since 2.2.0 2018-06-08
 */
@Entity
@Table(name = "leaf_segment")
@EntityListeners(AuditingEntityListener.class)
public class LeafSegmentEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "biz_tag")
  private String bizTag;

  @Column(name = "max_id")
  private Long maxId;

  @Column(name = "step")
  private Long step;

  @Column(name = "description")
  private String description;

  @CreatedDate
  @Column(name = "created")
  private Date created;

  @LastModifiedDate
  @Column(name = "last_modified")
  private Date lastModified;

  public String getBizTag() {
    return bizTag;
  }

  public Long getMaxId() {
    return maxId;
  }

  public Long getStep() {
    return step;
  }

  public String getDescription() {
    return description;
  }

  public Date getCreated() {
    return created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setBizTag(String bizTag) {
    this.bizTag = bizTag;
  }

  public void setMaxId(Long maxId) {
    this.maxId = maxId;
  }

  public void setStep(Long step) {
    this.step = step;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  @Override
  public String toString() {
    return "LeafSegmentEntity [bizTag=" + bizTag + ", maxId=" + maxId + ", step=" + step
        + ", description=" + description + ", created=" + created + ", lastModified=" + lastModified
        + "]";
  }

}
