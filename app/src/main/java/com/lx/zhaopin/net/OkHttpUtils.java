package com.lx.zhaopin.net;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.alibaba.fastjson.JSON;
import com.lx.zhaopin.bean.User;
import com.lx.zhaopin.common.MainActivity;
import com.lx.zhaopin.common.MyApplication;
import com.lx.zhaopin.utils.Send;
import com.lx.zhaopin.utils.SpUtil;

import net.neiquan.okhttp.NetCallBack;
import net.neiquan.okhttp.Result;
import net.neiquan.okhttp.ResultModel;
import net.neiquan.okhttp.body.BodyWrapper;
import net.neiquan.okhttp.builder.UploadRequestBuilder;
import net.neiquan.okhttp.listener.DownloadListener;
import net.neiquan.okhttp.listener.UploadListener;

import org.haitao.common.utils.AppLog;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Description
 * Author by wangHaitao(a758277560@gmail.com)
 * Created  on 2016/5/31
 * Version 1.0
 */
public class OkHttpUtils {

    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private static OkHttpClient mHttpClient;
    private static OkHttpUtils okHttpUtils;

    /**
     * 是否已经在主界面了 登录的时候要重置为 false
     */
    public static boolean alreadyInHome=false;
    private static Context context;

    public static void init(Context _context) {
        context = _context;
    }

    public static void init(Context _context, Class<?> skpCls) {
        context = _context;
        cls = skpCls;
    }

    private static Class<?> cls;

    public static void SetSessionOutCls(Class<?> _cls) {
        cls = _cls;
    }
    // 上面的代码主要是为了 过期跳转主页

    private static OkHttpUtils init() {
        synchronized (OkHttpUtils.class) {
            if (mHttpClient == null) {
                mHttpClient = new OkHttpClient();
                okHttpUtils = new OkHttpUtils();
            }
        }
        return okHttpUtils;
    }

    public static OkHttpUtils getInstance() {
        return mHttpClient == null ? init() : okHttpUtils;
    }

    /**
     * get 同步
     *
     * @param url
     * @param netCallBack
     * @param type
     */
    public void get(String url, final NetCallBack netCallBack, final Class type) {
        get(url, null, netCallBack, type);
    }

    /**
     * get 同步
     *
     * @param url
     * @param map
     * @param netCallBack
     * @param type
     */
    public void get(String url, Map<String, Object> map, final NetCallBack netCallBack, final Class type) {
        url = appendGetParams(url, map);
        Request request = new Request.Builder()
                .url(url)
                .build();
        sync(map, request, netCallBack, type);
    }

    /**
     * get异步
     *
     * @param url
     * @param netCallBack
     * @param type
     */
    public void getAnsy(String url, final NetCallBack netCallBack, final Class type) {
        getAnsy(url, null, netCallBack, type);
    }

    /**
     * get异步
     *
     * @param url
     * @param map         请求参数
     * @param netCallBack
     * @param type
     */
    public void getAnsy(String url, Map<String, Object> map, final NetCallBack netCallBack, final Class type) {
        String token="";
        User user = (User) SpUtil.getObject(context,"userentity");
        if(null!=user){
            token=user.getToken();
        }else {
            token=Send.DEFAULT_TOKEN;
        }
        url = appendGetParams(url, map);
        Request request = new Request.Builder()
                .header("X-Token",token)
                .url(url)
                .build();
        async(map, request, netCallBack, type);
    }


    /**
     * get异步
     *
     * @param url
     * @param map         请求参数
     * @param netCallBack
     * @param type
     */
    public void getAnsyNoToken(String url, Map<String, Object> map, final NetCallBack netCallBack, final Class type) {
        url = appendGetParams(url, map);
        Request request = new Request.Builder()
                .url(url)
                .build();
        async(map, request, netCallBack, type);
    }

    /**
     * get异步
     *
     * @param url
     * @param map         请求参数
     * @param netCallBack
     * @param type
     */
    public void getAnsyPinjie(String url, Map<String, Object> map, String urlPinjie, final NetCallBack netCallBack, final Class type) {
        url = appendGetParams(url, map);
        Request request = new Request.Builder()
                .url(url+urlPinjie)
                .build();

        async(map, request, netCallBack, type);
    }


