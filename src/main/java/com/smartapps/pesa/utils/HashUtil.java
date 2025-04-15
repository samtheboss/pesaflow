package com.smartapps.pesa.utils;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashUtil {

    public static String generateBase64EncodedHmac(String data, String key) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] hashBytes = sha256_HMAC.doFinal(data.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hashBytes) {
                hex.append(String.format("%02x", b));
            }
            return Base64.getEncoder().encodeToString(hex.toString().getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate HMAC SHA256 hash", e);
        }
    }
    public static String generateBase64EncodedHmac3(String data, String key)  {
        System.out.println("this is data "+data);
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKey);
            byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

    }
}
