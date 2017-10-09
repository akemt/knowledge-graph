package com.beyond.algo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/8 0008 下午 6:59
 */
public class MD5EncodeUtil {
    private static Logger logger = LoggerFactory.getLogger(MD5EncodeUtil.class);
    //定义加密方式
    private final static String MD5 = "MD5";

    /**
     * MD5 加密
     *
     * @param :data 需要加密的字符串
     * @return 加密之后的字符串
     */
    public static String md5(String source) {
        // 验证传入的字符串
        if (StringUtils.isEmpty(source)) {
            return "";
        }

        try {
            MessageDigest md = MessageDigest.getInstance( MD5 );
            byte[] bytes = md.digest(source.getBytes("utf-8"));
            return byteArrayToHexString(bytes);
        } catch (Exception e) {
            logger.error("字符串使用Md5加密失败" + source + "' to MD5!", e);
            return null;
        }
    }
    /**
     * 转换字节数组为十六进制字符串
     *
     * @param bytes
     *            字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
        }
        return sb.toString();
    }
    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String key = "123";
        //System.out.println(sha(key));
        System.out.println(md5(key));
    }
}
