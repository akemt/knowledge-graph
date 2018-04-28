package com.hiekn.plantdata.bean.graph;


import java.util.ArrayList;
import java.util.List;


public class RelationInfoBean {
    private String id;
    private List<KVBean<String, String>> kvs;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<KVBean<String, String>> getKvs() {
        return this.kvs;
    }

    public void addKv(Object k, String v) {
        if (this.kvs == null) {
            this.kvs = new ArrayList();
        }
        this.kvs.add(new KVBean(k, v));
    }
}