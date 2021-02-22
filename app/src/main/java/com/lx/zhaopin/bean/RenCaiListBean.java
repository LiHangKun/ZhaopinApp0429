package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class RenCaiListBean extends CommonBean {


    /**
     * dataList : [{"age":"","avatar":"http://39.96.78.51/userfiles/member/2020/5/2ysgtysorn.jpg","education":{"id":"899919864ee84faba953e6c435bfa0e8","name":"本科"},"experienceEducation":{"id":"c00abaf6482e4ee98ad80f81e11c4418","major":"发酵技术","school":"图图吃蛋糕大学"},"experienceWorkList":[{"beginDate":"2017-04-30","companyName":"的服务范围黑丝等你多久","endDate":"2020-06-30","id":"7d3d724f6e2543a596d0ab05c4364285","positionName":"发变得患得患失素","skills":"得好多话,都觉得好多话,发几个解放军,都觉得好多话,发几个解放军,放假放假多久"}],"id":"4ecc5e04ac094516a43edcca0285994f","latestCity":{"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":""},"maxSalary":"","minSalary":"","name":"Seven","resumeSkillList":[{"id":"2017-04-30","name":"的服务范围黑丝等你多久"}],"sex":"1","workYears":""}]
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
         * age :
         * avatar : http://39.96.78.51/userfiles/member/2020/5/2ysgtysorn.jpg
         * education : {"id":"899919864ee84faba953e6c435bfa0e8","name":"本科"}
         * experienceEducation : {"id":"c00abaf6482e4ee98ad80f81e11c4418","major":"发酵技术","school":"图图吃蛋糕大学"}
         * experienceWorkList : [{"beginDate":"2017-04-30","companyName":"的服务范围黑丝等你多久","endDate":"2020-06-30","id":"7d3d724f6e2543a596d0ab05c4364285","positionName":"发变得患得患失素","skills":"得好多话,都觉得好多话,发几个解放军,都觉得好多话,发几个解放军,放假放假多久"}]
         * id : 4ecc5e04ac094516a43edcca0285994f
         * latestCity : {"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":""}
         * maxSalary :
         * minSalary :
         * name : Seven
         * resumeSkillList : [{"id":"2017-04-30","name":"的服务范围黑丝等你多久"}]
         * sex : 1
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
        private String sex;
        private String workYears;
        private List<ExperienceWorkListBean> experienceWorkList;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getWorkYears() {
            return workYears;
        }

        public void setWorkYears(String workYears) {
            this.workYears = workYears;
        }

        public List<ExperienceWorkListBean> getExperienceWorkList() {
            return experienceWorkList;
        }

        public void setExperienceWorkList(List<ExperienceWorkListBean> experienceWorkList) {
            this.experienceWorkList = experienceWorkList;
        }

        public List<ResumeSkillListBean> getResumeSkillList() {
            return resumeSkillList;
        }

        public void setResumeSkillList(List<ResumeSkillListBean> resumeSkillList) {
            this.resumeSkillList = resumeSkillList;
        }

        public static class EducationBean {
            /**
             * id : 899919864ee84faba953e6c435bfa0e8
             * name : 本科
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
             * id : c00abaf6482e4ee98ad80f81e11c4418
             * major : 发酵技术
             * school : 图图吃蛋糕大学
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
             * sort :
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

        public static class ExperienceWorkListBean {
            /**
             * beginDate : 2017-04-30
             * companyName : 的服务范围黑丝等你多久
             * endDate : 2020-06-30
             * id : 7d3d724f6e2543a596d0ab05c4364285
             * positionName : 发变得患得患失素
             * skills : 得好多话,都觉得好多话,发几个解放军,都觉得好多话,发几个解放军,放假放假多久
             */

            private String beginDate;
            private String companyName;
            private String endDate;
            private String id;
            private String positionName;
            private String skills;
            private String experience;

            public String getExperience() {
                return experience;
            }

            public void setExperience(String experience) {
                this.experience = experience;
            }

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

        public static class ResumeSkillListBean {
            /**
             * id : 2017-04-30
             * name : 的服务范围黑丝等你多久
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
