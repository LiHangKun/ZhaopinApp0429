package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.SelectQiWangBean;
import com.lx.zhaopin.common.MainActivity;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.ToastFactory;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Request;
import okhttp3.Response;

//首页的选择行业
//seleQiWangType1
public class SelectHangYeActivity extends BaseActivity implements View.OnClickListener {


    private String[] mVals = new String[]
            {"HelloHelloHelloHelloHelloHello", "AndroidAndroid", "WeclomeWeclome Hi ", "ButtonButtonButton", "TextViewTextView", "Hello",
                    "Android", "WeclomeWeclomeWeclomeWeclomeWeclome", "Button ImageView", "TextView", "Helloworld",
                    "AndroidAndroidAndroidAndroid", "Weclome Hello", "ButtonButtonButton Text", "TextView",};
    private TagFlowLayout tagFlowLayout;
    private List<SelectQiWangBean.DataListBean> mSelectedData;
    private List<SelectQiWangBean.DataListBean> dataList;
    List<String> StringList = new ArrayList<>();
    private static final String TAG = "SelectHangYeActivity";
    private String mid;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.selecthangye_activity);
        initMe();
    }

    private void initMe() {
        tagFlowLayout = findViewById(R.id.tagFlowLayout);
        TextView okID = findViewById(R.id.okID);
        ImageView frViewClick = findViewById(R.id.frViewClick);
        okID.setOnClickListener(this);
        frViewClick.setOnClickListener(this);

        mid = getIntent().getStringExtra("mid");

        /*tagFlowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "FlowLayout Clicked", Toast.LENGTH_SHORT).show();
            }
        });*/

        getDataList();


        /*tagFlowLayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.tv, tagFlowLayout, false);

                tv.setText(s);
                return tv;
            }

        });*/

    }

    private void getDataList() {
        Map<String, String> params = new HashMap<>();
        params.put("parentid", "");
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.seleQiWangType1, params, new BaseCallback<SelectQiWangBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, SelectQiWangBean resultBean) {
                if (resultBean.getDataList() != null) {
                    if (resultBean.getDataList().size() != 0) {
                        dataList = resultBean.getDataList();
                        mSelectedData = new ArrayList<>();
                        for (int i = 0; i < dataList.size(); i++) {
                            StringList.add(dataList.get(i).getName());
                        }

                        TagAdapter tagAdapter = new TagAdapter<String>(StringList) {
                            @Override
                            public View getView(FlowLayout parent, int position, String s) {
                                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.tv, tagFlowLayout, false);
                                tv.setText(s);
                                return tv;
                            }

                            @Override
                            public void unSelected(int position, View view) {
                                super.unSelected(position, view);
                               /* if (null != mOnItemClickListener)
                                    mOnItemClickListener.onItemUnSelect(dataList.get(position));*/
                            }
                        };

                        //tagAdapter.setSelectedList(positions);
                        //设置显示数据
                        tagFlowLayout.setAdapter(tagAdapter);


                        tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {


                            @Override
                            public void onSelected(Set<Integer> selectPosSet) {
                                Log.i(TAG, "onSelected:11111 " + selectPosSet.toString()); //  [0, 2]

                                setSelectData(selectPosSet);
                            }
                        });
                    }

                }

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    private String categoryIds = "";
    List<String> HangIDList = new ArrayList<>();

    private void setSelectData(Set<Integer> selectPosSet) {
        if (mSelectedData != null) {
            mSelectedData.clear();
        }
        Iterator<Integer> iterator = selectPosSet.iterator();
        while (iterator.hasNext()) {
            int next = iterator.next();
            mSelectedData.add(dataList.get(next));
        }

        for (int i = 0; i < mSelectedData.size(); i++) {
            HangIDList.add(mSelectedData.get(i).getId());
        }
        categoryIds = HangIDList.toString().replace("[", "").replace("]", "").replace(" ", "").trim();

        Log.i(TAG, "setSelectData: 输出的信息" + categoryIds);
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
            case R.id.okID:

                //NewUserChoose
                if (TextUtils.isEmpty(categoryIds)) {
                    ToastFactory.getToast(mContext, "请先选择行业").show();
                    return;
                } else {
                    newUserChoose(categoryIds);
                }
                break;
            case R.id.frViewClick:
                //跳过
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;

        }
    }

    private void newUserChoose(String categoryIds) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", mid);
        params.put("categoryIds", categoryIds);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.NewUserChoose, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }
}
