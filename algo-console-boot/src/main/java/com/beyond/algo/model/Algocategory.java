package com.beyond.algo.model;

public class Algocategory {
    private String catsn;

    private String catname;

    public String getCatsn() {
        return catsn;
    }

    public void setCatsn(String catsn) {
        this.catsn = catsn == null ? null : catsn.trim();
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname == null ? null : catname.trim();
    }
}