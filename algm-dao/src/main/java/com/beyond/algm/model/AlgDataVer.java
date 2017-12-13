package com.beyond.algm.model;

public class AlgDataVer {
    private String dataVerSn;

    private String dataSn;

    private String verSn;

    public String getDataVerSn() {
        return dataVerSn;
    }

    public void setDataVerSn(String dataVerSn) {
        this.dataVerSn = dataVerSn == null ? null : dataVerSn.trim();
    }

    public String getDataSn() {
        return dataSn;
    }

    public void setDataSn(String dataSn) {
        this.dataSn = dataSn == null ? null : dataSn.trim();
    }

    public String getVerSn() {
        return verSn;
    }

    public void setVerSn(String verSn) {
        this.verSn = verSn == null ? null : verSn.trim();
    }
}