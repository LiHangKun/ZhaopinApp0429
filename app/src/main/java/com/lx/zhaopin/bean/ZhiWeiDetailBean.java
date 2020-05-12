package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

public class ZhiWeiDetailBean extends CommonBean {


    /**
     * HRID :
     * city : {"id":"","name":"名称"}
     * company : {"city":{"id":"","name":"名称"},"district":{"id":"","name":"名称"},"financing":{"name":""},"id":"","images":"","industry":{"name":""},"intro":"","lat":"","lng":"","logo":"","name":"","staffNum":""}
     * deliverResume :
     * delivered :
     * duty :
     * education : {"id":"","name":"名称"}
     * experienceYear : {"id":"","name":"名称"}
     * id :
     * location :
     * maxSalary :
     * minSalary :
     * name : 职位名称
     * positionType : 1
     * skills :
     * workfare :
     */

    private String HRID;
    private CityBean city;
    private CompanyBean company;
    private String deliverResume;
    private String delivered;
    private String duty;
    private EducationBean education;
    private ExperienceYearBean experienceYear;
    private String id;
    private String location;
    private String maxSalary;
    private String minSalary;
    private String name;
    private String positionType;
    private String collected;

    public String getCollected() {
        return collected;
    }

    public void setCollected(String collected) {
        this.collected = collected;
    }

    private String skills;
    private String workfare;

    public String getHRID() {
        return HRID;
    }

    public void setHRID(String HRID) {
        this.HRID = HRID;
    }

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }

    public String getDeliverResume() {
        return deliverResume;
    }

    public void setDeliverResume(String deliverResume) {
        this.deliverResume = deliverResume;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public EducationBean getEducation() {
        return education;
    }

    public void setEducation(EducationBean education) {
        this.education = education;
    }

    public ExperienceYearBean getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(ExperienceYearBean experienceYear) {
        this.experienceYear = experienceYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getWorkfare() {
        return workfare;
    }

    public void setWorkfare(String workfare) {
        this.workfare = workfare;
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

    public static class CompanyBean {
        /**
         * city : {"id":"","name":"名称"}
         * district : {"id":"","name":"名称"}
         * financing : {"name":""}
         * id :
         * images :
         * industry : {"name":""}
         * intro :
         * lat :
         * lng :
         * logo :
         * name :
         * staffNum :
         */

        private CityBeanX city;
        private DistrictBean district;
        private FinancingBean financing;
        private String id;
        private String images;
        private IndustryBean industry;
        private String intro;
        private String lat;
        private String lng;
        private String logo;
        private String name;
        private String staffNum;

        public CityBeanX getCity() {
            return city;
        }

        public void setCity(CityBeanX city) {
            this.city = city;
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

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
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

        public String getStaffNum() {
            return staffNum;
        }

        public void setStaffNum(String staffNum) {
            this.staffNum = staffNum;
        }

        public static class CityBeanX {
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
    }

    public static class EducationBean {
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

    public static class ExperienceYearBean {
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
}
