package com.lx.zhaopin.bean;


import com.lx.zhaopin.http.CommonBean;

public class PhoneStateBean extends CommonBean {


    /**
     * exist :  接口中涉及到”是/否”的字段都是：1表示是，0表示否
     */

    private String exist;
    private String authCode;
    private String joinImage;
    private String ratio;
    private String count;
    private String interviewId;
    private String jobFeedbackCount;
    private String systemMessageCount;
    private String chatApplyCount;
    private String name;
    private String avatar;
    private String chatApplyText;
    private String id;
    private String HRId;
    private String logo;
    private String location;
    private String lat;
    private String lng;
    private String positionName;
    private String companyName;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getChatRecordId() {
        return chatRecordId;
    }

    public void setChatRecordId(String chatRecordId) {
        this.chatRecordId = chatRecordId;
    }

    private String chatRecordId;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHRId() {
        return HRId;
    }

    public void setHRId(String HRId) {
        this.HRId = HRId;
    }

    public String getChatApplyText() {
        return chatApplyText;
    }

    public void setChatApplyText(String chatApplyText) {
        this.chatApplyText = chatApplyText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getJobFeedbackCount() {
        return jobFeedbackCount;
    }

    public void setJobFeedbackCount(String jobFeedbackCount) {
        this.jobFeedbackCount = jobFeedbackCount;
    }

    public String getSystemMessageCount() {
        return systemMessageCount;
    }

    public void setSystemMessageCount(String systemMessageCount) {
        this.systemMessageCount = systemMessageCount;
    }

    public String getChatApplyCount() {
        return chatApplyCount;
    }

    public void setChatApplyCount(String chatApplyCount) {
        this.chatApplyCount = chatApplyCount;
    }

    public String getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }

    public String getrRongToken() {
        return rRongToken;
    }

    public void setrRongToken(String rRongToken) {
        this.rRongToken = rRongToken;
    }

    private String rRongToken;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getJoinImage() {
        return joinImage;
    }

    public void setJoinImage(String joinImage) {
        this.joinImage = joinImage;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }
}
