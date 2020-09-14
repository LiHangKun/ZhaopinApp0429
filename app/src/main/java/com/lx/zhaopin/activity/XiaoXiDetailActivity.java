package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.XiaoXiDetailAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.MessageDetailBean;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.MyDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Response;

public class XiaoXiDetailActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    private RecyclerView recyclerView;
    private String messageId;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.xiaoxidetail_activity);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        titleTv.setText("消息");
        baseTop.setVisibility(View.GONE);
        messageId = getIntent().getStringExtra("messageId");
        recyclerView = findViewById(R.id.recyclerView);

        getMessageDetail(messageId);

    }

    //messageDetail
    private void getMessageDetail(String msgId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("msgId", msgId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.messageDetail, params, new SpotsCallBack<MessageDetailBean>(mContext) {
            @Override
            public void onSuccess(Response response, MessageDetailBean resultBean) {

                XiaoXiDetailAdapter xiaoXiDetailAdapter = new XiaoXiDetailAdapter(mContext, resultBean.getDataList());
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView.setAdapter(xiaoXiDetailAdapter);
                xiaoXiDetailAdapter.setOnFeedClickListener(new XiaoXiDetailAdapter.OnFeedClickListener() {
                    @Override
                    public void feedClick(final String id) {
                        View view = getLayoutInflater().inflate(R.layout.dialog_feed_mianshi, null);
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
                                    ToastFactory.getToast(mContext, "反馈信息不能为空").show();
                                    return;
                                } else {
                                    woFeedInfo(id, edit1.getText().toString().trim());
                                    mMyDialog.dismiss();
                                }
                            }
                        });
                    }
                });

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }


    private void woFeedInfo(String interviewId, String content) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewId", interviewId);
        params.put("content", content);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.woFeed, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                getMessageDetail(messageId);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.left_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_layout:
                onBackPressed();
                break;
        }
    }
}
