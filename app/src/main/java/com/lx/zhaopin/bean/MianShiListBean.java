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
         * date :
         * interviews : [{"company":{"id":"","logo":"","name":""},"denyReason":"不合适原因","id":"","interviewDate":"","interviewStatus":"","offer":"","position":{"id":"","maxSalary":"","minSalary":"","name":""}}]
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
             * company : {"id":"","logo":"","name":""}
             * denyReason : 不合适原因
             * id :
             * interviewDate :
             * interviewStatus :
             * offer :
             * position : {"id":"","maxSalary":"","minSalary":"","name":""}
             */

            private CompanyBean company;
            private String denyReason;
            private String id;
            private String interviewDate;
            private String interviewStatus;
            private String offer;
            private PositionBean position;

            public CompanyBean getCompany() {
                return company;
            }

            public void setCompany(CompanyBean company) {
                this.company = company;
            }

            public String getDenyReason() {
                return denyReason;
            }

            public void setDenyReason(String denyReason) {
                this.denyReason = denyReason;
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

            public String getOffer() {
                return offer;
            }

            public void setOffer(String offer) {
                this.offer = offer;
            }

            public PositionBean getPosition() {
                return position;
            }

            public void setPosition(PositionBean position) {
                this.position = position;
            }

            public static class CompanyBean {
                /**
                 * id :
                 * logo :
                 * name :
                 */

                private String id;
                private String logo;
                private String name;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
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
            }

            public static class PositionBean {
                /**
                 * id :
                 * maxSalary :
                 * minSalary :
                 * name :
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
