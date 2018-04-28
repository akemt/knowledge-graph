package com.hiekn.plantdata.Entity;


import java.util.Date;

/**
 * 用户实体类
 */
public class User {

    private String usrSID;

    private String usrName;

    private String usrPassword;

    private String usrNodeID;

    private String status;

    private String level;

    private Date createTime;

    private Date updateTime;

    public String getUsrSID() {
        return usrSID;
    }

    public void setUsrSID(String usrSID) {
        this.usrSID = usrSID;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public String getUsrNodeID() {
        return usrNodeID;
    }

    public void setUsrNodeID(String usrNodeID) {
        this.usrNodeID = usrNodeID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
