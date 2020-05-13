package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.OfferDetailBean;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Response;

public class QiuZhiFeedActivity extends BaseActivity {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;

    @BindView(R.id.caoZuoView)
    LinearLayout caoZuoView;

    private String offerID;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.qiuzhifeed_activity);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        topTitle.setText("求职反馈");
        offerID = getIntent().getStringExtra("offerID");
        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }
        getOfferDetail(offerID);

    }


    private void getOfferDetail(final String offerId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("offerId", offerId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.offerDetail, params, new SpotsCallBack<OfferDetailBean>(mContext) {
            @Override
            public void onSuccess(Response response, OfferDetailBean resultBean) {

                tv1.setText(resultBean.getJobhunter().getName());
                tv2.setText(resultBean.getContent());
                tv3.setText(resultBean.getCompany().getName());
                tv4.setText(resultBean.getSendDate());

                //offerStatus  1待处理 2 已接受 3 已拒绝
                String offerStatus = resultBean.getOfferStatus();
                switch (offerStatus) {
                    case "1":
                        caoZuoView.setVisibility(View.VISIBLE);
                        break;
                    case "2":
                    case "3":
                        caoZuoView.setVisibility(View.GONE);
                        break;

                }

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


    @OnClick({R.id.tv5, R.id.tv6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv5:
                //拒绝
                caoZuoOfferMe(offerID, "3");
                break;
            case R.id.tv6:
                //接受
                caoZuoOfferMe(offerID, "2");
                break;
        }
    }

    private void caoZuoOfferMe(String offerId, String status) {
        Map<String, String> params = new HashMap<>();
        params.put("offerId", offerId);
        params.put("status", status);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.caoZuoOffer, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                EventBus.getDefault().post(new MessageEvent(5, null, null, null, null, null, null));
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 500);


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }
}
