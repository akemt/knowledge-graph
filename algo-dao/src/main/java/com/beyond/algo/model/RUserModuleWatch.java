package com.beyond.algo.model;

public class RUserModuleWatch {
    private String wtcsn;

    private String usrsn;

    private String modsn;

    private String wtchasstar;

    private String wtchasfollow;

    public String getWtcsn() {
        return wtcsn;
    }

    public void setWtcsn(String wtcsn) {
        this.wtcsn = wtcsn == null ? null : wtcsn.trim();
    }

    public String getUsrsn() {
        return usrsn;
    }

    public void setUsrsn(String usrsn) {
        this.usrsn = usrsn == null ? null : usrsn.trim();
    }

    public String getModsn() {
        return modsn;
    }

    public void setModsn(String modsn) {
        this.modsn = modsn == null ? null : modsn.trim();
    }

    public String getWtchasstar() {
        return wtchasstar;
    }

    public void setWtchasstar(String wtchasstar) {
        this.wtchasstar = wtchasstar == null ? null : wtchasstar.trim();
    }

    public String getWtchasfollow() {
        return wtchasfollow;
    }

    public void setWtchasfollow(String wtchasfollow) {
        this.wtchasfollow = wtchasfollow == null ? null : wtchasfollow.trim();
    }
}