package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class HRMianShiListBean extends CommonBean {


    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * date : 2020-05-30
         * interviews : [{"cancelReason":"有事不去了","content":"","id":"1ae7cf5a9b634cc1966bcb037372ba7c","interviewDate":"2020-05-30 00:00","interviewStatus":"6","jobhunter":{"avatar":"http://39.96.78.51/userfiles/member/2020/5/rnthf8vslc.jpeg","id":"e6151ce13783416bad54fffc10b19c0d","name":"崔老师好"},"mobile":"17600057277","nullifier":"2","offerStatus":"","position":{"id":"52cd69637f754a4bb5af79edb48b1037","maxSalary":50,"minSalary":20,"name":"iOS工程师"}}]
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
             * cancelReason : 有事不去了
             * content :
             * id : 1ae7cf5a9b634cc1966bcb037372ba7c
             * interviewDate : 2020-05-30 00:00
             * interviewStatus : 6
             * jobhunter : {"avatar":"http://39.96.78.51/userfiles/member/2020/5/rnthf8vslc.jpeg","id":"e6151ce13783416bad54fffc10b19c0d","name":"崔老师好"}
             * mobile : 17600057277
             * nullifier : 2
             * offerStatus :
             * position : {"id":"52cd69637f754a4bb5af79edb48b1037","maxSalary":50,"minSalary":20,"name":"iOS工程师"}
             */

            private String cancelReason;
            private String content;
            private String id;
            private String interviewDate;
            private String interviewStatus;
            private JobhunterBean jobhunter;
            private String mobile;
            private String nullifier;
            private String offerStatus;
            private String offer;

            public String getOffer() {
                return offer;
            }

            public void setOffer(String offer) {
                this.offer = offer;
            }

            public String getSendDate() {
                return sendDate;
            }

            public void setSendDate(String sendDate) {
                this.sendDate = sendDate;
            }

            private String sendDate;
            private PositionBean position;

            public String getCancelReason() {
                return cancelReason;
            }

            public void setCancelReason(String cancelReason) {
                this.cancelReason = cancelReason;
            }

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

            public String getNullifier() {
                return nullifier;
            }

            public void setNullifier(String nullifier) {
                this.nullifier = nullifier;
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
                 * avatar : http://39.96.78.51/userfiles/member/2020/5/rnthf8vslc.jpeg
                 * id : e6151ce13783416bad54fffc10b19c0d
                 * name : 崔老师好
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
                 * id : 52cd69637f754a4bb5af79edb48b1037
                 * maxSalary : 50
                 * minSalary : 20
                 * name : iOS工程师
                 */

                private String id;
                private int maxSalary;
                private int minSalary;
                private String name;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
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
            }
        }
    }
}
