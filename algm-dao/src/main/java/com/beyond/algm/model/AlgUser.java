package com.beyond.algm.model;

import java.util.Date;

public class AlgUser {
    private String usrSn;

    private String ownerId;

    private String usrName;

    private String usrCode;

    private String usrUrl;

    private String isOrg;

    private String email;

    private String passwd;

    private String telephone;

    private String needNotify;

    private Float usrSpace;

    private Float usrAddSpace;

    private Float usrUsedSpace;

    private Date createDate;

    private Date updateDate;

    private String privateToken;

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

    public String getUsrSn() {
        return usrSn;
    }

    public void setUsrSn(String usrSn) {
        this.usrSn = usrSn == null ? null : usrSn.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName == null ? null : usrName.trim();
    }

    public String getUsrCode() {
        return usrCode;
    }

    public void setUsrCode(String usrCode) {
        this.usrCode = usrCode == null ? null : usrCode.trim();
    }

    public String getUsrUrl() {
        return usrUrl;
    }

    public void setUsrUrl(String usrUrl) {
        this.usrUrl = usrUrl == null ? null : usrUrl.trim();
    }

    public String getIsOrg() {
        return isOrg;
    }

    public void setIsOrg(String isOrg) {
        this.isOrg = isOrg == null ? null : isOrg.trim();
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getNeedNotify() {
        return needNotify;
    }

    public void setNeedNotify(String needNotify) {
        this.needNotify = needNotify == null ? null : needNotify.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Float getUsrSpace() {
        return usrSpace;
    }

    public void setUsrSpace(Float usrSpace) {
        this.usrSpace = usrSpace;
    }

    public Float getUsrAddSpace() {
        return usrAddSpace;
    }

    public void setUsrAddSpace(Float usrAddSpace) {
        this.usrAddSpace = usrAddSpace;
    }

    public Float getUsrUsedSpace() {
        return usrUsedSpace;
    }

    public void setUsrUsedSpace(Float usrUsedSpace) {
        this.usrUsedSpace = usrUsedSpace;
    }
}