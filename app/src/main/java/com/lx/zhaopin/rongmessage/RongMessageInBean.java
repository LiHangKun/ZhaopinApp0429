package com.lx.zhaopin.rongmessage;

public class RongMessageInBean {

    private String icon;
    private String id;
    private String locatin;
    private String name;
    private String type;

    public RongMessageInBean() {
    }

    public RongMessageInBean(String icon, String id, String locatin, String name, String type) {
        this.icon = icon;
        this.id = id;
        this.locatin = locatin;
        this.name = name;
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocatin() {
        return locatin;
    }

    public void setLocatin(String locatin) {
        this.locatin = locatin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
