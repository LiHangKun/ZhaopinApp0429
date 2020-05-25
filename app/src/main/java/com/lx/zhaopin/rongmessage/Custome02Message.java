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

//发送简历：RCD:SendJianLiMsg    ---->   JianLiMsg
//接收简历：RCD: ReceiveJianLiMsg
//拒绝接收简历：RCD: RefuseJianLiMsg
//面试邀约：RCD:SendMianShiMsg      ---->   MianShiMsg
//接受面试：RCD:ReceiveMianShiMsg
//拒绝面试：RCD:RefuseMianShiMsg
//求职者取消面试：RCD:CancleMianShiMsg
//HR取消面试：RCD:HRCancleMianShiMsg

@MessageTag(value = "RCD:TipMsg", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class Custome02Message extends MessageContent {

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

    public Custome02Message() {
    }

    public Custome02Message(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public Custome02Message(Parcel in) {
        content = in.readString();
        type = in.readString();
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    public static Custome02Message obtain(String content, String type) {
        return new Custome02Message(content, type);
    }

    public static final Creator<Custome02Message> CREATOR = new Creator<Custome02Message>() {
        @Override
        public Custome02Message createFromParcel(Parcel source) {
            return new Custome02Message(source);
        }

        @Override
        public Custome02Message[] newArray(int size) {
            return new Custome02Message[size];
        }
    };


    public Custome02Message(byte[] data) {
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
