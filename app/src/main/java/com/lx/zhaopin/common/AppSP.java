package com.lx.zhaopin.common;

import android.util.Log;

import java.util.Random;

public class AppSP {

    public static final String USER_TYPE = "USER_TYPE";//0 是求职 1是HR

    //    params.put("pageCount", YunDongSP.pageCount);
    public static final String DaKaMi = "300";//打卡距离
    public static final String pageCount = "10";
    public static final String ManJine = "1";
    public static boolean isToHome = false;//个人中心


    public static boolean isToShopCar = false;//返回首页到购物车  //index = 2

    public static final String Tan_ShouYe = "TAN_SHOUYE";
    public static final String Tan_Map = "TAN_MAP";
    private static final String TAG = "YunDongSP";
    public static final String sCity = "scity";
    public static final String sCityAll3 = "sCityAll3";//省市区
    public static final String sCityAll = "sCityAll";
    public static final String sStringW = "sstringw";
    public static final String sStringJ = "sstringj";
    public static final String JupshID = "jupshid";
    public static final String isLogin = "ISLOGIN";//用户是否登录
    public static final String UID = "uid";//用户UID
    public static final String USER_NAME = "U_NAME";//用户名称
    public static final String USER_ICON = "U_ICON";//用户头像
    public static final String USER_RongToken = "USER_RongToken";//融云Token
    public static final String USER_PHONE = "user_phone";//用户电话号码
    public static final String isFirstIndex = "ISFIRSTINDEX";  //是否是第一次进入
    public static final String xieyi = "xieyi";//启动的协议


    public static final int PMS_LOCATION = 1003;// 权限编号，自定义----定位  打电话
    public static final int PMS_CALL_PHONE = 1004;// 权限编号，打电话
    public static final int PMS_CAMERA = 1005;// 权限编号，自定义----相机
    public static final String SEARCH = "SEARCH";//用户搜索结果
    public static final String SEARCH_USER_NAME = "SEARCH_USER_NAME";//HR搜索名字
    public static final String WORK_JINENG = "WORK_JINENG";//用户的专业技能


    //----------关于支付部分----------
    public final static String Beecloud_ID = "cfeb7a37-1050-4945-8d41-0ee20e5149d9";
    public final static String Beecloud_Secret = "263a1a17-dd31-4700-b882-8c6ae7218468";
    //https://open.weixin.qq.com/cgi-bin/appdetail?t=manage/detail&type=app&lang=zh_CN&token=7be8a6e48f5103fbe30297c10e7703c3fadb70d1&appid=wx652cfd72764fec7e
    public static final String WX_APP_ID = "wx652cfd72764fec7e";
    public static final int CODE_REFRESH = 168;
    //----------关于支付部分----------


    public static String getNum() {
        StringBuilder sb = new StringBuilder();
        //随机生成6位数  发送到聚合
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int a = random.nextInt(10);
            sb.append(a);
        }
        Log.i(TAG, "getNum: 中航人商城 随机数" + sb.toString());
        return sb.toString();
    }


    /**
     * 获取随机验证码
     */
    public static String getNum6() {
        StringBuilder sb = new StringBuilder();
        //随机生成6位数  发送到聚合
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int a = random.nextInt(10);
            sb.append(a);
        }
        Log.i(TAG, "getNum: 中航人商城 随机数" + sb.toString());
        return sb.toString();
    }
}
