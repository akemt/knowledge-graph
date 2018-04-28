package com.hiekn.plantdata.bean.rest;


import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RestResp<T> {
    private Integer code;
    private String msg;
    private RestData<T> data;
    private Long tt;

    public RestResp() {
        this.code = Integer.valueOf(200);
        this.msg = "";
        this.data = null;
    }

    public RestResp(Long tt) {
        this.code = Integer.valueOf(200);
        this.msg = "";
        this.data = null;

        this.tt = tt;
    }

    public RestResp(Integer code, Long tt) {
        this(code, null, tt);
    }

    public RestResp(Integer code, String msg, Long tt) {
        this.code = Integer.valueOf(200);
        this.msg = "";
        this.data = null;

        this.code = code;
        this.msg = ((msg == null) ? RestReturnCode.getMsg(code.intValue()).toString() : msg);
        this.tt = tt;
    }

    public RestResp(T data, Long tt) {
        this(new RestData(data), tt);
    }

    public RestResp(List<T> data, Long tt) {
        this(new RestData(data), tt);
    }

    public RestResp(List<T> data, Long count, Long tt) {
        this(data, tt);
        this.data.setRsCount(count);
    }

    public RestResp(List<T> data, Integer count, Long tt) {
        this(data, tt);
        this.data.setRsCount(Long.valueOf(count.intValue()));
    }

    public RestResp(RestData<T> data, Long tt) {
        this.code = Integer.valueOf(200);
        this.msg = "";
        this.data = null;

        this.data = ((data == null) ? new RestData(new ArrayList()) : data);
        this.tt = tt;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RestData<T> getData() {
        return this.data;
    }

    public void setData(RestData<T> data) {
        this.data = data;
    }

    public Long getTt() {
        return this.tt;
    }

    public void setTt(Long tt) {
        this.tt = tt;
    }
}