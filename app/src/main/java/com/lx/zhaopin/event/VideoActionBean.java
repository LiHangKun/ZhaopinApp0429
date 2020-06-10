package com.lx.zhaopin.event;

public class VideoActionBean {

    private String thumbsup;
    private String is_thumbsup;
    private String status;
    private String unreadCount;

    public String getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(String unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumbsup() {
        return thumbsup;
    }

    public void setThumbsup(String thumbsup) {
        this.thumbsup = thumbsup;
    }

    public String getIs_thumbsup() {
        return is_thumbsup;
    }

    public void setIs_thumbsup(String is_thumbsup) {
        this.is_thumbsup = is_thumbsup;
    }
}
