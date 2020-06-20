package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class MessageDetailBean extends CommonBean {


    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * chatDate :
         * chatStatus : 1
         * lastChatDate :
         * subhead : 副标题
         */

        private String chatDate;
        private String chatStatus;
        private String lastChatDate;
        private String subhead;
        private String feedback;
        private String feedbackContent;

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public String getFeedbackContent() {
            return feedbackContent;
        }

        public void setFeedbackContent(String feedbackContent) {
            this.feedbackContent = feedbackContent;
        }

        public String getChatDate() {
            return chatDate;
        }

        public void setChatDate(String chatDate) {
            this.chatDate = chatDate;
        }

        public String getChatStatus() {
            return chatStatus;
        }

        public void setChatStatus(String chatStatus) {
            this.chatStatus = chatStatus;
        }

        public String getLastChatDate() {
            return lastChatDate;
        }

        public void setLastChatDate(String lastChatDate) {
            this.lastChatDate = lastChatDate;
        }

        public String getSubhead() {
            return subhead;
        }

        public void setSubhead(String subhead) {
            this.subhead = subhead;
        }
    }
}
