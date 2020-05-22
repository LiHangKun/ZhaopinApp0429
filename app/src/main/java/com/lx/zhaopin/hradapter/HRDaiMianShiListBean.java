package com.lx.zhaopin.hradapter;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class HRDaiMianShiListBean extends CommonBean {


    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * date : 2020-05-23
         * interviews : [{"content":"","id":"119806e696e34412af88121611c5f966","interviewDate":"2020-05-23 00:08","interviewStatus":"3","jobhunter":{"avatar":"http://39.96.78.51/userfiles/avatar.jpg","id":"e6f5695327ae41378db342321f4bc008","name":"123"},"mobile":"15738372614","offerStatus":"","position":{"id":"566a907d24b44edcb42e523c0c140c1e","maxSalary":52,"minSalary":23,"name":"网络工程师"}},{"content":"","id":"7eb4c9cb745a432abdf4f1cd22291066","interviewDate":"2020-05-23 00:08","interviewStatus":"3","jobhunter":{"avatar":"http://39.96.78.51/userfiles/member/2020/5/rub8svf0dh.jpeg","id":"f8745c5aae5241c485daca5c3e155ef2","name":"用户"},"mobile":"15738372614","offerStatus":"","position":{"id":"566a907d24b44edcb42e523c0c140c1e","maxSalary":52,"minSalary":23,"name":"网络工程师"}},{"content":"","id":"c6fa49ebc77348f68f248677947d8a73","interviewDate":"2020-05-23 00:08","interviewStatus":"3","jobhunter":{"avatar":"http://39.96.78.51/userfiles/member/2020/5/fa2ef5j8zp.jpg","id":"04019f73793d4e3d906d70c0a27af8bb","name":"用户"},"mobile":"15738372614","offerStatus":"","position":{"id":"566a907d24b44edcb42e523c0c140c1e","maxSalary":52,"minSalary":23,"name":"网络工程师"}}]
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
             * content :
             * id : 119806e696e34412af88121611c5f966
             * interviewDate : 2020-05-23 00:08
             * interviewStatus : 3
             * jobhunter : {"avatar":"http://39.96.78.51/userfiles/avatar.jpg","id":"e6f5695327ae41378db342321f4bc008","name":"123"}
             * mobile : 15738372614
             * offerStatus :
             * position : {"id":"566a907d24b44edcb42e523c0c140c1e","maxSalary":52,"minSalary":23,"name":"网络工程师"}
             */

            private String content;
            private String id;
            private String interviewDate;
            private String interviewStatus;
            private JobhunterBean jobhunter;
            private String mobile;
            private String offerStatus;
            private PositionBean position;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getInterviewDate() {
                return interviewDate;
            }

            public void setInterviewDate(String interviewDate) {
                this.interviewDate = interviewDate;
            }

            public String getInterviewStatus() {
                return interviewStatus;
            }

            public void setInterviewStatus(String interviewStatus) {
                this.interviewStatus = interviewStatus;
            }

            public JobhunterBean getJobhunter() {
                return jobhunter;
            }

            public void setJobhunter(JobhunterBean jobhunter) {
                this.jobhunter = jobhunter;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getOfferStatus() {
                return offerStatus;
            }

            public void setOfferStatus(String offerStatus) {
                this.offerStatus = offerStatus;
            }

            public PositionBean getPosition() {
                return position;
            }

            public void setPosition(PositionBean position) {
                this.position = position;
            }

            public static class JobhunterBean {
                /**
                 * avatar : http://39.96.78.51/userfiles/avatar.jpg
                 * id : e6f5695327ae41378db342321f4bc008
                 * name : 123
                 */

                private String avatar;
                private String id;
                private String name;

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

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

            public static class PositionBean {
                /**
                 * id : 566a907d24b44edcb42e523c0c140c1e
                 * maxSalary : 52
                 * minSalary : 23
                 * name : 网络工程师
                 */

                private String id;
                private String maxSalary;
                private String minSalary;
                private String name;

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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
