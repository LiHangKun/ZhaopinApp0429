package net.neiquan.okhttp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;

import com.alibaba.fastjson.JSON;

import net.neiquan.okhttp.body.BodyWrapper;
import net.neiquan.okhttp.builder.UploadRequestBuilder;
import net.neiquan.okhttp.listener.DownloadListener;
import net.neiquan.okhttp.listener.UploadListener;

import org.haitao.common.utils.AppLog;
import org.haitao.common.utils.ToastUtil;

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
    private static OkHttpClient mHttpClient ;
    private static OkHttpUtils okHttpUtils ;

    /**
     * 是否已经在主界面了 登录的时候要重置为 false
     */
    public static boolean alreadyInHome;
    private  static Context context;
    public static void init(Context _context){
        context=_context;
    }
    public static void init(Context _context,Class<?> skpCls){
        context=_context;
        cls=skpCls;
    }
    private static Class<?> cls;

    public static void SetSessionOutCls(Class<?> _cls){
        cls=_cls;
    }
    // 上面的代码主要是为了 过期跳转主页

    private static OkHttpUtils init() {
        synchronized (OkHttpUtils.class) {
            if (mHttpClient == null) {
                mHttpClient = new OkHttpClient();
                okHttpUtils =new OkHttpUtils();
            }
        }
        return okHttpUtils;
    }

    public static OkHttpUtils getInstance() {
        return mHttpClient == null ? init() : okHttpUtils;
    }

    /**
     * get 同步
     * @param url
     * @param netCallBack
     * @param type
     */
    public void get(String url,  final NetCallBack netCallBack, final Class type){
        get(url,null,netCallBack,type);
    }

    /**
     * get 同步
     * @param url
     * @param map
     * @param netCallBack
     * @param type
     */
    public void get(String url, Map<String, String> map, final NetCallBack netCallBack, final Class type){
        url= appendGetParams(url,map);
        Request request = new Request.Builder()
                .url(url)
                .build();
        sync(map,request,netCallBack,type);
    }

    /**
     * get异步
     * @param url
     * @param netCallBack
     * @param type
     */
    public void getAnsy(String url, final NetCallBack netCallBack, final Class type){
        getAnsy(url,null,netCallBack,type);
    }
    /**
     * get异步
     * @param url
     * @param map 请求参数
     * @param netCallBack
     * @param type
     */
    public void getAnsy(String url, Map<String, String> map, final NetCallBack netCallBack, final Class type){
        url= appendGetParams(url,map);
        Request request = new Request.Builder()
                .url(url)
                .build();
        async(map,request,netCallBack,type);
    }
    /**
     * post 同步
     * @param url
     * @param netCallBack
     * @param type
     */
    public void post(String url,  final NetCallBack netCallBack, final Class type){
        post(url,null,netCallBack,type);
    }
    /**
     * post 同步
     * @param url
     * @param map 请求参数
     * @param netCallBack
     * @param type
     */
    public void post(String url, Map<String, Object> map, final NetCallBack netCallBack, final Class type){
        RequestBody body = appendPostParams(map);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        sync(map,request,netCallBack,type);
    }
    /**
     * 异步post
     * @param url
     * @param netCallBack
     * @param type
     */
    public void postAnsy(String url, final NetCallBack netCallBack, final Class type){
        postAnsy(url,null,netCallBack,type);
    }
    /**
     * 异步post
     * @param url
     * @param map 请求参数
     * @param netCallBack
     * @param type
     */
    public void postAnsy(String url, Map<String, Object> map, final NetCallBack netCallBack, final Class type){
        RequestBody body = appendPostParams(map);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        async(map,request,netCallBack,type);
    }

    /**
     * 异步post json
     * @param url
     * @param map 请求参数
     * @param netCallBack
     * @param type
     */
    public void postJsonAnsy(String url, Map<String, Object> map, final NetCallBack netCallBack, final Class type){
        postJsonAnsy(url, JSON.toJSONString(map),netCallBack,type);
    }
    /**
     * 异步post json
     * @param url
     * @param json 请求参数
     * @param netCallBack
     * @param type
     */
    public void postJsonAnsy(String url, String json, final NetCallBack netCallBack, final Class type){
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        MediaType JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        async(json,request,netCallBack,type);
    }
    /**
     * 同步
     * @param request
     * @param netCallBack
     * @param type
     */
    private  void  sync(Object requstPram,final Request request, final NetCallBack netCallBack, final Class type){
        AppLog.e("url "+request.url()+" "+requstPram);
        Response response = null;
        try {
            response = mHttpClient.newCall(request).execute();
            // 网络请求成功
            if (response.isSuccessful()) {
                paserResponse (requstPram,response,netCallBack,type);
            }else{
                paserFail(requstPram,request,response.code(),netCallBack);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 异步
     * @param request
     * @param netCallBack
     * @param type
     */
    private  void  async(final Object requstPram,final Request request, final NetCallBack netCallBack, final Class type){
        AppLog.e("url "+request.url()+" "+requstPram);
        mHttpClient.newCall(request).enqueue(new Callback(){

            @Override
            public void onFailure(Request request, IOException e) {
                paserFail(requstPram,request,404,netCallBack);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                paserResponse (requstPram,response,netCallBack,type);
            }
        });
    }
    /**
     * 下载文件 有进度显示
     * @param url
     * @param downloadListener
     * @return
     */
    public  Call download(String url, DownloadListener downloadListener) {
        Request request = new Request.Builder().url(url).build();
        Call call = BodyWrapper.addProgressResponseListener(mHttpClient, downloadListener).newCall(request);
        call.enqueue(downloadListener);
        return  call;
    }

    /**
     * 异步post 上传文件
     * @param url
     * @param file
     * @param map
     * @param uploadListener
     */
    public void postFile(String url, Pair<String, File> file,Map<String, String> map, UploadListener uploadListener){
        new UploadRequestBuilder()
                .url(url)
                .file(file)
                .setParams(map)
                .setWriteTimeOut(20)
                .start(mHttpClient ,uploadListener);
    }
    /**
     * 创建 get 参数
     * @param url
     * @param params
     * @return
     */
    protected String appendGetParams(String url, Map<String, String> params) {
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
     * @param map
     * @return
     */
    private RequestBody appendPostParams(Map<String, Object> map){

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
    private void  paserFail(Object requstPram,Request request,final int code, final  NetCallBack netCallBack){
        AppLog.e("fail url "+request.url()+" code "+code+" " +requstPram);
        if (netCallBack!=null){
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // onFailure(e);
                    if ( netCallBack!=null){
                        netCallBack.onFail(true,code,"");
                    }
                }
            });

        }

    }

    /**
     * 处理成功结果
     * @param response
     * @param netCallBack
     */
    private void  paserResponse (Object requstPram,final Response response, final NetCallBack netCallBack, final Class type){
        String res="";
        try {
            res =response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    netCallBack.onFail(true,0,"服务器没有数据...");
                }
            });
        }
        AppLog.jsonAppend("========= url ========"+response.request().url().toString()+"\nrquestbody "+requstPram+"\n" +
                "code:"+response.code()+"\nresult:",res);
        if (response.isSuccessful()){
            if (netCallBack!=null){
                final Result result  = JSON.parseObject(res,  Result.class);
                if(result.getCode()==0){
                    //服务器逻辑成功
                    //判断 是否有对象
                    final ResultModel body = new ResultModel();
                    if(null!=type){
                        // 判断是jsonObject 还是 jsonArray
                        if (result.getResponse()!=null){
                            if (isJsonArray(result.getResponse())){
                                List<?> ls = JSON.parseArray(result.getResponse(), type);
                                body.setModelList(ls);
                            }else{
                                body.setModel(JSON.parseObject(result.getResponse(), type));
                            }
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    netCallBack.onSuccess(result.getResponse(),result.getErrorMessage(),body);
                                }
                            });
                        }else{
                            // 服务器没有返回对象
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    netCallBack.onFail(true,0,"服务器没有数据...");
                                }
                            });

                        }
                    }else{
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                AppLog.e("=============返回体==============="+result.getResponse());
                                netCallBack.onSuccess(result.getResponse(),result.getErrorMessage(),null);
                            }
                        });
                    }
                }else{
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            netCallBack.onFail(false,result.getCode(),result.getErrorMessage());
                            //
                        }
                    });
                    if (result.getCode()==50||result.getCode()==51){
                        // 登录
                        if (!alreadyInHome && context!=null && cls!=null) {
                            alreadyInHome=true; // 防止多次跳转
                            Intent intent = new Intent(context,cls);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("sessionOut", true);
                            context.startActivity(intent);
                            ToastUtil.shortShowToast(result.getErrorMessage());
                        }
                    }
                }

            }
        }else{
            if (netCallBack!=null){
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onFail(false,response.code(),"服务器异常...");
                    }
                });
            }
        }
    }
    /**
     * 判断是否是json数组
     * @param str
     * @return
     */
    public boolean isJsonArray(String str){
        final char[] strChar = str.substring(0, 1).toCharArray();
        final char firstChar = strChar[0];
        return firstChar == '[' ;
    }
}
