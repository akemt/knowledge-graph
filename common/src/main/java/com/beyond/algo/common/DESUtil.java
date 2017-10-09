package com.beyond.algo.common;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.SecureRandom;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
/**
 * @author ：zhangxiangjie
 * @Description:密码加密解密
 * @date ：15:15 2017/9/26
 */
public class DESUtil {
    public DESUtil() {
    }
    /**
     * 加密
     * @param dataSource String
     * @param password String
     * @return byte[]
     */
    public static String encrypt(String dataSource, String password) {
        try{
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            byte[] byteResult = cipher.doFinal(dataSource.getBytes());
            //将数据结果转换成字符串结果返回
            String stringResult = new BASE64Encoder().encode(byteResult);
            return stringResult;
        }catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解密
     * @param src String
     * @param password String
     * @return byte[]
     * @throws Exception
     */

    public static String decrypt(String src, String password) throws Exception {

        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        //将字符串转换成数组
        byte[] buf = new BASE64Decoder().decodeBuffer(src);
        byte[] byteResult = cipher.doFinal(buf);
        return new String(byteResult);
    }
}
