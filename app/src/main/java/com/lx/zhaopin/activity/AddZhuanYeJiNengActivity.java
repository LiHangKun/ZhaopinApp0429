package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
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

import okhttp3.Response;

//此页面是 添加和删除专业技能,当传递过来的ID 不等于空的时候 显示删除按钮
public class AddZhuanYeJiNengActivity extends BaseActivity implements View.OnClickListener {

    private String name;
    private String id;
    private EditText edit1;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.addzhuanyejineng_activity);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        topTitle.setText("专业技能");
        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }

        rightText.setVisibility(View.VISIBLE);
        rightText.setText("删除");
        rightText.setOnClickListener(this);
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");

        if (!TextUtils.isEmpty(id)) {
            rightText.setVisibility(View.VISIBLE);
        } else {
            rightText.setVisibility(View.INVISIBLE);
        }

        edit1 = findViewById(R.id.edit1);
        TextView okID = findViewById(R.id.okID);
        okID.setOnClickListener(this);
        edit1.setText(name);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rightText:
                //删除专业技能
                StyledDialog.init(mContext);
                StyledDialog.buildIosAlert("", "\r是否删除专业技能?", new MyDialogListener() {
                    @Override
                    public void onFirst() {

                    }

                    @Override
                    public void onSecond() {
                        delZYJNMe(id);


                    }
                }).setBtnColor(R.color.mainColor2, R.color.mainColor1, 0).setBtnText("取消", "确定").show();

                break;
            case R.id.okID:
                //保存
                if (TextUtils.isEmpty(id)) {
                    //新增专业技能
                    if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                        ToastFactory.getToast(mContext, "专业技能不能为空").show();
                        return;
                    } else {
                        addNewZYJN(edit1.getText().toString().trim());
                    }
                } else {
                    //修改专业技能
                    if (TextUtils.isEmpty(edit1.getText().toString().trim())) {
                        ToastFactory.getToast(mContext, "专业技能不能为空").show();
                        return;
                    } else {
                        editZYJNMe(edit1.getText().toString().trim(), id);
                    }
                }

                break;
        }
    }

    //修改专业技能
    private void editZYJNMe(String name, String id) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("sid", id);
        params.put("name", name);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.EditZyJN, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                EventBus.getDefault().post(new MessageEvent(3, null, null, null, null, null, null));
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

    //新增专业技能
    private void addNewZYJN(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("name", name);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.ADDZyJN, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                EventBus.getDefault().post(new MessageEvent(3, null, null, null, null, null, null));
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

    private void delZYJNMe(String sid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("sid", sid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.delZyJN, params, new SpotsCallBack<PhoneStateBean>(mContext) {
            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                ToastFactory.getToast(mContext, resultBean.getResultNote()).show();
                EventBus.getDefault().post(new MessageEvent(3, null, null, null, null, null, null));
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
