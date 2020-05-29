package com.lx.zhaopin.rong;


import android.util.Log;

import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.utils.SPTool;

import java.util.List;

import io.rong.callkit.AudioPlugin;
import io.rong.callkit.VideoPlugin;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.emoticon.IEmoticonTab;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

/**
 * Created by kxn on 2019/6/22 0022.
 */
public class MyExtensionModule extends DefaultExtensionModule {

    private static final String TAG = "MyExtensionModule";

    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModules = super.getPluginModules(conversationType);
        pluginModules.clear();
        //拍照  图片 语音聊天  视频聊天   邀约
        //pluginModules.add(new MySightPlugin());//拍照  MySightPlugin
        pluginModules.add(new MyImagePlugin());//图片 MyImagePlugin
        pluginModules.add(new AudioPlugin());//语音聊天  MyYinPinPlugin
        pluginModules.add(new VideoPlugin());//视频聊天  MyShiPinPlugin   VideoPlugin

        String chatWhere = SPTool.getSessionValue(AppSP.chatWhere);
        Log.i(TAG, "getPluginModules: 角色的跳转进入 " + chatWhere);  //1是 HR 角色的跳转进入

        if (chatWhere.equals("1")) {
            pluginModules.add(new YaoYuePlugin());//邀约
        }







        /*if (conversationType.equals(Conversation.ConversationType.PRIVATE)) {
            pluginModules.add(new GamePlugin());
        }
        if (conversationType.equals(Conversation.ConversationType.PRIVATE)) {
            pluginModules.add(new SendGiftPlugin());
        }
        pluginModules.add(new MyLocationPlugin());*/


        return pluginModules;
    }

    /*@Override
    public List<IEmoticonTab> getEmoticonTabs() {
        List<IEmoticonTab> emoticonTabs = super.getEmoticonTabs();
        emoticonTabs.add(new MyBoyEmoticon());
        emoticonTabs.add(new MyGirlEmoticon());
        return emoticonTabs;
    }*/


    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
        return super.getEmoticonTabs();
    }
}