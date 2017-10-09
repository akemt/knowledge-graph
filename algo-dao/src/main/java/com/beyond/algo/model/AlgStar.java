package com.beyond.algo.model;

import java.util.Date;

public class AlgStar {
    private String starSn;

    private String modSn;

    private String usrSn;

    private Date creatTime;

    public String getStarSn() {
        return starSn;
    }

    public void setStarSn(String starSn) {
        this.starSn = starSn == null ? null : starSn.trim();
    }

    public String getModSn() {
        return modSn;
    }

    public void setModSn(String modSn) {
        this.modSn = modSn == null ? null : modSn.trim();
    }

    public String getUsrSn() {
        return usrSn;
    }

    public void setUsrSn(String usrSn) {
        this.usrSn = usrSn == null ? null : usrSn.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}