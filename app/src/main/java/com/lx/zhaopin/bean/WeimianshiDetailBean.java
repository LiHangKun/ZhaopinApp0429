package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

//求职者看到我的
public class WeimianshiDetailBean extends CommonBean {

    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * date : 2020-09-12
         * interviews : [{"id":"f50efed0faee462e994885b385e5e0ca","company":{"id":"b1b1d19d298446c586ccb3e9f95049b0","name":"测试 测试 测试 ","logo":"/userfiles/company/2020/7/ir3y454qie.jpg","financingName":"","industry2Name":"","visits":0},"position":{"id":"5d4e7c2ca34841b8aae2d08dd875ad3a","createDate":"2020-09-07 14:00:48","updateDate":"2020-09-07 14:00:48","company":{"id":"b1b1d19d298446c586ccb3e9f95049b0","createDate":"2020-07-20 15:22:25","updateDate":"2020-09-04 09:39:03","num":"测试 测试 测试 ","name":"测试 测试 测试 ","logo":"/userfiles/company/2020/7/ir3y454qie.jpg","mobile":"123123123","email":"852330925@qq.com","financing":{"id":"caa59d0df8b84004a31d0de91e3f4999","name":"未融资"},"staffNum":"0-49人","industry1":{"id":"805489d7cb4c41b598db6ee2398f135f","remarks":"","createDate":"2020-07-20 13:56:10","updateDate":"2020-07-20 13:56:10","parent":{"id":"0","sort":30,"children":[],"parentId":"0"},"parentIds":"0,","name":"批发/零售/贸易","sort":8,"children":[],"level":"1","parentId":"0"},"industry2":{"id":"f6f4258ef7444a458808def367e1145b","remarks":"","createDate":"2020-07-20 13:59:22","updateDate":"2020-07-20 13:59:22","parent":{"id":"805489d7cb4c41b598db6ee2398f135f","sort":30,"children":[],"parentId":"0"},"parentIds":"0,805489d7cb4c41b598db6ee2398f135f,","name":"消费品","sort":1,"children":[],"level":"2","parentId":"805489d7cb4c41b598db6ee2398f135f"},"fund":"500W","legalPerson":"柯柯","pic1":"/userfiles/company/2020/7/ltkkevr02i.jpg","pic2":"/userfiles/company/2020/7/79dje4fcoq.jpg","pic3":"/userfiles/company/2020/7/conxegjgcn.jpg","companyStatus":"1","auditStatus":"2","registerDate":"2020-07-20 15:22:25","auditDate":"2020-07-24 11:29:53","intro":"一起来玩乐高叭1阿斯顿发送到发送到发士大夫阿萨德发送发斯蒂芬发送到发送到发送到发送到发送到发送到发送到发手动阀商店1","images":"/userfiles/company/2020/8/8i4xnr0dto.jpg|/userfiles/company/2020/8/cb2topsleq.jpg|/userfiles/company/2020/8/pwics7hctf.jpg","service":"一起来玩乐高叭士大夫奥德赛发送到发送到发士大夫阿萨德高达噶是的个阿什斗鬼阿什斗鬼阿萨德干啥蛋糕阿什斗鬼阿什斗鬼阿什斗鬼阿什斗鬼gas多个噶是的","location":"大唐总部基地东区3的501","lat":"","lng":"","password":"9708a59aba60a02b3e5f1892ef90f0abf9b48e32f87c2bfd0073b651","improved":"0","collectCount":0,"financingName":"","industry2Name":"","visits":52},"industry1":{"id":"805489d7cb4c41b598db6ee2398f135f","name":"批发/零售/贸易","sort":30,"children":[],"parentId":"0"},"industry2":{"id":"f6f4258ef7444a458808def367e1145b","name":"消费品","sort":30,"children":[],"parentId":"0"},"companyLng":"","companyLat":"","name":"iOS","keywords":"Object-C","education":{"id":"899919864ee84faba953e6c435bfa0e8","name":"本科"},"educationSort":2,"province":{"id":"110000","name":"热门城市","sort":30,"children":[],"type":"1","parentId":"0"},"city":{"id":"120100","name":"天津","sort":30,"children":[],"parentId":"0"},"district":{"id":"120116","name":"滨海新区","sort":30,"children":[],"parentId":"0"},"location":"泰达企业总部3号楼","lng":"117.711641","lat":"39.107607","financing":{"id":"caa59d0df8b84004a31d0de91e3f4999","name":"未融资"},"positionCategory1":{"id":"7ba473f44bd542a5b77183cc5396b997","name":"互联网/通信及硬件","sort":30,"children":[],"level":"1","levelList":[],"parentId":"0"},"positionCategory2":{"id":"8b617f7292154ccd9bfc8c2390336019","name":"移动研发","sort":30,"children":[],"level":"2","levelList":[],"parentId":"0"},"positionCategory3":{"id":"cc661577ddf5421285770fd4ee0c1a8e","name":"移动开发","sort":30,"children":[],"level":"3","levelList":[],"parentId":"0"},"positionType":"1","experienceYear":{"id":"165deca730524295809095ff8ac3e8a3","name":"1-3年","minYear":1,"maxYear":3},"minYear":1,"maxYear":3,"minSalary":7,"maxSalary":10,"duty":"iOSApp开发","skills":"Swift","positionStatus":"2","opened":"1","auditDate":"2020-09-07 14:02:31","workfare":"年终奖","collectCount":0,"improperCount":0,"reportedCount":0,"chatCount":0,"interviewCount":0,"publishDate":"2020-09-07 14:00:48","jobSkill":"有丰富的项目经验","recruiter":{"id":"5c2cef9ca9cd4feb9981113cbf110d1d","createDate":"2020-09-03 16:25:01","updateDate":"2020-09-03 16:25:01","jobNature":"1","jobStatus":"1","arrivalTime":"1","num":"job1666268","name":"求职墙","mobile":"16634146268","avatar":"http://39.96.78.51/userfiles/member/2020/9/zmftgaeckd.jpg","sex":"1","birthday":"1996-09","workDate":"2018-09-03","memberStatus":"1","recruiter":"1","registerDate":"2020-09-03 16:25:01","password":"961a1909094ad66d25a6bc6f70b469db1b53e73c61cfc82fdba1d6fe","lastReadDate":"2020-09-03 16:25:01","openResume":"0","openChat":"0","neverLogin":"0","improvedDegree":25,"collectCount":0,"latestCityName":"","positionName":"HR"}},"interviewDate":"2020-09-12 14:41","mobile":"14:41","interviewStatus":"3","offerStatus":"","content":""}]
         */

