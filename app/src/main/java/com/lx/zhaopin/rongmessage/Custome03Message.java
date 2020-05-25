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

//简历：RCD:JianLiMsg
//面试：RCD:MianShiMsg
//白底：RCD:TipMsg
//提示：RCD:GrayTipMsg

//所有人的灰底白字的提示 content 就是提示文本类型
@MessageTag(value = "RCD:SendMianShiMsg", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class Custome03Message extends MessageContent {

    private static final String TAG = "Custome03Message";
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

    public Custome03Message() {
    }

    public Custome03Message(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public Custome03Message(Parcel in) {
        content = in.readString();
        type = in.readString();
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    public static Custome03Message obtain(String content, String type) {
        return new Custome03Message(content, type);
    }

    public static final Creator<Custome03Message> CREATOR = new Creator<Custome03Message>() {
        @Override
        public Custome03Message createFromParcel(Parcel source) {
            return new Custome03Message(source);
        }

        @Override
        public Custome03Message[] newArray(int size) {
            return new Custome03Message[size];
        }
    };


    public Custome03Message(byte[] data) {
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
