package com.meme.util;

// import lombok.experimental.UtilityClass;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

// @UtilityClass
public class AESUtil {

    private static final String AES = "AES";
    private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";
    private static final int IV_LEN = 16;
    private static final int T_LEN = 128;

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 生成密钥
     *
     * @return 密钥
     * @throws NoSuchAlgorithmException 错误的加密算法
     */
    public static String generateKey() throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(RANDOM);
        SecretKey secretKey = keyGenerator.generateKey();

        return Base64.getUrlEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * 加密
     *
     * @param secretKey 密钥
     * @param raw       待加密数据
     * @return 加密数据
     * @throws GeneralSecurityException 加密失败
     */
    public static String encrypt(String secretKey, String raw) throws GeneralSecurityException {

        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getUrlDecoder().decode(secretKey), AES);
        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);

        byte[] newIv = new byte[IV_LEN];
        RANDOM.nextBytes(newIv);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(T_LEN, newIv);

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, gcmParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(raw.getBytes(StandardCharsets.UTF_8));

        byte[] encryptedBytesWithIv = new byte[newIv.length + encryptedBytes.length];
        System.arraycopy(newIv, 0, encryptedBytesWithIv, 0, newIv.length);
        System.arraycopy(encryptedBytes, 0, encryptedBytesWithIv, newIv.length, encryptedBytes.length);

        return Base64.getUrlEncoder().encodeToString(encryptedBytesWithIv);
    }

    /**
     * 解密
     *
     * @param secretKey 密钥
     * @param encrypted 加密数据
     * @return 解密后数据
     * @throws GeneralSecurityException 解密失败
     */
    public static String decrypt(String secretKey, String encrypted) throws GeneralSecurityException {

        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getUrlDecoder().decode(secretKey), AES);
        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);

        byte[] encryptedBytesWithIv = Base64.getUrlDecoder().decode(encrypted);

        byte[] iv = new byte[IV_LEN];
        System.arraycopy(encryptedBytesWithIv, 0, iv, 0, iv.length);
        byte[] encryptedBytes = new byte[encryptedBytesWithIv.length - IV_LEN];
        System.arraycopy(encryptedBytesWithIv, iv.length, encryptedBytes, 0, encryptedBytes.length);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(T_LEN, iv);

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, gcmParameterSpec);
        byte[] bytes = cipher.doFinal(encryptedBytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }
    public static void main(String[] args) throws GeneralSecurityException {

        String key = generateKey();

        String encrypt = encrypt(key, "12345");
        System.out.println(encrypt);
        System.out.println(decrypt(key, encrypt));

        System.out.println(key);
    }

}
