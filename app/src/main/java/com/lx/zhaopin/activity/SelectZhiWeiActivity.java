package com.lx.zhaopin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.SelectQiWang1Adapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.SelectQiWangBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.other.ZhiWeiAdapter;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.view.ClearEditText;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import okhttp3.Response;

//选择职位类型
public class SelectZhiWeiActivity extends BaseActivity {
    @BindView(R.id.search_img)
    ImageView searchImg;
    @BindView(R.id.clearEditText)
    ClearEditText clearEditText;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.black_view)
    View blackView;
    @BindView(R.id.recyclerView_left)
    RecyclerView recyclerViewLeft;
    @BindView(R.id.recyclerView_right)
    RecyclerView recyclerViewRight;
    @BindView(R.id.slide_ll)
    LinearLayout slideLl;
    private List<SelectQiWangBean.DataListBean> allList1;
    private List<SelectQiWangBean.DataListBean> allList2;
    private List<SelectQiWangBean.DataListBean> allList3;
    private ZhiWeiAdapter selectQiWang1Adapter;
    private ZhiWeiAdapter selectQiWang2Adapter;
    private ZhiWeiAdapter selectQiWang3Adapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.activity_select_zhiwei);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        topTitle.setText("选择职位类型");
        recyclerView = findViewById(R.id.recyclerView);

        /*if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }*/
        getDataList("", 1);

        allList1 = new ArrayList<>();
        allList2 = new ArrayList<>();
        allList3 = new ArrayList<>();
        selectQiWang1Adapter = new ZhiWeiAdapter(mContext, allList1, 1);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(selectQiWang1Adapter);

        selectQiWang2Adapter = new ZhiWeiAdapter(mContext, allList2, 2);
        recyclerViewLeft.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewLeft.setAdapter(selectQiWang2Adapter);

        selectQiWang3Adapter = new ZhiWeiAdapter(mContext, allList3, 3);
        recyclerViewRight.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewRight.setAdapter(selectQiWang3Adapter);

        selectQiWang1Adapter.SetOnItemClickListener(new ZhiWeiAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int i, String parentid, String name) {
                getDataList(parentid, 2);
            }
        });
        selectQiWang2Adapter.SetOnItemClickListener(new ZhiWeiAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int i, String parentid, String name) {
                getDataList(parentid, 3);
            }
        });
        selectQiWang3Adapter.SetOnItemClickListener(new ZhiWeiAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int i, String id, String name) {
                Intent intent = new Intent();
                intent.putExtra("zhiwei", name);
                intent.putExtra("zhiweiId", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        clearEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchStr = clearEditText.getText().toString();
                    if (!TextUtils.isEmpty(searchStr)) {
                        search(searchStr);
                    }
                }
                return false;
            }
        });
    }

    private void search(String str) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        Log.e("mid", "mid="+SPTool.getSessionValue(AppSP.UID));
        params.put("name", str);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.search, params, new BaseCallback<SelectQiWangBean>() {
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
//                        setData(type, resultBean.getDataList());
                    }
                }

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    private void getDataList(String parentid, final int type) {
        Map<String, String> params = new HashMap<>();
        params.put("parentid", parentid);
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
                        setData(type, resultBean.getDataList());
                    }
                }

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }

    private void setData(int type, List<SelectQiWangBean.DataListBean> list) {
        switch (type) {
            case 1:
                allList1.addAll(list);
                selectQiWang1Adapter.notifyDataSetChanged();
                break;
            case 2:
                allList2.clear();
                blackView.setVisibility(View.VISIBLE);
                slideLl.setVisibility(View.VISIBLE);
                allList2.addAll(list);
                selectQiWang2Adapter.notifyDataSetChanged();
                break;
            case 3:
                allList3.clear();
                allList3.addAll(list);
                selectQiWang3Adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }


}
