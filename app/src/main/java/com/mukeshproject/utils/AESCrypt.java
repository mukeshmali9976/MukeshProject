package com.mukeshproject.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


@SuppressWarnings("ALL")
public final class AESCrypt {

    private static final String AES_MODE = "AES/CBC/PKCS5Padding";
    private static final String CHARSET = "UTF-8";
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final String ALGORITHM = "AES";
    private static final byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    private AESCrypt() {
    }

    private static SecretKeySpec generateKey(final String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] bytes = password.getBytes(CHARSET);
        digest.update(bytes, 0, bytes.length);

        return new SecretKeySpec(bytes, ALGORITHM);
    }

    public static String encrypt(String msg, boolean isPref) throws GeneralSecurityException {

        try {

            if (msg != null && msg.length() > 0) {

                String message = msg;
                if (!isPref) {
                    message = URLEncoder.encode(msg, CHARSET);
                }

                String passToSend = randomString(38);
                String password = passToSend.substring(0, 16) + passToSend.substring(22);
                final SecretKeySpec key = generateKey(password);

                Log.e("AESCrypt", "*******************************");
                Log.e("Without Encryption", message);
                Log.e("AESCrypt", "*******************************");
                Log.e("PassToSend", passToSend);
                Log.e("AESCrypt", "*******************************");
                Log.e("Password", password);

                byte[] cipherText = encrypt(key, ivBytes, message.getBytes(CHARSET));

                Log.e("AESCrypt", "*******************************");
                Log.e("With Encryption", Base64.encodeToString(cipherText, Base64.NO_WRAP));

                return stringKnitter(passToSend, Base64.encodeToString(cipherText, Base64.NO_WRAP));
            } else {
                return msg;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new GeneralSecurityException(e);
        }
    }

    public static String encrypt(String msg, String password) throws GeneralSecurityException {

        try {

            if (msg != null && msg.length() > 0) {

                final SecretKeySpec key = generateKey(password);

                byte[] cipherText = encrypt(key, ivBytes, msg.getBytes(CHARSET));

                return Base64.encodeToString(cipherText, Base64.NO_WRAP);

            } else {
                return msg;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new GeneralSecurityException(e);
        }
    }

    public static byte[] encrypt(final SecretKeySpec key, final byte[] iv, final byte[] message) throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

        return cipher.doFinal(message);
    }

    public static String decrypt(final String str) throws GeneralSecurityException {

        try {

            String msg;
            if (str != null && str.length() > 0) {
                msg = str;
            } else {
                return str;
            }

            if (msg.length() <= 50) {
                msg = encrypt(msg, true);
            }

            String key = msg.substring(0, 16) + msg.substring(22, 38);
            final SecretKeySpec password = generateKey(key);

            byte[] decodedCipherText = Base64.decode(digestPadding(msg), Base64.NO_WRAP);

            byte[] decryptedBytes = decrypt(password, ivBytes, decodedCipherText);

            String message = new String(decryptedBytes, CHARSET);

            Log.e("AESCrypt", "*******************************");
            Log.e("With Encryption", msg);
            Log.e("AESCrypt", "*******************************");
            Log.e("Key", msg.substring(0, 38));
            Log.e("AESCrypt", "*******************************");
            Log.e("Password", key);
            Log.e("AESCrypt", "*******************************");
            Log.e("Without Encryption", message);

            return message;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new GeneralSecurityException(e);
        }
    }

    public static String decrypt(final String str, String password) throws GeneralSecurityException {

        try {

            String msg;
            if (str != null && str.length() > 0) {
                msg = str;
            } else {
                return str;
            }

            final SecretKeySpec key = generateKey(password);

            byte[] decodedCipherText = Base64.decode(msg, Base64.NO_WRAP);

            byte[] decryptedBytes = decrypt(key, ivBytes, decodedCipherText);

            return new String(decryptedBytes, CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new GeneralSecurityException(e);
        }
    }

    private static String stringKnitter(String password, String msg) {
        return (password + msg);
    }

    private static String digestPadding(final String msg) {

        return msg.substring(38);
    }

    private static String generatePassowrd(String msg, int length) {

        return msg.substring(0, length);
    }

    public static byte[] decrypt(final SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText) {

        byte[] decryptedBytes;
        try {
            final Cipher cipher = Cipher.getInstance(AES_MODE);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            decryptedBytes = cipher.doFinal(decodedCipherText);

        } catch (Exception e) {
            e.printStackTrace();
            String s = "null";
            decryptedBytes = s.getBytes();
        }
        return decryptedBytes;
    }

    private static String randomString(int length) {

        char[] characterSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        // char[] characterSet = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789".toCharArray();
        Random random = new SecureRandom();
        char[] result = new char[length * length];
        for (int i = 0; i < result.length; i++) {
            int randomCharIndex = random.nextInt(characterSet.length);
            result[i] = characterSet[randomCharIndex];
        }
        String str = (new String(result));
        try {
            if (str.length() > length) {
                str = generatePassowrd(str, length);
            } else {
                str += str;
                str = generatePassowrd(str, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }
}
