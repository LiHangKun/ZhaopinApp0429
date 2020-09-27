package com.lx.zhaopin.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.SelectQiWangBean;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.other.ZhiWeiAdapter;
import com.lx.zhaopin.utils.DisplayUtil;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.ClearEditText;
import com.lx.zhaopin.view.CustomTextView;
import com.lx.zhaopin.view.FlowLiner;

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
public class SelectUserHangYeActivity extends BaseActivity {

    @BindView(R.id.search_img)
    ImageView searchImg;
    @BindView(R.id.clearEditText)
    ClearEditText clearEditText;
    @BindView(R.id.sel_hor_scrollview)
    HorizontalScrollView selHorScrollview;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.black_view)
    View blackView;
    @BindView(R.id.flowLiner)
    FlowLiner flowLiner;
    private List<SelectQiWangBean.DataListBean> allList1;
    private List<SelectQiWangBean.DataListBean> allList2;
    private ZhiWeiAdapter selectQiWang1Adapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.activity_select_hangye);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        topTitle.setText("选择行业");
        rightNextParentText.setText("不限");
        rightNextParentText.setTextColor(Color.parseColor("#151413"));
        rightNextParentText.setTextSize(14);
        recyclerView = findViewById(R.id.recyclerView);

        /*if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }*/
        getDataList("", 1);

        allList1 = new ArrayList<>();
        allList2 = new ArrayList<>();
        selectQiWang1Adapter = new ZhiWeiAdapter(mContext, allList1, 1);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(selectQiWang1Adapter);

        selectQiWang1Adapter.SetOnItemClickListener(new ZhiWeiAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int i, String parentid, String name) {
                getDataList(parentid, 2);
            }
        });

    }


    private void getDataList(String parentid, final int type) {
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
                if (resultBean != null && resultBean.getDataList() != null) {
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
                allList2.addAll(list);
                flowLiner.removeAllViews();
                addDetails();
                break;
        }
    }

    private void addDetails() {
        for (int i = 0; i < allList2.size(); i++) {
            final CustomTextView textView = new CustomTextView(this);
            textView.setRadius(DisplayUtil.dip2px(this, 16));
            textView.setPressedStrokeColor(Color.parseColor("#1678FF"));
            textView.setPressedStrokeWidth(DisplayUtil.dip2px(this, 1));
            FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, ViewUtil.dp2px(this, 10), ViewUtil.dp2px(this, 10), 0);
            textView.setLayoutParams(layoutParams);
            String str = allList2.get(i).getName();
            textView.setText(str);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(16);
            textView.setPadding(ViewUtil.dp2px(this, 10), ViewUtil.dp2px(this, 5), ViewUtil.dp2px(this, 10), ViewUtil.dp2px(this, 5));
            textView.setSelectedTextColor(Color.parseColor("#151413"), Color.parseColor("#1678FF"));
            textView.setFocusable(true);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setSelected(true);
                }
            });
            flowLiner.addView(textView);
        }
        flowLiner.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }


}
