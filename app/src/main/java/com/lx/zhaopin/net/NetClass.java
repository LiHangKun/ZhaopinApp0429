package com.lx.zhaopin.net;

public class NetClass {


    //http://www.qiuzhiqiang.com/
    public static final boolean isDeg = true;
    public static final String BASE_URL;
    //public static final String Base_FileCui = "http://39.96.78.51/api/service/member/file/upload";//单张图片
    public static final String Base_FileCui = "http://www.qiuzhiqiang.com/api/service/member/file/upload";//单张图片


    static {
        if (isDeg) {
            //BASE_URL = "http://39.96.78.51/api/service/";//测试环境地址
            BASE_URL = "http://www.qiuzhiqiang.com/api/service/";//测试环境地址
        } else {
            BASE_URL = "正式生产环境地址/";//正式生产环境地址
        }
    }


    public static final String Web_XieYi1 = BASE_URL + "common/protocol1";//用户协议
    public static final String Web_XieYi2 = BASE_URL + "common/privacy";//隐私政策
    public static final String Web_XieYi3 = BASE_URL + "coupon";//优惠券使用说明
    public static final String Web_XieYi4 = BASE_URL + "authentication";//实名认证说明
    public static final String Web_XieYi5 = BASE_URL + "expand";//推广活动规则
    public static final String Web_XieYi6 = BASE_URL + "about";//关于我们
    public static final String Web_XieYi7 = BASE_URL + "item";//服务条款
    public static final String Web_XieYi8 = BASE_URL + "question";//常见问题
    public static final String Web_XieYi9 = BASE_URL + "settle";//结算说明
    public static final String Web_XieYi10 = BASE_URL + "prepay";//预付说明
    public static final String Web_XieYi11 = BASE_URL + "settle";//结算说明
    public static final String Web_XieYi12 = BASE_URL + "expand";//活动规则

    //http://39.96.78.51/share/#/?pid=
    //http://39.96.78.51/share/#/pages/index/cv?rid=


    /*public static final String Share_Gang = "http://39.96.78.51/share/#/?pid=";
    public static final String Share_Ren = "http://39.96.78.51/share/#/pages/index/cv?rid=";
    public static final String Share_AppLogo = "http://39.96.78.51/zhaopinlogo.png";
    public static final String title = "欢迎使用";*/

    public static final String Share_Gang = "http://www.qiuzhiqiang.com/share/#/?pid=";
    public static final String Share_Ren = "http://www.qiuzhiqiang.com/share/#/pages/index/cv?rid=";
    public static final String Share_AppLogo = "http://www.qiuzhiqiang.com/zhaopinlogo.png";
    public static final String title = "欢迎使用";


}
