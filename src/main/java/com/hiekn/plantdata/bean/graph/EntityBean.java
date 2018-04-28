package com.hiekn.plantdata.bean.graph;


import java.util.ArrayList;
import java.util.List;


public class EntityBean {
    private Long id;
    private String name;
    private String classId;
    private String meaningTag;
    private Integer kgType = Integer.valueOf(1);
    private String img;
    private List<KVBean<String, String>> extra;

    public EntityBean() {
    }

    public EntityBean(Long id, String name, String classId, String meaningTag) {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.meaningTag = meaningTag;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassId() {
        return this.classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getMeaningTag() {
        return this.meaningTag;
    }

    public void setMeaningTag(String meaningTag) {
        this.meaningTag = meaningTag;
    }

    public Integer getKgType() {
        return this.kgType;
    }

    public void setKgType(Integer kgType) {
        this.kgType = kgType;
    }

    public List<KVBean<String, String>> getExtra() {
        return this.extra;
    }

    public void addExtra(String k, String v) {
        if (this.extra == null) {
            this.extra = new ArrayList();
        }
        KVBean eib = new KVBean(k, v);
        this.extra.add(eib);
    }
}
