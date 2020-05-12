package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class ZhuCiZhiWuBean extends CommonBean {


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
