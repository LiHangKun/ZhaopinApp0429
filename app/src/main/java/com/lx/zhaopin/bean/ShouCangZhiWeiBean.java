package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class ShouCangZhiWeiBean extends CommonBean {

    /**
     * pageNo : 1
     * totalPage : 1
     * dataList : [{"id":"cd18a316d91849baa0c6cf06764f84d6","company":{"id":"af4249e443b54fdfb103c1ef18b518f0","name":"天津恒凯科技有限公司","logo":"http://39.96.78.51/userfiles/company/2020/8/7lr5gew3pt.png","intro":"天津恒凯科技有限公司成立于2010年，注册资金1000万元，是一家从事工业自动化技术研究、工程应用与信息技术服务的专业化公司。在工业自动化控制领域尤其是DCS控制系统方面有雄厚的技术力量。是Honeywell公司中石化区域多年的优秀代理商；       \n    自成立之初,我们一直致力于工厂工业自动化系统的应用研究，逐渐发展成 为专业的工业自控系统集成商，集工程设计、成套、服务于一体。现可为工业自动化工程提供全面的技术咨询、系统设计、产品配套、系统集成、安装、开发、调试、培训、维护等全面的服务。通过多年的项目技术成果为恒凯赢得了广大用户的肯定与尊重。     \n\n    公司将以全球化的专业品质为发展之本，以诚信务实、合作共赢的经营理念为发展指导，努力为广大的用户提供最好的产品与服务。\n","financingName":"","industry2Name":"","visits":0},"name":"销售工程师","education":{"name":"本科"},"city":{"name":"济宁市","sort":30,"children":[],"parentId":"0"},"district":{"name":"任城区","sort":30,"children":[],"parentId":"0"},"location":"开源路8号","positionType":"2","experienceYear":{"name":"3-5年"},"minSalary":5,"maxSalary":8,"opened":"1","workfare":"五险一金,年终奖,带薪年假,六日双休,节日福利,定期体检","recruiter":{"id":"d7ac4f0eddcc4f8babbd210c12b7024d","createDate":"2020-08-28 10:06:00","updateDate":"2020-08-28 10:06:00","jobNature":"1","jobStatus":"1","arrivalTime":"1","num":"job1996302","name":"用户6302","mobile":"19902006302","avatar":"http://39.96.78.51/userfiles/avatar.jpg","sex":"1","birthday":"1996-08","workDate":"2018-08-28","memberStatus":"1","recruiter":"1","registerDate":"2020-08-28 10:06:00","password":"c84c141bb32817527b87c07bd2ae7cf0c6d35f51b28a984467f5226b","lastReadDate":"2020-08-28 10:06:00","openResume":"0","openChat":"0","neverLogin":"0","improvedDegree":0,"collectCount":0,"latestCityName":"","positionName":"人事"}},{"id":"132279e4e5394c66858e69edc07d7325","company":{"id":"2c8a9f17078b4330ad2b73e9ffaa61ae","name":"测试","logo":"http://39.96.78.51/userfiles/company/2020/8/jn1zujyn19.png","intro":"成都我的错2121","financingName":"","industry2Name":"","visits":0},"name":"111111","education":{"name":"博士"},"city":{"name":"北京","sort":30,"children":[],"parentId":"0"},"district":{"name":"东城区","sort":30,"children":[],"parentId":"0"},"location":"山西路53号","positionType":"1","experienceYear":{"name":"经验不限"},"minSalary":1,"maxSalary":2,"opened":"1","workfare":"餐补","recruiter":{"id":"053bec92ddfe41d08c95ce3ab98866ef","createDate":"2020-08-05 14:41:31","updateDate":"2020-09-08 18:11:06","jobNature":"1","jobStatus":"3","arrivalTime":"1","num":"job1760525","name":"巽风","mobile":"17653420525","avatar":"http://39.96.78.51/userfiles/member/2020/8/6khkocn0v7.JPG","sex":"1","birthday":"2020-08","workDate":"2018-08-05","experienceEducation":{"id":"ab65fcd0fdd94f998a33e67de596effc","school":"德州科技职业学院","major":"计算机科学与技术"},"education":{"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"},"educationSort":"3","memberStatus":"1","recruiter":"1","registerDate":"2020-08-05 14:41:31","password":"98b10a895d6ee1eda6c558cdb514f74b544ab6590f190efb7fbb27da","lastReadDate":"2020-08-05 14:41:31","openResume":"0","openChat":"0","neverLogin":"0","positionCategories":"","improvedDegree":100,"collectCount":0,"latestCityName":"","positionName":"java"}}]
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
         * id : cd18a316d91849baa0c6cf06764f84d6
         * company : {"id":"af4249e443b54fdfb103c1ef18b518f0","name":"天津恒凯科技有限公司","logo":"http://39.96.78.51/userfiles/company/2020/8/7lr5gew3pt.png","intro":"天津恒凯科技有限公司成立于2010年，注册资金1000万元，是一家从事工业自动化技术研究、工程应用与信息技术服务的专业化公司。在工业自动化控制领域尤其是DCS控制系统方面有雄厚的技术力量。是Honeywell公司中石化区域多年的优秀代理商；       \n    自成立之初,我们一直致力于工厂工业自动化系统的应用研究，逐渐发展成 为专业的工业自控系统集成商，集工程设计、成套、服务于一体。现可为工业自动化工程提供全面的技术咨询、系统设计、产品配套、系统集成、安装、开发、调试、培训、维护等全面的服务。通过多年的项目技术成果为恒凯赢得了广大用户的肯定与尊重。     \n\n    公司将以全球化的专业品质为发展之本，以诚信务实、合作共赢的经营理念为发展指导，努力为广大的用户提供最好的产品与服务。\n","financingName":"","industry2Name":"","visits":0}
         * name : 销售工程师
         * education : {"name":"本科"}
         * city : {"name":"济宁市","sort":30,"children":[],"parentId":"0"}
         * district : {"name":"任城区","sort":30,"children":[],"parentId":"0"}
         * location : 开源路8号
         * positionType : 2
         * experienceYear : {"name":"3-5年"}
         * minSalary : 5
         * maxSalary : 8
         * opened : 1
         * workfare : 五险一金,年终奖,带薪年假,六日双休,节日福利,定期体检
         * recruiter : {"id":"d7ac4f0eddcc4f8babbd210c12b7024d","createDate":"2020-08-28 10:06:00","updateDate":"2020-08-28 10:06:00","jobNature":"1","jobStatus":"1","arrivalTime":"1","num":"job1996302","name":"用户6302","mobile":"19902006302","avatar":"http://39.96.78.51/userfiles/avatar.jpg","sex":"1","birthday":"1996-08","workDate":"2018-08-28","memberStatus":"1","recruiter":"1","registerDate":"2020-08-28 10:06:00","password":"c84c141bb32817527b87c07bd2ae7cf0c6d35f51b28a984467f5226b","lastReadDate":"2020-08-28 10:06:00","openResume":"0","openChat":"0","neverLogin":"0","improvedDegree":0,"collectCount":0,"latestCityName":"","positionName":"人事"}
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
             * id : af4249e443b54fdfb103c1ef18b518f0
             * name : 天津恒凯科技有限公司
             * logo : http://39.96.78.51/userfiles/company/2020/8/7lr5gew3pt.png
             * intro : 天津恒凯科技有限公司成立于2010年，注册资金1000万元，是一家从事工业自动化技术研究、工程应用与信息技术服务的专业化公司。在工业自动化控制领域尤其是DCS控制系统方面有雄厚的技术力量。是Honeywell公司中石化区域多年的优秀代理商；
             自成立之初,我们一直致力于工厂工业自动化系统的应用研究，逐渐发展成 为专业的工业自控系统集成商，集工程设计、成套、服务于一体。现可为工业自动化工程提供全面的技术咨询、系统设计、产品配套、系统集成、安装、开发、调试、培训、维护等全面的服务。通过多年的项目技术成果为恒凯赢得了广大用户的肯定与尊重。

             公司将以全球化的专业品质为发展之本，以诚信务实、合作共赢的经营理念为发展指导，努力为广大的用户提供最好的产品与服务。
             * financingName :
             * industry2Name :
             * visits : 0
             */

            private String id;
            private String name;
            private String logo;
            private String intro;
            private String financingName;
            private String industry2Name;
            private int visits;

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
             * name : 本科
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
             * name : 济宁市
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
             * name : 任城区
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
             * name : 3-5年
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
             * id : d7ac4f0eddcc4f8babbd210c12b7024d
             * createDate : 2020-08-28 10:06:00
             * updateDate : 2020-08-28 10:06:00
             * jobNature : 1
             * jobStatus : 1
             * arrivalTime : 1
             * num : job1996302
             * name : 用户6302
             * mobile : 19902006302
             * avatar : http://39.96.78.51/userfiles/avatar.jpg
             * sex : 1
             * birthday : 1996-08
             * workDate : 2018-08-28
             * memberStatus : 1
             * recruiter : 1
             * registerDate : 2020-08-28 10:06:00
             * password : c84c141bb32817527b87c07bd2ae7cf0c6d35f51b28a984467f5226b
             * lastReadDate : 2020-08-28 10:06:00
             * openResume : 0
             * openChat : 0
             * neverLogin : 0
             * improvedDegree : 0
             * collectCount : 0
             * latestCityName :
             * positionName : 人事
             */

            private String id;
            private String createDate;
            private String updateDate;
            private String jobNature;
            private String jobStatus;
            private String arrivalTime;
            private String num;
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

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
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
