package com.lx.zhaopin.rongmessage;

import android.os.Parcel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

//参考  CustomeGroupTipMessage

@MessageTag(value = "RCD:CancleMianShiMsg", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class Custome5Message extends MessageContent {

    private static final String TAG = "Custome5Message";
    public String content;//接收的邀约ID
    public String type;//企业的ID 用于查询企业的名字和 企业的头像

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Custome5Message() {
    }

    public Custome5Message(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public Custome5Message(Parcel in) {
        content = in.readString();
        type = in.readString();
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    public static Custome5Message obtain(String content, String type) {
        return new Custome5Message(content, type);
    }

    public static final Creator<Custome5Message> CREATOR = new Creator<Custome5Message>() {
        @Override
        public Custome5Message createFromParcel(Parcel source) {
            return new Custome5Message(source);
        }

        @Override
        public Custome5Message[] newArray(int size) {
            return new Custome5Message[size];
        }
    };


    public Custome5Message(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {

        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);

            if (jsonObj.has("content"))
                content = jsonObj.optString("content");
            if (jsonObj.has("type"))
                type = jsonObj.optString("type");

        } catch (JSONException e) {
        }

    }


    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content", getContent());
            jsonObject.put("type", getType());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        try {
            return jsonObject.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(content);
        parcel.writeString(type);
        ParcelUtils.writeToParcel(parcel, getUserInfo());
    }
}
