package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.rongmessage.RongUtil;
import com.lx.zhaopin.utils.BussEvent;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Response;

public class QuxiaoMianshiActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "QuxiaoMianshiActivity";
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.content_et)
    EditText contentEt;
    @BindView(R.id.tijiao_tv)
    TextView tijiaoTv;
    private String quxiaoId;
    private String hridContent;
    private String interviewId2Content;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.activity_quxiaomianshi);
        ButterKnife.bind(this);
        quxiaoId=getIntent().getStringExtra("quxiaoID");
        hridContent=getIntent().getStringExtra("hridContent");
        interviewId2Content=getIntent().getStringExtra("interviewId2Content");
        init();
    }

    private void init() {
        titleTv.setText("取消面试");
        baseTop.setVisibility(View.GONE);
        contentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String content=contentEt.getText().toString().trim();
                  if(!TextUtils.isEmpty(content)){
                      tijiaoTv.setBackgroundResource(R.drawable.code_bg);
                  }else {
                      tijiaoTv.setBackgroundResource(R.drawable.code_noselect_bg);
                  }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
    public void onClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.left_layout,R.id.tijiao_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_layout:
                onBackPressed();
                break;
            case R.id.tijiao_tv:
                if(TextUtils.isEmpty(contentEt.getText().toString().trim())){
                    ToastFactory.getToast(mContext, "取消原因不能为空").show();
                    return;
                }else {
                    quXiaoMianShiMe(quxiaoId,contentEt.getText().toString().trim());
                }
                break;
        }
    }


    //取消面试
    private void quXiaoMianShiMe(String interviewId, String cancelReason) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("interviewId", interviewId);
        params.put("cancelReason", cancelReason);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.quXiaoMianShi_Type1, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                RongUtil.qiuZhiQuXiao(hridContent, interviewId2Content);
                EventBus.getDefault().post(new MessageEvent(10, null, null, null, null, null, null));
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                EventBus.getDefault().post(new BussEvent(BussEvent.REFRESH_MIANSHIDETAIL));
                finish();

            }

            @Override
            public void onError(Response response, int code, Exception e) {
                ToastFactory.getToast(mContext, "取消面试失败，请重试").show();
            }
        });


    }

}
