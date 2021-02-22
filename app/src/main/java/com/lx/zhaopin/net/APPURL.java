package com.lx.zhaopin.net;

/**
 * Created by cxh on 2016/7/16.
 */
public interface APPURL {
//     String BASE_URL = "http://192.168.0.106:8095";
//    String BASE_URL = "http://114.115.213.123:8095";
            String BASE_URL = "http://49.4.6.203:8094";
//     String BASE_URL = "http://30g24c0931.wicp.vip";

    /**
     *
     *登录 通过维修编号登录
     */
    String LOGINBYCODE= APPURL.BASE_URL + "/auth/user/repairlogin";


    /*
     * 设备安装列表
     */
    String MECHINEANZHUANGLIST= APPURL.BASE_URL + "/macDetailInfo/list";


}
