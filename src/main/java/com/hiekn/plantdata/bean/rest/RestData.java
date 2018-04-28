package com.hiekn.plantdata.bean.rest;


import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.util.ArrayList;
import java.util.List;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RestData<T> {
    private List<T> rsData;
    private Long rsCount;

    public RestData() {
    }

    public RestData(T data) {
        List rsData = new ArrayList(1);
        rsData.add(data);
        this.rsData = rsData;
    }

    public RestData(List<T> rsData) {
        this.rsData = rsData;
    }

    public RestData(List<T> rsData, Integer count) {
        this(rsData);
        this.rsCount = Long.valueOf(count.intValue());
    }

    public RestData(List<T> rsData, Long count) {
        this(rsData);
        this.rsCount = count;
    }

    public List<T> getRsData() {
        return this.rsData;
    }

    public void setRsData(List<T> rsData) {
        this.rsData = rsData;
    }

    public Long getRsCount() {
        return this.rsCount;
    }

    public void setRsCount(Long rsCount) {
        this.rsCount = rsCount;
    }
}
