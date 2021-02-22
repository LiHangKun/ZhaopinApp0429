package com.lx.zhaopin.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.MianShiDetailBean;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.rongmessage.RongUtil;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.TellUtil;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.MyDialog;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import okhttp3.Response;

//面试官看到的--面试详情
public class MianShiDetailType1Activity extends BaseActivity {
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.imageState)
    ImageView imageState;
    @BindView(R.id.roundedImageView)
    RoundedImageView roundedImageView;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.llView1OnClick)
    LinearLayout llView1OnClick;
    @BindView(R.id.llView2OnClick)
    LinearLayout llView2OnClick;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6Click)
    TextView tv6Click;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.quxiaoTV)
    TextView quxiaoTV;
    @BindView(R.id.okID)
    TextView okID;
    @BindView(R.id.qiuZhiView)
    LinearLayout qiuZhiView;
    @BindView(R.id.quxiaoHR)
    TextView quxiaoHR;
    private String lat;
    private String lng;
    private String id;
    private String interviewId2;
    private String phone;
    private String hrName;
    private String hrid;
    private String jobId;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.miaoshidetailtype1_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("面试详情");

        interviewId2 = getIntent().getStringExtra("interviewId");
       /* if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }*/
        getMianShiDetail(interviewId2);


    }


    //面试官看到的--面试详情
    private void getMianShiDetail(String interviewId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewId", interviewId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.mianshiDetail_HR, params, new SpotsCallBack<MianShiDetailBean>(mContext) {
            @Override
            public void onSuccess(Response response, MianShiDetailBean resultBean) {
                tv1.setText(resultBean.getJobhunter().getName());
                hrid = resultBean.getJobhunter().getId();
                hrName = resultBean.getJobhunter().getName();

                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                        .error(R.mipmap.imageerror)).load(resultBean.getJobhunter().getAvatar()).into(roundedImageView);

                jobId = resultBean.getJobhunter().getId();
                phone = resultBean.getJobhunter().getMobile();
                tv2.setText(resultBean.getInterviewDate() + " 面试");
                MianShiDetailType1Activity.this.id = resultBean.getId();
                String interviewStatus = resultBean.getInterviewStatus();
                tv3.setVisibility(View.GONE);
                //1 待接受  2 已拒绝 3 待面试 4 已超时 5 已到达 6 已取消 7 已录取 8 不合适
                switch (interviewStatus) {
                    case "1":
                        imageState.setImageResource(R.drawable.daitongyi);
                        quxiaoHR.setVisibility(View.GONE);
                        break;
                    case "2":
                        imageState.setImageResource(R.drawable.yijujue);
                        quxiaoHR.setVisibility(View.GONE);
                        break;
                    case "3":
                        imageState.setImageResource(R.drawable.daimianshi);
                        //quxiaoHR.setVisibility(View.GONE);
                        break;
                    case "4":
                        imageState.setImageResource(R.drawable.yichaoshi);
                        quxiaoHR.setVisibility(View.GONE);
                        break;
                    case "5":
                        imageState.setImageResource(R.drawable.yidaoda);
                        quxiaoHR.setVisibility(View.GONE);
                        break;
                    case "6":
                        imageState.setImageResource(R.drawable.yiquxiao);
                        tv3.setText("取消原因:" + resultBean.getCancelReason());
                        tv3.setVisibility(View.VISIBLE);
                        quxiaoHR.setVisibility(View.GONE);
                        break;
                    case "7":
                        imageState.setImageResource(R.drawable.yitongyi);
                        quxiaoHR.setVisibility(View.GONE);
                        break;
                    case "8":
                        imageState.setImageResource(R.drawable.buheshi);
                        tv3.setText("不合适原因:" + resultBean.getDenyReason());
                        tv3.setVisibility(View.VISIBLE);
                        quxiaoHR.setVisibility(View.GONE);
                        break;
                }

                tv4.setText(resultBean.getInterviewDate());
                tv5.setText(resultBean.getPosition().getName());
                tv6Click.setText(resultBean.getLocation());


                if (TextUtils.isEmpty(resultBean.getRemarks())) {
                    tv7.setText("备注: " + "暂无备注信息");
                } else {
                    tv7.setText("备注: " + resultBean.getRemarks());
                }

                lat = resultBean.getLat();
                lng = resultBean.getLng();


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }


    @PermissionGrant(AppSP.PMS_LOCATION)
    public void pmsLocationSuccess() {
        //权限授权成功
        TellUtil.tell(mContext, phone);
    }

    /*拨打电话*/
    private void callPhone() {
        if (null != phone) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                MPermissions.requestPermissions(this, AppSP.PMS_LOCATION, Manifest.permission.CALL_PHONE);
            } else {
                pmsLocationSuccess();
            }
        }
    }

    private static final String TAG = "MianShiDetailType1Activ";

    private void goLiaoTianMethod() {
        String userId = SPTool.getSessionValue(AppSP.UID);
        String nickName = SPTool.getSessionValue(AppSP.USER_NAME);
        String userHead = SPTool.getSessionValue(AppSP.USER_ICON);

        Log.i(TAG, "onClick: " + userId + "<>" + nickName + "<>" + userHead);
        if (null != userId && null != nickName && null != userHead)
            RongIM.getInstance().setCurrentUserInfo(new UserInfo(userId, nickName, Uri.parse(userHead)));
        RongIM.getInstance().setMessageAttachedUserInfo(true);
        //对方的ID 姓名
        //RongIM.getInstance().startPrivateChat(mContext, hrid, "张三");
        RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.PRIVATE, hrid, hrName);
    }

    @OnClick({R.id.llView1OnClick, R.id.llView2OnClick, R.id.tv6Click, R.id.quxiaoTV, R.id.okID, R.id.qiuZhiView, R.id.quxiaoHR})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llView1OnClick:
                //ToastFactory.getToast(mContext, "聊天").show();
                goLiaoTianMethod();
                break;
            case R.id.llView2OnClick:
                if (!TextUtils.isEmpty(phone)) {
                    callPhone();
                }
                break;
            case R.id.tv6Click:
                //ToastFactory.getToast(mContext, "导航").show();
                gotoGaode(lat, lng);
                break;
            case R.id.okID:
                ToastFactory.getToast(mContext, "我已到达").show();
                break;
            case R.id.qiuZhiView:
                //这个不要了
                break;
            case R.id.quxiaoHR:
                //取消面试的弹框
                View view = getLayoutInflater().inflate(R.layout.dialog_quxiao_mianshi, null);
                final MyDialog mMyDialog = new MyDialog(mContext, 0, 0, view, R.style.DialogTheme2);
                final EditText edit1 = view.findViewById(R.id.edit1);
                mMyDialog.setCancelable(true);
                mMyDialog.show();

                view.findViewById(R.id.quxiaoTV).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMyDialog.dismiss();
                    }
                });

                view.findViewById(R.id.okID).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                            ToastFactory.getToast(mContext, "取消原因不能为空").show();
                            return;
                        } else {
                            quXiaoMianShiMe(id, edit1.getText().toString().trim());
                            mMyDialog.dismiss();
                        }
                    }
                });
        }
    }


    //HR 取消面试
    private void quXiaoMianShiMe(String interviewId, String cancelReason) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewId", interviewId);
        params.put("cancelReason", cancelReason);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.HR_QuXiao, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                RongUtil.HRQuXiao(jobId, interviewId2);
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                EventBus.getDefault().post(new MessageEvent(10, null, null, null, null, null, null));
                getMianShiDetail(interviewId2);

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });


    }


    /*打开高德导航*/
    private void gotoGaode(String lat, String lon) {
        if (isAvilible(mContext, "com.autonavi.minimap")) {
            try {
                //116.304521,40.003865
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=慧医&poiname=我的目的地&lat=" + lat + "&lon=" + lon + "&dev=0");
                startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }


    /* 检查手机上是否安装了指定的软件
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }
}
