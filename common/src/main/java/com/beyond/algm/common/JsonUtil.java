package com.beyond.algm.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.beyond.algm.common.Assert.isNULL;

/**
 * Jackson 格式化工具
 * Created by qihe on 2017/10/27
 */
public class JsonUtil {

    /**
     * 数据转化为json格式
     *
     * @param data 待转换的数据
     * @return
     */
    public static String toJson(Object data) {

        if(isNULL(data)){
            return "";
        }

        try {
            return writeObjectToJson(data);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 把json串转化为对象
     *
     * @param json  json数据
     * @param clazz 生成对象的类型
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String json, Class<T> clazz) {

        try {
            return readValueToObject(json, clazz);
        } catch (Exception e) {

        }

        return null;

    }

    public static String writeObjectToJson(Object object) throws Exception {
        if (Assert.isEmpty(object)) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T readValueToObject(String json, Class<T> tClass) throws Exception {
        if (Assert.isEmpty(json)) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tClass);
    }

}
