package com.beyond.algo.dao.model;

public class Versionusage {
    private String usgsn;

    private String modsn;

    private Integer starcnt;

    private Integer followcnt;

    private Integer callcnt;

    private Long creditcnt;

    public String getUsgsn() {
        return usgsn;
    }

    public void setUsgsn(String usgsn) {
        this.usgsn = usgsn == null ? null : usgsn.trim();
    }

    public String getModsn() {
        return modsn;
    }

    public void setModsn(String modsn) {
        this.modsn = modsn == null ? null : modsn.trim();
    }

    public Integer getStarcnt() {
        return starcnt;
    }

    public void setStarcnt(Integer starcnt) {
        this.starcnt = starcnt;
    }

    public Integer getFollowcnt() {
        return followcnt;
    }

    public void setFollowcnt(Integer followcnt) {
        this.followcnt = followcnt;
    }

    public Integer getCallcnt() {
        return callcnt;
    }

    public void setCallcnt(Integer callcnt) {
        this.callcnt = callcnt;
    }

    public Long getCreditcnt() {
        return creditcnt;
    }

    public void setCreditcnt(Long creditcnt) {
        this.creditcnt = creditcnt;
    }
}