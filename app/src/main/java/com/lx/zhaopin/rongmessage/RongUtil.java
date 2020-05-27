package com.lx.zhaopin.rongmessage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lx.zhaopin.bean.LoginBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.rong.MyEmoticonModule;
import com.lx.zhaopin.rong.MyExtensionModule;
import com.lx.zhaopin.utils.SPTool;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by kxn on 2019/6/19 0019.
 */
public class RongUtil {
    public static void startConversation(Context mContext, String taid, String userName) {
        RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.PRIVATE, taid, userName);
    }

    public static void startGroupConversation(Context mContext, String taid, String userName, String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.GROUP, taid, userName, bundle);
    }

    /**
     * 会话置顶
     *
     * @param conversationType
     * @param taid
     * @param isTop
     */
    public static void setConversationToTop(Conversation.ConversationType conversationType, String taid, boolean isTop) {
        RongIM.getInstance().setConversationToTop(conversationType, taid, isTop, new RongIMClient.ResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    /**
     * 消息免打扰开启或关闭
     */
    public static void setConversationNotificationStatus(Conversation.ConversationType conversationType, String taid, boolean isLord) {

        Conversation.ConversationNotificationStatus conversationNotificationStatus;
        if (isLord)
            conversationNotificationStatus = Conversation.ConversationNotificationStatus.DO_NOT_DISTURB;
        else
            conversationNotificationStatus = Conversation.ConversationNotificationStatus.NOTIFY;

        RongIM.getInstance().setConversationNotificationStatus(conversationType, taid, conversationNotificationStatus, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                Log.e("onSuccess", "onSuccess");
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
            }
        });
    }

    /**
     * 删除会话
     *
     * @param conversationType
     * @param taid
     */
    public static void removeConversation(Conversation.ConversationType conversationType, String taid) {
        RongIM.getInstance().removeConversation(conversationType, taid, new RongIMClient.ResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }


    //发送职位消息
    public static void sendZhiWeiMessage(String targetId, String gameName, String icon) {
        RongMessageInBean rongMessageInBean = new RongMessageInBean();
        rongMessageInBean.setId(gameName);
        rongMessageInBean.setIcon(icon);
        Custome1Message custome1Message = new Custome1Message();
        custome1Message.content = new Gson().toJson(rongMessageInBean);
        RongIM.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, custome1Message, "发送职位消息", "", new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Integer integer) {
            }
        });
    }


    //查看简历,拒绝和同意  //  type 0 同意  1 拒绝    RefuseJianLiMsg
    public static void jujueAndTongYi(String targetId, String type, String icon, String duanID) {
        RongMessageInBean rongMessageInBean = new RongMessageInBean();
        rongMessageInBean.setType(type);
        rongMessageInBean.setIcon(icon);
        rongMessageInBean.setLocatin(duanID);
        Custome3Message custome3Message = new Custome3Message();
        custome3Message.content = new Gson().toJson(rongMessageInBean);
        RongIM.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, custome3Message, "查看简历,拒绝和同意", "", new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Integer integer) {
            }
        });
    }


    //发送邀约
    public static void faYaoYue(String targetId, String gongSiName, String icon, String duanID, String gongSiID) {
        RongMessageInBean rongMessageInBean = new RongMessageInBean();
        rongMessageInBean.setName(gongSiName);
        rongMessageInBean.setIcon(icon);
        rongMessageInBean.setLocatin(duanID);
        rongMessageInBean.setId(gongSiID);
        Custome03Message custome03Message = new Custome03Message();
        custome03Message.content = new Gson().toJson(rongMessageInBean);
        RongIM.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, custome03Message, "发送邀约", "", new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Integer integer) {
            }
        });
    }


    //发送职位邀约,拒绝和同意  //  type 0 同意  1 拒绝   ReceiveMianShiMsg
    public static void YaoYueJujueAndTongYi(String targetId, String type, String icon, String duanID) {
        RongMessageInBean rongMessageInBean = new RongMessageInBean();
        rongMessageInBean.setType(type);
        rongMessageInBean.setIcon(icon);
        rongMessageInBean.setLocatin(duanID);
        Custome4Message custome4Message = new Custome4Message();
        custome4Message.content = new Gson().toJson(rongMessageInBean);
        RongIM.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, custome4Message, "查看简历,拒绝和同意", "", new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Integer integer) {
            }
        });
    }


    //求职者取消面试
    public static void qiuZhiQuXiao(String targetId, String mianShiID) {
        RongMessageInBean rongMessageInBean = new RongMessageInBean();
        rongMessageInBean.setId(mianShiID);
        Custome5Message custome5Message = new Custome5Message();
        custome5Message.content = new Gson().toJson(rongMessageInBean);
        RongIM.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, custome5Message, "求职者取消面试", "", new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Integer integer) {
            }
        });
    }

    //求职者取消面试
    public static void HRQuXiao(String targetId, String mianShiID) {
        RongMessageInBean rongMessageInBean = new RongMessageInBean();
        rongMessageInBean.setId(mianShiID);
        Custome05Message custome05Message = new Custome05Message();
        custome05Message.content = new Gson().toJson(rongMessageInBean);
        RongIM.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, custome05Message, "求职者取消面试", "", new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Integer integer) {
            }
        });
    }


    //发送游戏消息
    public static void sendGameMessage(String targetId, String gameName) {
        /*MsgBean msgBean = new MsgBean();
        msgBean.gameName = gameName;
        CustomeGameMessage customeGameMessage = new CustomeGameMessage();
        customeGameMessage.content = new Gson().toJson(msgBean);
        RongIM.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, customeGameMessage, "游戏邀约", "", new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Integer integer) {
            }
        });*/
    }


    //发送提示消息
    public static void sendTipMessage(String targetId, String type, String info) {
        /*MsgBean msgBean = new MsgBean();
        msgBean.type = type;
        msgBean.info = info;
        CustomeTipMessage customeTipMessage = new CustomeTipMessage();
        customeTipMessage.content = new Gson().toJson(msgBean);
        RongIM.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, customeTipMessage, "消息", "", new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Integer integer) {
            }
        });*/
    }


    //发送Gif 消息
    public static void sendGiftMessage(String targetId, String info) {
      /*  MsgBean msgBean = new MsgBean();
        msgBean.info = info;
        CustomeGiftMessage customeGiftMessage = new CustomeGiftMessage();
        customeGiftMessage.content = new Gson().toJson(msgBean);
        RongIM.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, customeGiftMessage, "消息", "", new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Integer integer) {
            }
        });*/
    }

    public static void sendEmoticonMessage(Conversation.ConversationType conversationType, String targetId, String info) {
       /* CustomeEmoticonMessage customeEmoticonMessage = new CustomeEmoticonMessage();
        customeEmoticonMessage.content = info;
        RongIM.getInstance().sendMessage(conversationType, targetId, customeEmoticonMessage, "消息", "", new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Integer integer) {
            }
        });*/
    }

    /*public static void sendMatchMessage(String targetId, DataObjectBean dataObjectBean) {
        MsgBean msgBean = new MsgBean();
        msgBean.constellation = dataObjectBean.constellation;
        msgBean.game = dataObjectBean.game;
        msgBean.questions = dataObjectBean.questions;
        msgBean.matchingnum = dataObjectBean.matchingnum;
        msgBean.usericon = dataObjectBean.usericon;
        msgBean.selficon = AppConsts.userHead;
        msgBean.selfid = AppConsts.userId;
        msgBean.userid = dataObjectBean.userid;
        msgBean.gametype = dataObjectBean.gametype;
        msgBean.level = dataObjectBean.level;
        msgBean.city = dataObjectBean.city;
        msgBean.myconstellation = dataObjectBean.myconstellation;
        msgBean.taconstellation = dataObjectBean.taconstellation;
        msgBean.gametype1 = dataObjectBean.gametype1;
        msgBean.gametype2 = dataObjectBean.gametype2;
        msgBean.gametype3 = dataObjectBean.gametype3;
        msgBean.gametype4 = dataObjectBean.gametype4;
        msgBean.gametype5 = dataObjectBean.gametype5;

        CustomeMatchMessage customeMatchMessage = new CustomeMatchMessage();
        customeMatchMessage.content = new Gson().toJson(msgBean);
        RongIM.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, customeMatchMessage, "匹配消息", "", new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Integer integer) {

            }
        });
    }*/


    public static void sendGroupMessage(String type, String userName, String uid, String gid) {
        /*CustomeGroupTipMessage customeGroupTipMessage = new CustomeGroupTipMessage();
        customeGroupTipMessage.type = type;
        customeGroupTipMessage.userName = userName;
        customeGroupTipMessage.uid = uid;
        RongIM.getInstance().sendMessage(Conversation.ConversationType.GROUP, gid, customeGroupTipMessage, "", "", new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Integer integer) {
            }
        });*/
    }


    public static void setInputProvider() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new MyExtensionModule());
            }
        }
    }

    public static void setMyExtensionModule(Context context) {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new MyEmoticonModule(context));
            }
        }
    }

    public static void saveAccount(Context context, LoginBean accountBean) {
        List<LoginBean> accountBeans = new ArrayList<>();
        String accountJson = SPTool.getSessionValue(AppSP.ACCOUNTS, "");
        if (!TextUtils.isEmpty(accountJson)) {
            //Json的解析类对象
            JsonParser parser = new JsonParser();
            //将JSON的String 转成一个JsonArray对象
            JsonArray jsonArray = parser.parse(accountJson).getAsJsonArray();
            Gson gson = new Gson();
            //加强for循环遍历JsonArray
            for (JsonElement user : jsonArray) {
                //使用GSON，直接转成Bean对象
                accountBeans.add(gson.fromJson(user, LoginBean.class));
            }
            if (accountBeans.size() >= 5)
                accountBeans.remove(0);

            for (int i = 0; i < accountBeans.size(); i++) {
                if (accountBeans.get(i).getId().equals(accountBean.getId()))
                    return;
            }
        }
        accountBeans.add(accountBean);
        SPTool.addSessionMap(AppSP.ACCOUNTS, new Gson().toJson(accountBeans));
    }
}