        private String date;
        private List<InterviewsBean> interviews;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<InterviewsBean> getInterviews() {
            return interviews;
        }

        public void setInterviews(List<InterviewsBean> interviews) {
            this.interviews = interviews;
        }

        public static class InterviewsBean {
            /**
             * id : f50efed0faee462e994885b385e5e0ca
             * company : {"id":"b1b1d19d298446c586ccb3e9f95049b0","name":"测试 测试 测试 ","logo":"/userfiles/company/2020/7/ir3y454qie.jpg","financingName":"","industry2Name":"","visits":0}
             * position : {"id":"5d4e7c2ca34841b8aae2d08dd875ad3a","createDate":"2020-09-07 14:00:48","updateDate":"2020-09-07 14:00:48","company":{"id":"b1b1d19d298446c586ccb3e9f95049b0","createDate":"2020-07-20 15:22:25","updateDate":"2020-09-04 09:39:03","num":"测试 测试 测试 ","name":"测试 测试 测试 ","logo":"/userfiles/company/2020/7/ir3y454qie.jpg","mobile":"123123123","email":"852330925@qq.com","financing":{"id":"caa59d0df8b84004a31d0de91e3f4999","name":"未融资"},"staffNum":"0-49人","industry1":{"id":"805489d7cb4c41b598db6ee2398f135f","remarks":"","createDate":"2020-07-20 13:56:10","updateDate":"2020-07-20 13:56:10","parent":{"id":"0","sort":30,"children":[],"parentId":"0"},"parentIds":"0,","name":"批发/零售/贸易","sort":8,"children":[],"level":"1","parentId":"0"},"industry2":{"id":"f6f4258ef7444a458808def367e1145b","remarks":"","createDate":"2020-07-20 13:59:22","updateDate":"2020-07-20 13:59:22","parent":{"id":"805489d7cb4c41b598db6ee2398f135f","sort":30,"children":[],"parentId":"0"},"parentIds":"0,805489d7cb4c41b598db6ee2398f135f,","name":"消费品","sort":1,"children":[],"level":"2","parentId":"805489d7cb4c41b598db6ee2398f135f"},"fund":"500W","legalPerson":"柯柯","pic1":"/userfiles/company/2020/7/ltkkevr02i.jpg","pic2":"/userfiles/company/2020/7/79dje4fcoq.jpg","pic3":"/userfiles/company/2020/7/conxegjgcn.jpg","companyStatus":"1","auditStatus":"2","registerDate":"2020-07-20 15:22:25","auditDate":"2020-07-24 11:29:53","intro":"一起来玩乐高叭1阿斯顿发送到发送到发士大夫阿萨德发送发斯蒂芬发送到发送到发送到发送到发送到发送到发送到发手动阀商店1","images":"/userfiles/company/2020/8/8i4xnr0dto.jpg|/userfiles/company/2020/8/cb2topsleq.jpg|/userfiles/company/2020/8/pwics7hctf.jpg","service":"一起来玩乐高叭士大夫奥德赛发送到发送到发士大夫阿萨德高达噶是的个阿什斗鬼阿什斗鬼阿萨德干啥蛋糕阿什斗鬼阿什斗鬼阿什斗鬼阿什斗鬼gas多个噶是的","location":"大唐总部基地东区3的501","lat":"","lng":"","password":"9708a59aba60a02b3e5f1892ef90f0abf9b48e32f87c2bfd0073b651","improved":"0","collectCount":0,"financingName":"","industry2Name":"","visits":52},"industry1":{"id":"805489d7cb4c41b598db6ee2398f135f","name":"批发/零售/贸易","sort":30,"children":[],"parentId":"0"},"industry2":{"id":"f6f4258ef7444a458808def367e1145b","name":"消费品","sort":30,"children":[],"parentId":"0"},"companyLng":"","companyLat":"","name":"iOS","keywords":"Object-C","education":{"id":"899919864ee84faba953e6c435bfa0e8","name":"本科"},"educationSort":2,"province":{"id":"110000","name":"热门城市","sort":30,"children":[],"type":"1","parentId":"0"},"city":{"id":"120100","name":"天津","sort":30,"children":[],"parentId":"0"},"district":{"id":"120116","name":"滨海新区","sort":30,"children":[],"parentId":"0"},"location":"泰达企业总部3号楼","lng":"117.711641","lat":"39.107607","financing":{"id":"caa59d0df8b84004a31d0de91e3f4999","name":"未融资"},"positionCategory1":{"id":"7ba473f44bd542a5b77183cc5396b997","name":"互联网/通信及硬件","sort":30,"children":[],"level":"1","levelList":[],"parentId":"0"},"positionCategory2":{"id":"8b617f7292154ccd9bfc8c2390336019","name":"移动研发","sort":30,"children":[],"level":"2","levelList":[],"parentId":"0"},"positionCategory3":{"id":"cc661577ddf5421285770fd4ee0c1a8e","name":"移动开发","sort":30,"children":[],"level":"3","levelList":[],"parentId":"0"},"positionType":"1","experienceYear":{"id":"165deca730524295809095ff8ac3e8a3","name":"1-3年","minYear":1,"maxYear":3},"minYear":1,"maxYear":3,"minSalary":7,"maxSalary":10,"duty":"iOSApp开发","skills":"Swift","positionStatus":"2","opened":"1","auditDate":"2020-09-07 14:02:31","workfare":"年终奖","collectCount":0,"improperCount":0,"reportedCount":0,"chatCount":0,"interviewCount":0,"publishDate":"2020-09-07 14:00:48","jobSkill":"有丰富的项目经验","recruiter":{"id":"5c2cef9ca9cd4feb9981113cbf110d1d","createDate":"2020-09-03 16:25:01","updateDate":"2020-09-03 16:25:01","jobNature":"1","jobStatus":"1","arrivalTime":"1","num":"job1666268","name":"求职墙","mobile":"16634146268","avatar":"http://39.96.78.51/userfiles/member/2020/9/zmftgaeckd.jpg","sex":"1","birthday":"1996-09","workDate":"2018-09-03","memberStatus":"1","recruiter":"1","registerDate":"2020-09-03 16:25:01","password":"961a1909094ad66d25a6bc6f70b469db1b53e73c61cfc82fdba1d6fe","lastReadDate":"2020-09-03 16:25:01","openResume":"0","openChat":"0","neverLogin":"0","improvedDegree":25,"collectCount":0,"latestCityName":"","positionName":"HR"}}
             * interviewDate : 2020-09-12 14:41
             * mobile : 14:41
             * interviewStatus : 3
             * offerStatus :
             * content :
             */

