package com.beyond.algm.model;

public class AlgProgramLang {
    private String lanSn;

    private String lanName;

    private String lanSuffix;

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

    public String getLanSuffix() {
        return lanSuffix;
    }

    public void setLanSuffix(String lanSuffix) {
        this.lanSuffix = lanSuffix == null ? null : lanSuffix.trim();
    }
}