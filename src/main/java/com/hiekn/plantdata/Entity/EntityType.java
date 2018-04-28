package com.hiekn.plantdata.Entity;


/**
 * 实体类-实体
 */
public class EntityType {

    private String ID;
    private String EntSID;
    private String usrSID;
    private String EntName;
    private String EntTmpl;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEntSID() {
        return EntSID;
    }

    public void setEntSID(String entSID) {
        EntSID = entSID;
    }

    public String getUsrSID() {
        return usrSID;
    }

    public void setUsrSID(String usrSID) {
        this.usrSID = usrSID;
    }

    public String getEntName() {
        return EntName;
    }

    public void setEntName(String entName) {
        EntName = entName;
    }

    public String getEntTmpl() {
        return EntTmpl;
    }

    public void setEntTmpl(String entTmpl) {
        EntTmpl = entTmpl;
    }
}
