package com.lx.zhaopin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.SelectQiWangBean;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.other.SelectedHangYeAdapter;
import com.lx.zhaopin.other.SpacesItemDecoration;
import com.lx.zhaopin.other.ZhiWeiAdapter;
import com.lx.zhaopin.utils.DisplayUtil;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.ClearEditText;
import com.lx.zhaopin.view.CustomTextView;
import com.lx.zhaopin.view.FlowLiner;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;
import okhttp3.Response;

//选择职位类型
public class SelectUserHangYeActivity extends BaseActivity {
    @BindView(R.id.search_img)
    ImageView searchImg;
    @BindView(R.id.clearEditText)
    ClearEditText clearEditText;
    @BindView(R.id.sel_hor_scrollview)
    RecyclerView selHorScrollview;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.black_view)
    View blackView;
    @BindView(R.id.flowLiner)
    FlowLiner flowLiner;
    @BindView(R.id.sel_num_tv)
    TextView selNumTv;
    @BindView(R.id.sure_tv)
    CustomTextView sureTv;
    @BindView(R.id.sel_second_ll)
    LinearLayout selSecondLl;
    private List<SelectQiWangBean.DataListBean> allList1;
    private List<SelectQiWangBean.DataListBean> allList2;
    private ZhiWeiAdapter selectQiWang1Adapter;
    private List<SelectQiWangBean.DataListBean> seLHangye = new ArrayList<>();
    private List<CustomTextView> selTextViews = new ArrayList<>();

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
        rightNextParentText.setVisibility(View.VISIBLE);
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
        setSelNumTvString();
    }

    @OnClick({R.id.flowLiner, R.id.right_tv, R.id.sure_tv})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_tv:
                Intent intent = new Intent();
                intent.putExtra("selAll", true);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.sure_tv:
                if (seLHangye.size() != 0) {
                    Intent intent1 = new Intent();
                    intent1.putExtra("selHangye", (Serializable) seLHangye);
                    setResult(RESULT_OK, intent1);
                    finish();
                }
                break;
        }
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
                selSecondLl.setVisibility(View.VISIBLE);
                allList2.clear();
                blackView.setVisibility(View.VISIBLE);
                allList2.addAll(list);
                flowLiner.removeAllViews();
                selTextViews.clear();
                addDetails();
                break;
        }
    }

    private void addDetails() {
        for (int i = 0; i < allList2.size(); i++) {
            final CustomTextView textView = new CustomTextView.Builder().setContext(this)
                    .setNormalTextColor(Color.parseColor("#151413"))
                    .setSelectedTextColor(Color.parseColor("#1678FF"))
                    .setRadius(DisplayUtil.dip2px(this, 16))
                    .setPressedStrokeColor(Color.parseColor("#1678FF"))
                    .setPressedStrokeWidth(DisplayUtil.dip2px(this, 1))
                    .createView();

            FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, ViewUtil.dp2px(this, 10), ViewUtil.dp2px(this, 10), 0);
            textView.setLayoutParams(layoutParams);
            String str = allList2.get(i).getName();
            textView.setText(str);
            textView.setDefineId(allList2.get(i).getId());
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(16);
            textView.setPadding(ViewUtil.dp2px(this, 10), ViewUtil.dp2px(this, 5), ViewUtil.dp2px(this, 10), ViewUtil.dp2px(this, 5));
            textView.setFocusable(true);
            for (SelectQiWangBean.DataListBean bean : seLHangye) {
                if (allList2.get(i).getId().equals(bean.getId())) {
                    textView.setSelected(true);
                    selTextViews.add(textView);
                }
            }
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (textView.isSelected()) {
                        for (SelectQiWangBean.DataListBean bean : seLHangye) {
                            if (bean.getId().equals(textView.getDefineId())) {
                                seLHangye.remove(bean);
                                break;
                            }
                        }
                        selTextViews.remove(textView);
                        textView.setSelected(false);
                    } else {
                        if (seLHangye.size() == 3) {
                            return;
                        }
                        seLHangye.add(allList2.get(finalI));
                        selTextViews.add(textView);
                        textView.setSelected(true);
                    }
                    setSelNumTvString();
                    setListData();
                }
            });
            flowLiner.addView(textView);
        }
        flowLiner.setVisibility(View.VISIBLE);
    }

    private void setSelNumTvString() {
        String format = "已选 (%d/3)";
        selNumTv.setText(String.format(format, seLHangye.size()));
        SpannableString spannableString = new SpannableString(selNumTv.getText().toString());
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#1678FF")), 4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        selNumTv.setText(spannableString);
    }

    private void setListData() {
        if (selHorScrollview.getAdapter() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            selHorScrollview.setLayoutManager(layoutManager);

            SpacesItemDecoration decoration = new SpacesItemDecoration(DisplayUtil.dip2px(this, 10), true);
            selHorScrollview.addItemDecoration(decoration);

            SelectedHangYeAdapter adapter = new SelectedHangYeAdapter(this, seLHangye);
            adapter.setDelListener(new SelectedHangYeAdapter.DelListener() {
                @Override
                public void onRemoved(SelectQiWangBean.DataListBean bean) {
                    seLHangye.remove(bean);
                    setSelNumTvString();
                    setListData();
                    for (int i = 0; i < selTextViews.size(); i++) {
                        if (selTextViews.get(i).getDefineId().equals(bean.getId()))
                            selTextViews.get(i).setSelected(false);
                    }
                }
            });
            selHorScrollview.setAdapter(adapter);
        } else {
            selHorScrollview.getAdapter().notifyDataSetChanged();
        }
    }


    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }


}
