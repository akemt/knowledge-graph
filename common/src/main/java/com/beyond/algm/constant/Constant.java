package com.beyond.algm.constant;

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

    public static final int FILE_READ_SIZE = 8192;

    public static final String ZIP_SUFFIX = ".zip";

    public final static Map map = new HashMap();
    static {
        map.put("Java", "build.xml");
        map.put("C", "value2");
    }
}
