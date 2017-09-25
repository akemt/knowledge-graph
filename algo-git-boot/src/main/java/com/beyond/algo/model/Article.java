package com.beyond.algo.model;

public class Article {
    private String atlsn;

    private String title;

    private String abstract;

    private String keywords;

    private String doi;

    private String author;

    private String citeinfo;

    public String getAtlsn() {
        return atlsn;
    }

    public void setAtlsn(String atlsn) {
        this.atlsn = atlsn == null ? null : atlsn.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAbstract() {
        return abstract;
    }

    public void setAbstract(String abstract) {
        this.abstract = abstract == null ? null : abstract.trim();
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

    public String getCiteinfo() {
        return citeinfo;
    }

    public void setCiteinfo(String citeinfo) {
        this.citeinfo = citeinfo == null ? null : citeinfo.trim();
    }
}