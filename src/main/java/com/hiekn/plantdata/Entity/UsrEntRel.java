package com.hiekn.plantdata.Entity;

/**
 * 实体-关系
 */
public class UsrEntRel {

    private String RtpSID;
    private String usrSID;
    private String RtpName;
    private String EntSID;
    private String Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getEntSID() {
        return EntSID;
    }

    public void setEntSID(String entSID) {
        EntSID = entSID;
    }

    public String getRtpSID() {
        return RtpSID;
    }

    public void setRtpSID(String rtpSID) {
        RtpSID = rtpSID;
    }

    public String getUsrSID() {
        return usrSID;
    }

    public void setUsrSID(String usrSID) {
        this.usrSID = usrSID;
    }

    public String getRtpName() {
        return RtpName;
    }

    public void setRtpName(String rtpName) {
        RtpName = rtpName;
    }
}
