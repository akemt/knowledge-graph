package com.beyond.algm.model;

public class AlgRUserOrgInvite {
    private String invSn;

    private String orgSn;

    private String usrSn;

    private String userEmail;

    private String isPend;

    private String isOldUser;

    public String getInvSn() {
        return invSn;
    }

    public void setInvSn(String invSn) {
        this.invSn = invSn == null ? null : invSn.trim();
    }

    public String getOrgSn() {
        return orgSn;
    }

    public void setOrgSn(String orgSn) {
        this.orgSn = orgSn == null ? null : orgSn.trim();
    }

    public String getUsrSn() {
        return usrSn;
    }

    public void setUsrSn(String usrSn) {
        this.usrSn = usrSn == null ? null : usrSn.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getIsPend() {
        return isPend;
    }

    public void setIsPend(String isPend) {
        this.isPend = isPend == null ? null : isPend.trim();
    }

    public String getIsOldUser() {
        return isOldUser;
    }

    public void setIsOldUser(String isOldUser) {
        this.isOldUser = isOldUser == null ? null : isOldUser.trim();
    }
}