    /**
     * delete异步
     *
     * @param url
     * @param map         请求参数
     * @param netCallBack
     * @param type
     */
    public void delAnsy(String url, Map<String, Object> map, String param, final NetCallBack netCallBack, final Class type) {
        User user = (User)SpUtil.getObject(context,"userentity");
        String token="";
        if(null!=user){
            token=user.getToken();
        }else {
            token=Send.DEFAULT_TOKEN;
        }
        Request request = new Request.Builder()
                .header("X-Token",token)
                .url(url+param)
                .delete()
                .build();
        async(map, request, netCallBack, type);
    }

    /**
     * get异步
     *
     * @param url
     * @param map         请求参数
     * @param netCallBack
     * @param type
     */
    public void getAnsy(String url, Map<String, Object> map, String param, final NetCallBack netCallBack, final Class type) {
        User user = (User) SpUtil.getObject(context,"userentity");
        String token="";
        if(null!=user){
            token=user.getToken();
        }else {
            token= Send.DEFAULT_TOKEN;
        }
        Request request = new Request.Builder()
                .header("X-Token",token)
                .url(url+param)
                .get()
                .build();
        async(map, request, netCallBack, type);
    }


    /**
     * post 同步
     *
     * @param url
     * @param netCallBack
     * @param type
     */
    public void post(String url, final NetCallBack netCallBack, final Class type) {
        post(url, null, netCallBack, type);
    }

    /**
     * post 同步
     *
     * @param url
     * @param map         请求参数
     * @param netCallBack
     * @param type
     */
    public void post(String url, Map<String, Object> map, final NetCallBack netCallBack, final Class type) {
        RequestBody body = appendPostParams(map);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        sync(map, request, netCallBack, type);
    }

    /**
     * 异步post
     *
     * @param url
     * @param netCallBack
     * @param type
     */
    public void postAnsy(String url, final NetCallBack netCallBack, final Class type) {
        postAnsy(url, null, netCallBack, type);
    }

    /**
     * 异步post
     *
     * @param url
     * @param map         请求参数
     * @param netCallBack
     * @param type
     */
    public void postAnsy(String url, Map<String, Object> map, final NetCallBack netCallBack, final Class type) {
        User user = (User)SpUtil.getObject(context,"userentity");
        String token="";
        if(null!=user){
            token=user.getToken();
        }else {
            token=Send.DEFAULT_TOKEN;
        }
        RequestBody body = appendPostParams(map);
        Request request = new Request.Builder()
                .header("X-Token",token)
                .url(url)
                .post(body)
                .build();
        async(map, request, netCallBack, type);
    }

    /**
     * 异步post json
     *
     * @param url
     * @param map         请求参数
     * @param netCallBack
     * @param type
     */
    public void postJsonAnsy(String url, Map<String, Object> map, final NetCallBack netCallBack, final Class type) {
        postJsonAnsy(url, JSON.toJSONString(map), netCallBack, type);
    }

    /**
     * 异步post json
     *
     * @param url
     * @param map         请求参数
     * @param netCallBack
     * @param type
     */
    public void postJsonAnsyLogin(String url, Map<String, Object> map, final NetCallBack netCallBack, final Class type) {
        postJsonAnsyLogin(url, JSON.toJSONString(map), netCallBack, type);
    }

    /**
     * 异步post json
     *
     * @param url
     * @param map         请求参数
     * @param netCallBack
     * @param type
     */
    public void postJsonAnsyPinjie(String url, Map<String, Object> map, String param, final NetCallBack netCallBack, final Class type) {
        postJsonAnsyPinjie(url, JSON.toJSONString(map),param, netCallBack, type);
    }

