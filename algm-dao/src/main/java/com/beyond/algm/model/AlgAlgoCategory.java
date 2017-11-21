package com.beyond.algm.model;

public class AlgAlgoCategory {
    private String catSn;

    private String catName;

    private String modSn;

    private String catSort;

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

    public String getModSn() {
        return modSn;
    }

    public void setModSn(String modSn) {
        this.modSn = modSn == null ? null : modSn.trim();
    }

    public String getCatSort() {
        return catSort;
    }

    public void setCatSort(String catSort) {
        this.catSort = catSort;
    }
}