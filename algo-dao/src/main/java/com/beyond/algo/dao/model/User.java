package com.beyond.algo.dao.model;

import java.util.Date;

public class User {
    private String usrsn;

    private String ownerid;

    private String usrname;

    private String usrcode;

    private String usrurl;

    private String isorg;

    private String email;

    private String passwd;

    private String neednotify;

    private Date createdate;

    private Date updatedate;

    public String getUsrsn() {
        return usrsn;
    }

    public void setUsrsn(String usrsn) {
        this.usrsn = usrsn == null ? null : usrsn.trim();
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid == null ? null : ownerid.trim();
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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
}