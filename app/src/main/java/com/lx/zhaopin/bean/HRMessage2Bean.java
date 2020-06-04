package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class HRMessage2Bean extends CommonBean {


    /**
     * dataList : [{"chatStatus":"1","id":"","jobhunter":{"avatar":"","id":"","name":""},"lastChatDate":"","unreadCount":""}]
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
         * chatStatus : 1
         * id :
         * jobhunter : {"avatar":"","id":"","name":""}
         * lastChatDate :
         * unreadCount :
         */

        private String chatStatus;
        private String id;
        private JobhunterBean jobhunter;
        private String lastChatDate;
        private String unreadCount;
        private String positionName;

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getChatStatus() {
            return chatStatus;
        }

        public void setChatStatus(String chatStatus) {
            this.chatStatus = chatStatus;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public JobhunterBean getJobhunter() {
            return jobhunter;
        }

        public void setJobhunter(JobhunterBean jobhunter) {
            this.jobhunter = jobhunter;
        }

        public String getLastChatDate() {
            return lastChatDate;
        }

        public void setLastChatDate(String lastChatDate) {
            this.lastChatDate = lastChatDate;
        }

        public String getUnreadCount() {
            return unreadCount;
        }

        public void setUnreadCount(String unreadCount) {
            this.unreadCount = unreadCount;
        }

        public static class JobhunterBean {
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
    }
}
