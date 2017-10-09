package com.beyond.algo.model;

public class AlgRVersionSrcFile {
    private String sn;

    private String verSn;

    private String sflSn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getVerSn() {
        return verSn;
    }

    public void setVerSn(String verSn) {
        this.verSn = verSn == null ? null : verSn.trim();
    }

    public String getSflSn() {
        return sflSn;
    }

    public void setSflSn(String sflSn) {
        this.sflSn = sflSn == null ? null : sflSn.trim();
    }
}