package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

public class LoginBean extends CommonBean {


    /**
     * avatar : 头像
     * jRongToken :
     * latestCity : {"id":"","lat":"","lng":"","name":""}
     * mid : 用户id
     * mobile : 手机号
     * name : 名称
     * neverLogin : 1
     */

    private String avatar;
    private String jRongToken;
    private LatestCityBean latestCity;
    private String mid;
    private String id;
    private String mobile;
    private String rRongToken;

    public String getrRongToken() {
        return rRongToken;
    }

    public void setrRongToken(String rRongToken) {
        this.rRongToken = rRongToken;
    }

    public String getjRongToken() {
        return jRongToken;
    }

    public void setjRongToken(String jRongToken) {
        this.jRongToken = jRongToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private String neverLogin;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getJRongToken() {
        return jRongToken;
    }

    public void setJRongToken(String jRongToken) {
        this.jRongToken = jRongToken;
    }

    public LatestCityBean getLatestCity() {
        return latestCity;
    }

    public void setLatestCity(LatestCityBean latestCity) {
        this.latestCity = latestCity;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeverLogin() {
        return neverLogin;
    }

    public void setNeverLogin(String neverLogin) {
        this.neverLogin = neverLogin;
    }

    public static class LatestCityBean {
        /**
         * id :
         * lat :
         * lng :
         * name :
         */

        private String id;
        private String lat;
        private String lng;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
