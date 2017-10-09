package com.beyond.algo.model;

public class AlgModuleUsage {
    private String usgSn;

    private String modSn;

    private Long starCnt;

    private Long followCnt;

    private Long callCnt;

    private Integer creditCnt;

    public String getUsgSn() {
        return usgSn;
    }

    public void setUsgSn(String usgSn) {
        this.usgSn = usgSn == null ? null : usgSn.trim();
    }

    public String getModSn() {
        return modSn;
    }

    public void setModSn(String modSn) {
        this.modSn = modSn == null ? null : modSn.trim();
    }

    public Long getStarCnt() {
        return starCnt;
    }

    public void setStarCnt(Long starCnt) {
        this.starCnt = starCnt;
    }

    public Long getFollowCnt() {
        return followCnt;
    }

    public void setFollowCnt(Long followCnt) {
        this.followCnt = followCnt;
    }

    public Long getCallCnt() {
        return callCnt;
    }

    public void setCallCnt(Long callCnt) {
        this.callCnt = callCnt;
    }

    public Integer getCreditCnt() {
        return creditCnt;
    }

    public void setCreditCnt(Integer creditCnt) {
        this.creditCnt = creditCnt;
    }
}