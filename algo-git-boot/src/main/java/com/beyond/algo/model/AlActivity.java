package com.beyond.algo.model;

public class AlActivity {
    private Integer activityId;

    private String activityName;

    private Integer activityRule;

    private String activityStatue;

    private Long createDate;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Integer getActivityRule() {
        return activityRule;
    }

    public void setActivityRule(Integer activityRule) {
        this.activityRule = activityRule;
    }

    public String getActivityStatue() {
        return activityStatue;
    }

    public void setActivityStatue(String activityStatue) {
        this.activityStatue = activityStatue == null ? null : activityStatue.trim();
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
}