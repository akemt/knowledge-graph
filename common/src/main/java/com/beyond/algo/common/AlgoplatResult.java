package com.beyond.algo.common;

import java.io.Serializable;

/**
 * @author ：zhangchuanzhi
 * @Description:定义共同返回结果
 * @date ：15:09 2017/9/25
 */

    public class AlgoplatResult<T> implements Serializable {

        private static final long serialVersionUID = 2488663702267110932L;
        private int code;
        private String msg;
        private String detail;
        private T data;

        public static AlgoplatResult<BaseEnum> successResponse() {
            return new AlgoplatResult<BaseEnum>(BaseEnum.SUCCESS);
        }

        public static AlgoplatResult<BaseEnum> failureResponse() {
            return new AlgoplatResult<BaseEnum>(BaseEnum.FAILURE);
        }

        public AlgoplatResult() {
            this.code = BaseEnum.SUCCESS.code;
            this.msg = BaseEnum.SUCCESS.msg;
        }

        public AlgoplatResult(T data) {
            this.code = BaseEnum.SUCCESS.code;
            this.msg = BaseEnum.SUCCESS.msg;
            this.data = data;
        }

        public AlgoplatResult(T data, String msg) {
            this.data = data;
            this.msg = msg;
        }
        public AlgoplatResult(T data, int code, String msg) {
            this.data = data;
            this.code = code;
            this.msg = msg;
        }

        public AlgoplatResult(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public AlgoplatResult(int code, String msg, String detail) {
            this.code = code;
            this.msg = msg;
            this.detail = detail;
        }

        public static <T> AlgoplatResult<T> illegalArg(T data) {
            return new AlgoplatResult<T>(data,BaseEnum.FAILURE.code, BaseEnum.PARAM_ERROR.msg);
        }

        public static <T> AlgoplatResult<T> ok(T data) {
            return new AlgoplatResult<T>(data, BaseEnum.SUCCESS.code, BaseEnum.SUCCESS.msg);
        }

        public static <T> AlgoplatResult<T> failure(T data) {
            return new AlgoplatResult<T>(data,  BaseEnum.FAILURE.code,BaseEnum.FAILURE.msg);
        }


        public int getCode() {
            return this.code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDetail() {
            return this.detail;
        }

        public <T extends AlgoplatResult> T addDetail(String detail) {
            this.setDetail(detail);
            return (T) this;
        }

        public T getData() {
            return this.data;
        }

        public void setData(T data) {
            this.data = data;
        }


}
