package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

public class AboutMeBean extends CommonBean {


    /**
     * address :
     * company : 1
     * email :
     * phone :
     * website :
     */

    private String address;
    private String company;
    private String email;
    private String phone;
    private String website;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
