package com.beyond.algo.dao.model;

public class Tag {
    private String tagsn;

    private String modsn;

    private String tagvalue;

    public String getTagsn() {
        return tagsn;
    }

    public void setTagsn(String tagsn) {
        this.tagsn = tagsn == null ? null : tagsn.trim();
    }

    public String getModsn() {
        return modsn;
    }

    public void setModsn(String modsn) {
        this.modsn = modsn == null ? null : modsn.trim();
    }

    public String getTagvalue() {
        return tagvalue;
    }

    public void setTagvalue(String tagvalue) {
        this.tagvalue = tagvalue == null ? null : tagvalue.trim();
    }
}