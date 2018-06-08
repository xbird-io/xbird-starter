package com.github.zhycn.id.service;

/**
 * 基于美团 Leaf-snowflake 方案的实现接口：https://tech.meituan.com/MT_Leaf.html
 * 
 * @author qizhaohong@lakala.com
 * @since 2.2.0 2018-06-08
 */
public interface LeafSnowflakeID {

  /**
   * 获取一个由 Leaf-snowflake 算法生成的18位长度的ID
   * 
   * @return 18位长度的ID
   */
  Long getId();

}
