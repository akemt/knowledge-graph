package com.beyond.algo.model;

public class AlgAuthCodeDomain {
    private String addSn;

    private String acdSn;

    private String addUrl;

    public String getAddSn() {
        return addSn;
    }

    public void setAddSn(String addSn) {
        this.addSn = addSn == null ? null : addSn.trim();
    }

    public String getAcdSn() {
        return acdSn;
    }

    public void setAcdSn(String acdSn) {
        this.acdSn = acdSn == null ? null : acdSn.trim();
    }

    public String getAddUrl() {
        return addUrl;
    }

    public void setAddUrl(String addUrl) {
        this.addUrl = addUrl == null ? null : addUrl.trim();
    }
}