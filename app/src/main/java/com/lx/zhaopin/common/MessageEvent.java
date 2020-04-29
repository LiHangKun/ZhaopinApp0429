package com.lx.zhaopin.common;

public class MessageEvent {

    public int messageType;
    public String keyWord1;
    public String keyWord2;
    public String keyWord3;
    public String keyWord4;

    public MessageEvent(){}

    public MessageEvent(int messageType, String keyWord1, String keyWord2, String keyWord3, String keyWord4) {
        this.messageType = messageType;
        this.keyWord1 = keyWord1;
        this.keyWord2 = keyWord2;
        this.keyWord3 = keyWord3;
        this.keyWord4 = keyWord4;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getKeyWord1() {
        return keyWord1;
    }

    public void setKeyWord1(String keyWord1) {
        this.keyWord1 = keyWord1;
    }

    public String getKeyWord2() {
        return keyWord2;
    }

    public void setKeyWord2(String keyWord2) {
        this.keyWord2 = keyWord2;
    }

    public String getKeyWord3() {
        return keyWord3;
    }

    public void setKeyWord3(String keyWord3) {
        this.keyWord3 = keyWord3;
    }

    public String getKeyWord4() {
        return keyWord4;
    }

    public void setKeyWord4(String keyWord4) {
        this.keyWord4 = keyWord4;
    }
}
