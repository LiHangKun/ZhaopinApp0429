package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.io.Serializable;
import java.util.List;

public class ZhiWeiDetailBean extends CommonBean implements Serializable {


    /**
     * HRID : hre6151ce13783416bad54fffc10b19c0d
     * city : {"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":30,"type":"2"}
     * collected : 1
     * company : {"city":{"children":["",""],"id":"410100","name":"郑州市","parentId":"0","sort":30},"financing":{"id":"4edccb5a934741bc95e509251ddd6896","name":"一轮融资"},"id":"7f246b2831254cb1b9ba5c2441668541","images":"/userfiles/company/2020/5/qprkzn4q5x.png","industry":{"children":["",""],"id":"8342740fa1fe419dac350c34b6031adf","name":"二级2","parentId":"0","sort":30},"intro":"服务好每一个客户","lat":"34.749272","lng":"113.735057","location":"郑东新区通泰路与宏昌街交叉口向东50米","logo":"http://39.96.78.51/userfiles/company/2020/5/yqhwombvot.jpg","name":"立信","staffNum":"100"}
     * deliverResume : 0
     * district : {"children":[],"id":"410105","name":"金水区","parentId":"0","sort":30}
     * duty : 高比例还原设计图稿，配合团队完成项目
     * education : {"id":"899919864ee84faba953e6c435bfa0e8","name":"本科"}
     * experienceYear : {"id":"165deca730524295809095ff8ac3e8a3","maxYear":5,"minYear":1,"name":"1-5"}
     * id : 045fc44509444842ad27d4a7ee30a021
     * location : 商都世贸中心
     * maxSalary : 12
     * minSalary : 6
     * name : 前端开发
     * positionType : 2
     * skills : html|css|javascript|jq|vue
     * workfare : 双休,五险一金
     */

    private String HRID;
    private String HRName;

    public String getHRName() {
        return HRName;
    }

    public void setHRName(String HRName) {
        this.HRName = HRName;
    }

    private CityBean city;
    private String collected;
    private CompanyBean company;
    private String deliverResume;
    private String delivered;

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    private DistrictBean district;
    private String duty;
    private EducationBean education;
    private ExperienceYearBean experienceYear;
    private String id;
    private String location;
    private int maxSalary;
    private int minSalary;
    private String name;
    private String positionType;
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

    public String getCollected() {
        return collected;
    }

    public void setCollected(String collected) {
        this.collected = collected;
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

    public DistrictBean getDistrict() {
        return district;
    }

    public void setDistrict(DistrictBean district) {
        this.district = district;
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

    public static class CityBean implements Serializable{
        /**
         * children : []
         * id : 410100
         * name : 郑州市
         * parentId : 0
         * sort : 30
         * type : 2
         */

        private String id;
        private String name;
        private String parentId;
        private int sort;
        private String type;
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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }

    public static class CompanyBean implements Serializable{
        /**
         * city : {"children":["",""],"id":"410100","name":"郑州市","parentId":"0","sort":30}
         * financing : {"id":"4edccb5a934741bc95e509251ddd6896","name":"一轮融资"}
         * id : 7f246b2831254cb1b9ba5c2441668541
         * images : /userfiles/company/2020/5/qprkzn4q5x.png
         * industry : {"children":["",""],"id":"8342740fa1fe419dac350c34b6031adf","name":"二级2","parentId":"0","sort":30}
         * intro : 服务好每一个客户
         * lat : 34.749272
         * lng : 113.735057
         * location : 郑东新区通泰路与宏昌街交叉口向东50米
         * logo : http://39.96.78.51/userfiles/company/2020/5/yqhwombvot.jpg
         * name : 立信
         * staffNum : 100
         */

        private CityBeanX city;
        private FinancingBean financing;
        private String id;
        private String images;
        private IndustryBean industry;
        private String intro;
        private String lat;
        private String lng;
        private String location;
        private String logo;
        private String name;
        private String staffNum;

        public CityBeanX getCity() {
            return city;
        }

        public void setCity(CityBeanX city) {
            this.city = city;
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

        public String getStaffNum() {
            return staffNum;
        }

        public void setStaffNum(String staffNum) {
            this.staffNum = staffNum;
        }

        public static class CityBeanX implements Serializable{
            /**
             * children : ["",""]
             * id : 410100
             * name : 郑州市
             * parentId : 0
             * sort : 30
             */

            private String id;
            private String name;
            private String parentId;
            private int sort;
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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<String> getChildren() {
                return children;
            }

            public void setChildren(List<String> children) {
                this.children = children;
            }
        }

        public static class FinancingBean implements Serializable{
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

        public static class IndustryBean implements Serializable{
            /**
             * children : ["",""]
             * id : 8342740fa1fe419dac350c34b6031adf
             * name : 二级2
             * parentId : 0
             * sort : 30
             */

            private String id;
            private String name;
            private String parentId;
            private int sort;
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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
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

    public static class DistrictBean implements Serializable{
        /**
         * children : []
         * id : 410105
         * name : 金水区
         * parentId : 0
         * sort : 30
         */

        private String id;
        private String name;
        private String parentId;
        private int sort;
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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }

    public static class EducationBean implements Serializable{
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

    public static class ExperienceYearBean implements Serializable{
        /**
         * id : 165deca730524295809095ff8ac3e8a3
         * maxYear : 5
         * minYear : 1
         * name : 1-5
         */

        private String id;
        private int maxYear;
        private int minYear;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getMaxYear() {
            return maxYear;
        }

        public void setMaxYear(int maxYear) {
            this.maxYear = maxYear;
        }

        public int getMinYear() {
            return minYear;
        }

        public void setMinYear(int minYear) {
            this.minYear = minYear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
