package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

public class VersionBean extends CommonBean {


    /**
     * androidFile : http://www.qiuzhiqiang.com/userfiles/1/attachment//version/version/2020/6/ugoxcnsjat.apk
     * androidUrl :
     * createDate : 2020-06-29
     * iosFile : http://www.qiuzhiqiang.com
     * iosUrl :
     * num : 2
     * remarks : 求职墙,全球首发
     * version : 1.0.1
     */

    private String androidFile;
    private String androidUrl;
    private String createDate;
    private String iosFile;
    private String iosUrl;
    private String num;
    private String remarks;
    private String version;

    public String getAndroidFile() {
        return androidFile;
    }

    public void setAndroidFile(String androidFile) {
        this.androidFile = androidFile;
    }

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getIosFile() {
        return iosFile;
    }

    public void setIosFile(String iosFile) {
        this.iosFile = iosFile;
    }

    public String getIosUrl() {
        return iosUrl;
    }

    public void setIosUrl(String iosUrl) {
        this.iosUrl = iosUrl;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
