package com.beyond.algo.model;

public class AlgDataSet {
    private String dataSetSn;

    private String usrSn;

    private String dataSetName;

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

    public String getDataSetName() {
        return dataSetName;
    }

    public void setDataSetName(String dataSetName) {
        this.dataSetName = dataSetName == null ? null : dataSetName.trim();
    }
}