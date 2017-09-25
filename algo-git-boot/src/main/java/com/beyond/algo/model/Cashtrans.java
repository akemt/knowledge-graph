package com.beyond.algo.model;

import java.math.BigDecimal;
import java.util.Date;

public class Cashtrans {
    private String trasn;

    private String usrsn;

    private Date paytime;

    private String paysrc;

    private BigDecimal cashpayamount;

    private String currency;

    private BigDecimal creditperunit;

    private Date createtime;

    public String getTrasn() {
        return trasn;
    }

    public void setTrasn(String trasn) {
        this.trasn = trasn == null ? null : trasn.trim();
    }

    public String getUsrsn() {
        return usrsn;
    }

    public void setUsrsn(String usrsn) {
        this.usrsn = usrsn == null ? null : usrsn.trim();
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getPaysrc() {
        return paysrc;
    }

    public void setPaysrc(String paysrc) {
        this.paysrc = paysrc == null ? null : paysrc.trim();
    }

    public BigDecimal getCashpayamount() {
        return cashpayamount;
    }

    public void setCashpayamount(BigDecimal cashpayamount) {
        this.cashpayamount = cashpayamount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public BigDecimal getCreditperunit() {
        return creditperunit;
    }

    public void setCreditperunit(BigDecimal creditperunit) {
        this.creditperunit = creditperunit;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}