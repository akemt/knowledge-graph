package com.hiekn.plantdata.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.hiekn.plantdata.exception.JsonException;

import java.lang.reflect.Type;

public final class JSONUtils {
    public static <T> T fromJson(String json, Class<T> cls) {
        try {
            return JSON.parseObject(json, cls);
        } catch (Exception e) {
            throw JsonException.newInstance();
        }
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        try {
            return JSON.parseObject(json, typeOfT, new Feature[0]);
        } catch (Exception e) {
            throw JsonException.newInstance();
        }
    }

    public static String toJson(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            throw JsonException.newInstance();
        }
    }
}