package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

public class MyJiaoYuInfoBean extends CommonBean {


    /**
     * beginDate :
     * education : {"id":"","name":""}
     * endDate :
     * experience :
     * id :
     * major :
     * school :
     */

    private String beginDate;
    private EducationBean education;
    private String endDate;
    private String experience;
    private String id;
    private String major;
    private String school;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public EducationBean getEducation() {
        return education;
    }

    public void setEducation(EducationBean education) {
        this.education = education;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public static class EducationBean {
        /**
         * id :
         * name :
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
