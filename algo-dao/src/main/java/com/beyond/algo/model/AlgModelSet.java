package com.beyond.algo.model;

public class AlgModelSet {
    private String modelSetSn;

    private String usrSn;

    private String modelSetName;

    public String getModelSetSn() {
        return modelSetSn;
    }

    public void setModelSetSn(String modelSetSn) {
        this.modelSetSn = modelSetSn == null ? null : modelSetSn.trim();
    }

    public String getUsrSn() {
        return usrSn;
    }

    public void setUsrSn(String usrSn) {
        this.usrSn = usrSn == null ? null : usrSn.trim();
    }

    public String getModelSetName() {
        return modelSetName;
    }

    public void setModelSetName(String modelSetName) {
        this.modelSetName = modelSetName == null ? null : modelSetName.trim();
    }
}