package com.hiekn.plantdata.Entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Neo4j 的节点实体类：分析主体
 */
@NodeEntity
public class Entity {

    @GraphId
    private Long id;
    private Long k;
    private String v;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getK() {
        return k;
    }

    public void setK(Long k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
