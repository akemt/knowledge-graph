package com.beyond.algo.common;

/**
 * @author ：zhangchuanzhi
 * @Description:共同方法
 * @date ：16:59 2017/10/30
 */
public class AdapterUtil {
    public static Object moduleAdapter(String lanName)  throws Exception {
       return Class.forName("com.beyond.algo.algoconsoleboot.adapter."+ lanName +"ModuleAdapter").newInstance();
    }
}