            private String id;
            private CompanyBean company;
            private PositionBean position;
            private String interviewDate;
            private String mobile;
            private String interviewStatus;
            private String offerStatus;
            private String content;

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

            public PositionBean getPosition() {
                return position;
            }

            public void setPosition(PositionBean position) {
                this.position = position;
            }

            public String getInterviewDate() {
                return interviewDate;
            }

            public void setInterviewDate(String interviewDate) {
                this.interviewDate = interviewDate;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getInterviewStatus() {
                return interviewStatus;
            }

            public void setInterviewStatus(String interviewStatus) {
                this.interviewStatus = interviewStatus;
            }

            public String getOfferStatus() {
                return offerStatus;
            }

            public void setOfferStatus(String offerStatus) {
                this.offerStatus = offerStatus;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public static class CompanyBean {
                /**
                 * id : b1b1d19d298446c586ccb3e9f95049b0
                 * name : 测试 测试 测试
                 * logo : /userfiles/company/2020/7/ir3y454qie.jpg
                 * financingName :
                 * industry2Name :
                 * visits : 0
                 */

                private String id;
                private String name;
                private String logo;
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

            public static class PositionBean {
                /**
                 * id : 5d4e7c2ca34841b8aae2d08dd875ad3a
                 * createDate : 2020-09-07 14:00:48
                 * updateDate : 2020-09-07 14:00:48
                 * company : {"id":"b1b1d19d298446c586ccb3e9f95049b0","createDate":"2020-07-20 15:22:25","updateDate":"2020-09-04 09:39:03","num":"测试 测试 测试 ","name":"测试 测试 测试 ","logo":"/userfiles/company/2020/7/ir3y454qie.jpg","mobile":"123123123","email":"852330925@qq.com","financing":{"id":"caa59d0df8b84004a31d0de91e3f4999","name":"未融资"},"staffNum":"0-49人","industry1":{"id":"805489d7cb4c41b598db6ee2398f135f","remarks":"","createDate":"2020-07-20 13:56:10","updateDate":"2020-07-20 13:56:10","parent":{"id":"0","sort":30,"children":[],"parentId":"0"},"parentIds":"0,","name":"批发/零售/贸易","sort":8,"children":[],"level":"1","parentId":"0"},"industry2":{"id":"f6f4258ef7444a458808def367e1145b","remarks":"","createDate":"2020-07-20 13:59:22","updateDate":"2020-07-20 13:59:22","parent":{"id":"805489d7cb4c41b598db6ee2398f135f","sort":30,"children":[],"parentId":"0"},"parentIds":"0,805489d7cb4c41b598db6ee2398f135f,","name":"消费品","sort":1,"children":[],"level":"2","parentId":"805489d7cb4c41b598db6ee2398f135f"},"fund":"500W","legalPerson":"柯柯","pic1":"/userfiles/company/2020/7/ltkkevr02i.jpg","pic2":"/userfiles/company/2020/7/79dje4fcoq.jpg","pic3":"/userfiles/company/2020/7/conxegjgcn.jpg","companyStatus":"1","auditStatus":"2","registerDate":"2020-07-20 15:22:25","auditDate":"2020-07-24 11:29:53","intro":"一起来玩乐高叭1阿斯顿发送到发送到发士大夫阿萨德发送发斯蒂芬发送到发送到发送到发送到发送到发送到发送到发手动阀商店1","images":"/userfiles/company/2020/8/8i4xnr0dto.jpg|/userfiles/company/2020/8/cb2topsleq.jpg|/userfiles/company/2020/8/pwics7hctf.jpg","service":"一起来玩乐高叭士大夫奥德赛发送到发送到发士大夫阿萨德高达噶是的个阿什斗鬼阿什斗鬼阿萨德干啥蛋糕阿什斗鬼阿什斗鬼阿什斗鬼阿什斗鬼gas多个噶是的","location":"大唐总部基地东区3的501","lat":"","lng":"","password":"9708a59aba60a02b3e5f1892ef90f0abf9b48e32f87c2bfd0073b651","improved":"0","collectCount":0,"financingName":"","industry2Name":"","visits":52}
                 * industry1 : {"id":"805489d7cb4c41b598db6ee2398f135f","name":"批发/零售/贸易","sort":30,"children":[],"parentId":"0"}
                 * industry2 : {"id":"f6f4258ef7444a458808def367e1145b","name":"消费品","sort":30,"children":[],"parentId":"0"}
                 * companyLng :
                 * companyLat :
                 * name : iOS
                 * keywords : Object-C
                 * education : {"id":"899919864ee84faba953e6c435bfa0e8","name":"本科"}
                 * educationSort : 2
                 * province : {"id":"110000","name":"热门城市","sort":30,"children":[],"type":"1","parentId":"0"}
                 * city : {"id":"120100","name":"天津","sort":30,"children":[],"parentId":"0"}
                 * district : {"id":"120116","name":"滨海新区","sort":30,"children":[],"parentId":"0"}
                 * location : 泰达企业总部3号楼
                 * lng : 117.711641
                 * lat : 39.107607
                 * financing : {"id":"caa59d0df8b84004a31d0de91e3f4999","name":"未融资"}
                 * positionCategory1 : {"id":"7ba473f44bd542a5b77183cc5396b997","name":"互联网/通信及硬件","sort":30,"children":[],"level":"1","levelList":[],"parentId":"0"}
                 * positionCategory2 : {"id":"8b617f7292154ccd9bfc8c2390336019","name":"移动研发","sort":30,"children":[],"level":"2","levelList":[],"parentId":"0"}
                 * positionCategory3 : {"id":"cc661577ddf5421285770fd4ee0c1a8e","name":"移动开发","sort":30,"children":[],"level":"3","levelList":[],"parentId":"0"}
                 * positionType : 1
                 * experienceYear : {"id":"165deca730524295809095ff8ac3e8a3","name":"1-3年","minYear":1,"maxYear":3}
                 * minYear : 1
                 * maxYear : 3
                 * minSalary : 7
                 * maxSalary : 10
                 * duty : iOSApp开发
                 * skills : Swift
                 * positionStatus : 2
                 * opened : 1
                 * auditDate : 2020-09-07 14:02:31
                 * workfare : 年终奖
                 * collectCount : 0
                 * improperCount : 0
                 * reportedCount : 0
                 * chatCount : 0
                 * interviewCount : 0
                 * publishDate : 2020-09-07 14:00:48
                 * jobSkill : 有丰富的项目经验
                 * recruiter : {"id":"5c2cef9ca9cd4feb9981113cbf110d1d","createDate":"2020-09-03 16:25:01","updateDate":"2020-09-03 16:25:01","jobNature":"1","jobStatus":"1","arrivalTime":"1","num":"job1666268","name":"求职墙","mobile":"16634146268","avatar":"http://39.96.78.51/userfiles/member/2020/9/zmftgaeckd.jpg","sex":"1","birthday":"1996-09","workDate":"2018-09-03","memberStatus":"1","recruiter":"1","registerDate":"2020-09-03 16:25:01","password":"961a1909094ad66d25a6bc6f70b469db1b53e73c61cfc82fdba1d6fe","lastReadDate":"2020-09-03 16:25:01","openResume":"0","openChat":"0","neverLogin":"0","improvedDegree":25,"collectCount":0,"latestCityName":"","positionName":"HR"}
                 */

                private String id;
                private String createDate;
                private String updateDate;
                private CompanyBeanX company;
                private Industry1BeanX industry1;
                private Industry2BeanX industry2;
                private String companyLng;
                private String companyLat;
                private String name;
                private String keywords;
                private EducationBean education;
                private int educationSort;
                private ProvinceBean province;
                private CityBean city;
                private DistrictBean district;
                private String location;
                private String lng;
                private String lat;
                private FinancingBeanX financing;
                private PositionCategory1Bean positionCategory1;
                private PositionCategory2Bean positionCategory2;
                private PositionCategory3Bean positionCategory3;
                private String positionType;
                private ExperienceYearBean experienceYear;
                private int minYear;
                private int maxYear;
                private int minSalary;
                private int maxSalary;
                private String duty;
                private String skills;
                private String positionStatus;
                private String opened;
                private String auditDate;
                private String workfare;
                private int collectCount;
                private int improperCount;
                private int reportedCount;
                private int chatCount;
                private int interviewCount;
                private String publishDate;
                private String jobSkill;
                private RecruiterBean recruiter;

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

                public CompanyBeanX getCompany() {
                    return company;
                }

                public void setCompany(CompanyBeanX company) {
                    this.company = company;
                }

                public Industry1BeanX getIndustry1() {
                    return industry1;
                }

                public void setIndustry1(Industry1BeanX industry1) {
                    this.industry1 = industry1;
                }

                public Industry2BeanX getIndustry2() {
                    return industry2;
                }

                public void setIndustry2(Industry2BeanX industry2) {
                    this.industry2 = industry2;
                }

                public String getCompanyLng() {
                    return companyLng;
                }

                public void setCompanyLng(String companyLng) {
                    this.companyLng = companyLng;
                }

                public String getCompanyLat() {
                    return companyLat;
                }

                public void setCompanyLat(String companyLat) {
                    this.companyLat = companyLat;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getKeywords() {
                    return keywords;
                }

                public void setKeywords(String keywords) {
                    this.keywords = keywords;
                }

                public EducationBean getEducation() {
                    return education;
                }

                public void setEducation(EducationBean education) {
                    this.education = education;
                }

                public int getEducationSort() {
                    return educationSort;
                }

                public void setEducationSort(int educationSort) {
                    this.educationSort = educationSort;
                }

                public ProvinceBean getProvince() {
                    return province;
                }

                public void setProvince(ProvinceBean province) {
                    this.province = province;
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

                public String getLng() {
                    return lng;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public FinancingBeanX getFinancing() {
                    return financing;
                }

                public void setFinancing(FinancingBeanX financing) {
                    this.financing = financing;
                }

                public PositionCategory1Bean getPositionCategory1() {
                    return positionCategory1;
                }

                public void setPositionCategory1(PositionCategory1Bean positionCategory1) {
                    this.positionCategory1 = positionCategory1;
                }

                public PositionCategory2Bean getPositionCategory2() {
                    return positionCategory2;
                }

                public void setPositionCategory2(PositionCategory2Bean positionCategory2) {
                    this.positionCategory2 = positionCategory2;
                }

                public PositionCategory3Bean getPositionCategory3() {
                    return positionCategory3;
                }

                public void setPositionCategory3(PositionCategory3Bean positionCategory3) {
                    this.positionCategory3 = positionCategory3;
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

                public int getMinYear() {
                    return minYear;
                }

                public void setMinYear(int minYear) {
                    this.minYear = minYear;
                }

                public int getMaxYear() {
                    return maxYear;
                }

                public void setMaxYear(int maxYear) {
                    this.maxYear = maxYear;
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

                public String getDuty() {
                    return duty;
                }

                public void setDuty(String duty) {
                    this.duty = duty;
                }

                public String getSkills() {
                    return skills;
                }

                public void setSkills(String skills) {
                    this.skills = skills;
                }

                public String getPositionStatus() {
                    return positionStatus;
                }

                public void setPositionStatus(String positionStatus) {
                    this.positionStatus = positionStatus;
                }

                public String getOpened() {
                    return opened;
                }

                public void setOpened(String opened) {
                    this.opened = opened;
                }

                public String getAuditDate() {
                    return auditDate;
                }

                public void setAuditDate(String auditDate) {
                    this.auditDate = auditDate;
                }

                public String getWorkfare() {
                    return workfare;
                }

                public void setWorkfare(String workfare) {
                    this.workfare = workfare;
                }

                public int getCollectCount() {
                    return collectCount;
                }

                public void setCollectCount(int collectCount) {
                    this.collectCount = collectCount;
                }

                public int getImproperCount() {
                    return improperCount;
                }

                public void setImproperCount(int improperCount) {
                    this.improperCount = improperCount;
                }

                public int getReportedCount() {
                    return reportedCount;
                }

                public void setReportedCount(int reportedCount) {
                    this.reportedCount = reportedCount;
                }

                public int getChatCount() {
                    return chatCount;
                }

                public void setChatCount(int chatCount) {
                    this.chatCount = chatCount;
                }

                public int getInterviewCount() {
                    return interviewCount;
                }

                public void setInterviewCount(int interviewCount) {
                    this.interviewCount = interviewCount;
                }

                public String getPublishDate() {
                    return publishDate;
                }

                public void setPublishDate(String publishDate) {
                    this.publishDate = publishDate;
                }

                public String getJobSkill() {
                    return jobSkill;
                }

                public void setJobSkill(String jobSkill) {
                    this.jobSkill = jobSkill;
                }

                public RecruiterBean getRecruiter() {
                    return recruiter;
                }

                public void setRecruiter(RecruiterBean recruiter) {
                    this.recruiter = recruiter;
                }

                public static class CompanyBeanX {
                    /**
                     * id : b1b1d19d298446c586ccb3e9f95049b0
                     * createDate : 2020-07-20 15:22:25
                     * updateDate : 2020-09-04 09:39:03
                     * num : 测试 测试 测试
                     * name : 测试 测试 测试
                     * logo : /userfiles/company/2020/7/ir3y454qie.jpg
                     * mobile : 123123123
                     * email : 852330925@qq.com
                     * financing : {"id":"caa59d0df8b84004a31d0de91e3f4999","name":"未融资"}
                     * staffNum : 0-49人
                     * industry1 : {"id":"805489d7cb4c41b598db6ee2398f135f","remarks":"","createDate":"2020-07-20 13:56:10","updateDate":"2020-07-20 13:56:10","parent":{"id":"0","sort":30,"children":[],"parentId":"0"},"parentIds":"0,","name":"批发/零售/贸易","sort":8,"children":[],"level":"1","parentId":"0"}
                     * industry2 : {"id":"f6f4258ef7444a458808def367e1145b","remarks":"","createDate":"2020-07-20 13:59:22","updateDate":"2020-07-20 13:59:22","parent":{"id":"805489d7cb4c41b598db6ee2398f135f","sort":30,"children":[],"parentId":"0"},"parentIds":"0,805489d7cb4c41b598db6ee2398f135f,","name":"消费品","sort":1,"children":[],"level":"2","parentId":"805489d7cb4c41b598db6ee2398f135f"}
                     * fund : 500W
                     * legalPerson : 柯柯
                     * pic1 : /userfiles/company/2020/7/ltkkevr02i.jpg
                     * pic2 : /userfiles/company/2020/7/79dje4fcoq.jpg
                     * pic3 : /userfiles/company/2020/7/conxegjgcn.jpg
                     * companyStatus : 1
                     * auditStatus : 2
                     * registerDate : 2020-07-20 15:22:25
                     * auditDate : 2020-07-24 11:29:53
                     * intro : 一起来玩乐高叭1阿斯顿发送到发送到发士大夫阿萨德发送发斯蒂芬发送到发送到发送到发送到发送到发送到发送到发手动阀商店1
                     * images : /userfiles/company/2020/8/8i4xnr0dto.jpg|/userfiles/company/2020/8/cb2topsleq.jpg|/userfiles/company/2020/8/pwics7hctf.jpg
                     * service : 一起来玩乐高叭士大夫奥德赛发送到发送到发士大夫阿萨德高达噶是的个阿什斗鬼阿什斗鬼阿萨德干啥蛋糕阿什斗鬼阿什斗鬼阿什斗鬼阿什斗鬼gas多个噶是的
                     * location : 大唐总部基地东区3的501
                     * lat :
                     * lng :
                     * password : 9708a59aba60a02b3e5f1892ef90f0abf9b48e32f87c2bfd0073b651
                     * improved : 0
                     * collectCount : 0
                     * financingName :
                     * industry2Name :
                     * visits : 52
                     */

                    private String id;
                    private String createDate;
                    private String updateDate;
                    private String num;
                    private String name;
                    private String logo;
                    private String mobile;
                    private String email;
                    private FinancingBean financing;
                    private String staffNum;
                    private Industry1Bean industry1;
                    private Industry2Bean industry2;
                    private String fund;
                    private String legalPerson;
                    private String pic1;
                    private String pic2;
                    private String pic3;
                    private String companyStatus;
                    private String auditStatus;
                    private String registerDate;
                    private String auditDate;
                    private String intro;
                    private String images;
                    private String service;
                    private String location;
                    private String lat;
                    private String lng;
                    private String password;
                    private String improved;
                    private int collectCount;
                    private String financingName;
                    private String industry2Name;
                    private int visits;

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

                    public String getEmail() {
                        return email;
                    }

                    public void setEmail(String email) {
                        this.email = email;
                    }

                    public FinancingBean getFinancing() {
                        return financing;
                    }

                    public void setFinancing(FinancingBean financing) {
                        this.financing = financing;
                    }

                    public String getStaffNum() {
                        return staffNum;
                    }

                    public void setStaffNum(String staffNum) {
                        this.staffNum = staffNum;
                    }

                    public Industry1Bean getIndustry1() {
                        return industry1;
                    }

                    public void setIndustry1(Industry1Bean industry1) {
                        this.industry1 = industry1;
                    }

                    public Industry2Bean getIndustry2() {
                        return industry2;
                    }

                    public void setIndustry2(Industry2Bean industry2) {
                        this.industry2 = industry2;
                    }

                    public String getFund() {
                        return fund;
                    }

                    public void setFund(String fund) {
                        this.fund = fund;
                    }

                    public String getLegalPerson() {
                        return legalPerson;
                    }

                    public void setLegalPerson(String legalPerson) {
                        this.legalPerson = legalPerson;
                    }

                    public String getPic1() {
                        return pic1;
                    }

                    public void setPic1(String pic1) {
                        this.pic1 = pic1;
                    }

                    public String getPic2() {
                        return pic2;
                    }

                    public void setPic2(String pic2) {
                        this.pic2 = pic2;
                    }

                    public String getPic3() {
                        return pic3;
                    }

                    public void setPic3(String pic3) {
                        this.pic3 = pic3;
                    }

                    public String getCompanyStatus() {
                        return companyStatus;
                    }

                    public void setCompanyStatus(String companyStatus) {
                        this.companyStatus = companyStatus;
                    }

                    public String getAuditStatus() {
                        return auditStatus;
                    }

                    public void setAuditStatus(String auditStatus) {
                        this.auditStatus = auditStatus;
                    }

                    public String getRegisterDate() {
                        return registerDate;
                    }

                    public void setRegisterDate(String registerDate) {
                        this.registerDate = registerDate;
                    }

                    public String getAuditDate() {
                        return auditDate;
                    }

                    public void setAuditDate(String auditDate) {
                        this.auditDate = auditDate;
                    }

                    public String getIntro() {
                        return intro;
                    }

                    public void setIntro(String intro) {
                        this.intro = intro;
                    }

                    public String getImages() {
                        return images;
                    }

                    public void setImages(String images) {
                        this.images = images;
                    }

                    public String getService() {
                        return service;
                    }

                    public void setService(String service) {
                        this.service = service;
                    }

                    public String getLocation() {
                        return location;
                    }

                    public void setLocation(String location) {
                        this.location = location;
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

                    public String getPassword() {
                        return password;
                    }

                    public void setPassword(String password) {
                        this.password = password;
                    }

                    public String getImproved() {
                        return improved;
                    }

                    public void setImproved(String improved) {
                        this.improved = improved;
                    }

                    public int getCollectCount() {
                        return collectCount;
                    }

                    public void setCollectCount(int collectCount) {
                        this.collectCount = collectCount;
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

                    public static class FinancingBean {
                        /**
                         * id : caa59d0df8b84004a31d0de91e3f4999
                         * name : 未融资
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

                    public static class Industry1Bean {
                        /**
                         * id : 805489d7cb4c41b598db6ee2398f135f
                         * remarks :
                         * createDate : 2020-07-20 13:56:10
                         * updateDate : 2020-07-20 13:56:10
                         * parent : {"id":"0","sort":30,"children":[],"parentId":"0"}
                         * parentIds : 0,
                         * name : 批发/零售/贸易
                         * sort : 8
                         * children : []
                         * level : 1
                         * parentId : 0
                         */

                        private String id;
                        private String remarks;
                        private String createDate;
                        private String updateDate;
                        private ParentBean parent;
                        private String parentIds;
                        private String name;
                        private int sort;
                        private String level;
                        private String parentId;
                        private List<?> children;

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }

                        public String getRemarks() {
                            return remarks;
                        }

                        public void setRemarks(String remarks) {
                            this.remarks = remarks;
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

                        public ParentBean getParent() {
                            return parent;
                        }

                        public void setParent(ParentBean parent) {
                            this.parent = parent;
                        }

                        public String getParentIds() {
                            return parentIds;
                        }

                        public void setParentIds(String parentIds) {
                            this.parentIds = parentIds;
                        }

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

                        public String getLevel() {
                            return level;
                        }

                        public void setLevel(String level) {
                            this.level = level;
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

                        public static class ParentBean {
                            /**
                             * id : 0
                             * sort : 30
                             * children : []
                             * parentId : 0
                             */

                            private String id;
                            private int sort;
                            private String parentId;
                            private List<?> children;

                            public String getId() {
                                return id;
                            }

                            public void setId(String id) {
                                this.id = id;
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
                    }

                    public static class Industry2Bean {
                        /**
                         * id : f6f4258ef7444a458808def367e1145b
                         * remarks :
                         * createDate : 2020-07-20 13:59:22
                         * updateDate : 2020-07-20 13:59:22
                         * parent : {"id":"805489d7cb4c41b598db6ee2398f135f","sort":30,"children":[],"parentId":"0"}
                         * parentIds : 0,805489d7cb4c41b598db6ee2398f135f,
                         * name : 消费品
                         * sort : 1
                         * children : []
                         * level : 2
                         * parentId : 805489d7cb4c41b598db6ee2398f135f
                         */

                        private String id;
                        private String remarks;
                        private String createDate;
                        private String updateDate;
                        private ParentBeanX parent;
                        private String parentIds;
                        private String name;
                        private int sort;
                        private String level;
                        private String parentId;
                        private List<?> children;

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }

                        public String getRemarks() {
                            return remarks;
                        }

                        public void setRemarks(String remarks) {
                            this.remarks = remarks;
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

                        public ParentBeanX getParent() {
                            return parent;
                        }

                        public void setParent(ParentBeanX parent) {
                            this.parent = parent;
                        }

                        public String getParentIds() {
                            return parentIds;
                        }

                        public void setParentIds(String parentIds) {
                            this.parentIds = parentIds;
                        }

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

                        public String getLevel() {
                            return level;
                        }

                        public void setLevel(String level) {
                            this.level = level;
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

                        public static class ParentBeanX {
                            /**
                             * id : 805489d7cb4c41b598db6ee2398f135f
                             * sort : 30
                             * children : []
                             * parentId : 0
                             */

                            private String id;
                            private int sort;
                            private String parentId;
                            private List<?> children;

                            public String getId() {
                                return id;
                            }

                            public void setId(String id) {
                                this.id = id;
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
                    }
                }

                public static class Industry1BeanX {
                    /**
                     * id : 805489d7cb4c41b598db6ee2398f135f
                     * name : 批发/零售/贸易
                     * sort : 30
                     * children : []
                     * parentId : 0
                     */

                    private String id;
                    private String name;
                    private int sort;
                    private String parentId;
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

                public static class Industry2BeanX {
                    /**
                     * id : f6f4258ef7444a458808def367e1145b
                     * name : 消费品
                     * sort : 30
                     * children : []
                     * parentId : 0
                     */

                    private String id;
                    private String name;
                    private int sort;
                    private String parentId;
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

                public static class EducationBean {
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

                public static class ProvinceBean {
                    /**
                     * id : 110000
                     * name : 热门城市
                     * sort : 30
                     * children : []
                     * type : 1
                     * parentId : 0
                     */

                    private String id;
                    private String name;
                    private int sort;
                    private String type;
                    private String parentId;
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

                public static class CityBean {
                    /**
                     * id : 120100
                     * name : 天津
                     * sort : 30
                     * children : []
                     * parentId : 0
                     */

                    private String id;
                    private String name;
                    private int sort;
                    private String parentId;
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
                     * id : 120116
                     * name : 滨海新区
                     * sort : 30
                     * children : []
                     * parentId : 0
                     */

                    private String id;
                    private String name;
                    private int sort;
                    private String parentId;
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

                public static class FinancingBeanX {
                    /**
                     * id : caa59d0df8b84004a31d0de91e3f4999
                     * name : 未融资
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

                public static class PositionCategory1Bean {
                    /**
                     * id : 7ba473f44bd542a5b77183cc5396b997
                     * name : 互联网/通信及硬件
                     * sort : 30
                     * children : []
                     * level : 1
                     * levelList : []
                     * parentId : 0
                     */

                    private String id;
                    private String name;
                    private int sort;
                    private String level;
                    private String parentId;
                    private List<?> children;
                    private List<?> levelList;

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

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public String getLevel() {
                        return level;
                    }

                    public void setLevel(String level) {
                        this.level = level;
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

                    public List<?> getLevelList() {
                        return levelList;
                    }

                    public void setLevelList(List<?> levelList) {
                        this.levelList = levelList;
                    }
                }

                public static class PositionCategory2Bean {
                    /**
                     * id : 8b617f7292154ccd9bfc8c2390336019
                     * name : 移动研发
                     * sort : 30
                     * children : []
                     * level : 2
                     * levelList : []
                     * parentId : 0
                     */

                    private String id;
                    private String name;
                    private int sort;
                    private String level;
                    private String parentId;
                    private List<?> children;
                    private List<?> levelList;

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

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public String getLevel() {
                        return level;
                    }

                    public void setLevel(String level) {
                        this.level = level;
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

                    public List<?> getLevelList() {
                        return levelList;
                    }

                    public void setLevelList(List<?> levelList) {
                        this.levelList = levelList;
                    }
                }

                public static class PositionCategory3Bean {
                    /**
                     * id : cc661577ddf5421285770fd4ee0c1a8e
                     * name : 移动开发
                     * sort : 30
                     * children : []
                     * level : 3
                     * levelList : []
                     * parentId : 0
                     */

                    private String id;
                    private String name;
                    private int sort;
                    private String level;
                    private String parentId;
                    private List<?> children;
                    private List<?> levelList;

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

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public String getLevel() {
                        return level;
                    }

                    public void setLevel(String level) {
                        this.level = level;
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

                    public List<?> getLevelList() {
                        return levelList;
                    }

                    public void setLevelList(List<?> levelList) {
                        this.levelList = levelList;
                    }
                }

                public static class ExperienceYearBean {
                    /**
                     * id : 165deca730524295809095ff8ac3e8a3
                     * name : 1-3年
                     * minYear : 1
                     * maxYear : 3
                     */

                    private String id;
                    private String name;
                    private int minYear;
                    private int maxYear;

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

                    public int getMinYear() {
                        return minYear;
                    }

                    public void setMinYear(int minYear) {
                        this.minYear = minYear;
                    }

                    public int getMaxYear() {
                        return maxYear;
                    }

                    public void setMaxYear(int maxYear) {
                        this.maxYear = maxYear;
                    }
                }

                public static class RecruiterBean {
                    /**
                     * id : 5c2cef9ca9cd4feb9981113cbf110d1d
                     * createDate : 2020-09-03 16:25:01
                     * updateDate : 2020-09-03 16:25:01
                     * jobNature : 1
                     * jobStatus : 1
                     * arrivalTime : 1
                     * num : job1666268
                     * name : 求职墙
                     * mobile : 16634146268
                     * avatar : http://39.96.78.51/userfiles/member/2020/9/zmftgaeckd.jpg
                     * sex : 1
                     * birthday : 1996-09
                     * workDate : 2018-09-03
                     * memberStatus : 1
                     * recruiter : 1
                     * registerDate : 2020-09-03 16:25:01
                     * password : 961a1909094ad66d25a6bc6f70b469db1b53e73c61cfc82fdba1d6fe
                     * lastReadDate : 2020-09-03 16:25:01
                     * openResume : 0
                     * openChat : 0
                     * neverLogin : 0
                     * improvedDegree : 25
                     * collectCount : 0
                     * latestCityName :
                     * positionName : HR
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
    }
}
