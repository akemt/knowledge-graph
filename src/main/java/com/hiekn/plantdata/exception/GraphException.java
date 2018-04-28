package com.hiekn.plantdata.exception;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class GraphException extends Exception {

    public GraphException(String message) {
        super(message);
    }

    /**
     * @param message 错误信息/错误编码
     * @param params  占位符使用的参数
     */
    public GraphException(String message, Object[] params) {
        super(message + Constant.SCRIPT_DELIMIT + toJson(params));
    }

    /**
     * @param message 错误信息/错误编码
     * @param params  占位符使用的参数
     */
    public GraphException(String message, List<String> params) {
        super(message + Constant.SCRIPT_DELIMIT + toJson(params));
    }

    /**
     * @param message 错误信息/错误编码
     * @param params  占位符使用的参数
     * @param cause   异常对象
     */
    public GraphException(String message, Object[] params, Throwable cause) {
        super(message + Constant.SCRIPT_DELIMIT + toJson(params), cause);
    }

    public GraphException(Throwable cause) {
        super(cause);
    }

    public GraphException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 数据转化为JSON格式(jackson)
     *
     * @param data
     * @return
     */
    private static String toJson(Object data) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
        }
        return null;
    }

}
