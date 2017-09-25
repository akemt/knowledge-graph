package com.beyond.algo.model;

public class Authcode {
    private String acdsn;

    private String usrsn;

    private String acdname;

    private String acdid;

    private String callfromclient;

    private String callfrombrowser;

    private String restricturl;

    private String datausetype;

    public String getAcdsn() {
        return acdsn;
    }

    public void setAcdsn(String acdsn) {
        this.acdsn = acdsn == null ? null : acdsn.trim();
    }

    public String getUsrsn() {
        return usrsn;
    }

    public void setUsrsn(String usrsn) {
        this.usrsn = usrsn == null ? null : usrsn.trim();
    }

    public String getAcdname() {
        return acdname;
    }

    public void setAcdname(String acdname) {
        this.acdname = acdname == null ? null : acdname.trim();
    }

    public String getAcdid() {
        return acdid;
    }

    public void setAcdid(String acdid) {
        this.acdid = acdid == null ? null : acdid.trim();
    }

    public String getCallfromclient() {
        return callfromclient;
    }

    public void setCallfromclient(String callfromclient) {
        this.callfromclient = callfromclient == null ? null : callfromclient.trim();
    }

    public String getCallfrombrowser() {
        return callfrombrowser;
    }

    public void setCallfrombrowser(String callfrombrowser) {
        this.callfrombrowser = callfrombrowser == null ? null : callfrombrowser.trim();
    }

    public String getRestricturl() {
        return restricturl;
    }

    public void setRestricturl(String restricturl) {
        this.restricturl = restricturl == null ? null : restricturl.trim();
    }

    public String getDatausetype() {
        return datausetype;
    }

    public void setDatausetype(String datausetype) {
        this.datausetype = datausetype == null ? null : datausetype.trim();
    }
}