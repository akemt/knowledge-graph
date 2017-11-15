package com.beyond.algm.algmcallboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "alg_user_call")
public class AlgUserCall {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long umcSn;

    private String verSn;

    private String algSn;

    private String callUsrSn;

    private String ownerUsrSn;

    private Long startTime;

    private Long endTime;

    private int duration;

    private float callPayAmount;

    public Long getUmcSn() {
        return umcSn;
    }

    public void setUmcSn(Long umcSn) {
        this.umcSn = umcSn;
    }

    public String getVerSn() {
        return verSn;
    }

    public void setVerSn(String verSn) {
        this.verSn = verSn;
    }

    public String getAlgSn() {
        return algSn;
    }

    public void setAlgSn(String algSn) {
        this.algSn = algSn;
    }

    public String getCallUsrSn() {
        return callUsrSn;
    }

    public void setCallUsrSn(String callUsrSn) {
        this.callUsrSn = callUsrSn;
    }

    public String getOwnerUsrSn() {
        return ownerUsrSn;
    }

    public void setOwnerUsrSn(String ownerUsrSn) {
        this.ownerUsrSn = ownerUsrSn;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getCallPayAmount() {
        return callPayAmount;
    }

    public void setCallPayAmount(float callPayAmount) {
        this.callPayAmount = callPayAmount;
    }

    @Override
    public String toString() {
        return "AlgUserCall{" +
                "umcSn='" + umcSn + '\'' +
                ", verSn='" + verSn + '\'' +
                ", algSn='" + algSn + '\'' +
                ", callUsrSn='" + callUsrSn + '\'' +
                ", ownerUsrSn='" + ownerUsrSn + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", callPayAmount=" + callPayAmount +
                '}';
    }
}