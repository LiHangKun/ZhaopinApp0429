package com.lx.zhaopin.rong;

import android.content.Context;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.RongExtension;
import io.rong.imkit.emoticon.IEmoticonTab;
import io.rong.imlib.model.Message;

/**
 * Created by kxn on 2020/4/29 0029.
 */
public class MyEmoticonModule extends DefaultExtensionModule {
    public MyEmoticonModule(Context context) {
        super(context);
    }

    @Override
    public void onInit(String appKey) {
        super.onInit(appKey);
    }

    @Override
    public void onDisconnect() {
        super.onDisconnect();
    }

    @Override
    public void onConnect(String token) {
        super.onConnect(token);
    }

    @Override
    public void onAttachedToExtension(RongExtension extension) {
        super.onAttachedToExtension(extension);
    }

    @Override
    public void onDetachedFromExtension() {
        super.onDetachedFromExtension();
    }

    @Override
    public void onReceivedMessage(Message message) {
        super.onReceivedMessage(message);
    }


    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
        List<IEmoticonTab> emoticonTabs = super.getEmoticonTabs();
        //这个是添加的2个底部的表情包
        /*emoticonTabs.add(new MyBoyEmoticon());
        emoticonTabs.add(new MyGirlEmoticon());*/
        return emoticonTabs;
    }
}
