package com.beyond.algo.model;

public class Programlang {
    private String lansn;

    private String lanname;

    public String getLansn() {
        return lansn;
    }

    public void setLansn(String lansn) {
        this.lansn = lansn == null ? null : lansn.trim();
    }

    public String getLanname() {
        return lanname;
    }

    public void setLanname(String lanname) {
        this.lanname = lanname == null ? null : lanname.trim();
    }
}