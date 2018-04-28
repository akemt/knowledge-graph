package com.hiekn.plantdata.Entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.QueryResult;

@NodeEntity
public class Enterprise {

    @GraphId
    private Long id;
    private long en_id;
    private String name;
    private String classId;
    private String meaningTag;
    private String img;
    private Integer kgType;

    public long getEn_id() {
        return en_id;
    }

    public void setEn_id(long en_id) {
        this.en_id = en_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getMeaningTag() {
        return meaningTag;
    }

    public void setMeaningTag(String meaningTag) {
        this.meaningTag = meaningTag;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getKgType() {
        return kgType;
    }

    public void setKgType(Integer kgType) {
        this.kgType = kgType;
    }

    @QueryResult
    public static class ResultheadData {
    }
}
