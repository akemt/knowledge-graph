package com.beyond.algo.model;

public class AlProject {
    private Integer projectId;

    private Integer userId;

    private String languageCode;

    private String algoId;

    private String projectName;

    private String sourceCode;

    private String httpUrl;

    private String sshUrl;

    private String callOther;

    private String gpuStandard;

    private Long createDate;

    private Long updateDate;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode == null ? null : languageCode.trim();
    }

    public String getAlgoId() {
        return algoId;
    }

    public void setAlgoId(String algoId) {
        this.algoId = algoId == null ? null : algoId.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode == null ? null : sourceCode.trim();
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl == null ? null : httpUrl.trim();
    }

    public String getSshUrl() {
        return sshUrl;
    }

    public void setSshUrl(String sshUrl) {
        this.sshUrl = sshUrl == null ? null : sshUrl.trim();
    }

    public String getCallOther() {
        return callOther;
    }

    public void setCallOther(String callOther) {
        this.callOther = callOther == null ? null : callOther.trim();
    }

    public String getGpuStandard() {
        return gpuStandard;
    }

    public void setGpuStandard(String gpuStandard) {
        this.gpuStandard = gpuStandard == null ? null : gpuStandard.trim();
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}