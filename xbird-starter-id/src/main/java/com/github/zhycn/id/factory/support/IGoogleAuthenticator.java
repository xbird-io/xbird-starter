package com.github.zhycn.id.factory.support;

public interface IGoogleAuthenticator {

  boolean authorize(String secret, int verificationCode) throws GoogleAuthenticatorException;

  boolean authorize(String secret, int verificationCode, long time)
      throws GoogleAuthenticatorException;

  GoogleAuthenticatorKey createCredentials();

  int getTotpPassword(String secret);

  int getTotpPassword(String secret, long time);

}
