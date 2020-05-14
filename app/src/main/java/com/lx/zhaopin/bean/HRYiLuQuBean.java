package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class HRYiLuQuBean extends CommonBean {


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
         * offerList : [{"content":"1","id":"","member":{"avatar":"","id":"","name":""},"offerStatus":"1","position":{"id":"","maxSalary":"","minSalary":"","name":""},"sendDate":""}]
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
             * content : 1
             * id :
             * member : {"avatar":"","id":"","name":""}
             * offerStatus : 1
             * position : {"id":"","maxSalary":"","minSalary":"","name":""}
             * sendDate :
             */

            private String content;
            private String id;
            private MemberBean member;
            private String offerStatus;
            private PositionBean position;
            private String sendDate;

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

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
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

            public static class MemberBean {
                /**
                 * avatar :
                 * id :
                 * name :
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
