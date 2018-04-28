package com.hiekn.plantdata.bean.graph;


import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RelationBean {
    private String id;
    private Long from;
    private Long to;
    private String attId;
    private String attName;
    private List<String> bsTime;
    private List<RelationInfoBean> nRInfo;
    private List<RelationInfoBean> oRInfo;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getFrom() {
        return this.from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return this.to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public String getAttId() {
        return this.attId;
    }

    public void setAttId(String attId) {
        this.attId = attId;
    }

    public String getAttName() {
        return this.attName;
    }

    public void setAttName(String attName) {
        this.attName = attName;
    }

    public List<String> getBsTime() {
        return this.bsTime;
    }

    public void addBsTime(String bsTime) {
        if (this.bsTime == null) {
            this.bsTime = new ArrayList();
        }
        this.bsTime.add(bsTime);
    }

    public List<RelationInfoBean> getnRInfo() {
        return this.nRInfo;
    }

    public List<RelationInfoBean> getoRInfo() {
        return this.oRInfo;
    }

    public void addrNumInfo(RelationInfoBean one) {
        if (this.nRInfo == null) {
            this.nRInfo = new ArrayList();
        }
        this.nRInfo.add(one);
    }

    public void addrObjectInfo(RelationInfoBean one) {
        if (this.oRInfo == null) {
            this.oRInfo = new ArrayList();
        }
        this.oRInfo.add(one);
    }

    public RelationBean() {
    }

    public RelationBean(String id, Long from, Long to, String attId) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.attId = attId;
    }

    class ModelValueSerializer implements ObjectSerializer {
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
            if ((object != null) && (!("".equals(object.toString()))))
                serializer.write(object);
        }
    }
}