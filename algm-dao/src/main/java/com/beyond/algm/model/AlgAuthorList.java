package com.beyond.algm.model;

public class AlgAuthorList {
    private Integer id;

    private String aid;

    private String fullname;

    private String simname;

    private String sid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getSimname() {
        return simname;
    }

    public void setSimname(String simname) {
        this.simname = simname == null ? null : simname.trim();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }
}