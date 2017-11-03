package com.beyond.algo.common;

import com.beyond.algo.exception.AlgException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;
@Slf4j
public class AESUtil {

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static SecretKey getKey(String password) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            _generator.init(128, secureRandom);
            return _generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(" 初始化密钥出现异常 ");
        }
    }

    public static void main(String[] args) {
    /*	System.out.println(encryptString("123456","49475801beyondjvkduedfj"));
		System.out.println(decrypt("B2AC264948ACA0906B9F12194F5564A9","49475801beyondjvkduedfj"));*/
    }

    public static String encryptAES(String src, String key) throws Exception {
        // 判断密钥是否为空
        if (key == null) {
            System.out.print("密钥不能为空");
            return null;
        }

        // 密钥补位
        int plus = 16 - key.length();
        byte[] data = key.getBytes("utf-8");
        byte[] raw = new byte[16];
        byte[] plusbyte = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
        for (int i = 0; i < 16; i++) {
            if (data.length > i)
                raw[i] = data[i];
            else
                raw[i] = plusbyte[plus];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // 算法/模式/补码方式
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(src.getBytes("utf-8"));

        //return new Base64().encodeToString(encrypted);//base64
        return binary(encrypted, 16); //十六进制
    }

    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);    // 这里的1代表正数
    }

    public static String decryptAES(String src, String key) throws AlgException {
        try {
            // 判断Key是否正确
            if (key == null) {
                System.out.print("Key为空null");
                return null;
            }

            // 密钥补位
            int plus = 16 - key.length();
            byte[] data = key.getBytes("utf-8");
            byte[] raw = new byte[16];
            byte[] plusbyte = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
            for (int i = 0; i < 16; i++) {
                if (data.length > i)
                    raw[i] = data[i];
                else
                    raw[i] = plusbyte[plus];
            }

            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            byte[] encrypted1 = toByteArray(src);//十六进制

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;

        } catch (Exception e) {
            log.error(e.toString());
            throw new AlgException("密码解密出错");
        }
    }

    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString 16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        if (hexString.isEmpty())
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }
}
