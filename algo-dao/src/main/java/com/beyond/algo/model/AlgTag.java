package com.beyond.algo.model;

public class AlgTag {
    private String tagSn;

    private String tagName;

    public String getTagSn() {
        return tagSn;
    }

    public void setTagSn(String tagSn) {
        this.tagSn = tagSn == null ? null : tagSn.trim();
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }
}