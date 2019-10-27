package com.example.g0569.module.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String encodeByMD5(String origin) {
        try {
            origin = origin + "g.0569";
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(origin.getBytes());
            byte[] digest = md.digest();

            return new String(digest).substring(0, 16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
