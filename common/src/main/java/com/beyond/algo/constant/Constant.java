package com.beyond.algo.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量
 */
public class Constant {

    /**
     * ES 脚本 多字段分隔符（使用的是不可见字符）,27----ESC (escape)
     */
    public static final String SCRIPT_DELIMIT = ((char) 27) + "";

    public final static Map map = new HashMap();
    static {
        map.put("Java", "build.xml");
        map.put("C", "value2");
    }
}
