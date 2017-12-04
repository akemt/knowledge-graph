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

    //java发布ant构建生成zip文件的后缀
    public static final String ZIP_SUFFIX = ".zip";

    //java jar 包后缀
    public static final String JAR_SUFFIX = ".jar";

    //java发布ant构建生成zip文件的后缀
    public static final String POM_XML = "pom.xml";

    //maven 打包后生成的target目录
    public static final String TARGET = "target";

    public final static Map map = new HashMap();
    static {
        map.put("Java", "build.xml");
        map.put("C", "value2");
    }
}
