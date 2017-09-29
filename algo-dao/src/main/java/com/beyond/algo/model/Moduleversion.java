package com.beyond.algo.model;

public class Moduleversion {
    private String versn;

    private String modsn;

    private Integer vercodel1;

    private Integer vercodel2;

    private Integer vercodel3;

    private Float verloyaltyfee;

    private String verrunurl;

    private String verrunport;

    private String vercompiled;

    private String verisdirty;

    private String verurl;

    private String verdependency;

    private String verdescription;

    public String getVersn() {
        return versn;
    }

    public void setVersn(String versn) {
        this.versn = versn == null ? null : versn.trim();
    }

    public String getModsn() {
        return modsn;
    }

    public void setModsn(String modsn) {
        this.modsn = modsn == null ? null : modsn.trim();
    }

    public Integer getVercodel1() {
        return vercodel1;
    }

    public void setVercodel1(Integer vercodel1) {
        this.vercodel1 = vercodel1;
    }

    public Integer getVercodel2() {
        return vercodel2;
    }

    public void setVercodel2(Integer vercodel2) {
        this.vercodel2 = vercodel2;
    }

    public Integer getVercodel3() {
        return vercodel3;
    }

    public void setVercodel3(Integer vercodel3) {
        this.vercodel3 = vercodel3;
    }

    public Float getVerloyaltyfee() {
        return verloyaltyfee;
    }

    public void setVerloyaltyfee(Float verloyaltyfee) {
        this.verloyaltyfee = verloyaltyfee;
    }

    public String getVerrunurl() {
        return verrunurl;
    }

    public void setVerrunurl(String verrunurl) {
        this.verrunurl = verrunurl == null ? null : verrunurl.trim();
    }

    public String getVerrunport() {
        return verrunport;
    }

    public void setVerrunport(String verrunport) {
        this.verrunport = verrunport == null ? null : verrunport.trim();
    }

    public String getVercompiled() {
        return vercompiled;
    }

    public void setVercompiled(String vercompiled) {
        this.vercompiled = vercompiled == null ? null : vercompiled.trim();
    }

    public String getVerisdirty() {
        return verisdirty;
    }

    public void setVerisdirty(String verisdirty) {
        this.verisdirty = verisdirty == null ? null : verisdirty.trim();
    }

    public String getVerurl() {
        return verurl;
    }

    public void setVerurl(String verurl) {
        this.verurl = verurl == null ? null : verurl.trim();
    }

    public String getVerdependency() {
        return verdependency;
    }

    public void setVerdependency(String verdependency) {
        this.verdependency = verdependency == null ? null : verdependency.trim();
    }

    public String getVerdescription() {
        return verdescription;
    }

    public void setVerdescription(String verdescription) {
        this.verdescription = verdescription == null ? null : verdescription.trim();
    }
}