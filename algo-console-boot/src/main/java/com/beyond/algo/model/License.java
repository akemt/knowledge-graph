package com.beyond.algo.model;

public class License {
    private String licsn;

    private String licname;

    private String liccontent;

    public String getLicsn() {
        return licsn;
    }

    public void setLicsn(String licsn) {
        this.licsn = licsn == null ? null : licsn.trim();
    }

    public String getLicname() {
        return licname;
    }

    public void setLicname(String licname) {
        this.licname = licname == null ? null : licname.trim();
    }

    public String getLiccontent() {
        return liccontent;
    }

    public void setLiccontent(String liccontent) {
        this.liccontent = liccontent == null ? null : liccontent.trim();
    }
}