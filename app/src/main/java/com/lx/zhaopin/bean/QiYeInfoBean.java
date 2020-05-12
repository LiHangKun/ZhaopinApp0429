package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

public class QiYeInfoBean extends CommonBean {


    /**
     * Industry : {"name":""}
     * city : {"id":"","name":"名称"}
     * collected : 1
     * district : {"id":"","name":"名称"}
     * financing : {"name":""}
     * fund :
     * id :
     * images :
     * intro :
     * lat :
     * legalPerson :
     * lng :
     * location :
     * logo :
     * name :
     * service :
     * staffNum :
     */

    private IndustryBean Industry;
    private CityBean city;
    private String collected;
    private DistrictBean district;
    private FinancingBean financing;
    private String fund;
    private String id;
    private String images;
    private String intro;
    private String lat;
    private String legalPerson;
    private String lng;
    private String location;
    private String logo;
    private String name;
    private String service;
    private String staffNum;

    public IndustryBean getIndustry() {
        return Industry;
    }

    public void setIndustry(IndustryBean Industry) {
        this.Industry = Industry;
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

    public DistrictBean getDistrict() {
        return district;
    }

    public void setDistrict(DistrictBean district) {
        this.district = district;
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

    public static class IndustryBean {
        /**
         * name :
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CityBean {
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

    public static class DistrictBean {
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

    public static class FinancingBean {
        /**
         * name :
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
