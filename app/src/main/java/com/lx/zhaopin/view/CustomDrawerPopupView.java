package com.lx.zhaopin.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.SelectQiWangBean;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lxj.xpopup.core.DrawerPopupView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;


public class CustomDrawerPopupView extends DrawerPopupView {
    private static final String TAG = "CustomDrawerPopupView";

    //private String[] mVals = new String[]{"HelloHelloHelloHelloHelloHello", "AndroidAndroidAndroidAndroid",};
    List<String> StringList = new ArrayList<>();

    private final Context mContext;
    private final String itemID;
    private TagFlowLayout tagFlowLayout;


    public CustomDrawerPopupView(@NonNull Context context, String id) {
        super(context);
        mContext = context;
        itemID = id;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_drawer_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        getDataList(itemID);


        tagFlowLayout = findViewById(R.id.tagFlowLayout);
        Log.e("tag", "CustomDrawerPopupView onCreate");


        tagFlowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "FlowLayout Clicked", Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void getDataList(String parentid) {
        Map<String, String> params = new HashMap<>();
        params.put("parentid", parentid);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.seleQiWangType2, params, new BaseCallback<SelectQiWangBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, SelectQiWangBean resultBean) {
                List<SelectQiWangBean.DataListBean> dataList = resultBean.getDataList();
                for (int i = 0; i < dataList.size(); i++) {
                    StringList.add(dataList.get(i).getName());
                }

                tagFlowLayout.setAdapter(new TagAdapter<String>(StringList) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.tv, tagFlowLayout, false);

                        tv.setText(s);
                        return tv;
                    }

                });

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    @Override
    protected void onShow() {
        super.onShow();
        Log.e(TAG, "CustomDrawerPopupView onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e(TAG, "CustomDrawerPopupView onDismiss");
    }
}