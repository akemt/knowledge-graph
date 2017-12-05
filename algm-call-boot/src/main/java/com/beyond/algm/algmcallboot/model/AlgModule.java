package com.beyond.algm.algmcallboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "alg_module")

public class AlgModule {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String modSn;

    private String usrSn;

    private String createSn;

    private String lanSn;

    private String catSn;

    private String licSn;

    private String atlSn;

    private String modName;

    private String modId;

    private String isOpenSrc;

    private String needWeb;

    private String needCallOther;

    private String envType;

    private String isTrain;

    private String isColony;

    private String colonyPlanId;

    private String modDesc;

    public String getModSn() {
        return modSn;
    }

    public void setModSn(String modSn) {
        this.modSn = modSn == null ? null : modSn.trim();
    }

    public String getCreateSn() {
        return createSn;
    }

    public void setCreateSn(String createSn) {
        this.createSn = createSn == null ? null : createSn.trim();
    }

    public String getUsrSn() {
        return usrSn;
    }

    public void setUsrSn(String usrSn) {
        this.usrSn = usrSn == null ? null : usrSn.trim();
    }

    public String getLanSn() {
        return lanSn;
    }

    public void setLanSn(String lanSn) {
        this.lanSn = lanSn == null ? null : lanSn.trim();
    }

    public String getCatSn() {
        return catSn;
    }

    public void setCatSn(String catSn) {
        this.catSn = catSn == null ? null : catSn.trim();
    }

    public String getLicSn() {
        return licSn;
    }

    public void setLicSn(String licSn) {
        this.licSn = licSn == null ? null : licSn.trim();
    }

    public String getAtlSn() {
        return atlSn;
    }

    public void setAtlSn(String atlSn) {
        this.atlSn = atlSn == null ? null : atlSn.trim();
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName == null ? null : modName.trim();
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId == null ? null : modId.trim();
    }

    public String getIsOpenSrc() {
        return isOpenSrc;
    }

    public void setIsOpenSrc(String isOpenSrc) {
        this.isOpenSrc = isOpenSrc == null ? null : isOpenSrc.trim();
    }

    public String getNeedWeb() {
        return needWeb;
    }

    public void setNeedWeb(String needWeb) {
        this.needWeb = needWeb == null ? null : needWeb.trim();
    }

    public String getNeedCallOther() {
        return needCallOther;
    }

    public void setNeedCallOther(String needCallOther) {
        this.needCallOther = needCallOther == null ? null : needCallOther.trim();
    }

    public String getEnvType() {
        return envType;
    }

    public void setEnvType(String envType) {
        this.envType = envType == null ? null : envType.trim();
    }
    public String getIsTrain() {
        return isTrain;
    }

    public void setIsTrain(String isTrain) {
        this.isTrain = isTrain == null ? null : isTrain.trim();
    }
    public String getIsColony() {
        return isColony;
    }

    public void setIsColony(String isColony) {
        this.isColony = isColony == null ? null : isColony.trim();
    }
    public String getColonyPlanId() {
        return colonyPlanId;
    }

    public void setColonyPlanId(String colonyPlanId) {
        this.colonyPlanId = colonyPlanId == null ? null : colonyPlanId.trim();
    }

    public String getModDesc() {
        return modDesc;
    }

    public void setModDesc(String modDesc) {
        this.modDesc = modDesc == null ? null : modDesc.trim();
    }

    @Override
    public String toString() {
        return "AlgModule{" +
                "modSn='" + modSn + '\'' +
                ", usrSn='" + usrSn + '\'' +
                ", createSn='" + createSn + '\'' +
                ", lanSn='" + lanSn + '\'' +
                ", catSn='" + catSn + '\'' +
                ", licSn='" + licSn + '\'' +
                ", atlSn='" + atlSn + '\'' +
                ", modName='" + modName + '\'' +
                ", modId='" + modId + '\'' +
                ", isOpenSrc='" + isOpenSrc + '\'' +
                ", needWeb='" + needWeb + '\'' +
                ", needCallOther='" + needCallOther + '\'' +
                ", envType='" + envType + '\'' +
                ", isTrain='" + isTrain + '\'' +
                ", isColony='" + isColony + '\'' +
                ", colonyPlanId='" + colonyPlanId + '\'' +
                ", modDesc='" + modDesc + '\'' +
                '}';
    }
}