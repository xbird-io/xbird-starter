package com.github.zhycn.id.factory.support;

import java.util.List;

public class TOTP {

  private String secretKey;
  private int code;
  private List<Integer> scratchCodes;
  private long timestamp;

  public TOTP() {}

  public TOTP(String secretKey, int code, List<Integer> scratchCodes, long timestamp) {
    this.secretKey = secretKey;
    this.code = code;
    this.scratchCodes = scratchCodes;
    this.timestamp = timestamp;
  }

  public int getCode() {
    return code;
  }

  public List<Integer> getScratchCodes() {
    return scratchCodes;
  }

  public String getSecretKey() {
    return secretKey;
  }


  public long getTimestamp() {
    return timestamp;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public void setScratchCodes(List<Integer> scratchCodes) {
    this.scratchCodes = scratchCodes;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "TOTP [secretKey=" + secretKey + ", code=" + code + ", scratchCodes=" + scratchCodes
        + ", timestamp=" + timestamp + "]";
  }

}
