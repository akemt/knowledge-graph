package com.hiekn.plantdata.Entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * 明细实体
 */
@NodeEntity
public class Info {

    @GraphId
    private Long id;
    private String brief;
    private String synonym;
    private String name;
    private Long in_id;
    private Long classId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public Long getIn_id() {
        return in_id;
    }

    public void setIn_id(Long in_id) {
        this.in_id = in_id;
    }
}
