package com.lx.zhaopin.rong;

import android.os.Parcel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

/**
 * Created by kxn on 2019/12/26 0026.
 */
@MessageTag(value = "RCD:MatchMsg", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class CustomeMatchMessage extends MessageContent {
    private static final String TAG = "CustomeMatchMessage";
    public String content;
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public CustomeMatchMessage() {
    }
    public CustomeMatchMessage(String content) {
        this.content = content;
    }

    public CustomeMatchMessage(Parcel in) {
        content = in.readString();
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    public static CustomeMatchMessage obtain(String content) {
        return new CustomeMatchMessage(content);
    }

    public static final Creator<CustomeMatchMessage> CREATOR = new Creator<CustomeMatchMessage>() {
        @Override
        public CustomeMatchMessage createFromParcel(Parcel source) {
            return new CustomeMatchMessage(source);
        }

        @Override
        public CustomeMatchMessage[] newArray(int size) {
            return new CustomeMatchMessage[size];
        }
    };

    public CustomeMatchMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {

        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);

            if (jsonObj.has("content"))
                content = jsonObj.optString("content");

        } catch (JSONException e) {
        }

    }

    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("content", getContent());
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
        ParcelUtils.writeToParcel(parcel, getUserInfo());
    }
}

