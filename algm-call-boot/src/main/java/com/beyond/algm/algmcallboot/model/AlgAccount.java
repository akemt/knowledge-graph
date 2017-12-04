package com.beyond.algm.algmcallboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "alg_account")
public class AlgAccount {
    @Id
    @GeneratedValue(strategy = IDENTITY)
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

    @Override
    public String toString() {
        return "AlgAccount{" +
                "accSn='" + accSn + '\'' +
                ", usrSn='" + usrSn + '\'' +
                ", cashBal=" + cashBal +
                ", earnBal=" + earnBal +
                ", freeBal=" + freeBal +
                ", freeSetTime=" + freeSetTime +
                '}';
    }
}