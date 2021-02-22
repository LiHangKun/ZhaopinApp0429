package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

public class OfferDetailBean extends CommonBean {


    /**
     * company : {"id":"","name":""}
     * content :
     * jobhunter : {"id":"","name":""}
     * offerId :
     * offerStatus : 1
     * sendDate :
     */

    private CompanyBean company;
    private String content;
    private JobhunterBean jobhunter;
    private String offerId;
    private String offerStatus;
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

    public JobhunterBean getJobhunter() {
        return jobhunter;
    }

    public void setJobhunter(JobhunterBean jobhunter) {
        this.jobhunter = jobhunter;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
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
         * name :
         */

        private String id;
        private String name;

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

    public static class JobhunterBean {
        /**
         * id :
         * name :
         */

        private String id;
        private String name;

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
