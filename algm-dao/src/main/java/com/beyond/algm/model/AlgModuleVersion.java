package com.beyond.algm.model;

import java.util.Date;

public class AlgModuleVersion {
    private String verSn;

    private String modSn;

    private Integer verCodeL1;

    private Integer verCodeL2;

    private Integer verCodeL3;

    private String latestCommit;

    private Float verLoyaltyFee;

    private String verRunUrl;

    private String verRunPort;

    private String verCompiled;

    private String verisDirty;

    private String verUrl;

    private Date createDate;

    private String verDependency;

    private String verDescription;

    public String getVerSn() {
        return verSn;
    }

    public void setVerSn(String verSn) {
        this.verSn = verSn == null ? null : verSn.trim();
    }

    public String getModSn() {
        return modSn;
    }

    public void setModSn(String modSn) {
        this.modSn = modSn == null ? null : modSn.trim();
    }

    public Integer getVerCodeL1() {
        return verCodeL1;
    }

    public void setVerCodeL1(Integer verCodeL1) {
        this.verCodeL1 = verCodeL1;
    }

    public Integer getVerCodeL2() {
        return verCodeL2;
    }

    public void setVerCodeL2(Integer verCodeL2) {
        this.verCodeL2 = verCodeL2;
    }

    public Integer getVerCodeL3() {
        return verCodeL3;
    }

    public void setVerCodeL3(Integer verCodeL3) {
        this.verCodeL3 = verCodeL3;
    }

    public Float getVerLoyaltyFee() {
        return verLoyaltyFee;
    }

    public void setVerLoyaltyFee(Float verLoyaltyFee) {
        this.verLoyaltyFee = verLoyaltyFee;
    }

    public String getVerRunUrl() {
        return verRunUrl;
    }

    public void setVerRunUrl(String verRunUrl) {
        this.verRunUrl = verRunUrl == null ? null : verRunUrl.trim();
    }

    public String getVerRunPort() {
        return verRunPort;
    }

    public void setVerRunPort(String verRunPort) {
        this.verRunPort = verRunPort == null ? null : verRunPort.trim();
    }

    public String getVerCompiled() {
        return verCompiled;
    }

    public void setVerCompiled(String verCompiled) {
        this.verCompiled = verCompiled == null ? null : verCompiled.trim();
    }

    public String getVerisDirty() {
        return verisDirty;
    }

    public void setVerisDirty(String verisDirty) {
        this.verisDirty = verisDirty == null ? null : verisDirty.trim();
    }

    public String getVerUrl() {
        return verUrl;
    }

    public void setVerUrl(String verUrl) {
        this.verUrl = verUrl == null ? null : verUrl.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getVerDependency() {
        return verDependency;
    }

    public void setVerDependency(String verDependency) {
        this.verDependency = verDependency == null ? null : verDependency.trim();
    }

    public String getVerDescription() {
        return verDescription;
    }

    public void setVerDescription(String verDescription) {
        this.verDescription = verDescription == null ? null : verDescription.trim();
    }

    public String getLatestCommit() {
        return latestCommit;
    }

    public void setLatestCommit(String latestCommit) {
        this.latestCommit = latestCommit;
    }
}