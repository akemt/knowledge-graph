package com.beyond.algo.model;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
    private String accsn;

    private String usrsn;

    private BigDecimal cashbal;

    private BigDecimal earnbal;

    private BigDecimal freebal;

    private Date freesettime;

    public String getAccsn() {
        return accsn;
    }

    public void setAccsn(String accsn) {
        this.accsn = accsn == null ? null : accsn.trim();
    }

    public String getUsrsn() {
        return usrsn;
    }

    public void setUsrsn(String usrsn) {
        this.usrsn = usrsn == null ? null : usrsn.trim();
    }

    public BigDecimal getCashbal() {
        return cashbal;
    }

    public void setCashbal(BigDecimal cashbal) {
        this.cashbal = cashbal;
    }

    public BigDecimal getEarnbal() {
        return earnbal;
    }

    public void setEarnbal(BigDecimal earnbal) {
        this.earnbal = earnbal;
    }

    public BigDecimal getFreebal() {
        return freebal;
    }

    public void setFreebal(BigDecimal freebal) {
        this.freebal = freebal;
    }

    public Date getFreesettime() {
        return freesettime;
    }

    public void setFreesettime(Date freesettime) {
        this.freesettime = freesettime;
    }
}