package com.hiekn.plantdata.bean.graph;


import java.util.ArrayList;
import java.util.List;

public class GraphStatBean {
    private String key;
    private String type;
    private List<String> atts;
    private List<GraphStatDetailBean> rs;

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getAtts() {
        return this.atts;
    }

    public void setAtts(List<String> atts) {
        this.atts = atts;
    }

    public List<GraphStatDetailBean> getRs() {
        return this.rs;
    }

    public void addRs(Long id, Integer count) {
        if (this.rs == null) {
            this.rs = new ArrayList();
        }
        this.rs.add(new GraphStatDetailBean(id, count));
    }

    class GraphStatDetailBean {
        Long id;
        Integer count;

        public GraphStatDetailBean(Long paramLong, Integer paramInteger) {
            this.id = paramLong;
            this.count = paramInteger;
        }

        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getCount() {
            return this.count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
