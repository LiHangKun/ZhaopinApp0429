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

//HR 或求职者 界面的白底消息, type 1,查看详情; 可以点击 type 2 您已向对方发送面试邀约,不可以点击; 3 您已接受面试邀请 不可以点击
@MessageTag(value = "RCD:TipMsg", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class Custome2Message extends MessageContent {

    private static final String TAG = "Custome2Message";
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

    public Custome2Message() {
    }

    public Custome2Message(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public Custome2Message(Parcel in) {
        content = in.readString();
        type = in.readString();
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    public static Custome2Message obtain(String content, String type) {
        return new Custome2Message(content, type);
    }

    public static final Creator<Custome2Message> CREATOR = new Creator<Custome2Message>() {
        @Override
        public Custome2Message createFromParcel(Parcel source) {
            return new Custome2Message(source);
        }

        @Override
        public Custome2Message[] newArray(int size) {
            return new Custome2Message[size];
        }
    };


    public Custome2Message(byte[] data) {
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