    /**
     * 异步post json
     *
     * @param url
     * @param json        请求参数
     * @param netCallBack
     * @param type
     */
    public void postJsonAnsyPinjie(String url, String json, String param, final NetCallBack netCallBack, final Class type) {
        User user = (User)SpUtil.getObject(context,"userentity");
        String token="";
        if(null!=user){
            token=user.getToken();
        }else {
            token=Send.DEFAULT_TOKEN;
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        MediaType JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .header("X-Token",token)
                .url(url+param)
                .post(body)
                .build();
        async(json, request, netCallBack, type);
    }

    /**
     * 异步post json
     *
     * @param url
     * @param json        请求参数
     * @param netCallBack
     * @param type
     */
    public void postJsonAnsy(String url, String json, final NetCallBack netCallBack, final Class type) {
        User user = (User)SpUtil.getObject(context,"userentity");
        String token="";
        if(null!=user){
            token=user.getToken();
        }else {
            token=Send.DEFAULT_TOKEN;
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        MediaType JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .header("X-Token",token)
                .url(url)
                .post(body)
                .build();
        async(json, request, netCallBack, type);
    }

    /**
     * 异步post json
     *
     * @param url
     * @param json        请求参数
     * @param netCallBack
     * @param type
     */
    public void postJsonAnsyLogin(String url, String json, final NetCallBack netCallBack, final Class type) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        MediaType JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        async(json, request, netCallBack, type);
    }

    /**
     * 异步post json
     *
     * @param url
     * @param map         请求参数
     * @param netCallBack
     * @param type
     */
    public void putJsonAnsy(String url, Map<String, Object> map, final NetCallBack netCallBack, final Class type) {
        putJsonAnsy(url, JSON.toJSONString(map), netCallBack, type);
    }

    /**
     * 异步post json
     *
     * @param url
     * @param json        请求参数
     * @param netCallBack
     * @param type
     */
    public void putJsonAnsy(String url, String json, final NetCallBack netCallBack, final Class type) {
        User user = (User)SpUtil.getObject(context,"userentity");
        String token="";
        if(null!=user){
            token=user.getToken();
        }else {
            token= Send.DEFAULT_TOKEN;
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        MediaType JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .header("X-Token",token)
                .url(url)
                .put(body)
                .build();
        async(json, request, netCallBack, type);
    }


    /**
     * 同步
     *
     * @param request
     * @param netCallBack
     * @param type
     */
    private void sync(Object requstPram, final Request request, final NetCallBack netCallBack, final Class type) {
        AppLog.e("url " + request.url() + " " + requstPram);
        Response response = null;
        try {
            response = mHttpClient.newCall(request).execute();
            // 网络请求成功
            if (response.isSuccessful()) {
                paserResponse(requstPram, response, netCallBack, type);
            } else {
                paserFail(requstPram, request, response.code(), netCallBack);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步
     *
     * @param request
     * @param netCallBack
     * @param type
     */
    private void async(final Object requstPram, final Request request, final NetCallBack netCallBack, final Class type) {
        AppLog.e("=======加密后url " + request.url() + " " + requstPram);
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                paserFail(requstPram, request, 404, netCallBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                paserResponse(requstPram, response, netCallBack, type);
            }
        });
    }

    /**
     * 下载文件 有进度显示
     *
     * @param url
     * @param downloadListener
     * @return
     */
    public Call download(String url, DownloadListener downloadListener) {
        Request request = new Request.Builder().url(url).build();
        Call call = BodyWrapper.addProgressResponseListener(mHttpClient, downloadListener).newCall(request);
        call.enqueue(downloadListener);
        return call;
    }

    /**
     * 异步post 上传文件
     *
     * @param url
     * @param file
     * @param map
     * @param uploadListener
     */
    public void postFile(String url, Pair<String, File> file, Map<String, String> map, UploadListener uploadListener) {
        new UploadRequestBuilder()
//                .addHeader("Content-Type","multipart/form-data; boundary=--------------------------461964038612349795025459")
//                .addHeader("cache-control","no-cache")
//                .addHeader("accept","*/*")
//                .addHeader("connection","keep-alive")
//                .addHeader("accept-encoding","gzip, deflate, br")
                .url(url)
                .file(file)
                .setParams(map)
                .setWriteTimeOut(20)
                .start(mHttpClient, uploadListener);
    }

    /**
     * 创建 get 参数
     *
     * @param url
     * @param params
     * @return
     */
    protected String appendGetParams(String url, Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }
        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 创建 post参数
     *
     * @param map
     * @return
     */
    private RequestBody appendPostParams(Map<String, Object> map) {

        // 创建请求的参数body
        FormBody.Builder builder = new FormBody.Builder();
        /**
         * 遍历key
         */
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                builder.add(entry.getKey(), entry.getValue().toString());
            }
        }
        RequestBody body = builder.build();
        return body;
    }


    /**
     * @param requstPram
     * @param request
     * @param code
     * @param netCallBack
     */
    private void paserFail(Object requstPram, Request request, final int code, final NetCallBack netCallBack) {
        AppLog.e("fail url " + request.url() + " code " + code + " " + requstPram);
        if (netCallBack != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // onFailure(e);
                    if (netCallBack != null) {
                        netCallBack.onFail(true, code, "服务器或者网络异常...");
                    }
                }
            });

        }

    }

    /**
     * 处理成功结果
     *
     * @param response
     * @param netCallBack
     */
    String res = "";
    private void paserResponse(Object requstPram, final Response response, final NetCallBack netCallBack, final Class type) {

        try {
            res = response.body().string();
            Log.e("====result======",res);
            AppLog.jsonAppend("========= url解密前 ========" + response.request().url().toString()
                    + "\nrquestbody " + requstPram + "\n" +
                    "code:" + response.code() + "\nresult:", res);
        } catch (IOException e) {
            e.printStackTrace();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    netCallBack.onFail(true, 0, "服务器没有数据...");
                }
            });
        }
        if (TextUtils.isEmpty(res)) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    netCallBack.onFail(true, 0, "服务器没有数据...");
                }
            });
            return;
        }
        if (!isJsonObj(res)) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    netCallBack.onFail(true, 0, res);
                }
            });
            return;
        }
        //解密
