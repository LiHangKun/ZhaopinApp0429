package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class QiuZhiyiXiangBean extends CommonBean {


    /**
     * arrivalTime : 3
     * jobNature : 2
     * jobStatus : 4
     * resumeExpectationList : [{"city":{"id":"330100","name":"杭州市"},"id":"3367250a93f14a61afcde8e785752421","maxSalary":7,"minSalary":4,"positionCategory3":{"id":"6f4b48602beb4d3482129c7ebc3dcfaa","name":"三级职位"},"resumeExpectationIndustryList":[{"id":"2108b1064dc24456bfd2e5ca1696b199","name":"金融"},{"id":"bafe78fd30e542ef94ad8ddfd0c45fd8","name":"互联网"}]},{"city":{"id":"110100","name":"北京城区"},"id":"8b6a4c19000545a78eab09fb7c69f609","maxSalary":10,"minSalary":8,"positionCategory3":{"id":"6f4b48602beb4d3482129c7ebc3dcfaa","name":"三级职位"},"resumeExpectationIndustryList":[{"id":"8342740fa1fe419dac350c34b6031adf","name":"二级2"}]}]
     */

    private String arrivalTime;
    private String jobNature;
    private String jobStatus;
    private List<ResumeExpectationListBean> resumeExpectationList;

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

    public List<ResumeExpectationListBean> getResumeExpectationList() {
        return resumeExpectationList;
    }

    public void setResumeExpectationList(List<ResumeExpectationListBean> resumeExpectationList) {
        this.resumeExpectationList = resumeExpectationList;
    }

    public static class ResumeExpectationListBean {
        /**
         * city : {"id":"330100","name":"杭州市"}
         * id : 3367250a93f14a61afcde8e785752421
         * maxSalary : 7
         * minSalary : 4
         * positionCategory3 : {"id":"6f4b48602beb4d3482129c7ebc3dcfaa","name":"三级职位"}
         * resumeExpectationIndustryList : [{"id":"2108b1064dc24456bfd2e5ca1696b199","name":"金融"},{"id":"bafe78fd30e542ef94ad8ddfd0c45fd8","name":"互联网"}]
         */

        private CityBean city;
        private String id;
        private int maxSalary;
        private int minSalary;
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

        public int getMaxSalary() {
            return maxSalary;
        }

        public void setMaxSalary(int maxSalary) {
            this.maxSalary = maxSalary;
        }

        public int getMinSalary() {
            return minSalary;
        }

        public void setMinSalary(int minSalary) {
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
             * id : 330100
             * name : 杭州市
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
             * id : 6f4b48602beb4d3482129c7ebc3dcfaa
             * name : 三级职位
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
             * id : 2108b1064dc24456bfd2e5ca1696b199
             * name : 金融
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
