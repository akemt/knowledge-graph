package com.beyond.algo.common;

/**
 * @author ：zhangchuanzh
 * @Description:定义枚举类型
 * @date ：15:10 2017/9/25
 */
public enum BaseEnum {
    SUCCESS(200, "成功"),
    FAILURE(201, "失败"),
    USER_NOT_LOGIN(202, "用户未登录"),
    NO_PERMISSION(401, "无此操作权限"),
    NO_RECORD(402, "查询无记录"),
    OBJECT_EXISTED(409, "对象已经存在"),
    PARAM_ERROR(501, "参数错误"),
    BUSINESS_ILLEGAL_OP(502, "非法的操作"),
    SESSION_EXPIRED(503, "session失效"),
    UNKNOWN_ERROR(504, "未知错误"),
    UNKNOWN_REQUEST(505, "未知请求");

    public int code;
    public String msg;

    private BaseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
