package com.beyond.algm.model;

import java.util.Date;

public class AlgModel {
    private String modelSn;

    private String datSn;

    private String usrSn;

    private String modelSetSn;

    private String verSn;

    private Date createTime;

    private String modelAddress;

    private String modelName;

    private String modelEnName;

    private String modelSize;

    private String isOpenSrc;

    private String comment;

    private Integer  modelCredit;

    private String modelContent;
    public String getModelSn() {
        return modelSn;
    }

    public void setModelSn(String modelSn) {
        this.modelSn = modelSn == null ? null : modelSn.trim();
    }

    public String getDatSn() {
        return datSn;
    }

    public void setDatSn(String datSn) {
        this.datSn = datSn == null ? null : datSn.trim();
    }

    public String getUsrSn() {
        return usrSn;
    }

    public void setUsrSn(String usrSn) {
        this.usrSn = usrSn == null ? null : usrSn.trim();
    }

    public String getModelSetSn() {
        return modelSetSn;
    }

    public void setModelSetSn(String modelSetSn) {
        this.modelSetSn = modelSetSn == null ? null : modelSetSn.trim();
    }

    public String getVerSn() {
        return verSn;
    }

    public void setVerSn(String verSn) {
        this.verSn = verSn == null ? null : verSn.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModelAddress() {
        return modelAddress;
    }

    public void setModelAddress(String modelAddress) {
        this.modelAddress = modelAddress == null ? null : modelAddress.trim();
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getModelEnName() {
        return modelEnName;
    }

    public void setModelEnName(String modelEnName) {
        this.modelEnName = modelEnName == null ? null : modelEnName.trim();
    }

    public String getModelSize() {
        return modelSize;
    }

    public void setModelSize(String modelSize) {
        this.modelSize = modelSize == null ? null : modelSize.trim();
    }

    public String getIsOpenSrc() {
        return isOpenSrc;
    }

    public void setIsOpenSrc(String isOpenSrc) {
        this.isOpenSrc = isOpenSrc == null ? null : isOpenSrc.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getModelCredit() {
        return modelCredit;
    }

    public void setModelCredit(Integer modelCredit) {
        this.modelCredit = modelCredit;
    }

    public String getModelContent() {
        return modelContent;
    }

    public void setModelContent(String modelContent) {
        this.modelContent = modelContent;
    }
}