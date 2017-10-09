package com.beyond.algo.model;

public class AlgArticle {
    private String atlSn;

    private String title;

    private String remark;

    private String keywords;

    private String doi;

    private String author;

    private String citeInfo;

    public String getAtlSn() {
        return atlSn;
    }

    public void setAtlSn(String atlSn) {
        this.atlSn = atlSn == null ? null : atlSn.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi == null ? null : doi.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getCiteInfo() {
        return citeInfo;
    }

    public void setCiteInfo(String citeInfo) {
        this.citeInfo = citeInfo == null ? null : citeInfo.trim();
    }
}