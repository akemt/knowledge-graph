package com.beyond.algo.dao.model;

public class Authcodedomain {
    private String addsn;

    private String acdsn;

    private String addurl;

    public String getAddsn() {
        return addsn;
    }

    public void setAddsn(String addsn) {
        this.addsn = addsn == null ? null : addsn.trim();
    }

    public String getAcdsn() {
        return acdsn;
    }

    public void setAcdsn(String acdsn) {
        this.acdsn = acdsn == null ? null : acdsn.trim();
    }

    public String getAddurl() {
        return addurl;
    }

    public void setAddurl(String addurl) {
        this.addurl = addurl == null ? null : addurl.trim();
    }
}