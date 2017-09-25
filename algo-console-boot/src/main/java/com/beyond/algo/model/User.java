package com.beyond.algo.model;

public class User {
    private String usrsn;

    private String accsn;

    private String useUsrsn;

    private String pyisn;

    private String usrname;

    private String usrcode;

    private String usrurl;

    private String isorg;

    private String email;

    private String passwd;

    private String neednotify;

    public String getUsrsn() {
        return usrsn;
    }

    public void setUsrsn(String usrsn) {
        this.usrsn = usrsn == null ? null : usrsn.trim();
    }

    public String getAccsn() {
        return accsn;
    }

    public void setAccsn(String accsn) {
        this.accsn = accsn == null ? null : accsn.trim();
    }

    public String getUseUsrsn() {
        return useUsrsn;
    }

    public void setUseUsrsn(String useUsrsn) {
        this.useUsrsn = useUsrsn == null ? null : useUsrsn.trim();
    }

    public String getPyisn() {
        return pyisn;
    }

    public void setPyisn(String pyisn) {
        this.pyisn = pyisn == null ? null : pyisn.trim();
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname == null ? null : usrname.trim();
    }

    public String getUsrcode() {
        return usrcode;
    }

    public void setUsrcode(String usrcode) {
        this.usrcode = usrcode == null ? null : usrcode.trim();
    }

    public String getUsrurl() {
        return usrurl;
    }

    public void setUsrurl(String usrurl) {
        this.usrurl = usrurl == null ? null : usrurl.trim();
    }

    public String getIsorg() {
        return isorg;
    }

    public void setIsorg(String isorg) {
        this.isorg = isorg == null ? null : isorg.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getNeednotify() {
        return neednotify;
    }

    public void setNeednotify(String neednotify) {
        this.neednotify = neednotify == null ? null : neednotify.trim();
    }
}