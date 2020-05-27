package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class PingBiRen extends CommonBean {


    /**
     * dataList : [{"age":"1","avatar":"http://39.96.78.51/userfiles/member/2020/5/fa2ef5j8zp.jpg","birthday":"1989-04","education":{"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"},"experienceEducation":{"id":"493c9d8ce3d2479eb9a31179962ccaf8","major":"旅途","school":"好多好多话"},"id":"04019f73793d4e3d906d70c0a27af8bb","latestCity":{"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":"1"},"maxSalary":"1","minSalary":"1","name":"冯茗","openResume":"1","resumeSkillList":[{"id":"9bfa9e7c1c754ed49d9346f9189c9950","name":"常胜大神"},{"id":"1664aeae7a3a48aba0fc944764a18cc6","name":"统计局高手"},{"id":"3fa4688da69f4d2b8157247c008ff491","name":"MSN哦婆婆婆婆婆婆哦哦哦"},{"id":"57d350fac4ea4cb485459a2b2d5943f4","name":"你婆婆送你"},{"id":"ce36757f8ba84ffd9487280059adb03f","name":"木木木ROM您破肉末哦婆母欧诺磨破红肉诺诺诺偷摸哦(´-ω-`)摸头欧诺"}],"sex":"1","workDate":"2014-05-01","workYears":"1"}]
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
         * avatar : http://39.96.78.51/userfiles/member/2020/5/fa2ef5j8zp.jpg
         * birthday : 1989-04
         * education : {"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"}
         * experienceEducation : {"id":"493c9d8ce3d2479eb9a31179962ccaf8","major":"旅途","school":"好多好多话"}
         * id : 04019f73793d4e3d906d70c0a27af8bb
         * latestCity : {"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":"1"}
         * maxSalary : 1
         * minSalary : 1
         * name : 冯茗
         * openResume : 1
         * resumeSkillList : [{"id":"9bfa9e7c1c754ed49d9346f9189c9950","name":"常胜大神"},{"id":"1664aeae7a3a48aba0fc944764a18cc6","name":"统计局高手"},{"id":"3fa4688da69f4d2b8157247c008ff491","name":"MSN哦婆婆婆婆婆婆哦哦哦"},{"id":"57d350fac4ea4cb485459a2b2d5943f4","name":"你婆婆送你"},{"id":"ce36757f8ba84ffd9487280059adb03f","name":"木木木ROM您破肉末哦婆母欧诺磨破红肉诺诺诺偷摸哦(´-ω-`)摸头欧诺"}]
         * sex : 1
         * workDate : 2014-05-01
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
             * id : 493c9d8ce3d2479eb9a31179962ccaf8
             * major : 旅途
             * school : 好多好多话
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
             * id : 9bfa9e7c1c754ed49d9346f9189c9950
             * name : 常胜大神
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
