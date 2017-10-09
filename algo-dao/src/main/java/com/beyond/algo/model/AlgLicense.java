package com.beyond.algo.model;

public class AlgLicense {
    private String licSn;

    private String licName;

    private String licContent;

    public String getLicSn() {
        return licSn;
    }

    public void setLicSn(String licSn) {
        this.licSn = licSn == null ? null : licSn.trim();
    }

    public String getLicName() {
        return licName;
    }

    public void setLicName(String licName) {
        this.licName = licName == null ? null : licName.trim();
    }

    public String getLicContent() {
        return licContent;
    }

    public void setLicContent(String licContent) {
        this.licContent = licContent == null ? null : licContent.trim();
    }
}