package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class QiuZhiyiXiangBean extends CommonBean {


    /**
     * arrivalTime : 3
     * dataList : [{"city":{"children":[""],"id":"110100","name":"北京城区","parentId":"0","sort":"","type":"2"},"id":"8b6a4c19000545a78eab09fb7c69f609","maxSalary":"","minSalary":"","positionCategory3":{"children":[""],"id":"6f4b48602beb4d3482129c7ebc3dcfaa","level":"3","name":"三级职位","parentId":"0","sort":""},"principal":"0","province":{"children":[""],"id":"110000","parentId":"0","sort":""},"resumeExpectationIndustryList":[""]}]
     * jobNature : 2
     * jobStatus : 4
     */

    private String arrivalTime;
    private String jobNature;
    private String jobStatus;
    private List<DataListBean> dataList;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * city : {"children":[""],"id":"110100","name":"北京城区","parentId":"0","sort":"","type":"2"}
         * id : 8b6a4c19000545a78eab09fb7c69f609
         * maxSalary :
         * minSalary :
         * positionCategory3 : {"children":[""],"id":"6f4b48602beb4d3482129c7ebc3dcfaa","level":"3","name":"三级职位","parentId":"0","sort":""}
         * principal : 0
         * province : {"children":[""],"id":"110000","parentId":"0","sort":""}
         * resumeExpectationIndustryList : [""]
         */

        private CityBean city;
        private String id;
        private String maxSalary;
        private String minSalary;
        private PositionCategory3Bean positionCategory3;
        private String principal;
        private ProvinceBean province;
        private List<String> resumeExpectationIndustryList;

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

        public String getPrincipal() {
            return principal;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
        }

        public ProvinceBean getProvince() {
            return province;
        }

        public void setProvince(ProvinceBean province) {
            this.province = province;
        }

        public List<String> getResumeExpectationIndustryList() {
            return resumeExpectationIndustryList;
        }

        public void setResumeExpectationIndustryList(List<String> resumeExpectationIndustryList) {
            this.resumeExpectationIndustryList = resumeExpectationIndustryList;
        }

        public static class CityBean {
            /**
             * children : [""]
             * id : 110100
             * name : 北京城区
             * parentId : 0
             * sort :
             * type : 2
             */

            private String id;
            private String name;
            private String parentId;
            private String sort;
            private String type;
            private List<String> children;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<String> getChildren() {
                return children;
            }

            public void setChildren(List<String> children) {
                this.children = children;
            }
        }

        public static class PositionCategory3Bean {
            /**
             * children : [""]
             * id : 6f4b48602beb4d3482129c7ebc3dcfaa
             * level : 3
             * name : 三级职位
             * parentId : 0
             * sort :
             */

            private String id;
            private String level;
            private String name;
            private String parentId;
            private String sort;
            private List<String> children;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
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

            public List<String> getChildren() {
                return children;
            }

            public void setChildren(List<String> children) {
                this.children = children;
            }
        }

        public static class ProvinceBean {
            /**
             * children : [""]
             * id : 110000
             * parentId : 0
             * sort :
             */

            private String id;
            private String parentId;
            private String sort;
            private List<String> children;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public List<String> getChildren() {
                return children;
            }

            public void setChildren(List<String> children) {
                this.children = children;
            }
        }
    }
}
