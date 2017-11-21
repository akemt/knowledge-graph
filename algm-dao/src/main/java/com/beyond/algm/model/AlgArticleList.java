package com.beyond.algm.model;

public class AlgArticleList {
    private Integer id;

    private String sid;

    private String doi;

    private String title;

    private String date;

    private String journalname;

    private String articletype;

    private String citation;

    private String papertext;

    private String summary;

    private String keywords;

    private String bibtex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi == null ? null : doi.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getJournalname() {
        return journalname;
    }

    public void setJournalname(String journalname) {
        this.journalname = journalname == null ? null : journalname.trim();
    }

    public String getArticletype() {
        return articletype;
    }

    public void setArticletype(String articletype) {
        this.articletype = articletype == null ? null : articletype.trim();
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation == null ? null : citation.trim();
    }

    public String getPapertext() {
        return papertext;
    }

    public void setPapertext(String papertext) {
        this.papertext = papertext == null ? null : papertext.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getBibtex() {
        return bibtex;
    }

    public void setBibtex(String bibtex) {
        this.bibtex = bibtex == null ? null : bibtex.trim();
    }
}