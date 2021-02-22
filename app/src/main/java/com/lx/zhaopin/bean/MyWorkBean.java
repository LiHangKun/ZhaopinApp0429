package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

public class MyWorkBean extends CommonBean {


    /**
     * beginDate :
     * companyName :
     * endDate :
     * experience :
     * id :
     * positionName :
     * skills :
     */

    private String beginDate;
    private String companyName;
    private String endDate;
    private String experience;
    private String id;
    private String positionName;
    private String skills;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
