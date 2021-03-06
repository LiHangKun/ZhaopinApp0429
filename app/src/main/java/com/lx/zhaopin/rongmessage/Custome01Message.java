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


//HR 看到求职者给我发送的一份求职简历,HR的操作是拒绝或者同意
@MessageTag(value = "RCD:ReceiveJianLiMsg", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class Custome01Message extends MessageContent {

    private static final String TAG = "Custome01Message";
    public String content;//这个就是简历的 ID
    public String type;

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

    public Custome01Message() {
    }

    public Custome01Message(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public Custome01Message(Parcel in) {
        content = in.readString();
        type = in.readString();
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    public static Custome01Message obtain(String content, String type) {
        return new Custome01Message(content, type);
    }

    public static final Creator<Custome01Message> CREATOR = new Creator<Custome01Message>() {
        @Override
        public Custome01Message createFromParcel(Parcel source) {
            return new Custome01Message(source);
        }

        @Override
        public Custome01Message[] newArray(int size) {
            return new Custome01Message[size];
        }
    };


    public Custome01Message(byte[] data) {
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
