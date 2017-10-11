package com.beyond.algo.model;

import java.util.Date;

public class AlgCashHis {
    private String traSn;

    private String usrSn;

    private String cashSrc;

    private String currency;

    private Float creditPerUnit;

    private Float cash;

    private String custNo;

    private String status;

    private String description;

    private Date cashTime;

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

    public String getCashSrc() {
        return cashSrc;
    }

    public void setCashSrc(String cashSrc) {
        this.cashSrc = cashSrc == null ? null : cashSrc.trim();
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

    public Float getCash() {
        return cash;
    }

    public void setCash(Float cash) {
        this.cash = cash;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo == null ? null : custNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCashTime() {
        return cashTime;
    }

    public void setCashTime(Date cashTime) {
        this.cashTime = cashTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}