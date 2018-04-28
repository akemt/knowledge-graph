package com.hiekn.plantdata.bean.item;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RelationItem
        implements Serializable {
    private static final long serialVersionUID = 5380215361023970542L;
    private String id;
    private long fromId;
    private String attId;
    private String attName;
    private long toId;
    private Map<String, String> infoMap;
    private List<ServiceEntityItem> attExtObjInfoList;
    private Map<String, List<ServiceEntityItem>> attExtObjInfoMap;

    public String getAttName() {
        return attName;
    }

    public void setAttName(String attName) {
        this.attName = attName;
    }

    public long getFromId() {
        return this.fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getToId() {
        return this.toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public String getAttId() {
        return this.attId;
    }

    public void setAttId(String attId) {
        this.attId = attId;
    }

    public Map<String, String> getInfoMap() {
        return this.infoMap;
    }

    public void setInfoMap(Map<String, String> infoMap) {
        this.infoMap = infoMap;
    }

    public List<ServiceEntityItem> getAttExtObjInfoList() {
        return this.attExtObjInfoList;
    }

    public void setAttExtObjInfoList(List<ServiceEntityItem> attExtObjInfoList) {
        this.attExtObjInfoList = attExtObjInfoList;
    }

    public static long getSerialversionuid() {
        return 5380215361023970542L;
    }

    public Map<String, List<ServiceEntityItem>> getAttExtObjInfoMap() {
        return this.attExtObjInfoMap;
    }

    public void setAttExtObjInfoMap(Map<String, List<ServiceEntityItem>> attExtObjInfoMap) {
        this.attExtObjInfoMap = attExtObjInfoMap;
    }
}