package com.lx.zhaopin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.MianShiDetailBean;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.MyDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.quxiaoTv)
    TextView quxiaoTv;
    private String lat;
    private String lng;
    private String id;
    private String interviewId2;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.miaoshidetailtype1_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("面试官看到的--面试详情");

        interviewId2 = getIntent().getStringExtra("interviewId");

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
                tv1.setText(resultBean.getCompany().getName());
                tv2.setText(resultBean.getInterviewDate() + " 面试");
                id = resultBean.getId();
                String interviewStatus = resultBean.getInterviewStatus();
                tv3.setVisibility(View.GONE);
                //1 待接受  2 已拒绝 3 待面试 4 已超时 5 已到达 6 已取消 7 已录取 8 不合适
                switch (interviewStatus) {
                    case "1":
                        imageState.setImageResource(R.drawable.daitongyi);
                        break;
                    case "2":
                        imageState.setImageResource(R.drawable.yijujue);
                        break;
                    case "3":
                        imageState.setImageResource(R.drawable.hom1s);
                        break;
                    case "4":
                        imageState.setImageResource(R.drawable.yichaoshi);
                        break;
                    case "5":
                        imageState.setImageResource(R.drawable.hom1s);
                        break;
                    case "6":
                        imageState.setImageResource(R.drawable.yiquxiao);
                        tv3.setText("取消原因:" + resultBean.getCancelReason());
                        tv3.setVisibility(View.VISIBLE);
                        break;
                    case "7":
                        imageState.setImageResource(R.drawable.yitongyi);
                        break;
                    case "8":
                        imageState.setImageResource(R.drawable.hom1s);
                        tv3.setText("不合适原因:" + resultBean.getDenyReason());
                        tv3.setVisibility(View.VISIBLE);
                        break;
                }

                tv4.setText(resultBean.getInterviewDate());
                tv5.setText(resultBean.getPosition().getName());
                tv6Click.setText(resultBean.getLocation());


                tv7.setText(resultBean.getRemarks());
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


    @OnClick({R.id.llView1OnClick, R.id.llView2OnClick, R.id.tv6Click, R.id.quxiaoTV, R.id.okID, R.id.qiuZhiView, R.id.quxiaoTv})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llView1OnClick:
                ToastFactory.getToast(mContext, "聊天").show();
                break;
            case R.id.llView2OnClick:
                ToastFactory.getToast(mContext, "打电话").show();
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
            case R.id.quxiaoTV:
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


    //取消面试
    private void quXiaoMianShiMe( String interviewId, String cancelReason) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewId", interviewId);
        params.put("cancelReason", cancelReason);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.quXiaoMianShi_Type2, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                ToastFactory.getToast(mContext, resultBean.getAuthCode()).show();

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
