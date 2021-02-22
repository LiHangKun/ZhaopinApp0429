package com.lx.zhaopin.home1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.GangWeiDetailActivity;
import com.lx.zhaopin.activity.MyShouCangGangActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.ShouYeQiuZhiZheBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.FastBlurUtil;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.dragcard.CardsAdapter;
import com.lx.zhaopin.view.dragcard.DragCardsView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

public class ShouYe4Fragment extends Fragment implements View.OnClickListener {


    private int nowPageIndex = 1;
    private int totalPage = 1;

    private static final String TAG = "ShouYe4Fragment";
    private String cityId = "";


    private boolean hasNo = false;
    private DragCardsView mDragCardsView;
    private List<ShouYeQiuZhiZheBean.DataListBean> list;
    private List<ShouYeQiuZhiZheBean.DataListBean> list1;
    private int total = 0, currentPositon = 0;
    private CardsAdapter mCardAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.ka_fragment, null);


        list = new ArrayList<>();
        list1 = new ArrayList<>();
        mDragCardsView = view.findViewById(R.id.dragCardsView);
        ImageView xiHuanNo = view.findViewById(R.id.xiHuanNo);
        ImageView xiHuanYes = view.findViewById(R.id.xiHuanYes);
        FrameLayout fl_list = view.findViewById(R.id.fl_list);

        fl_list.setOnClickListener(this);
        xiHuanNo.setOnClickListener(this);
        xiHuanYes.setOnClickListener(this);
        getDataList("1", "", SPTool.getSessionValue(AppSP.sStringJ), SPTool.getSessionValue(AppSP.sStringW), cityId, String.valueOf(nowPageIndex), AppSP.pageCount);
        return view;

    }


    //职位分页列表求职者
    private void getDataList(String dataType, String name, String lng, String lat, String cityId, String pageNo, String pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("dataType", dataType);
        params.put("name", name);
        params.put("lng", lng);
        params.put("lat", lat);
        params.put("cityId", cityId);
        params.put("pageNo", pageNo);
        params.put("pageSize", "200");
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.zhiWeiPageList, params, new SpotsCallBack<ShouYeQiuZhiZheBean>(getActivity()) {
            @Override
            public void onSuccess(Response response, ShouYeQiuZhiZheBean resultBean) {
                if (resultBean.getDataList() != null) {
                    totalPage = resultBean.getTotalPage();
                    if (resultBean.getDataList().size() == 0) {
                        //没有数据
                    } else {
                        //有数据
                        list.addAll(resultBean.getDataList());
                        list1.addAll(resultBean.getDataList());
                        total = list.size();
                        initData();
                        final String head = list.get(currentPositon).getCompany().getLogo();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (!TextUtils.isEmpty(head) && head.startsWith("http")) {
                                        Bitmap bmp = Picasso.with(getContext()).load(head).get();
                                        Message message = new Message();
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("bitmap", bmp);
                                        message.setData(bundle);
                                        mHandler.sendMessage(message);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });

    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap = msg.getData().getParcelable("bitmap");
            Bitmap blurBitmap = FastBlurUtil.toBlur(bitmap, 8);
            //ivBg.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //ivBg.setImageBitmap(blurBitmap);
        }
    };


    private void initData() {
        mCardAdapter = new CardsAdapter(getContext(), list);
        mDragCardsView.setAdapter(mCardAdapter);
        mDragCardsView.setOnItemClickListener(new DragCardsView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                //ToastFactory.getToast(getActivity(), "处理点击事件" + list1.get(currentPositon).getId()).show();
                //TODO  处理点击事件
                Intent intent = new Intent(getActivity(), GangWeiDetailActivity.class);
                intent.putExtra("pid", list1.get(currentPositon).getId());
                startActivity(intent);

            }
        });
        mDragCardsView.setFlingListener(new DragCardsView.onDragListener() {

            @Override
            public void removeFirstObjectInAdapter(boolean isLeft) {
                // TODO Auto-generated method stub
                if (isLeft) {
                    browseyujian(currentPositon);
                    next();
                } else {
                    sendLove(currentPositon);
                    next();
                }
                if (list.size() > 0) {
                    list.remove(0);
                }
                mCardAdapter.notifyDataSetChanged();

            }

            @Override
            public void onSelectLeft(double distance) {
                // TODO Auto-generated method stub


            }


            @Override
            public void onSelectRight(double distance) {
                // TODO Auto-generated method stub

            }


            @Override
            public void onCardReturn() {
                // TODO Auto-generated method stub
            }

            @Override
            public void onCardMoveDistance(double distance) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // TODO Auto-generated method stub
//                ToastUtil.show("需要补牌了");

            }
        });
    }


    /**
     * 喜欢
     */
    private void sendLove(int position) {

        if (hasNo) {
            //ToastFactory.getToast(getActivity(), "喜欢0000000").show();
            return;
        } else {
            //ToastFactory.getToast(getActivity(), "喜欢---------------CCCCCCCCCCCCCCCCC" + list1.get(position).getId()).show();
            Log.i(TAG, "sendLove:喜欢 " + list1.get(position).getId());
            xiHuan(list1.get(position).getId());


        }

    }


    private void xiHuan(String pid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.zhiWeiShouCang, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    private void next() {
        if (currentPositon < (total - 1)) {
            currentPositon++;
        } else {
            ToastFactory.getToast(getActivity(), "没有更多推荐数据了").show();
            /*nowPageIndex++;
            getDataList("1", "", SPTool.getSessionValue(AppSP.sStringJ), SPTool.getSessionValue(AppSP.sStringW), cityId, String.valueOf(nowPageIndex), AppSP.pageCount);*/
            hasNo = true;
        }
    }


    /**
     * 遇见
     */
    private void browseyujian(int position) {
        if (hasNo) {
            //ToastFactory.getToast(getActivity(), "呵呵-----0").show();
            return;
        } else {
            //ToastFactory.getToast(getActivity(), "不喜欢" + list1.get(position).getId()).show();
            Log.i(TAG, "browseyujian: 不喜欢" + list1.get(position).getId());
            buXiHuan(list1.get(position).getId());
        }

    }

    private void buXiHuan(String pid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("pid", pid);
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.zhiWeiIsHeShi, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xiHuanNo:
                //ToastFactory.getToast(getActivity(), "不喜欢00000000000").show();
                break;
            case R.id.xiHuanYes:
                //ToastFactory.getToast(getActivity(), "喜欢------------------------").show();
                break;
            case R.id.fl_list:
                //我的收藏岗位
                startActivity(new Intent(getActivity(), MyShouCangGangActivity.class));
                break;
        }
    }
}