//        res = Send.decode(res);
//        AppLog.jsonAppend("========= url解密后 ========" + response.request().url().toString()
//                + "\nrquestbody " + requstPram + "\n" +
//                "code:" + response.code() + "\nresult:", res);
        if (response.isSuccessful()) {
            if (netCallBack != null) {
                final Result result = JSON.parseObject(res, Result.class);
//               Log.e("---result------",result.getData()+"");
                if (result.getCode() == 200) {
                    //服务器逻辑成功
                    //判断 是否有对象
                    final ResultModel body = new ResultModel();
                    if (null != type) {
                        // 判断是jsonObject 还是 jsonArray
                        if (result.getData()!= null) {
                            if (isJsonArray(result.getData())) {
                                List<?> ls = JSON.parseArray(result.getData(), type);
                                body.setModelList(ls);
//                                Log.e("---resultnew------",result.getData()+"");
                            } else {
                                body.setModel(JSON.parseObject(result.getData(), type));
                            }
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    netCallBack.onSuccess(result.getData(), result.getMsg(), body);
                                }
                            });
                        } else {
                            // 服务器没有返回对象
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if(!TextUtils.isEmpty(result.getMsg())){
                                        netCallBack.onFail(true, 0, result.getMsg());
                                    }else {
                                        netCallBack.onFail(true, 0, res);
                                    }

                                }
                            });

                        }
                    } else {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(!TextUtils.isEmpty(result.getMsg())){
                                    netCallBack.onSuccess(result.getData(), result.getMsg(), null);
                                }else {
                                    netCallBack.onSuccess(result.getData(), res, null);
                                }
//                                AppLog.e("=============返回体===============" + result.getResponse());

                            }
                        });
                    }
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(!TextUtils.isEmpty(result.getMsg())){
                                netCallBack.onFail(false, result.getCode(), result.getMsg());
                            }else {
                                netCallBack.onFail(false, result.getCode(), res);
                            }

                            //
                        }
                    });



//                    AppLog.e("==========111======="+result.getCode());
                    if (result.getCode() == 333) {
                        // 登录
//                        AppLog.e("==========222======="+result.getCode());
                        if (!alreadyInHome ) {
//                            AppLog.e("==========333======="+result.getCode());
                            alreadyInHome = true; // 防止多次跳转
                            Intent intent = new Intent(MyApplication.context, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Bundle extras = new Bundle();
                            extras.putBoolean("sessionOut", true);
                            intent.putExtras(extras);
                            MyApplication.context.startActivity(intent);
                        }
                    }
                }

            }
        } else
        {
            if (netCallBack != null) {
                final Result result = JSON.parseObject(res, Result.class);
                if(result.getCode() == 400){
//                    User user=(User) SpUtil.getObject(MyApplication.context,"userentity");
//                    user.setToken("");
//                    SpUtil.putObject(MyApplication.context,"userentity",user);
//                    Intent mIntent=new Intent(MyApplication.context,XLoginActivity.class);
//                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    MyApplication.context.startActivity(mIntent);
//                    MyApplication.getInstance().finishAllActivity();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        final String result =res;
                        try {
                            JSONObject jsonObject = new JSONObject(res);
                            String name=jsonObject.getString("msg");
                            if(!TextUtils.isEmpty(name)){
                                netCallBack.onFail(false, response.code(), name);
                            }else {
                                netCallBack.onFail(false, response.code(), res);
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

//                        netCallBack.onFail(false, response.code(), "向服务器请求失败...");
                    }
                });
            }
        }

    }

    /**
     * 判断是否是json数组
     *
     * @param str
     * @return
     */
    public boolean isJsonArray(String str) {
        final char[] strChar = str.substring(0, 1).toCharArray();
        final char firstChar = strChar[0];
        return firstChar == '[';
    }

    public boolean isJsonObj(String str) {
        final char[] strChar = str.substring(0, 1).toCharArray();
        final char firstChar = strChar[0];
        return firstChar == '{';
    }
}
