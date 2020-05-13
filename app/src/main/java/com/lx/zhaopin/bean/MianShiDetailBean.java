package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class MianShiDetailBean extends CommonBean {


    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * cancelReason :
         * company : {"id":"","logo":"","name":""}
         * denyReason : 不合适原因
         * id :
         * interviewDate :
         * interviewStatus :
         * lat :
         * lng :
         * location :
         * mobile :
         * nullifier : 1
         * offer :
         * position : {"id":"","maxSalary":"","minSalary":"","name":""}
         * remarks : 备注
         */

        private String cancelReason;
        private CompanyBean company;
        private String denyReason;
        private String id;
        private String interviewDate;
        private String interviewStatus;
        private String lat;
        private String lng;
        private String location;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String mobile;
        private String nullifier;
        private String offer;
        private PositionBean position;
        private String remarks;

        public String getCancelReason() {
            return cancelReason;
        }

        public void setCancelReason(String cancelReason) {
            this.cancelReason = cancelReason;
        }

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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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
