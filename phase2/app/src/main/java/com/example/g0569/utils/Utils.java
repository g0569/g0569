package com.example.g0569.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

  public static String serializeToString(Object obj) throws Exception{
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
    objOut.writeObject(obj);
    String str = byteOut.toString("ISO-8859-1");
    return str;
  }

  public static Object deserializeToObject(String str) throws Exception{
    ByteArrayInputStream byteIn = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
    ObjectInputStream objIn = new ObjectInputStream(byteIn);
    Object obj =objIn.readObject();
    return obj;
  }
}
