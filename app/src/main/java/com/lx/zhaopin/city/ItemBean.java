package com.lx.zhaopin.city;

public abstract class ItemBean extends JavaBean {
    private String areaId;
    private String areaName;

    public ItemBean() {
    }

    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String toString() {
        return "areaId=" + this.areaId + ",areaName=" + this.areaName;
    }
}
