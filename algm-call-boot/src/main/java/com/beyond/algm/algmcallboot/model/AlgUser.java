package com.beyond.algm.algmcallboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "alg_user")
public class AlgUser {
    @Id
    @GeneratedValue(strategy = IDENTITY)
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

    private Date createDate;

    private Date updateDate;

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

    @Override
    public String toString() {
        return "AlgUser{" +
                "usrSn='" + usrSn + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", usrName='" + usrName + '\'' +
                ", usrCode='" + usrCode + '\'' +
                ", usrUrl='" + usrUrl + '\'' +
                ", isOrg='" + isOrg + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", telephone='" + telephone + '\'' +
                ", needNotify='" + needNotify + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}