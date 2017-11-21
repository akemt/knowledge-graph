package com.beyond.algm.model;

public class AlgRUserModuleWatch {
    private String wtcSn;

    private String usrSn;

    private String modSn;

    private String wtcHasStar;

    private String wtcHasFollow;

    public String getWtcSn() {
        return wtcSn;
    }

    public void setWtcSn(String wtcSn) {
        this.wtcSn = wtcSn == null ? null : wtcSn.trim();
    }

    public String getUsrSn() {
        return usrSn;
    }

    public void setUsrSn(String usrSn) {
        this.usrSn = usrSn == null ? null : usrSn.trim();
    }

    public String getModSn() {
        return modSn;
    }

    public void setModSn(String modSn) {
        this.modSn = modSn == null ? null : modSn.trim();
    }

    public String getWtcHasStar() {
        return wtcHasStar;
    }

    public void setWtcHasStar(String wtcHasStar) {
        this.wtcHasStar = wtcHasStar == null ? null : wtcHasStar.trim();
    }

    public String getWtcHasFollow() {
        return wtcHasFollow;
    }

    public void setWtcHasFollow(String wtcHasFollow) {
        this.wtcHasFollow = wtcHasFollow == null ? null : wtcHasFollow.trim();
    }
}