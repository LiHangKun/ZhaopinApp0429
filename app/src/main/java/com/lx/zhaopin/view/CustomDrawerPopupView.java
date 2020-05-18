package com.lx.zhaopin.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.SelectQiWangBean;
import com.lx.zhaopin.event.TypeEvent;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lxj.xpopup.core.DrawerPopupView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Request;
import okhttp3.Response;


public class CustomDrawerPopupView extends DrawerPopupView implements View.OnClickListener {
    private static final String TAG = "CustomDrawerPopupView";


    List<String> StringList = new ArrayList<>();
    private final Context mContext;
    private final String itemID;
    private TagFlowLayout tagFlowLayout;
    private List<SelectQiWangBean.DataListBean> dataList;
    private List<SelectQiWangBean.DataListBean> mSelectedData;




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


        TextView okID = findViewById(R.id.okID);
        okID.setOnClickListener(this);
        getDataList(itemID);


        tagFlowLayout = findViewById(R.id.tagFlowLayout);
        Log.e("tag", "CustomDrawerPopupView onCreate");


        tagFlowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "FlowLayout Clicked", Toast.LENGTH_SHORT).show();
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
                        if (null != mOnItemClickListener)
                            mOnItemClickListener.onItemUnSelect(dataList.get(position));
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

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    private void setSelectData(Set<Integer> selectPosSet) {
        if (mSelectedData != null) {
            mSelectedData.clear();
        }
        Iterator<Integer> iterator = selectPosSet.iterator();
        while (iterator.hasNext()) {
            int next = iterator.next();
            mSelectedData.add(dataList.get(next));
        }

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.okID:
                //确定
                EventBus.getDefault().post(new TypeEvent(mSelectedData));
                dismiss();
                break;
        }
    }


    //自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener23 {
        //void onItemSelect(ArrayList<String> afterHashSetList);
        void onItemSelect(List<SelectQiWangBean.DataListBean> dataList);

        void onItemUnSelect(SelectQiWangBean.DataListBean unSelect);
    }

    private OnItemClickListener23 mOnItemClickListener;//声明自定义的接口

    //定义方法并传给外面的使用者
    public void setOnItemClickListener(OnItemClickListener23 listener) {
        this.mOnItemClickListener = listener;
    }
}