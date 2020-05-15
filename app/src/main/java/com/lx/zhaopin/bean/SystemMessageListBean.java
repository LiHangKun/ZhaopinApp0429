package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class SystemMessageListBean extends CommonBean {


    /**
     * dataList : [{"correlation":"1","id":"","messageType":"1","sendDate":"","title":"","unread":"1"}]
     * totalCount : 10
     * totalPage : 2
     */

    private int totalCount;
    private int totalPage;
    private List<DataListBean> dataList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * correlation : 1
         * id :
         * messageType : 1
         * sendDate :
         * title :
         * unread : 1
         */

        private String correlation;
        private String id;
        private String messageType;
        private String sendDate;
        private String title;
        private String unread;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCorrelation() {
            return correlation;
        }

        public void setCorrelation(String correlation) {
            this.correlation = correlation;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getSendDate() {
            return sendDate;
        }

        public void setSendDate(String sendDate) {
            this.sendDate = sendDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUnread() {
            return unread;
        }

        public void setUnread(String unread) {
            this.unread = unread;
        }
    }
}
