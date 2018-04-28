package com.hiekn.plantdata.exception;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1381325479896057076L;
    private Integer code;

    public BaseException(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}