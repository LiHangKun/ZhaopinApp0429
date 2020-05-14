package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class QiuZhiFeedBean extends CommonBean {


    /**
     * dataList : [{"chatStatus":"1","company":{"id":"","logo":"","name":""},"id":"","lastChatDate":"","unreadCount":""}]
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
         * company : {"id":"","logo":"","name":""}
         * id :
         * lastChatDate :
         * unreadCount :
         */

        private String chatStatus;
        private CompanyBean company;
        private String id;
        private String lastChatDate;
        private String unreadCount;

        public String getChatStatus() {
            return chatStatus;
        }

        public void setChatStatus(String chatStatus) {
            this.chatStatus = chatStatus;
        }

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
    }
}
