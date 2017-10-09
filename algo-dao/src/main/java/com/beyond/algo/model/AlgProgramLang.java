package com.beyond.algo.model;

public class AlgProgramLang {
    private String lanSn;

    private String lanName;

    public String getLanSn() {
        return lanSn;
    }

    public void setLanSn(String lanSn) {
        this.lanSn = lanSn == null ? null : lanSn.trim();
    }

    public String getLanName() {
        return lanName;
    }

    public void setLanName(String lanName) {
        this.lanName = lanName == null ? null : lanName.trim();
    }
}