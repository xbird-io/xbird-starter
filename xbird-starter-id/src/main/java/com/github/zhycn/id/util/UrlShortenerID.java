/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.zhycn.id.util;

import org.springframework.util.DigestUtils;

/**
 * URL Shortener Generator
 * 
 * @author zhycn
 * @since 2.2.0 2018-04-18
 */
public abstract class UrlShortenerID {

  /**
   * 默认用于生成短链的字符串
   */
  private static final String ALPHABET =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @return 返回生成短网址数组，选其一作为短网址
   */
  public static String[] create(String url) {
    return create(url, 6);
  }

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @param length 指定生成短链接的长度，取值范围：1~6
   * @return 返回生成短网址数组，选其一作为短网址
   */
  public static String[] create(String url, int length) {
    return create(url, RandomID.randomInt(8), length);
  }

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @param secret 和实际网址混合生成短网址的掩码
   * @param length 指定生成短链接的长度，取值范围：1~6
   * @return 返回生成短网址数组，选其一作为短网址
   */
  public static String[] create(String url, String secret, int length) {
    return create(url, secret, length, ALPHABET.toCharArray());
  }

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @param secret 和实际网址混合生成短网址的掩码
   * @param length 指定生成短链接的长度，取值范围：1~6
   * @param chars 指定用于生成短链的字符串
   * @return 返回生成短网址数组，选其一作为短网址
   */
  private static String[] create(String url, String secret, int length, char[] chars) {
    // 1. 通过MD5混合加密实际网址
    String md5EncryptResult = DigestUtils.md5DigestAsHex((secret + url).getBytes());

    // 判断有效长度，默认值：6
    if (length < 1 || length > 6) {
      length = 6; 
    }

    // 2. 定义返回结果数组，经过换算后会生成4组短链接字符串
    String[] result = new String[4];
    for (int i = 0; i < result.length; i++) {
      // 把加密字符按照8位一组16进制与 0x3FFFFFFF进行位与运算
      String substring = md5EncryptResult.substring(i * 8, i * 8 + 8);
      // 转换为Long类型（Integer可能会因长度不够而出错）
      long hex = 0x3FFFFFFF & Long.parseLong(substring, 16);
      String outChars = "";
      for (int j = 0; j < length; j++) {
        // 把得到的值与 0x0000003D进行位与运算，取得字符数组 chars索引
        long index = 0x0000003D & hex;
        // 把取得的字符相加
        outChars += chars[(int) index];
        // 每次循环按位右移 5 位
        hex = hex >> 5;
      }
      result[i] = outChars;
    }

    // 3. 返回结果数组
    return result;
  }
  
}
