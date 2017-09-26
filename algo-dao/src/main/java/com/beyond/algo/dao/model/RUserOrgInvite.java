package com.beyond.algo.dao.model;

public class RUserOrgInvite {
    private String invsn;

    private String orgsn;

    private String usrsn;

    private String useremail;

    private String ispend;

    private String isolduser;

    public String getInvsn() {
        return invsn;
    }

    public void setInvsn(String invsn) {
        this.invsn = invsn == null ? null : invsn.trim();
    }

    public String getOrgsn() {
        return orgsn;
    }

    public void setOrgsn(String orgsn) {
        this.orgsn = orgsn == null ? null : orgsn.trim();
    }

    public String getUsrsn() {
        return usrsn;
    }

    public void setUsrsn(String usrsn) {
        this.usrsn = usrsn == null ? null : usrsn.trim();
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail == null ? null : useremail.trim();
    }

    public String getIspend() {
        return ispend;
    }

    public void setIspend(String ispend) {
        this.ispend = ispend == null ? null : ispend.trim();
    }

    public String getIsolduser() {
        return isolduser;
    }

    public void setIsolduser(String isolduser) {
        this.isolduser = isolduser == null ? null : isolduser.trim();
    }
}