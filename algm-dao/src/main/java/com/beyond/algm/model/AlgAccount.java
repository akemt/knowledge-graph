package com.beyond.algm.model;

import java.util.Date;

public class AlgAccount {
    private String accSn;

    private String usrSn;

    private Float cashBal;

    private Float earnBal;

    private Float freeBal;

    private Date freeSetTime;

    public String getAccSn() {
        return accSn;
    }

    public void setAccSn(String accSn) {
        this.accSn = accSn == null ? null : accSn.trim();
    }

    public String getUsrSn() {
        return usrSn;
    }

    public void setUsrSn(String usrSn) {
        this.usrSn = usrSn == null ? null : usrSn.trim();
    }

    public Float getCashBal() {
        return cashBal;
    }

    public void setCashBal(Float cashBal) {
        this.cashBal = cashBal;
    }

    public Float getEarnBal() {
        return earnBal;
    }

    public void setEarnBal(Float earnBal) {
        this.earnBal = earnBal;
    }

    public Float getFreeBal() {
        return freeBal;
    }

    public void setFreeBal(Float freeBal) {
        this.freeBal = freeBal;
    }

    public Date getFreeSetTime() {
        return freeSetTime;
    }

    public void setFreeSetTime(Date freeSetTime) {
        this.freeSetTime = freeSetTime;
    }
}