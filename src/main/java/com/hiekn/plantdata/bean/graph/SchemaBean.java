package com.hiekn.plantdata.bean.graph;

import java.util.ArrayList;
import java.util.List;

public class SchemaBean {
    private List<KVBean<String, String>> types;
    private List<AttBean> atts;

    public List<KVBean<String, String>> getTypes() {
        return this.types;
    }

    public void setTypes(List<KVBean<String, String>> types) {
        this.types = types;
    }

    public List<AttBean> getAtts() {
        return this.atts;
    }

    public void addAtt(String k, String v, Integer type) {
        if (this.atts == null) {
            this.atts = new ArrayList();
        }
        this.atts.add(new AttBean(k, v, type));
    }

    class AttBean {
        private String k;
        private String v;
        private Integer type;

        public AttBean(String paramInteger1, String paramString, Integer paramInteger2) {
            this.k = paramInteger1;
            this.v = paramString;
            this.type = paramInteger2;
        }

        public String getK() {
            return this.k;
        }

        public void setK(String k) {
            this.k = k;
        }

        public String getV() {
            return this.v;
        }

        public void setV(String v) {
            this.v = v;
        }

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }
}