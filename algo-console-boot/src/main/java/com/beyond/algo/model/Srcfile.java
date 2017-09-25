package com.beyond.algo.model;

public class Srcfile {
    private String sflsn;

    private String sflname;

    private String sflurl;

    public String getSflsn() {
        return sflsn;
    }

    public void setSflsn(String sflsn) {
        this.sflsn = sflsn == null ? null : sflsn.trim();
    }

    public String getSflname() {
        return sflname;
    }

    public void setSflname(String sflname) {
        this.sflname = sflname == null ? null : sflname.trim();
    }

    public String getSflurl() {
        return sflurl;
    }

    public void setSflurl(String sflurl) {
        this.sflurl = sflurl == null ? null : sflurl.trim();
    }
}