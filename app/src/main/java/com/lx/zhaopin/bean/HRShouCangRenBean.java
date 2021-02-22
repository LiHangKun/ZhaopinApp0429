package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class HRShouCangRenBean extends CommonBean {


    /**
     * dataList : [{"age":"1","avatar":"http://39.96.78.51/userfiles/member/2020/5/jmhw1ddttw.jpg","birthday":"2009-05","education":{"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"},"experienceEducation":{"id":"90672dba5c374293a7f27330fde25239","major":"土木工程","school":"河南理工大学"},"id":"9bebe8048573487b894b1e4067179e02","latestCity":{"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":"1"},"maxSalary":"1","minSalary":"1","name":"用户","openResume":"1","resumeSkillList":[{"id":"deb374a1149d44f8a359506fc84d5970","name":"123"},{"id":"cf7a85a7264d40eb8a628213aacd1188","name":"1111"},{"id":"a93018457b4b414f9ff5155bfce6350c","name":"cfjjfhjjfj"},{"id":"6da0005fc48f4e44b8a24267e4d732fc","name":"jfjvvjvjjvjvjvjvjvjvvjjv"}],"sex":"1","workDate":"2017-09-01","workYears":"1"}]
     * pageNo : 1
     * pageSize : 10
     * totalCount : 1
     * totalPage : 1
     */

    private int pageNo;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<DataListBean> dataList;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

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
         * age : 1
         * avatar : http://39.96.78.51/userfiles/member/2020/5/jmhw1ddttw.jpg
         * birthday : 2009-05
         * education : {"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"}
         * experienceEducation : {"id":"90672dba5c374293a7f27330fde25239","major":"土木工程","school":"河南理工大学"}
         * id : 9bebe8048573487b894b1e4067179e02
         * latestCity : {"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":"1"}
         * maxSalary : 1
         * minSalary : 1
         * name : 用户
         * openResume : 1
         * resumeSkillList : [{"id":"deb374a1149d44f8a359506fc84d5970","name":"123"},{"id":"cf7a85a7264d40eb8a628213aacd1188","name":"1111"},{"id":"a93018457b4b414f9ff5155bfce6350c","name":"cfjjfhjjfj"},{"id":"6da0005fc48f4e44b8a24267e4d732fc","name":"jfjvvjvjjvjvjvjvjvjvvjjv"}]
         * sex : 1
         * workDate : 2017-09-01
         * workYears : 1
         */

        private String age;
        private String avatar;
        private String birthday;
        private EducationBean education;
        private ExperienceEducationBean experienceEducation;
        private String id;
        private LatestCityBean latestCity;
        private String maxSalary;
        private String minSalary;
        private String name;
        private String openResume;
        private String sex;
        private String workDate;
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

        public String getOpenResume() {
            return openResume;
        }

        public void setOpenResume(String openResume) {
            this.openResume = openResume;
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

        public List<ResumeSkillListBean> getResumeSkillList() {
            return resumeSkillList;
        }

        public void setResumeSkillList(List<ResumeSkillListBean> resumeSkillList) {
            this.resumeSkillList = resumeSkillList;
        }

        public static class EducationBean {
            /**
             * id : 3214b5eb26364ccc97832d2d73ed21e6
             * name : 专科
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
             * id : 90672dba5c374293a7f27330fde25239
             * major : 土木工程
             * school : 河南理工大学
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
             * children : []
             * id : 410100
             * name : 郑州市
             * parentId : 0
             * sort : 1
             */

            private String id;
            private String name;
            private String parentId;
            private String sort;
            private List<?> children;

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

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }

        public static class ResumeSkillListBean {
            /**
             * id : deb374a1149d44f8a359506fc84d5970
             * name : 123
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
