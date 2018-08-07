package cn.xbird.starter.id.autoconfigure;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;

import cn.xbird.starter.id.factory.support.HmacHashFunction;
import cn.xbird.starter.id.factory.support.KeyRepresentation;

/**
 * TOTP Configuration Properties
 * 
 * @author zhycn
 * @since Better.SR1 2018-08-07
 */
@ConfigurationProperties(prefix = "xbird.id.totp")
public class TotpProperties {

  private long timeStepSizeInMillis = TimeUnit.SECONDS.toMillis(30);
  private int windowSize = 3;
  private int codeDigits = 6;
  private KeyRepresentation keyRepresentation = KeyRepresentation.BASE32;
  private HmacHashFunction hmacHashFunction = HmacHashFunction.HmacSHA1;

  public long getTimeStepSizeInMillis() {
    return timeStepSizeInMillis;
  }

  public void setTimeStepSizeInMillis(long timeStepSizeInMillis) {
    this.timeStepSizeInMillis = timeStepSizeInMillis;
  }

  public int getWindowSize() {
    return windowSize;
  }

  public void setWindowSize(int windowSize) {
    this.windowSize = windowSize;
  }

  public int getCodeDigits() {
    return codeDigits;
  }

  public void setCodeDigits(int codeDigits) {
    this.codeDigits = codeDigits;
  }

  public KeyRepresentation getKeyRepresentation() {
    return keyRepresentation;
  }

  public void setKeyRepresentation(KeyRepresentation keyRepresentation) {
    this.keyRepresentation = keyRepresentation;
  }

  public HmacHashFunction getHmacHashFunction() {
    return hmacHashFunction;
  }

  public void setHmacHashFunction(HmacHashFunction hmacHashFunction) {
    this.hmacHashFunction = hmacHashFunction;
  }

}
