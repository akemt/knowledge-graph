package com.beyond.algo.dao.model;

public class RVersionSrcfile {
    private String sn;

    private String versn;

    private String sflsn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getVersn() {
        return versn;
    }

    public void setVersn(String versn) {
        this.versn = versn == null ? null : versn.trim();
    }

    public String getSflsn() {
        return sflsn;
    }

    public void setSflsn(String sflsn) {
        this.sflsn = sflsn == null ? null : sflsn.trim();
    }
}