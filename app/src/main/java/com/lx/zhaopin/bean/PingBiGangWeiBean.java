package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class PingBiGangWeiBean extends CommonBean {

    /**
     * pageNo : 1
     * totalPage : 1
     * dataList : [{"id":"8defa9ffd45344d5baab0dbea4c22ae3","company":{"name":"天津恒佳企业管理服务有限公司","logo":"http://39.96.78.51/userfiles/company/2020/7/pidnofthbo.png","intro":"恒佳人力是中国唯一一家新三板创新层上市的公众公司，股票代码为\u201c837953\u201d。\n天津恒佳企业管理服务有限公司是一家集企业管理咨询、人力资源外包、国内劳务派遣于一体的综合性管理服务公司。汇集业内精英，依托全国范围的服务网络、先进的互联网服务平台和强大的计算机技术支持，为客户提供人力资源外包，涵盖人力资源派遣、人事代理、招聘外包、法务咨询、福利管理、咨询与培训等全方位、多层次、高效率的一站式人力资源外包服务，致力于持续推动中国人力资源外包产业健康发展。\n在2016年成功上市，进入新三板基础层，并在2017年成功进入新三板创新层至今。全国唯一新三板创新层的上市人力资源公司，全国有32家分子公司，已在全国近百个主要城市设立分公司及办事处，业务覆盖全国多个城市。\n恒佳将以卓越的创新能力、一流的战略和运营管理水平，成为社会尊敬、客户信赖、员工自豪的具全球竞争力的标杆企业。\n","financingName":"","industry2Name":"","visits":0},"name":"人资经理","education":{"name":"专科"},"city":{"name":"天津","sort":30,"children":[],"parentId":"0"},"district":{"name":"滨海新区","sort":30,"children":[],"parentId":"0"},"location":"第六大街110号","positionType":"1","experienceYear":{"name":"5-10年"},"minSalary":10,"maxSalary":15,"opened":"1","workfare":"","recruiter":{"id":"d6538b7bede242b2b20e35f5c3146f6d","createDate":"2020-07-21 15:10:45","updateDate":"2020-07-21 15:10:45","jobNature":"1","jobStatus":"1","arrivalTime":"1","name":"恒佳王春燕","mobile":"15122959990","avatar":"http://39.96.78.51/userfiles/member/2020/7/xkkdscw8xc.jpeg","sex":"1","birthday":"1984-04","workDate":"2007-07-01","memberStatus":"1","recruiter":"1","registerDate":"2020-07-21 15:10:45","password":"1fb83a148741ec0ade97a6fb9d691e3948799780c8625308e3b1596c","lastReadDate":"2020-07-21 15:10:45","openResume":"0","openChat":"0","neverLogin":"0","improvedDegree":25,"collectCount":0,"latestCityName":"","positionName":"HRD"}},{"id":"e064d9045027466e8874a3563fdf9c4c","company":{"name":"万仕联（天津）房地产经纪有限公司","logo":"http://39.96.78.51/userfiles/company/2020/8/fiaw8alto1.jpg","intro":"万仕联（天津）房地产经纪有限公司成立于2010年，是滨海新区专门从事房地产综合服务的企业之一。万仕联（天津）房地产经纪有限公司具有高效的资源平台，完善的网络运营体系，为客户提供安全、快捷、专业的全程置家服务。我们一直坚信人是企业最重要的财富，是企业发展的源动力，我们始终把培养房地产行业的销售精英当作最重要的事业去做。期待你的加入！成就我们共同的梦想！企业的高速发展使我们求贤若渴，思杰如疾，但做人要诚实，一定要提前告诉你的是：我们不会许诺令人垂涎的薪酬，但我们承诺\u201c万仕联的人生\u201d会令你受益终生；我们看到\u201c菜鸟\u201d也会头痛，但我们专业的培训会使每一棵新苗都能成为明日的精英！","financingName":"","industry2Name":"","visits":0},"name":"电话销售","education":{"name":"专科"},"city":{"name":"天津","sort":30,"children":[],"parentId":"0"},"district":{"name":"滨海新区","sort":30,"children":[],"parentId":"0"},"location":"杭州道街徐州里10栋底商","positionType":"2","experienceYear":{"name":"经验不限"},"minSalary":4,"maxSalary":8,"opened":"1","workfare":"无责底薪,不加班,带娃上班,60%提成","recruiter":{"id":"5d5bac1ead24420185c656262c61a385","createDate":"2020-08-03 17:39:26","updateDate":"2020-08-06 15:28:03","jobNature":"1","jobStatus":"1","arrivalTime":"1","name":"刘","mobile":"13821949213","avatar":"http://39.96.78.51/userfiles/member/2020/8/wvooypucir.jpeg","sex":"2","birthday":"1996-07","workDate":"2016-08-01","experienceEducation":{"id":"99ac6714d17347238fb401c45aec7f8e","school":"天津广播电视大学","major":"行政管理"},"education":{"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"},"educationSort":"3","memberStatus":"1","recruiter":"1","registerDate":"2020-08-03 17:39:26","password":"8e495ba9c52a775e5daf335308b52e2acf19e4c6643718a896751672","lastReadDate":"2020-08-03 17:39:26","openResume":"0","openChat":"1","neverLogin":"0","improvedDegree":100,"collectCount":0,"latestCityName":"","positionName":"人事经理"}}]
     * pageSize : 10
     * totalCount : 2
     */

    private int pageNo;
    private int totalPage;
    private int pageSize;
    private int totalCount;
    private List<DataListBean> dataList;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * id : 8defa9ffd45344d5baab0dbea4c22ae3
         * company : {"name":"天津恒佳企业管理服务有限公司","logo":"http://39.96.78.51/userfiles/company/2020/7/pidnofthbo.png","intro":"恒佳人力是中国唯一一家新三板创新层上市的公众公司，股票代码为\u201c837953\u201d。\n天津恒佳企业管理服务有限公司是一家集企业管理咨询、人力资源外包、国内劳务派遣于一体的综合性管理服务公司。汇集业内精英，依托全国范围的服务网络、先进的互联网服务平台和强大的计算机技术支持，为客户提供人力资源外包，涵盖人力资源派遣、人事代理、招聘外包、法务咨询、福利管理、咨询与培训等全方位、多层次、高效率的一站式人力资源外包服务，致力于持续推动中国人力资源外包产业健康发展。\n在2016年成功上市，进入新三板基础层，并在2017年成功进入新三板创新层至今。全国唯一新三板创新层的上市人力资源公司，全国有32家分子公司，已在全国近百个主要城市设立分公司及办事处，业务覆盖全国多个城市。\n恒佳将以卓越的创新能力、一流的战略和运营管理水平，成为社会尊敬、客户信赖、员工自豪的具全球竞争力的标杆企业。\n","financingName":"","industry2Name":"","visits":0}
         * name : 人资经理
         * education : {"name":"专科"}
         * city : {"name":"天津","sort":30,"children":[],"parentId":"0"}
         * district : {"name":"滨海新区","sort":30,"children":[],"parentId":"0"}
         * location : 第六大街110号
         * positionType : 1
         * experienceYear : {"name":"5-10年"}
         * minSalary : 10
         * maxSalary : 15
         * opened : 1
         * workfare :
         * recruiter : {"id":"d6538b7bede242b2b20e35f5c3146f6d","createDate":"2020-07-21 15:10:45","updateDate":"2020-07-21 15:10:45","jobNature":"1","jobStatus":"1","arrivalTime":"1","name":"恒佳王春燕","mobile":"15122959990","avatar":"http://39.96.78.51/userfiles/member/2020/7/xkkdscw8xc.jpeg","sex":"1","birthday":"1984-04","workDate":"2007-07-01","memberStatus":"1","recruiter":"1","registerDate":"2020-07-21 15:10:45","password":"1fb83a148741ec0ade97a6fb9d691e3948799780c8625308e3b1596c","lastReadDate":"2020-07-21 15:10:45","openResume":"0","openChat":"0","neverLogin":"0","improvedDegree":25,"collectCount":0,"latestCityName":"","positionName":"HRD"}
         */

        private String id;
        private CompanyBean company;
        private String name;
        private EducationBean education;
        private CityBean city;
        private DistrictBean district;
        private String location;
        private String positionType;
        private ExperienceYearBean experienceYear;
        private int minSalary;
        private int maxSalary;
        private String opened;
        private String workfare;
        private RecruiterBean recruiter;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public EducationBean getEducation() {
            return education;
        }

        public void setEducation(EducationBean education) {
            this.education = education;
        }

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public DistrictBean getDistrict() {
            return district;
        }

        public void setDistrict(DistrictBean district) {
            this.district = district;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getPositionType() {
            return positionType;
        }

        public void setPositionType(String positionType) {
            this.positionType = positionType;
        }

        public ExperienceYearBean getExperienceYear() {
            return experienceYear;
        }

        public void setExperienceYear(ExperienceYearBean experienceYear) {
            this.experienceYear = experienceYear;
        }

        public int getMinSalary() {
            return minSalary;
        }

        public void setMinSalary(int minSalary) {
            this.minSalary = minSalary;
        }

        public int getMaxSalary() {
            return maxSalary;
        }

        public void setMaxSalary(int maxSalary) {
            this.maxSalary = maxSalary;
        }

        public String getOpened() {
            return opened;
        }

        public void setOpened(String opened) {
            this.opened = opened;
        }

        public String getWorkfare() {
            return workfare;
        }

        public void setWorkfare(String workfare) {
            this.workfare = workfare;
        }

        public RecruiterBean getRecruiter() {
            return recruiter;
        }

        public void setRecruiter(RecruiterBean recruiter) {
            this.recruiter = recruiter;
        }

        public static class CompanyBean {
            /**
             * name : 天津恒佳企业管理服务有限公司
             * logo : http://39.96.78.51/userfiles/company/2020/7/pidnofthbo.png
             * intro : 恒佳人力是中国唯一一家新三板创新层上市的公众公司，股票代码为“837953”。
             天津恒佳企业管理服务有限公司是一家集企业管理咨询、人力资源外包、国内劳务派遣于一体的综合性管理服务公司。汇集业内精英，依托全国范围的服务网络、先进的互联网服务平台和强大的计算机技术支持，为客户提供人力资源外包，涵盖人力资源派遣、人事代理、招聘外包、法务咨询、福利管理、咨询与培训等全方位、多层次、高效率的一站式人力资源外包服务，致力于持续推动中国人力资源外包产业健康发展。
             在2016年成功上市，进入新三板基础层，并在2017年成功进入新三板创新层至今。全国唯一新三板创新层的上市人力资源公司，全国有32家分子公司，已在全国近百个主要城市设立分公司及办事处，业务覆盖全国多个城市。
             恒佳将以卓越的创新能力、一流的战略和运营管理水平，成为社会尊敬、客户信赖、员工自豪的具全球竞争力的标杆企业。
             * financingName :
             * industry2Name :
             * visits : 0
             */

            private String name;
            private String logo;
            private String intro;
            private String financingName;
            private String industry2Name;
            private int visits;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getFinancingName() {
                return financingName;
            }

            public void setFinancingName(String financingName) {
                this.financingName = financingName;
            }

            public String getIndustry2Name() {
                return industry2Name;
            }

            public void setIndustry2Name(String industry2Name) {
                this.industry2Name = industry2Name;
            }

            public int getVisits() {
                return visits;
            }

            public void setVisits(int visits) {
                this.visits = visits;
            }
        }

        public static class EducationBean {
            /**
             * name : 专科
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
             * name : 天津
             * sort : 30
             * children : []
             * parentId : 0
             */

            private String name;
            private int sort;
            private String parentId;
            private List<?> children;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }

        public static class DistrictBean {
            /**
             * name : 滨海新区
             * sort : 30
             * children : []
             * parentId : 0
             */

            private String name;
            private int sort;
            private String parentId;
            private List<?> children;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }

        public static class ExperienceYearBean {
            /**
             * name : 5-10年
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class RecruiterBean {
            /**
             * id : d6538b7bede242b2b20e35f5c3146f6d
             * createDate : 2020-07-21 15:10:45
             * updateDate : 2020-07-21 15:10:45
             * jobNature : 1
             * jobStatus : 1
             * arrivalTime : 1
             * name : 恒佳王春燕
             * mobile : 15122959990
             * avatar : http://39.96.78.51/userfiles/member/2020/7/xkkdscw8xc.jpeg
             * sex : 1
             * birthday : 1984-04
             * workDate : 2007-07-01
             * memberStatus : 1
             * recruiter : 1
             * registerDate : 2020-07-21 15:10:45
             * password : 1fb83a148741ec0ade97a6fb9d691e3948799780c8625308e3b1596c
             * lastReadDate : 2020-07-21 15:10:45
             * openResume : 0
             * openChat : 0
             * neverLogin : 0
             * improvedDegree : 25
             * collectCount : 0
             * latestCityName :
             * positionName : HRD
             */

            private String id;
            private String createDate;
            private String updateDate;
            private String jobNature;
            private String jobStatus;
            private String arrivalTime;
            private String name;
            private String mobile;
            private String avatar;
            private String sex;
            private String birthday;
            private String workDate;
            private String memberStatus;
            private String recruiter;
            private String registerDate;
            private String password;
            private String lastReadDate;
            private String openResume;
            private String openChat;
            private String neverLogin;
            private int improvedDegree;
            private int collectCount;
            private String latestCityName;
            private String positionName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
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

            public String getArrivalTime() {
                return arrivalTime;
            }

            public void setArrivalTime(String arrivalTime) {
                this.arrivalTime = arrivalTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getWorkDate() {
                return workDate;
            }

            public void setWorkDate(String workDate) {
                this.workDate = workDate;
            }

            public String getMemberStatus() {
                return memberStatus;
            }

            public void setMemberStatus(String memberStatus) {
                this.memberStatus = memberStatus;
            }

            public String getRecruiter() {
                return recruiter;
            }

            public void setRecruiter(String recruiter) {
                this.recruiter = recruiter;
            }

            public String getRegisterDate() {
                return registerDate;
            }

            public void setRegisterDate(String registerDate) {
                this.registerDate = registerDate;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getLastReadDate() {
                return lastReadDate;
            }

            public void setLastReadDate(String lastReadDate) {
                this.lastReadDate = lastReadDate;
            }

            public String getOpenResume() {
                return openResume;
            }

            public void setOpenResume(String openResume) {
                this.openResume = openResume;
            }

            public String getOpenChat() {
                return openChat;
            }

            public void setOpenChat(String openChat) {
                this.openChat = openChat;
            }

            public String getNeverLogin() {
                return neverLogin;
            }

            public void setNeverLogin(String neverLogin) {
                this.neverLogin = neverLogin;
            }

            public int getImprovedDegree() {
                return improvedDegree;
            }

            public void setImprovedDegree(int improvedDegree) {
                this.improvedDegree = improvedDegree;
            }

            public int getCollectCount() {
                return collectCount;
            }

            public void setCollectCount(int collectCount) {
                this.collectCount = collectCount;
            }

            public String getLatestCityName() {
                return latestCityName;
            }

            public void setLatestCityName(String latestCityName) {
                this.latestCityName = latestCityName;
            }

            public String getPositionName() {
                return positionName;
            }

            public void setPositionName(String positionName) {
                this.positionName = positionName;
            }
        }
    }
}
