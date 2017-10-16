package com.beyond.algo.common;

import java.util.UUID;

/**
 * @author ：zhangchuanzhi
 * @Description:生成唯一键
 * @date ：9:03 2017/9/26
 */
public class UUIDUtil {
    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
