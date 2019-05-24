package com.ari.cryp;

import android.annotation.SuppressLint;
import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class FTAes {

    public static String encrypt(String input, String key) {
        byte[] crypted = null;
        try {
            SecretKeySpec e = new SecretKeySpec(key.getBytes(), "AES");
            @SuppressLint("GetInstance") Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, e);
            crypted = cipher.doFinal(input.getBytes());
        } catch (Exception var5) {
            System.out.println(var5.toString());
        }
        if (crypted != null) {
            return new String(Base64.encode(crypted, Base64.NO_WRAP));
        } else {
            return "";
        }
    }

    public static String decrypt(String input, String key) {
        byte[] output = null;
        try {
            SecretKeySpec e = new SecretKeySpec(key.getBytes(), "AES");
            @SuppressLint("GetInstance") Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, e);
            output = cipher.doFinal(Base64.decode(input, Base64.NO_WRAP));
        } catch (Exception var5) {
            System.out.println(var5.toString());
        }
        if (output != null) {
            return new String(output);
        } else {
            return "";
        }
    }
    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}

