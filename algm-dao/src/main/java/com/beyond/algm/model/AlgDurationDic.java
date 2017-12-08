package com.beyond.algm.model;

public class AlgDurationDic {
    private String dicSn;

    private String buyDate;

    public String getDicSn() {
        return dicSn;
    }

    public void setDicSn(String dicSn) {
        this.dicSn = dicSn == null ? null : dicSn.trim();
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate == null ? null : buyDate.trim();
    }
}