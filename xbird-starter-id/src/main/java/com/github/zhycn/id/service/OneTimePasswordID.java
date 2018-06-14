package com.github.zhycn.id.service;

import com.github.zhycn.id.factory.support.GoogleAuthenticatorException;
import com.github.zhycn.id.factory.support.TOTP;

/**
 * the Time-based One-time Password (TOTP) from Google Authenticator.
 * 
 * @author zhycn
 * @since 2.2.0 2018-06-14
 */
public interface OneTimePasswordID {

  boolean authorize(String secretKey, int code, long timestamp) throws GoogleAuthenticatorException;

  boolean authorize(TOTP totp) throws GoogleAuthenticatorException;

  TOTP getTotpPassword();

  TOTP getTotpPassword(long time);

}
