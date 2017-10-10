package com.beyond.algo.model;

import java.util.Date;

public class AlgCashTrans {
    private String traSn;

    private String usrSn;

    private Date payTime;

    private String payNo;

    private String status;

    private String paySrc;

    private Float cashPayAmount;

    private String currency;

    private Float creditPerUnit;

    private Date createTime;

    public String getTraSn() {
        return traSn;
    }

    public void setTraSn(String traSn) {
        this.traSn = traSn == null ? null : traSn.trim();
    }

    public String getUsrSn() {
        return usrSn;
    }

    public void setUsrSn(String usrSn) {
        this.usrSn = usrSn == null ? null : usrSn.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPaySrc() {
        return paySrc;
    }

    public void setPaySrc(String paySrc) {
        this.paySrc = paySrc == null ? null : paySrc.trim();
    }

    public Float getCashPayAmount() {
        return cashPayAmount;
    }

    public void setCashPayAmount(Float cashPayAmount) {
        this.cashPayAmount = cashPayAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Float getCreditPerUnit() {
        return creditPerUnit;
    }

    public void setCreditPerUnit(Float creditPerUnit) {
        this.creditPerUnit = creditPerUnit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}