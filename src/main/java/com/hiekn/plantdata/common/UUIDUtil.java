package com.hiekn.plantdata.common;

import java.util.Random;
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
    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
