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
 * 随机生成字符串接口
 * 
 * @author zhycn
 * @since 2.2.0 2018-06-08
 */
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
