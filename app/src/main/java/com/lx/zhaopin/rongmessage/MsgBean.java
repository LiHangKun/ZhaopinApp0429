package com.lx.zhaopin.rongmessage;

/**
 * Created by kxn on 2019/12/26 0026.
 */
public class MsgBean {
    public String gameName;
    public String constellation;//是否同一星座 0否 1是
    public String game;//是否同一游戏 0否 1是
    public String questions;//相同测试题(没有返空)
    public String matchingnum;//匹配度
    public String usericon;//对方头像
    public String selficon;//发送方头像
    public String selfid;//发送方id
    public String userid;//对方id
    public String gametype;//同一游戏类型(同一游戏才会有)
    public String level;//是否相同段位 0否 1是(同一游戏才会有)
    public String city;//是否同一城市 0否 1是
    public String type;//  1 代表游戏 2代表礼物
    public String info;// type为游戏时 1 同意 2 拒绝  type 为礼物时 表示礼物名称
    public int unreadMessageCount;//未读消息
    public String myconstellation;//我的星座
    public String taconstellation;//对方星座
    public String gametype1;//同一游戏1
    public String gametype2;//同一游戏2
    public String gametype3;//同是大神游戏
    public String gametype4;//可以学习游戏1
    public String gametype5;//可以学习游戏2
}
