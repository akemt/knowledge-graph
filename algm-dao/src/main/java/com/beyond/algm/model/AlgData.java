package com.beyond.algm.model;

import java.util.Date;

public class AlgData {
    private String dataSn;

    private String dataSetSn;

    private String usrSn;

    private String dataName;

    private String dataEnName;

    private Date creatTime;

    private String dataSize;

    private String dataAddr;

    private String dataLoc;

    private String isOpenSrc;

    private String dataAbs;

    private String dataContent;

    private Integer dataCredit;

    public String getDataSn() {
        return dataSn;
    }

    public void setDataSn(String dataSn) {
        this.dataSn = dataSn == null ? null : dataSn.trim();
    }

    public String getDataSetSn() {
        return dataSetSn;
    }

    public void setDataSetSn(String dataSetSn) {
        this.dataSetSn = dataSetSn == null ? null : dataSetSn.trim();
    }

    public String getUsrSn() {
        return usrSn;
    }

    public void setUsrSn(String usrSn) {
        this.usrSn = usrSn == null ? null : usrSn.trim();
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public String getDataEnName() {
        return dataEnName;
    }

    public void setDataEnName(String dataEnName) {
        this.dataEnName = dataEnName == null ? null : dataEnName.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize == null ? null : dataSize.trim();
    }

    public String getDataAddr() {
        return dataAddr;
    }

    public void setDataAddr(String dataAddr) {
        this.dataAddr = dataAddr == null ? null : dataAddr.trim();
    }

    public String getDataLoc() {
        return dataLoc;
    }

    public void setDataLoc(String dataLoc) {
        this.dataLoc = dataLoc == null ? null : dataLoc.trim();
    }

    public String getIsOpenSrc() {
        return isOpenSrc;
    }

    public void setIsOpenSrc(String isOpenSrc) {
        this.isOpenSrc = isOpenSrc == null ? null : isOpenSrc.trim();
    }

    public String getDataAbs() {
        return dataAbs;
    }

    public void setDataAbs(String dataAbs) {
        this.dataAbs = dataAbs == null ? null : dataAbs.trim();
    }

    public String getDataContent() {
        return dataContent;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent == null ? null : dataContent.trim();
    }

    public Integer getDataCredit() {
        return dataCredit;
    }

    public void setDataCredit(Integer dataCredit) {
        this.dataCredit = dataCredit;
    }
}