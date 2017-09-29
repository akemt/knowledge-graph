package com.beyond.algo.model;

import java.math.BigDecimal;
import java.util.Date;

public class RUserModuleCalltrans {
    private String umcsn;

    private String versn;

    private String usrsn;

    private Date starttime;

    private Date endtime;

    private Date duration;

    private Integer billedcallcnt;

    private BigDecimal callpayamount;

    private Integer modcallcnt;

    public String getUmcsn() {
        return umcsn;
    }

    public void setUmcsn(String umcsn) {
        this.umcsn = umcsn == null ? null : umcsn.trim();
    }

    public String getVersn() {
        return versn;
    }

    public void setVersn(String versn) {
        this.versn = versn == null ? null : versn.trim();
    }

    public String getUsrsn() {
        return usrsn;
    }

    public void setUsrsn(String usrsn) {
        this.usrsn = usrsn == null ? null : usrsn.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Integer getBilledcallcnt() {
        return billedcallcnt;
    }

    public void setBilledcallcnt(Integer billedcallcnt) {
        this.billedcallcnt = billedcallcnt;
    }

    public BigDecimal getCallpayamount() {
        return callpayamount;
    }

    public void setCallpayamount(BigDecimal callpayamount) {
        this.callpayamount = callpayamount;
    }

    public Integer getModcallcnt() {
        return modcallcnt;
    }

    public void setModcallcnt(Integer modcallcnt) {
        this.modcallcnt = modcallcnt;
    }
}