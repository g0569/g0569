package com.example.g0569.module.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** Utils. */
public class Utils {

  /**
   * Encode a string using MD5 with salt
   *
   * @param origin the origin string
   * @return the encoded string
   */
  public static String encodeByMD5(String origin) {
    try {
      origin = origin + "g.0569";
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(origin.getBytes());
      byte[] digest = md.digest();

      return new String(digest).substring(0, 5);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }
}
