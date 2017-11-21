package com.beyond.algm.model;

public class AlgSrcFile {
    private String sflSn;

    private String sflName;

    private String sflUrl;

    public String getSflSn() {
        return sflSn;
    }

    public void setSflSn(String sflSn) {
        this.sflSn = sflSn == null ? null : sflSn.trim();
    }

    public String getSflName() {
        return sflName;
    }

    public void setSflName(String sflName) {
        this.sflName = sflName == null ? null : sflName.trim();
    }

    public String getSflUrl() {
        return sflUrl;
    }

    public void setSflUrl(String sflUrl) {
        this.sflUrl = sflUrl == null ? null : sflUrl.trim();
    }
}