package com.beyond.algm.model;

import java.util.Date;

public class AlgAuthCode {
    private String acdSn;

    private String usrSn;

    private String acdName;

    private String acdId;

    private String callFromClient;

    private String callFromBrowser;

    private String restrictUrl;

    private String dataUseType;

    private Date createDate;

    public String getAcdSn() {
        return acdSn;
    }

    public void setAcdSn(String acdSn) {
        this.acdSn = acdSn == null ? null : acdSn.trim();
    }

    public String getUsrSn() {
        return usrSn;
    }

    public void setUsrSn(String usrSn) {
        this.usrSn = usrSn == null ? null : usrSn.trim();
    }

    public String getAcdName() {
        return acdName;
    }

    public void setAcdName(String acdName) {
        this.acdName = acdName == null ? null : acdName.trim();
    }

    public String getAcdId() {
        return acdId;
    }

    public void setAcdId(String acdId) {
        this.acdId = acdId == null ? null : acdId.trim();
    }

    public String getCallFromClient() {
        return callFromClient;
    }

    public void setCallFromClient(String callFromClient) {
        this.callFromClient = callFromClient == null ? null : callFromClient.trim();
    }

    public String getCallFromBrowser() {
        return callFromBrowser;
    }

    public void setCallFromBrowser(String callFromBrowser) {
        this.callFromBrowser = callFromBrowser == null ? null : callFromBrowser.trim();
    }

    public String getRestrictUrl() {
        return restrictUrl;
    }

    public void setRestrictUrl(String restrictUrl) {
        this.restrictUrl = restrictUrl == null ? null : restrictUrl.trim();
    }

    public String getDataUseType() {
        return dataUseType;
    }

    public void setDataUseType(String dataUseType) {
        this.dataUseType = dataUseType == null ? null : dataUseType.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}