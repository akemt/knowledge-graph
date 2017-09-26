package com.beyond.algo.dao.model;

public class Module {
    private String modsn;

    private String usgsn;

    private String usrsn;

    private String lansn;

    private String licsn;

    private String modname;

    private String modid;

    private String isopensrc;

    private String needweb;

    private String callother;

    private String envtype;

    public String getModsn() {
        return modsn;
    }

    public void setModsn(String modsn) {
        this.modsn = modsn == null ? null : modsn.trim();
    }

    public String getUsgsn() {
        return usgsn;
    }

    public void setUsgsn(String usgsn) {
        this.usgsn = usgsn == null ? null : usgsn.trim();
    }

    public String getUsrsn() {
        return usrsn;
    }

    public void setUsrsn(String usrsn) {
        this.usrsn = usrsn == null ? null : usrsn.trim();
    }

    public String getLansn() {
        return lansn;
    }

    public void setLansn(String lansn) {
        this.lansn = lansn == null ? null : lansn.trim();
    }

    public String getLicsn() {
        return licsn;
    }

    public void setLicsn(String licsn) {
        this.licsn = licsn == null ? null : licsn.trim();
    }

    public String getModname() {
        return modname;
    }

    public void setModname(String modname) {
        this.modname = modname == null ? null : modname.trim();
    }

    public String getModid() {
        return modid;
    }

    public void setModid(String modid) {
        this.modid = modid == null ? null : modid.trim();
    }

    public String getIsopensrc() {
        return isopensrc;
    }

    public void setIsopensrc(String isopensrc) {
        this.isopensrc = isopensrc == null ? null : isopensrc.trim();
    }

    public String getNeedweb() {
        return needweb;
    }

    public void setNeedweb(String needweb) {
        this.needweb = needweb == null ? null : needweb.trim();
    }

    public String getCallother() {
        return callother;
    }

    public void setCallother(String callother) {
        this.callother = callother == null ? null : callother.trim();
    }

    public String getEnvtype() {
        return envtype;
    }

    public void setEnvtype(String envtype) {
        this.envtype = envtype == null ? null : envtype.trim();
    }
}