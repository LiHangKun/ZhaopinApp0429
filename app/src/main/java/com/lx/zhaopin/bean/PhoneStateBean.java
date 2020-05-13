package com.lx.zhaopin.bean;


import com.lx.zhaopin.http.CommonBean;

public class PhoneStateBean extends CommonBean {


    /**
     * exist :  接口中涉及到”是/否”的字段都是：1表示是，0表示否
     */

    private String exist;
    private String authCode;
    private String joinImage;
    private String ratio;
    private String count;

    public String getrRongToken() {
        return rRongToken;
    }

    public void setrRongToken(String rRongToken) {
        this.rRongToken = rRongToken;
    }

    private String rRongToken;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getJoinImage() {
        return joinImage;
    }

    public void setJoinImage(String joinImage) {
        this.joinImage = joinImage;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }
}
