package com.github.zhycn.id.factory;

public interface ILeafSegment {

  void init(String bizTag, long step);

  void init(String bizTag, long startId, long step);

  void init(String bizTag, long startId, long step, String desc);

  Long getId(String bizTag);

  Long getId(String bizTag, Long userId, Integer dbSize);

}
