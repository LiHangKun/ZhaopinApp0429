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
         * InterviewDate :
         * appointCount :
         * id :
         * totalCount :
         */

        private String InterviewDate;
        private String appointCount;
        private String id;
        private String totalCount;
        private String remainCount;

        public String getRemainCount() {
            return remainCount;
        }

        public void setRemainCount(String remainCount) {
            this.remainCount = remainCount;
        }

        public String getInterviewDate() {
            return InterviewDate;
        }

        public void setInterviewDate(String InterviewDate) {
            this.InterviewDate = InterviewDate;
        }

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

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }
    }
}
