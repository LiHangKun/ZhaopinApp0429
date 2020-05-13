package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class GuanZhuGongSiBean extends CommonBean {


    /**
     * dataList : [{"financing":{"name":""},"id":"","industry":{"name":""},"logo":"","name":"","staffNum":""}]
     * totalCount : 1
     * totalPage : 1
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
         * financing : {"name":""}
         * id :
         * industry : {"name":""}
         * logo :
         * name :
         * staffNum :
         */

        private FinancingBean financing;
        private String id;
        private IndustryBean industry;
        private String logo;
        private String name;
        private String staffNum;

        public FinancingBean getFinancing() {
            return financing;
        }

        public void setFinancing(FinancingBean financing) {
            this.financing = financing;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public IndustryBean getIndustry() {
            return industry;
        }

        public void setIndustry(IndustryBean industry) {
            this.industry = industry;
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

        public String getStaffNum() {
            return staffNum;
        }

        public void setStaffNum(String staffNum) {
            this.staffNum = staffNum;
        }

        public static class FinancingBean {
            /**
             * name :
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class IndustryBean {
            /**
             * name :
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
