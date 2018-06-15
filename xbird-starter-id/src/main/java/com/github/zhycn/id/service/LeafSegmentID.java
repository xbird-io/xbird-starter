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
package com.github.zhycn.id.service;

/**
 * 基于美团 Leaf-segment 数据库方案的实现接口：https://tech.meituan.com/MT_Leaf.html
 * 
 * @author zhycn
 * @since 2.2.0 2018-06-08
 */
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
