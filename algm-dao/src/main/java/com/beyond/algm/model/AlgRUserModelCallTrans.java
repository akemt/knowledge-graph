package com.beyond.algm.model;

import java.util.Date;

public class AlgRUserModelCallTrans {
    private String umcSn;

    private String modelSn;

    private String callUsrSn;

    private String ownerUsrSn;

    private Date startTime;

    private Date endTime;

    private Long duration;

    private Float callPayAmount;

    private Date creatTime;

    public String getUmcSn() {
        return umcSn;
    }

    public void setUmcSn(String umcSn) {
        this.umcSn = umcSn == null ? null : umcSn.trim();
    }

    public String getModelSn() {
        return modelSn;
    }

    public void setModelSn(String modelSn) {
        this.modelSn = modelSn == null ? null : modelSn.trim();
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

    public Float getCallPayAmount() {
        return callPayAmount;
    }

    public void setCallPayAmount(Float callPayAmount) {
        this.callPayAmount = callPayAmount;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}