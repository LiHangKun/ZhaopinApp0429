package com.lx.zhaopin.net;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import net.neiquan.okhttp.NetCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * Author by wangHaitao(a758277560@gmail.com)
 * Created  on 2015/12/picture1.
 * Version picture1.0
 */
public class NetUtils {

    /**
     * pageSize
     */
    public static int PAGE_SIZE = 10;

    private static NetUtils single = null;

    // 静态工厂方法
    public static NetUtils getInstance() {
        if (single == null) {
            single = new NetUtils();
        }
        return single;
    }

    public void norPost(String url, Object object, final NetCallBack callback, final Class classType) {
        OkHttpUtils.getInstance().postJsonAnsy(url, JSON.toJSONString(object), callback, classType);
    }

    public void norPostPinjie(String url, Object object, String pinjie, final NetCallBack callback, final Class classType) {
        OkHttpUtils.getInstance().postJsonAnsyPinjie(url, JSON.toJSONString(object),pinjie, callback, classType);
    }



    public void norGet(String url, final NetCallBack callback, final Class classType) {
        OkHttpUtils.getInstance().getAnsy(url,callback,classType);
    }

    public void norGet(String url, Map<String, Object> map, final NetCallBack callback, final Class classType) {
        OkHttpUtils.getInstance().getAnsy(url,map,callback,classType);
       Log.e("传递的url===",url);

    }

    public void norGetNoToken(String url, Map<String, Object> map, final NetCallBack callback, final Class classType) {
        OkHttpUtils.getInstance().getAnsyNoToken(url,map,callback,classType);
        Log.e("传递的url===",url);

    }


    public void norGetPinjie(String url, Map<String, Object> map, String pinjieUrl, final NetCallBack callback, final Class classType) {
        OkHttpUtils.getInstance().getAnsyPinjie(url,map,pinjieUrl,callback,classType);
        Log.e("传递的url===",url);

    }



    public void norDelete(String url, Map<String, Object> map, String param, final NetCallBack callback, final Class classType) {
        OkHttpUtils.getInstance().delAnsy(url,map,param,callback,classType);
        Log.e("传递的url===",url);

    }

    public void norGet(String url, Map<String, Object> map, String param, final NetCallBack callback, final Class classType) {
        OkHttpUtils.getInstance().getAnsy(url,map,param,callback,classType);
        Log.e("传递的url===",url);

    }

    public void norPost(String url, Map<String, Object> map, final NetCallBack callback, final Class classType) {
        OkHttpUtils.getInstance().postJsonAnsy(url, map, callback, classType);
    }


    public void norPostLogin(String url, Map<String, Object> map, final NetCallBack callback, final Class classType) {
        OkHttpUtils.getInstance().postJsonAnsyLogin(url, map, callback, classType);
    }


    public void norPostPinjie(String url, Map<String, Object> map, final NetCallBack callback, final Class classType) {
        OkHttpUtils.getInstance().postJsonAnsy(url, map, callback, classType);
    }

    public void norPut(String url, Map<String, Object> map, final NetCallBack callback, final Class classType) {
        OkHttpUtils.getInstance().putJsonAnsy(url, map, callback, classType);
    }

    public void norPostUser(String url, Map<String, Object> map, final NetCallBack callback, final Class classType) {
//        if (map == null) {
//            map = new HashMap<String, Object>();
//        }
//        if (MyApplication.getInstance().isLogin()) {
//            User user=(User) SpUtil.getObject(MyApplication.getContext(),"userentity");
//            map.put("token", user.getToken()); // 拼接userid
//        }
        //map.put("deviceType", 0); // 拼接userid
        OkHttpUtils.getInstance().postJsonAnsy(url, map, callback, classType);
        //OkHttpUtils.getInstance().SetSessionOutCls(WelcomeActivity.class);
    }


    /**
     * 登录 通过维修编号登录
     * employeeNo	integer($int32)       员工编号
     */
    public void loginByNo(int employeeNo,final NetCallBack callback, final Class classType){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employeeNo",employeeNo);
        norPostLogin(APPURL.LOGINBYCODE,params,callback, classType);
    }


    /**
     *设备安装列表
     * pageCurrent     integer        当前页           Default value : 1
     * pageSize        integer        页面大小         Default value : 10
     */
    public void getMechineAnzhuangList(int pageCurrent,int pageSize,final NetCallBack callback, final Class classType){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pageCurrent",pageCurrent);
        params.put("pageSize",pageSize);
        norGetNoToken(APPURL.MECHINEANZHUANGLIST,params,callback, classType);
    }




    //    /**
//     * 删除文件
//     *id   integer($int64)  文件ID
//     */
//    public void deleteFile(int id, final NetCallBack callback, final Class classType){
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("id",id);
//        String param="/"+id;
//        norGet(APPURL.DELETEFILE,params,param,callback, classType);
//    }


    /**
     * 获取终端信息
     * id = xxx //qrcodestr
     */
//    public void saveKefu(String content,long fromId,long orderId,String chatType,final NetCallBack callback, final Class classType){
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("content",content);
//        params.put("fromId",fromId);
//        params.put("orderId",orderId);
//        params.put("chatType",chatType);
//        norPost(APPURL.SAVEKEFU,params,callback, classType);
//    }



//    /**
//     * 关注或取消关注
//     *
//     */
//    public void fensiChange(int targetUserid ,final NetCallBack callback, final Class classType){
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("targetUserid",targetUserid);
//        String param="?"+"targetUserid="+targetUserid;
//        norPostPinjie(APPURL.GUANZHUCHANGE,params,param,callback, classType);
//    }

//    /**
//     * 删除我的话题
//     *
//     */
//    public void topicDel(int topicId, final NetCallBack callback, final Class classType){
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("topicId",topicId);
//        String param="/"+topicId;
//        norDelete(APPURL.TOPICDEL,params,param,callback, classType);
//    }

}
