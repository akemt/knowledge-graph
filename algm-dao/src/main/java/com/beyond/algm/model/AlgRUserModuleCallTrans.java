package com.beyond.algm.model;

import java.util.Date;

public class AlgRUserModuleCallTrans {
    private String umcSn;

    private String verSn;

    private String callUsrSn;

    private String ownerUsrSn;

    private Date startTime;

    private Date endTime;

    private Long duration;

    private Long billedCallCnt;

    private Float callPayAmount;

    private Long modCallCnt;

    public String getUmcSn() {
        return umcSn;
    }

    public void setUmcSn(String umcSn) {
        this.umcSn = umcSn == null ? null : umcSn.trim();
    }

    public String getVerSn() {
        return verSn;
    }

    public void setVerSn(String verSn) {
        this.verSn = verSn == null ? null : verSn.trim();
    }

    public String getCallUsrSn() {
        return callUsrSn;
    }

    public void setCallUsrSn(String callUsrSn) {
        this.callUsrSn = callUsrSn == null ? null : callUsrSn.trim();
    }

    public String getOwnerUsrSn() {
        return ownerUsrSn;
    }

    public void setOwnerUsrSn(String ownerUsrSn) {
        this.ownerUsrSn = ownerUsrSn == null ? null : ownerUsrSn.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getBilledCallCnt() {
        return billedCallCnt;
    }

    public void setBilledCallCnt(Long billedCallCnt) {
        this.billedCallCnt = billedCallCnt;
    }

    public Float getCallPayAmount() {
        return callPayAmount;
    }

    public void setCallPayAmount(Float callPayAmount) {
        this.callPayAmount = callPayAmount;
    }

    public Long getModCallCnt() {
        return modCallCnt;
    }

    public void setModCallCnt(Long modCallCnt) {
        this.modCallCnt = modCallCnt;
    }
}