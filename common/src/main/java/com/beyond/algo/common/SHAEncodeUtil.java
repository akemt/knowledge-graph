package com.beyond.algo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/8 0008 下午 7:01
 */
public class SHAEncodeUtil {
    private static Logger logger = LoggerFactory.getLogger(SHAEncodeUtil.class);
    //定义加密方式
    private final static String SHA = "SHA";

    /**
     * SHA 加密
     *
     * @param data 需要加密的字符串
     * @return 加密之后的字符串
     */
    public static String sha(String data) {
        // 验证传入的字符串
        if (StringUtils.isEmpty(data)) {
            return "";
        }
        try {
            // 创建具有指定算法名称的信息摘要
            MessageDigest sha = MessageDigest.getInstance( SHA );
            // 使用指定的字节数组对摘要进行最后更新
            sha.update(data.getBytes("utf-8"));
            // 完成摘要计算
            byte[] bytes = sha.digest();
            // 将得到的字节数组变成字符串返回
            return byteArrayToHexString(bytes);
        } catch (Exception e) {
            logger.error("字符串使用SHA加密失败", e);
            return null;
        }
    }
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
        }
        return sb.toString();
    }
}
