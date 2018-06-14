package com.github.zhycn.id.factory;

import java.util.Date;

import org.springframework.util.Assert;

import com.github.zhycn.id.factory.support.GoogleAuthenticatorException;
import com.github.zhycn.id.factory.support.GoogleAuthenticatorKey;
import com.github.zhycn.id.factory.support.IGoogleAuthenticator;
import com.github.zhycn.id.factory.support.TOTP;
import com.github.zhycn.id.service.OneTimePasswordID;

public class OneTimePasswordFactory implements OneTimePasswordID {

  private IGoogleAuthenticator googleAuthenticator;

  public OneTimePasswordFactory(IGoogleAuthenticator googleAuthenticator) {
    this.googleAuthenticator = googleAuthenticator;
  }

  @Override
  public boolean authorize(String secretKey, int code, long timestamp)
      throws GoogleAuthenticatorException {
    return googleAuthenticator.authorize(secretKey, code, timestamp);
  }

  @Override
  public boolean authorize(TOTP totp) throws GoogleAuthenticatorException {
    Assert.notNull(totp, "TOTP must be not null.");
    return authorize(totp.getSecretKey(), totp.getCodeAsInt(), totp.getTimestamp());
  }

  @Override
  public TOTP getTotpPassword() {
    return getTotpPassword(new Date().getTime());
  }

  @Override
  public TOTP getTotpPassword(long timestamp) {
    GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
    String code = googleAuthenticator.getTotpPassword(key.getKey(), timestamp);
    return new TOTP(key.getKey(), code, key.getScratchCodes(), timestamp);
  }

}
