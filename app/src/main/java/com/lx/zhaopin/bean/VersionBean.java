package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

public class VersionBean extends CommonBean {


    /**
     * content :
     * number :
     * type : 0
     * url :
     * version :
     */

    private String content;
    private String number;
    private String type;
    private String url;
    private String version;
    private String num;
    private String androidFile;
    private String androidUrl;
    private String iosFile;
    private String iosUrl;
    private String remarks;
    private String createDate;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
