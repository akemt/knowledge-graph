package com.beyond.algm.algmcallboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "alg_module_version")
public class AlgModuleVersion {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String verSn;

    private String modSn;

    private Integer ver_code_l1;

    private Integer ver_code_l2;

    private Integer ver_code_l3;

    private float verLoyaltyFee;

    public String getVerSn() {
        return verSn;
    }

    public void setVerSn(String verSn) {
        this.verSn = verSn;
    }

    public String getModSn() {
        return modSn;
    }

    public void setModSn(String modSn) {
        this.modSn = modSn;
    }

    public Integer getVer_code_l1() {
        return ver_code_l1;
    }

    public void setVer_code_l1(Integer ver_code_l1) {
        this.ver_code_l1 = ver_code_l1;
    }

    public Integer getVer_code_l2() {
        return ver_code_l2;
    }

    public void setVer_code_l2(Integer ver_code_l2) {
        this.ver_code_l2 = ver_code_l2;
    }

    public Integer getVer_code_l3() {
        return ver_code_l3;
    }

    public void setVer_code_l3(Integer ver_code_l3) {
        this.ver_code_l3 = ver_code_l3;
    }

    public float getVerLoyaltyFee() {
        return verLoyaltyFee;
    }

    public void setVerLoyaltyFee(float verLoyaltyFee) {
        this.verLoyaltyFee = verLoyaltyFee;
    }
}