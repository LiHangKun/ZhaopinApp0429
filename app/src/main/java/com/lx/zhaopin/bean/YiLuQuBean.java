package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class YiLuQuBean extends CommonBean {


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
         * offerList : [{"company":{"id":"","logo":"","name":""},"content":"1","id":"","offerStatus":"1","position":{"id":"","maxSalary":"","minSalary":"","name":""},"sendDate":""}]
         */

        private String date;
        private List<OfferListBean> offerList;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<OfferListBean> getOfferList() {
            return offerList;
        }

        public void setOfferList(List<OfferListBean> offerList) {
            this.offerList = offerList;
        }

        public static class OfferListBean {
            /**
             * company : {"id":"","logo":"","name":""}
             * content : 1
             * id :
             * offerStatus : 1
             * position : {"id":"","maxSalary":"","minSalary":"","name":""}
             * sendDate :
             */

            private CompanyBean company;
            private String content;
            private String id;
            private String offerStatus;
            private PositionBean position;
            private String sendDate;

            public CompanyBean getCompany() {
                return company;
            }

            public void setCompany(CompanyBean company) {
                this.company = company;
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

            public String getSendDate() {
                return sendDate;
            }

            public void setSendDate(String sendDate) {
                this.sendDate = sendDate;
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
