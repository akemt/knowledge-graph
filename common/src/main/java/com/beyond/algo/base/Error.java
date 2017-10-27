package com.beyond.algo.base;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/10/27 11:45
 */
public class Error {

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 错误详细信息描述
     */
    private Object detail;


    public Error() {
    }

    public Error(String errorCode) {
        this.errorCode = errorCode;
    }

    public Error(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Error(String errorCode, String errorMessage, Object detail) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.detail = detail;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }
}
