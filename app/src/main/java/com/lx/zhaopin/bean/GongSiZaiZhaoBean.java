package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class GongSiZaiZhaoBean extends CommonBean {


    /**
     * dataList : [{"city":{"children":[],"id":"320100","name":"南京市","parentId":"0","sort":30,"type":"2"},"company":{"id":"7f246b2831254cb1b9ba5c2441668541","images":"/userfiles/company/2020/5/qprkzn4q5x.png","intro":"服务好每一个客户","lat":"34.749272","lng":"113.735057","location":"郑东新区通泰路与宏昌街交叉口向东50米","logo":"/userfiles/company/2020/5/yqhwombvot.jpg","name":"立信","service":"致力于app开发"},"district":{"children":[],"id":"320104","name":"秦淮区","parentId":"0","sort":30},"duty":"理解客户需求","education":{"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"},"exIdList":[],"experienceYear":{"id":"09262bb793484743af54627c3ee55283","maxYear":5,"minYear":2,"name":"2-5"},"id":"e435dcd180fc4ca1a14fbf30220c1677","industry1IdList":[],"industry2IdList":[],"location":"秦淮湖畔","maxSalary":10,"minSalary":7,"name":"产品经理","opened":"1","positionCategory1IdList":[],"positionCategory2IdList":[],"positionCategory3IdList":[],"positionStatus":"2","positionType":"3","publishDate":"2020-05-16 09:16:56","skills":"墨刀","workfare":"双休,五险一金,员工旅游,节假日福利"},{"city":{"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":30,"type":"2"},"company":{"id":"7f246b2831254cb1b9ba5c2441668541","images":"/userfiles/company/2020/5/qprkzn4q5x.png","intro":"服务好每一个客户","lat":"34.749272","lng":"113.735057","location":"郑东新区通泰路与宏昌街交叉口向东50米","logo":"/userfiles/company/2020/5/yqhwombvot.jpg","name":"立信","service":"致力于app开发"},"district":{"children":[],"id":"410105","name":"金水区","parentId":"0","sort":30},"duty":"财务","education":{"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"},"exIdList":[],"experienceYear":{"id":"165deca730524295809095ff8ac3e8a3","maxYear":5,"minYear":1,"name":"1-5"},"id":"2310fe4af89a46719e4071b4f0d26135","industry1IdList":[],"industry2IdList":[],"location":"商都世贸中心","maxSalary":8,"minSalary":6,"name":"财务","opened":"1","positionCategory1IdList":[],"positionCategory2IdList":[],"positionCategory3IdList":[],"positionStatus":"2","positionType":"2","publishDate":"2020-05-16 09:03:48","skills":"不限","workfare":"双休,五险一金"},{"city":{"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":30,"type":"2"},"company":{"id":"7f246b2831254cb1b9ba5c2441668541","images":"/userfiles/company/2020/5/qprkzn4q5x.png","intro":"服务好每一个客户","lat":"34.749272","lng":"113.735057","location":"郑东新区通泰路与宏昌街交叉口向东50米","logo":"/userfiles/company/2020/5/yqhwombvot.jpg","name":"立信","service":"致力于app开发"},"district":{"children":[],"id":"410105","name":"金水区","parentId":"0","sort":30},"duty":"高比例还原设计图稿，配合团队完成项目","education":{"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"},"exIdList":[],"experienceYear":{"id":"165deca730524295809095ff8ac3e8a3","maxYear":5,"minYear":1,"name":"1-5"},"id":"b29e3f08e6fb4af29801d0636c852ca4","industry1IdList":[],"industry2IdList":[],"location":"商都世贸中心","maxSalary":12,"minSalary":6,"name":"开发测试","opened":"1","positionCategory1IdList":[],"positionCategory2IdList":[],"positionCategory3IdList":[],"positionStatus":"2","positionType":"2","publishDate":"2020-05-16 09:03:04","skills":"html|css|javascript|jq|vue","workfare":"双休,五险一金"},{"city":{"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":30,"type":"2"},"company":{"id":"7f246b2831254cb1b9ba5c2441668541","images":"/userfiles/company/2020/5/qprkzn4q5x.png","intro":"服务好每一个客户","lat":"34.749272","lng":"113.735057","location":"郑东新区通泰路与宏昌街交叉口向东50米","logo":"/userfiles/company/2020/5/yqhwombvot.jpg","name":"立信","service":"致力于app开发"},"district":{"children":[""],"id":"410105","name":"金水区","parentId":"0","sort":30},"duty":"java开发","education":{"id":"899919864ee84faba953e6c435bfa0e8","name":"本科"},"exIdList":[],"experienceYear":{"id":"09262bb793484743af54627c3ee55283","maxYear":5,"minYear":2,"name":"2-5"},"id":"091495aff3d94094ba3d1b410abc389f","industry1IdList":[],"industry2IdList":[],"location":"商都世贸中心","maxSalary":10,"minSalary":7,"name":"java开发","opened":"1","positionCategory1IdList":[],"positionCategory2IdList":[],"positionCategory3IdList":[],"positionStatus":"2","positionType":"1","publishDate":"2020-05-16 08:59:33","skills":"java","workfare":"双休,五险一金"}]
     * pageNo : 1
     * pageSize : 10
     * totalCount : 4
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
         * city : {"children":[],"id":"320100","name":"南京市","parentId":"0","sort":30,"type":"2"}
         * company : {"id":"7f246b2831254cb1b9ba5c2441668541","images":"/userfiles/company/2020/5/qprkzn4q5x.png","intro":"服务好每一个客户","lat":"34.749272","lng":"113.735057","location":"郑东新区通泰路与宏昌街交叉口向东50米","logo":"/userfiles/company/2020/5/yqhwombvot.jpg","name":"立信","service":"致力于app开发"}
         * district : {"children":[],"id":"320104","name":"秦淮区","parentId":"0","sort":30}
         * duty : 理解客户需求
         * education : {"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"}
         * exIdList : []
         * experienceYear : {"id":"09262bb793484743af54627c3ee55283","maxYear":5,"minYear":2,"name":"2-5"}
         * id : e435dcd180fc4ca1a14fbf30220c1677
         * industry1IdList : []
         * industry2IdList : []
         * location : 秦淮湖畔
         * maxSalary : 10
         * minSalary : 7
         * name : 产品经理
         * opened : 1
         * positionCategory1IdList : []
         * positionCategory2IdList : []
         * positionCategory3IdList : []
         * positionStatus : 2
         * positionType : 3
         * publishDate : 2020-05-16 09:16:56
         * skills : 墨刀
         * workfare : 双休,五险一金,员工旅游,节假日福利
         */

        private CityBean city;
        private CompanyBean company;
        private DistrictBean district;
        private String duty;
        private EducationBean education;
        private ExperienceYearBean experienceYear;
        private String id;
        private String location;
        private int maxSalary;
        private int minSalary;
        private String name;
        private String opened;
        private String positionStatus;
        private String positionType;
        private String publishDate;
        private String skills;
        private String workfare;
        private List<?> exIdList;
        private List<?> industry1IdList;
        private List<?> industry2IdList;
        private List<?> positionCategory1IdList;
        private List<?> positionCategory2IdList;
        private List<?> positionCategory3IdList;

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

        public String getOpened() {
            return opened;
        }

        public void setOpened(String opened) {
            this.opened = opened;
        }

        public String getPositionStatus() {
            return positionStatus;
        }

        public void setPositionStatus(String positionStatus) {
            this.positionStatus = positionStatus;
        }

        public String getPositionType() {
            return positionType;
        }

        public void setPositionType(String positionType) {
            this.positionType = positionType;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
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

        public List<?> getExIdList() {
            return exIdList;
        }

        public void setExIdList(List<?> exIdList) {
            this.exIdList = exIdList;
        }

        public List<?> getIndustry1IdList() {
            return industry1IdList;
        }

        public void setIndustry1IdList(List<?> industry1IdList) {
            this.industry1IdList = industry1IdList;
        }

        public List<?> getIndustry2IdList() {
            return industry2IdList;
        }

        public void setIndustry2IdList(List<?> industry2IdList) {
            this.industry2IdList = industry2IdList;
        }

        public List<?> getPositionCategory1IdList() {
            return positionCategory1IdList;
        }

        public void setPositionCategory1IdList(List<?> positionCategory1IdList) {
            this.positionCategory1IdList = positionCategory1IdList;
        }

        public List<?> getPositionCategory2IdList() {
            return positionCategory2IdList;
        }

        public void setPositionCategory2IdList(List<?> positionCategory2IdList) {
            this.positionCategory2IdList = positionCategory2IdList;
        }

        public List<?> getPositionCategory3IdList() {
            return positionCategory3IdList;
        }

        public void setPositionCategory3IdList(List<?> positionCategory3IdList) {
            this.positionCategory3IdList = positionCategory3IdList;
        }

        public static class CityBean {
            /**
             * children : []
             * id : 320100
             * name : 南京市
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

        public static class CompanyBean {
            /**
             * id : 7f246b2831254cb1b9ba5c2441668541
             * images : /userfiles/company/2020/5/qprkzn4q5x.png
             * intro : 服务好每一个客户
             * lat : 34.749272
             * lng : 113.735057
             * location : 郑东新区通泰路与宏昌街交叉口向东50米
             * logo : /userfiles/company/2020/5/yqhwombvot.jpg
             * name : 立信
             * service : 致力于app开发
             */

            private String id;
            private String images;
            private String intro;
            private String lat;
            private String lng;
            private String location;
            private String logo;
            private String name;
            private String service;

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
        }

        public static class DistrictBean {
            /**
             * children : []
             * id : 320104
             * name : 秦淮区
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

        public static class ExperienceYearBean {
            /**
             * id : 09262bb793484743af54627c3ee55283
             * maxYear : 5
             * minYear : 2
             * name : 2-5
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
}
