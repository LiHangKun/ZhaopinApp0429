package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class YuLanBean extends CommonBean {


    /**
     * arrivalTime : 1
     * avatar :
     * birthday :
     * education : {"id":"","name":""}
     * experienceEducationList : [{"beginDate":"","education":{"id":"","name":""},"endDate":"","experience":"","id":"","major":"","school":""}]
     * experienceWorkList : [{"beginDate":"","companyName":"","endDate":"","experience":"","id":"","positionName":"","skills":""}]
     * jobNature : 1
     * jobStatus : 1
     * latestCityName : 1
     * mobile :
     * name :
     * resumeExpectationList : [{"city":{"id":"","name":""},"id":"","maxSalary":"","minSalary":"","positionCategory3":{"id":"","name":""},"resumeExpectationIndustryList":[{"id":"","name":""}]}]
     * resumeSkillList : [{"id":"","name":""}]
     * sex : 1
     * workDate :
     * workYears :
     */

    private String arrivalTime;
    private String avatar;
    private String birthday;
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private EducationBean education;
    private String jobNature;
    private String jobStatus;
    private String latestCityName;
    private String mobile;
    private String name;
    private String sex;
    private String minSalary;
    private String maxSalary;

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    private String workDate;
    private String workYears;
    private List<ExperienceEducationListBean> experienceEducationList;
    private List<ExperienceWorkListBean> experienceWorkList;
    private List<ResumeExpectationListBean> resumeExpectationList;
    private List<ResumeSkillListBean> resumeSkillList;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public EducationBean getEducation() {
        return education;
    }

    public void setEducation(EducationBean education) {
        this.education = education;
    }

    public String getJobNature() {
        return jobNature;
    }

    public void setJobNature(String jobNature) {
        this.jobNature = jobNature;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getLatestCityName() {
        return latestCityName;
    }

    public void setLatestCityName(String latestCityName) {
        this.latestCityName = latestCityName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getWorkYears() {
        return workYears;
    }

    public void setWorkYears(String workYears) {
        this.workYears = workYears;
    }

    public List<ExperienceEducationListBean> getExperienceEducationList() {
        return experienceEducationList;
    }

    public void setExperienceEducationList(List<ExperienceEducationListBean> experienceEducationList) {
        this.experienceEducationList = experienceEducationList;
    }

    public List<ExperienceWorkListBean> getExperienceWorkList() {
        return experienceWorkList;
    }

    public void setExperienceWorkList(List<ExperienceWorkListBean> experienceWorkList) {
        this.experienceWorkList = experienceWorkList;
    }

    public List<ResumeExpectationListBean> getResumeExpectationList() {
        return resumeExpectationList;
    }

    public void setResumeExpectationList(List<ResumeExpectationListBean> resumeExpectationList) {
        this.resumeExpectationList = resumeExpectationList;
    }

    public List<ResumeSkillListBean> getResumeSkillList() {
        return resumeSkillList;
    }

    public void setResumeSkillList(List<ResumeSkillListBean> resumeSkillList) {
        this.resumeSkillList = resumeSkillList;
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

    public static class ExperienceEducationListBean {
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
        private EducationBeanX education;
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

        public EducationBeanX getEducation() {
            return education;
        }

        public void setEducation(EducationBeanX education) {
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

        public static class EducationBeanX {
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

    public static class ExperienceWorkListBean {
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

    public static class ResumeExpectationListBean {
        /**
         * city : {"id":"","name":""}
         * id :
         * maxSalary :
         * minSalary :
         * positionCategory3 : {"id":"","name":""}
         * resumeExpectationIndustryList : [{"id":"","name":""}]
         */

        private CityBean city;
        private String id;
        private String maxSalary;
        private String minSalary;
        private PositionCategory3Bean positionCategory3;
        private List<ResumeExpectationIndustryListBean> resumeExpectationIndustryList;

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMaxSalary() {
            return maxSalary;
        }

        public void setMaxSalary(String maxSalary) {
            this.maxSalary = maxSalary;
        }

        public String getMinSalary() {
            return minSalary;
        }

        public void setMinSalary(String minSalary) {
            this.minSalary = minSalary;
        }

        public PositionCategory3Bean getPositionCategory3() {
            return positionCategory3;
        }

        public void setPositionCategory3(PositionCategory3Bean positionCategory3) {
            this.positionCategory3 = positionCategory3;
        }

        public List<ResumeExpectationIndustryListBean> getResumeExpectationIndustryList() {
            return resumeExpectationIndustryList;
        }

        public void setResumeExpectationIndustryList(List<ResumeExpectationIndustryListBean> resumeExpectationIndustryList) {
            this.resumeExpectationIndustryList = resumeExpectationIndustryList;
        }

        public static class CityBean {
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

        public static class PositionCategory3Bean {
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

        public static class ResumeExpectationIndustryListBean {
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

    public static class ResumeSkillListBean {
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
