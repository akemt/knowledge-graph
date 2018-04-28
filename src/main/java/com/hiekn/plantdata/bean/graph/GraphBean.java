package com.hiekn.plantdata.bean.graph;

import java.util.List;


public class GraphBean {
    private List<EntityBean> entityList;
    private List<RelationBean> relationList;
    private List<PathAGBean> connects;
    private List<GraphStatBean> stats;

    public List<EntityBean> getEntityList() {
        return this.entityList;
    }

    public void setEntityList(List<EntityBean> entityList) {
        this.entityList = entityList;
    }

    public List<RelationBean> getRelationList() {
        return this.relationList;
    }

    public void setRelationList(List<RelationBean> relationList) {
        this.relationList = relationList;
    }

    public List<PathAGBean> getConnects() {
        return this.connects;
    }

    public void setConnects(List<PathAGBean> connects) {
        this.connects = connects;
    }

    public List<GraphStatBean> getStats() {
        return this.stats;
    }

    public void setStats(List<GraphStatBean> stats) {
        this.stats = stats;
    }
}