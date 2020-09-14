package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class MianShiListBean extends CommonBean {


    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * date : 2020-09-03
         * interviews : [{"id":"4439db896cfc4e0d8c4f00a852ddd5d4","createDate":"2020-09-02 17:32:09","updateDate":"2020-09-02 17:32:15","company":{"id":"2c8a9f17078b4330ad2b73e9ffaa61ae","name":"测试","logo":"http://39.96.78.51/userfiles/company/2020/8/jn1zujyn19.png","financingName":"","industry2Name":"","visits":0},"recruiter":{"id":"053bec92ddfe41d08c95ce3ab98866ef","createDate":"2020-08-05 14:41:31","updateDate":"2020-09-09 17:43:45","jobNature":"2","jobStatus":"3","arrivalTime":"1","num":"job1760525","name":"巽风","mobile":"17653420525","avatar":"http://39.96.78.51/userfiles/member/2020/8/6khkocn0v7.JPG","sex":"1","birthday":"2020-08","workDate":"2018-08-05","experienceEducation":{"id":"ab65fcd0fdd94f998a33e67de596effc","school":"德州科技职业学院","major":"计算机科学与技术"},"education":{"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"},"educationSort":"3","memberStatus":"1","recruiter":"1","registerDate":"2020-08-05 14:41:31","password":"98b10a895d6ee1eda6c558cdb514f74b544ab6590f190efb7fbb27da","lastReadDate":"2020-08-05 14:41:31","openResume":"0","openChat":"0","neverLogin":"0","positionCategories":"","improvedDegree":100,"collectCount":0,"latestCityName":"","positionName":"HR"},"member":{"id":"053bec92ddfe41d08c95ce3ab98866ef","name":"巽风","mobile":"17653420525","avatar":"/userfiles/member/2020/8/6khkocn0v7.JPG","latestCityName":""},"position":{"id":"d9e565cafc434ab8853d35191c5c6ce1","name":"csssssss","minSalary":1,"maxSalary":1},"interviewDate":"2020-09-03 08:01","mobile":"17653420525","location":"苏州路与锦州道交叉口西150米","lng":"117.652255","lat":"39.038641","chat":{"id":"3683dd7f87344190825c44f0fef7daac","initiator":"1","unreadCount":15,"jobhunterUnreadCount":1,"recruiterUnreadCount":0,"chatDetailList":[]},"interviewStatus":"11","offer":"13a772a9ccb541cd85f74ea36ff2b31c","agreeDate":"2020-09-02 17:32:15","arrivalDate":"2020-09-02 17:32:59","assessDate":"2020-09-02 17:33:06","offerStatus":"","content":""},{"id":"8c4481d682ef447ca6374a1b226abb69","createDate":"2020-09-02 16:43:27","updateDate":"2020-09-02 16:43:40","company":{"id":"2c8a9f17078b4330ad2b73e9ffaa61ae","name":"测试","logo":"http://39.96.78.51/userfiles/company/2020/8/jn1zujyn19.png","financingName":"","industry2Name":"","visits":0},"recruiter":{"id":"053bec92ddfe41d08c95ce3ab98866ef","createDate":"2020-08-05 14:41:31","updateDate":"2020-09-09 17:43:45","jobNature":"2","jobStatus":"3","arrivalTime":"1","num":"job1760525","name":"巽风","mobile":"17653420525","avatar":"http://39.96.78.51/userfiles/member/2020/8/6khkocn0v7.JPG","sex":"1","birthday":"2020-08","workDate":"2018-08-05","experienceEducation":{"id":"ab65fcd0fdd94f998a33e67de596effc","school":"德州科技职业学院","major":"计算机科学与技术"},"education":{"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"},"educationSort":"3","memberStatus":"1","recruiter":"1","registerDate":"2020-08-05 14:41:31","password":"98b10a895d6ee1eda6c558cdb514f74b544ab6590f190efb7fbb27da","lastReadDate":"2020-08-05 14:41:31","openResume":"0","openChat":"0","neverLogin":"0","positionCategories":"","improvedDegree":100,"collectCount":0,"latestCityName":"","positionName":"HR"},"member":{"id":"053bec92ddfe41d08c95ce3ab98866ef","name":"巽风","mobile":"17653420525","avatar":"/userfiles/member/2020/8/6khkocn0v7.JPG","latestCityName":""},"position":{"id":"d9e565cafc434ab8853d35191c5c6ce1","name":"csssssss","minSalary":1,"maxSalary":1},"interviewDate":"2020-09-03 08:01","mobile":"17653420525","location":"苏州路与锦州道交叉口西150米","lng":"117.652255","lat":"39.038641","chat":{"id":"3683dd7f87344190825c44f0fef7daac","initiator":"1","unreadCount":15,"jobhunterUnreadCount":1,"recruiterUnreadCount":0,"chatDetailList":[]},"interviewStatus":"11","offer":"a6113acb2f9a4e12934e6d12d2b23ac7","agreeDate":"2020-09-02 16:43:40","arrivalDate":"2020-09-02 16:43:51","assessDate":"2020-09-02 16:43:56","offerStatus":"","content":""}]
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
             * id : 4439db896cfc4e0d8c4f00a852ddd5d4
             * createDate : 2020-09-02 17:32:09
             * updateDate : 2020-09-02 17:32:15
             * company : {"id":"2c8a9f17078b4330ad2b73e9ffaa61ae","name":"测试","logo":"http://39.96.78.51/userfiles/company/2020/8/jn1zujyn19.png","financingName":"","industry2Name":"","visits":0}
             * recruiter : {"id":"053bec92ddfe41d08c95ce3ab98866ef","createDate":"2020-08-05 14:41:31","updateDate":"2020-09-09 17:43:45","jobNature":"2","jobStatus":"3","arrivalTime":"1","num":"job1760525","name":"巽风","mobile":"17653420525","avatar":"http://39.96.78.51/userfiles/member/2020/8/6khkocn0v7.JPG","sex":"1","birthday":"2020-08","workDate":"2018-08-05","experienceEducation":{"id":"ab65fcd0fdd94f998a33e67de596effc","school":"德州科技职业学院","major":"计算机科学与技术"},"education":{"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"},"educationSort":"3","memberStatus":"1","recruiter":"1","registerDate":"2020-08-05 14:41:31","password":"98b10a895d6ee1eda6c558cdb514f74b544ab6590f190efb7fbb27da","lastReadDate":"2020-08-05 14:41:31","openResume":"0","openChat":"0","neverLogin":"0","positionCategories":"","improvedDegree":100,"collectCount":0,"latestCityName":"","positionName":"HR"}
             * member : {"id":"053bec92ddfe41d08c95ce3ab98866ef","name":"巽风","mobile":"17653420525","avatar":"/userfiles/member/2020/8/6khkocn0v7.JPG","latestCityName":""}
             * position : {"id":"d9e565cafc434ab8853d35191c5c6ce1","name":"csssssss","minSalary":1,"maxSalary":1}
             * interviewDate : 2020-09-03 08:01
             * mobile : 17653420525
             * location : 苏州路与锦州道交叉口西150米
             * lng : 117.652255
             * lat : 39.038641
             * chat : {"id":"3683dd7f87344190825c44f0fef7daac","initiator":"1","unreadCount":15,"jobhunterUnreadCount":1,"recruiterUnreadCount":0,"chatDetailList":[]}
             * interviewStatus : 11
             * offer : 13a772a9ccb541cd85f74ea36ff2b31c
             * agreeDate : 2020-09-02 17:32:15
             * arrivalDate : 2020-09-02 17:32:59
             * assessDate : 2020-09-02 17:33:06
             * offerStatus :
             * content :
             */

            private String id;
            private String createDate;
            private String updateDate;
            private CompanyBean company;
            private RecruiterBean recruiter;
            private MemberBean member;
            private PositionBean position;
            private String interviewDate;
            private String mobile;
            private String location;
            private String lng;
            private String lat;
            private ChatBean chat;
            private String interviewStatus;
            private String offer;
            private String agreeDate;
            private String arrivalDate;
            private String assessDate;
            private String offerStatus;
            private String content;

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

            public CompanyBean getCompany() {
                return company;
            }

            public void setCompany(CompanyBean company) {
                this.company = company;
            }

            public RecruiterBean getRecruiter() {
                return recruiter;
            }

            public void setRecruiter(RecruiterBean recruiter) {
                this.recruiter = recruiter;
            }

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
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

            public ChatBean getChat() {
                return chat;
            }

            public void setChat(ChatBean chat) {
                this.chat = chat;
            }

            public String getInterviewStatus() {
                return interviewStatus;
            }

            public void setInterviewStatus(String interviewStatus) {
                this.interviewStatus = interviewStatus;
            }

            public String getOffer() {
                return offer;
            }

            public void setOffer(String offer) {
                this.offer = offer;
            }

            public String getAgreeDate() {
                return agreeDate;
            }

            public void setAgreeDate(String agreeDate) {
                this.agreeDate = agreeDate;
            }

            public String getArrivalDate() {
                return arrivalDate;
            }

            public void setArrivalDate(String arrivalDate) {
                this.arrivalDate = arrivalDate;
            }

            public String getAssessDate() {
                return assessDate;
            }

            public void setAssessDate(String assessDate) {
                this.assessDate = assessDate;
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
                 * id : 2c8a9f17078b4330ad2b73e9ffaa61ae
                 * name : 测试
                 * logo : http://39.96.78.51/userfiles/company/2020/8/jn1zujyn19.png
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

            public static class RecruiterBean {
                /**
                 * id : 053bec92ddfe41d08c95ce3ab98866ef
                 * createDate : 2020-08-05 14:41:31
                 * updateDate : 2020-09-09 17:43:45
                 * jobNature : 2
                 * jobStatus : 3
                 * arrivalTime : 1
                 * num : job1760525
                 * name : 巽风
                 * mobile : 17653420525
                 * avatar : http://39.96.78.51/userfiles/member/2020/8/6khkocn0v7.JPG
                 * sex : 1
                 * birthday : 2020-08
                 * workDate : 2018-08-05
                 * experienceEducation : {"id":"ab65fcd0fdd94f998a33e67de596effc","school":"德州科技职业学院","major":"计算机科学与技术"}
                 * education : {"id":"3214b5eb26364ccc97832d2d73ed21e6","name":"专科"}
                 * educationSort : 3
                 * memberStatus : 1
                 * recruiter : 1
                 * registerDate : 2020-08-05 14:41:31
                 * password : 98b10a895d6ee1eda6c558cdb514f74b544ab6590f190efb7fbb27da
                 * lastReadDate : 2020-08-05 14:41:31
                 * openResume : 0
                 * openChat : 0
                 * neverLogin : 0
                 * positionCategories :
                 * improvedDegree : 100
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
                private ExperienceEducationBean experienceEducation;
                private EducationBean education;
                private String educationSort;
                private String memberStatus;
                private String recruiter;
                private String registerDate;
                private String password;
                private String lastReadDate;
                private String openResume;
                private String openChat;
                private String neverLogin;
                private String positionCategories;
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

                public ExperienceEducationBean getExperienceEducation() {
                    return experienceEducation;
                }

                public void setExperienceEducation(ExperienceEducationBean experienceEducation) {
                    this.experienceEducation = experienceEducation;
                }

                public EducationBean getEducation() {
                    return education;
                }

                public void setEducation(EducationBean education) {
                    this.education = education;
                }

                public String getEducationSort() {
                    return educationSort;
                }

                public void setEducationSort(String educationSort) {
                    this.educationSort = educationSort;
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

                public String getPositionCategories() {
                    return positionCategories;
                }

                public void setPositionCategories(String positionCategories) {
                    this.positionCategories = positionCategories;
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

                public static class ExperienceEducationBean {
                    /**
                     * id : ab65fcd0fdd94f998a33e67de596effc
                     * school : 德州科技职业学院
                     * major : 计算机科学与技术
                     */

                    private String id;
                    private String school;
                    private String major;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getSchool() {
                        return school;
                    }

                    public void setSchool(String school) {
                        this.school = school;
                    }

                    public String getMajor() {
                        return major;
                    }

                    public void setMajor(String major) {
                        this.major = major;
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
            }

            public static class MemberBean {
                /**
                 * id : 053bec92ddfe41d08c95ce3ab98866ef
                 * name : 巽风
                 * mobile : 17653420525
                 * avatar : /userfiles/member/2020/8/6khkocn0v7.JPG
                 * latestCityName :
                 */

                private String id;
                private String name;
                private String mobile;
                private String avatar;
                private String latestCityName;

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

                public String getLatestCityName() {
                    return latestCityName;
                }

                public void setLatestCityName(String latestCityName) {
                    this.latestCityName = latestCityName;
                }
            }

            public static class PositionBean {
                /**
                 * id : d9e565cafc434ab8853d35191c5c6ce1
                 * name : csssssss
                 * minSalary : 1
                 * maxSalary : 1
                 */

                private String id;
                private String name;
                private int minSalary;
                private int maxSalary;

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
            }

            public static class ChatBean {
                /**
                 * id : 3683dd7f87344190825c44f0fef7daac
                 * initiator : 1
                 * unreadCount : 15
                 * jobhunterUnreadCount : 1
                 * recruiterUnreadCount : 0
                 * chatDetailList : []
                 */

                private String id;
                private String initiator;
                private int unreadCount;
                private int jobhunterUnreadCount;
                private int recruiterUnreadCount;
                private List<?> chatDetailList;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getInitiator() {
                    return initiator;
                }

                public void setInitiator(String initiator) {
                    this.initiator = initiator;
                }

                public int getUnreadCount() {
                    return unreadCount;
                }

                public void setUnreadCount(int unreadCount) {
                    this.unreadCount = unreadCount;
                }

                public int getJobhunterUnreadCount() {
                    return jobhunterUnreadCount;
                }

                public void setJobhunterUnreadCount(int jobhunterUnreadCount) {
                    this.jobhunterUnreadCount = jobhunterUnreadCount;
                }

                public int getRecruiterUnreadCount() {
                    return recruiterUnreadCount;
                }

                public void setRecruiterUnreadCount(int recruiterUnreadCount) {
                    this.recruiterUnreadCount = recruiterUnreadCount;
                }

                public List<?> getChatDetailList() {
                    return chatDetailList;
                }

                public void setChatDetailList(List<?> chatDetailList) {
                    this.chatDetailList = chatDetailList;
                }
            }
        }
    }
}
