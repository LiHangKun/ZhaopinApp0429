package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class QiYeInfoBean extends CommonBean {


    /**
     * auditStatus : 2
     * city : {"children":[""],"id":"410100","name":"郑州市","parentId":"0","sort":""}
     * collected : 1
     * financing : {"id":"4edccb5a934741bc95e509251ddd6896","name":"一轮融资"}
     * fund : 1000万
     * id : 7f246b2831254cb1b9ba5c2441668541
     * images : http://39.96.78.51/userfiles/company/2020/5/qprkzn4q5x.png
     * industry : {"children":[""],"id":"8342740fa1fe419dac350c34b6031adf","name":"二级2","parentId":"0","sort":""}
     * intro : 服务好每一个客户
     * lat : 34.749272
     * legalPerson : 郝先生
     * lng : 113.735057
     * location : 郑东新区通泰路与宏昌街交叉口向东50米
     * logo : http://39.96.78.51/userfiles/company/2020/5/yqhwombvot.jpg
     * mobile : 13203812466
     * name : 立信
     * service : 致力于app开发
     * staffNum : 100
     */

    private String auditStatus;
    private CityBean city;
    private String collected;
    private FinancingBean financing;
    private String fund;
    private String id;
    private String images;
    private IndustryBean industry;
    private String intro;
    private String lat;
    private String legalPerson;
    private String lng;
    private String location;
    private String logo;
    private String mobile;
    private String name;
    private String service;
    private String staffNum;

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    public String getCollected() {
        return collected;
    }

    public void setCollected(String collected) {
        this.collected = collected;
    }

    public FinancingBean getFinancing() {
        return financing;
    }

    public void setFinancing(FinancingBean financing) {
        this.financing = financing;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public IndustryBean getIndustry() {
        return industry;
    }

    public void setIndustry(IndustryBean industry) {
        this.industry = industry;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public static class CityBean {
        /**
         * children : [""]
         * id : 410100
         * name : 郑州市
         * parentId : 0
         * sort :
         */

        private String id;
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

    public static class FinancingBean {
        /**
         * id : 4edccb5a934741bc95e509251ddd6896
         * name : 一轮融资
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

    public static class IndustryBean {
        /**
         * children : [""]
         * id : 8342740fa1fe419dac350c34b6031adf
         * name : 二级2
         * parentId : 0
         * sort :
         */

        private String id;
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
}
