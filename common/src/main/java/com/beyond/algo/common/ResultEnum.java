package com.beyond.algo.common;

/**
 * @author ：zhangchuanzh
 * @Description:定义枚举类型
 * @date ：15:10 2017/9/25
 */
public enum ResultEnum {
    USER_NOT_LOGIN(100, "用户未登录"),
    SESSION_EXPIRED(101, "session失效"),
    // 200-* 请求成功 或成功但是有错误
    SUCCESS(200, "请求成功"),
    NO_RECORD(201, "查询无记录"),
    OBJECT_EXISTED(203, "对象已经存在"),
    PARAM_ERROR(204, "参数错误"),
    FAILURE(205, "请求失败"),

    NO_PERMISSION(300, "无此操作权限"),

    UNKNOWN_ERROR(501, "未知错误"),
    UNKNOWN_REQUEST(502, "未知请求");

    public int code;
    public String msg;

    private ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
