package com.beyond.algo.model;

public class AlgRModuleTag {
    private String sn;

    private String modSn;

    private String tagSn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getModSn() {
        return modSn;
    }

    public void setModSn(String modSn) {
        this.modSn = modSn == null ? null : modSn.trim();
    }

    public String getTagSn() {
        return tagSn;
    }

    public void setTagSn(String tagSn) {
        this.tagSn = tagSn == null ? null : tagSn.trim();
    }
}