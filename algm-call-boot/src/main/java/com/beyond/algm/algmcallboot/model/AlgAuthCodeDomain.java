package com.beyond.algm.algmcallboot.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "alg_auth_code_domain")
public class AlgAuthCodeDomain {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String addSn;

    private String acdSn;

    private String addUrl;

    public String getAddSn() {
        return addSn;
    }

    public void setAddSn(String addSn) {
        this.addSn = addSn == null ? null : addSn.trim();
    }

    public String getAcdSn() {
        return acdSn;
    }

    public void setAcdSn(String acdSn) {
        this.acdSn = acdSn == null ? null : acdSn.trim();
    }

    public String getAddUrl() {
        return addUrl;
    }

    public void setAddUrl(String addUrl) {
        this.addUrl = addUrl == null ? null : addUrl.trim();
    }

    @Override
    public String toString() {
        return "AlgAuthCodeDomain{" +
                "addSn='" + addSn + '\'' +
                ", acdSn='" + acdSn + '\'' +
                ", addUrl='" + addUrl + '\'' +
                '}';
    }
}