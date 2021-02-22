package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class HRShenQingBean extends CommonBean {


    /**
     * dataList : [{"age":"","avatar":"","education":{"id":"","name":""},"experienceEducation":{"id":"","major":"专业","school":"学校"},"id":"","latestCity":{"id":"","name":"名称"},"maxSalary":"","minSalary":"","name":"求职者名称","requestId":"","requestStatus":"","resumeSkillList":[{"id":"","name":""}],"workYears":""}]
     * totalCount : 10
     * totalPage : 2
     */

    private int totalCount;
    private int totalPage;
    private List<DataListBean> dataList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * age :
         * avatar :
         * education : {"id":"","name":""}
         * experienceEducation : {"id":"","major":"专业","school":"学校"}
         * id :
         * latestCity : {"id":"","name":"名称"}
         * maxSalary :
         * minSalary :
         * name : 求职者名称
         * requestId :
         * requestStatus :
         * resumeSkillList : [{"id":"","name":""}]
         * workYears :
         */

        private String age;
        private String avatar;
        private EducationBean education;
        private ExperienceEducationBean experienceEducation;
        private String id;
        private LatestCityBean latestCity;
        private String maxSalary;
        private String minSalary;
        private String name;
        private String requestId;
        private String requestStatus;
        private String openResume;

        public String getOpenResume() {
            return openResume;
        }

        public void setOpenResume(String openResume) {
            this.openResume = openResume;
        }

        private String workYears;
        private List<ResumeSkillListBean> resumeSkillList;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public EducationBean getEducation() {
            return education;
        }

        public void setEducation(EducationBean education) {
            this.education = education;
        }

        public ExperienceEducationBean getExperienceEducation() {
            return experienceEducation;
        }

        public void setExperienceEducation(ExperienceEducationBean experienceEducation) {
            this.experienceEducation = experienceEducation;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public LatestCityBean getLatestCity() {
            return latestCity;
        }

        public void setLatestCity(LatestCityBean latestCity) {
            this.latestCity = latestCity;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getRequestStatus() {
            return requestStatus;
        }

        public void setRequestStatus(String requestStatus) {
            this.requestStatus = requestStatus;
        }

        public String getWorkYears() {
            return workYears;
        }

        public void setWorkYears(String workYears) {
            this.workYears = workYears;
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

        public static class ExperienceEducationBean {
            /**
             * id :
             * major : 专业
             * school : 学校
             */

            private String id;
            private String major;
            private String school;

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
        }

        public static class LatestCityBean {
            /**
             * id :
             * name : 名称
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
}
