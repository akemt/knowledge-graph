package com.beyond.algo.model;

public class AlgAlgoCategory {
    private String catSn;

    private String catName;

    public String getCatSn() {
        return catSn;
    }

    public void setCatSn(String catSn) {
        this.catSn = catSn == null ? null : catSn.trim();
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }
}