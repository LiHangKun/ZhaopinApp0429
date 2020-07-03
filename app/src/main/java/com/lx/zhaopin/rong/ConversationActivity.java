package com.lx.zhaopin.rong;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.GangWeiDetailActivity;
import com.lx.zhaopin.activity.QiYeInfoActivity;
import com.lx.zhaopin.activity.RenCaiDetailActivity;
import com.lx.zhaopin.activity.YuLanJianLiActivity;
import com.lx.zhaopin.bean.AllRongInfoBean;
import com.lx.zhaopin.bean.DianIconBean;
import com.lx.zhaopin.bean.GnagWeiBean;
import com.lx.zhaopin.bean.ZhiWeiDetailBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import okhttp3.Request;
import okhttp3.Response;

public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {

    private String param;
    private static final String TAG = "ConversationActivity";
    private String userId;
    private LinearLayout hrView;
    private TextView tv1;
    private TextView tv2;
    private TextView titleName;

    Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
    //String targetId = "接收方 ID";

    Message.SentStatus sentStatus = Message.SentStatus.SENT;
    String content = "这是一条插入数据";
    long sentTime = System.currentTimeMillis();
    private TextView tv1Gang;
    private TextView tv2Gang;
    private LinearLayout llViewGangWei;

    private String SP_pid = "";
    private String SP_hrid;
    private String fanSP_pid;
    private String where_userType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hua_activity);
        init();
    }

    private void init() {

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(this);
        initPhotoError();
        titleName = findViewById(R.id.titleName);

        hrView = findViewById(R.id.hrView);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv1Gang = findViewById(R.id.tv1Gang);
        tv2Gang = findViewById(R.id.tv2Gang);
        llViewGangWei = findViewById(R.id.llViewGangWei);

        SP_pid = SPTool.getSessionValue(AppSP.pid);
        SP_hrid = SPTool.getSessionValue(AppSP.hrid);

        //where_userType  0 是从求职者来的   1 是从HR来的
        where_userType = SPTool.getSessionValue(AppSP.USER_TYPE);

        llViewGangWei.setOnClickListener(this);
        Uri uri = getIntent().getData();
        if (uri != null) {
            param = uri.getQueryParameter("title");
            if (null != param) {
            }
            userId = uri.getQueryParameter("targetId");

            SPTool.addSessionMap("rid", userId);
            Log.i(TAG, "initView: " + param + "-----" + userId);
        }

        getTitleName(titleName, userId);


        /*RongIM.getInstance().insertOutgoingMessage(conversationType, userId, sentStatus, content, sentTime, new RongIMClient.ResultCallback<Message>() {

         *//**
         * 成功回调
         * @param message 插入的消息
         *//*
            @Override
            public void onSuccess(Message message) {

            }

            *//**
         * 失败回调
         * @param errorCode 错误码
         *//*
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });*/


        RongIM.setConversationClickListener(new RongIM.ConversationClickListener() {
            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo, String s) {
                //ToastFactory.getToast(ConversationActivity.this, userInfo.getUserId()).show();
                /*Intent intent = new Intent(ConversationActivity.this, RenCaiDetailActivity.class);
                intent.putExtra("rid", userInfo.getUserId());
                startActivity(intent);*/


                if (!userInfo.getUserId().equals(SPTool.getSessionValue(AppSP.UID))) {
                    dianIcon(userInfo.getUserId());
                }


                return true;
            }

            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo, String s) {
                return false;
            }

            @Override
            public boolean onMessageClick(Context context, View view, Message message) {
                return false;
            }

            @Override
            public boolean onMessageLinkClick(Context context, String s, Message message) {
                return false;
            }

            @Override
            public boolean onMessageLongClick(Context context, View view, Message message) {
                return false;
            }
        });


       /* if (TextUtils.isEmpty(SP_pid)) {
            llViewGangWei.setVisibility(View.GONE);
        } else {
            updatePositionMe(SP_pid);
        }*/

        //where_userType  0 是从求职者来的   1 是从HR来的
        if (!where_userType.equals("1")) {
            updatePositionMe(SP_pid);
        } else {
            llViewGangWei.setVisibility(View.GONE);
        }


    }

    private void updatePositionMe(String pid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("userId", userId);
        //params.put("userId", SP_hrid);
        //params.put("pid", pid);
        OkHttpHelper.getInstance().post(this, NetClass.BASE_URL + NetCuiMethod.updatePosition, params, new BaseCallback<GnagWeiBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, GnagWeiBean resultBean) {
                //"updated":"1" ,//是否已更新最新岗位，1.是 0否
                String updated = resultBean.getUpdated();
                fanSP_pid = resultBean.getPid();
                switch (updated) {
                    case "1":
                        gangWeiInfo(resultBean.getPid());
                        break;
                    case "0":
                        //gangWeiInfo(SP_pid);
                        gangWeiInfo(resultBean.getPid());
                        break;
                }


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    //获取岗位信息
    private void gangWeiInfo(final String pid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(ConversationActivity.this, NetClass.BASE_URL + NetCuiMethod.zhiWeiDetail, params, new BaseCallback<ZhiWeiDetailBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, ZhiWeiDetailBean resultBean) {
                Log.e(TAG, "onSuccess: 获取的职位详情 http---" + resultBean.getName() + "----" + pid);
                tv1Gang.setText(resultBean.getName());
                tv2Gang.setText(resultBean.getMinSalary() + "-" + resultBean.getMaxSalary() + "K");

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    //dianIcon
    private void dianIcon(final String userId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("userId", userId);
        OkHttpHelper.getInstance().post(ConversationActivity.this, NetClass.BASE_URL + NetCuiMethod.dianIcon, params, new BaseCallback<DianIconBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, DianIconBean resultBean) {
                //where_userType  0 是从求职者来的   1 是从HR来的
                Log.e(TAG, "onSuccess: 点击头像  http------" + where_userType + "---" + resultBean.getHr() + "---" + resultBean.getCid());
                switch (where_userType) {
                    case "0":
                        if (resultBean.getHr().equals("1")) {
                            Intent intent = new Intent(ConversationActivity.this, QiYeInfoActivity.class);
                            intent.putExtra("qiYeID", resultBean.getCid());
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(ConversationActivity.this, YuLanJianLiActivity.class);
                            //intent.putExtra("rid", userId);
                            startActivity(intent);
                        }
                        break;
                    case "1":

                        Log.i(TAG, "onSuccess: 点头像" + userId.substring(2, userId.length()));
                        if (!userId.substring(2, userId.length()).equals(SPTool.getSessionValue(AppSP.UID))) {
                            Intent intent = new Intent(ConversationActivity.this, RenCaiDetailActivity.class);
                            intent.putExtra("rid", userId);
                            startActivity(intent);
                        }


                        break;
                }


                //是否具备hr权限，1是，0否


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    private void getTitleName(final TextView titleName, String userId) {
        /*Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("userId", userId);
        OkHttpHelper.getInstance().post(ConversationActivity.this, NetClass.BASE_URL + NetCuiMethod.getRongUserInfo, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                titleName.setText(resultBean.getName());

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });*/


        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("userIds", userId);
        OkHttpHelper.getInstance().post(ConversationActivity.this, NetClass.BASE_URL + NetCuiMethod.rongAllInfo, params, new BaseCallback<AllRongInfoBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, AllRongInfoBean resultBean) {
                //titleName.setText(resultBean.getName());
                //hr  0 否  1 是
                if (resultBean.getDataList() != null) {
                    if (resultBean.getDataList().size() != 0) {
                        String hr = resultBean.getDataList().get(0).getHr();
                        switch (hr) {
                            case "0":
                                hrView.setVisibility(View.GONE);
                                titleName.setVisibility(View.VISIBLE);
                                titleName.setText(resultBean.getDataList().get(0).getName());
                                break;
                            case "1":
                                titleName.setVisibility(View.GONE);
                                hrView.setVisibility(View.VISIBLE);
                                tv1.setText(resultBean.getDataList().get(0).getName());
                                tv2.setText(resultBean.getDataList().get(0).getPositionName() + "-" + resultBean.getDataList().get(0).getCompanyName());
                                break;
                        }
                    }
                }


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initPhotoError() {
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.llViewGangWei:
                //跳转到岗位详情
                Intent intent = new Intent(this, GangWeiDetailActivity.class);
                intent.putExtra("pid", fanSP_pid);
                startActivity(intent);
                break;
        }
    }

}
