package com.hiekn.plantdata.Entity;

/**
 * 实体-属性
 */
public class UsrEntAttrib {

    private String AtbSID;
    private String usrSID;
    private String DtpSID;
    private String AtbName;
    private String ObjType;
    private String ObjSID;
    private String UntName;
    private String EntSID;

    public String getEntSID() {
        return EntSID;
    }

    public void setEntSID(String entSID) {
        EntSID = entSID;
    }

    public String getAtbSID() {
        return AtbSID;
    }

    public void setAtbSID(String atbSID) {
        AtbSID = atbSID;
    }

    public String getUsrSID() {
        return usrSID;
    }

    public void setUsrSID(String usrSID) {
        this.usrSID = usrSID;
    }

    public String getDtpSID() {
        return DtpSID;
    }

    public void setDtpSID(String dtpSID) {
        DtpSID = dtpSID;
    }

    public String getAtbName() {
        return AtbName;
    }

    public void setAtbName(String atbName) {
        AtbName = atbName;
    }

    public String getObjType() {
        return ObjType;
    }

    public void setObjType(String objType) {
        ObjType = objType;
    }

    public String getObjSID() {
        return ObjSID;
    }

    public void setObjSID(String objSID) {
        ObjSID = objSID;
    }

    public String getUntName() {
        return UntName;
    }

    public void setUntName(String untName) {
        UntName = untName;
    }
}
