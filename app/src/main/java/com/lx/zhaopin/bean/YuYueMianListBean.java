package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class YuYueMianListBean extends CommonBean {


    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * appointCount :
         * id : 21281ba3eed54d79b45dec4c14c8bf26
         * interviewDate : 2020-05-17 09:00
         * remainCount :
         * totalCount :
         */

        private String appointCount;
        private String id;
        private String interviewDate;
        private String remainCount;
        private String totalCount;

        public String getAppointCount() {
            return appointCount;
        }

        public void setAppointCount(String appointCount) {
            this.appointCount = appointCount;
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

        public String getRemainCount() {
            return remainCount;
        }

        public void setRemainCount(String remainCount) {
            this.remainCount = remainCount;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }
    }
}
