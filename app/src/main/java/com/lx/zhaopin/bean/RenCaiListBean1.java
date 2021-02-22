package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class RenCaiListBean1 extends CommonBean {


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
         * id : 358f06996a5b4a7a96f88f64cb7a19fe
         * name : 用户2289
         * avatar : http://39.96.78.51/userfiles/avatar.jpg
         * workYears : 0
         * educationName : 文盲
         * latestCityName :
         * superiority : 暂无数据
         * max : 0
         * min : 0
         * positionName : 无工作经历
         * companyName : 无工作经历
         * age : 2
         * works : []
         * resumeExpectationList : []
         */

        private String id;
        private String name;
        private String avatar;
        private String workYears;
        private String educationName;
        private String latestCityName;
        private String superiority;
        private int max;
        private int min;
        private String positionName;
        private String companyName;
        private int age;
        private List<?> works;
        private List<?> resumeExpectationList;

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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getWorkYears() {
            return workYears;
        }

        public void setWorkYears(String workYears) {
            this.workYears = workYears;
        }

        public String getEducationName() {
            return educationName;
        }

        public void setEducationName(String educationName) {
            this.educationName = educationName;
        }

        public String getLatestCityName() {
            return latestCityName;
        }

        public void setLatestCityName(String latestCityName) {
            this.latestCityName = latestCityName;
        }

        public String getSuperiority() {
            return superiority;
        }

        public void setSuperiority(String superiority) {
            this.superiority = superiority;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<?> getWorks() {
            return works;
        }

        public void setWorks(List<?> works) {
            this.works = works;
        }

        public List<?> getResumeExpectationList() {
            return resumeExpectationList;
        }

        public void setResumeExpectationList(List<?> resumeExpectationList) {
            this.resumeExpectationList = resumeExpectationList;
        }
    }
}
