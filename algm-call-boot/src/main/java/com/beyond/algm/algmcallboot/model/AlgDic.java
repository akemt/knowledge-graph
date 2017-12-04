package com.beyond.algm.algmcallboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "alg_dic")

public class AlgDic {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String dicSn;

    private String dicCode;

    private String dicKey;

    private String dicValue;

    private Integer dicSort;

    private String dicDesc;

    private String dicParentId;

    private String dicRelation;

    public String getDicSn() {
        return dicSn;
    }

    public void setDicSn(String dicSn) {
        this.dicSn = dicSn == null ? null : dicSn.trim();
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode == null ? null : dicCode.trim();
    }

    public String getDicKey() {
        return dicKey;
    }

    public void setDicKey(String dicKey) {
        this.dicKey = dicKey == null ? null : dicKey.trim();
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue == null ? null : dicValue.trim();
    }

    public Integer getDicSort() {
        return dicSort;
    }

    public void setDicSort(Integer dicSort) {
        this.dicSort = dicSort;
    }

    public String getDicDesc() {
        return dicDesc;
    }

    public void setDicDesc(String dicDesc) {
        this.dicDesc = dicDesc == null ? null : dicDesc.trim();
    }

    public String getDicParentId() {
        return dicParentId;
    }

    public void setDicParentId(String dicParentId) {
        this.dicParentId = dicParentId == null ? null : dicParentId.trim();
    }

    public String getDicRelation() {
        return dicRelation;
    }

    public void setDicRelation(String dicRelation) {
        this.dicRelation = dicRelation == null ? null : dicRelation.trim();
    }

    @Override
    public String toString() {
        return "AlgDic{" +
                "dicSn='" + dicSn + '\'' +
                ", dicCode='" + dicCode + '\'' +
                ", dicKey='" + dicKey + '\'' +
                ", dicValue='" + dicValue + '\'' +
                ", dicSort=" + dicSort +
                ", dicDesc='" + dicDesc + '\'' +
                ", dicParentId='" + dicParentId + '\'' +
                ", dicRelation='" + dicRelation + '\'' +
                '}';
    }
}