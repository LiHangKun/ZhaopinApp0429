package com.lx.zhaopin.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lin.cardlib.CardLayoutManager;
import com.lin.cardlib.CardSetting;
import com.lin.cardlib.CardTouchHelperCallback;
import com.lin.cardlib.OnSwipeCardListener;
import com.lin.cardlib.utils.ReItemTouchHelper;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.Login1PhoneCodeActivity;
import com.lx.zhaopin.activity.MyShouCangGangActivity;
import com.lx.zhaopin.activity.PingBiGangActivity;
import com.lx.zhaopin.activity.SearchActivity;
import com.lx.zhaopin.activity.SelectCityPro1ListActivity;
import com.lx.zhaopin.adapter.CardAdapter;
import com.lx.zhaopin.adapter.CardAdapterNew;
import com.lx.zhaopin.base.BaseFragment;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.ShouYeQiuZhiZheBean;
import com.lx.zhaopin.home1.ShouYe1Fragment;
import com.lx.zhaopin.home1.ShouYe2Fragment;
import com.lx.zhaopin.home1.ShouYe3Fragment;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.FastBlurUtil;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.view.dragcard.DragCardsView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

//求职者看到的首页
public class Home1Fragment extends BaseFragment implements View.OnClickListener {

    public ViewPager viewPager;
    private TextView tv1;
    private TextView tv2;
    private ArrayList<Fragment> fragments;
    private MyPagerAdapter adapter;
    private LinearLayout llSearchView;
    private TextView tvCity;
    private TextView tv3;
    private LinearLayout viewType1;
    private FrameLayout viewType2;


    private boolean kaPian = true;
    private boolean hasNo = false;
    private DragCardsView mDragCardsView;
    //private List<ShouYeQiuZhiZheBean.DataListBean> list;
    private List<ShouYeQiuZhiZheBean.DataListBean> NewAllList;
    ReItemTouchHelper mReItemTouchHelper;
    private List<ShouYeQiuZhiZheBean.DataListBean> list1;
    private int total = 0, currentPositon = 0;

    private int nowPageIndex = 1;
    private int totalPage = 1;
    private static final String TAG = "Home1Fragment";
    private String cityId = "";
    private RecyclerView recyclerViewKa;

    private int kaPositon = 0;

    private List<ShouYeQiuZhiZheBean.DataListBean> allList;
    private List<ShouYeQiuZhiZheBean.DataListBean> allListKa;
    //private MyAdapter myAdapter;
    private ImageView fl_list;
    private ImageView dituImage;
    private CardAdapterNew cardAdapter;
    private LinearLayout openLayout;
    private ImageView closeImg;
    private ImageView pingbiImg;
    private ImageView openSHouCangImg;
    private LinearLayout shouCangLayout;
    private ImageView openImg;
    private ImageView changeRoleImg;


    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int arg0) {
            return fragments.get(arg0);
        }

        public int getCount() {
            return fragments.size();
        }
    }


    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(MessageEvent event) {
        int messageType = event.getMessageType();
        switch (messageType) {
            case 4:
                String cityName = event.getKeyWord1();
                String cityNameID = event.getKeyWord2();
                tvCity.setText(cityName);


                break;
            case 11:
                getDataList("1", "", SPTool.getSessionValue(AppSP.sStringJ), SPTool.getSessionValue(AppSP.sStringW), cityId, String.valueOf(nowPageIndex), AppSP.pageCount);

                Log.i(TAG, "getEventmessage: 收到消息更新卡片");
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(container.getContext(), R.layout.home1fragment_layout, null);
        viewPager = view.findViewById(R.id.viewPager);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        viewType1 = view.findViewById(R.id.ViewType1);
        viewType2 = view.findViewById(R.id.ViewType2);
        dituImage = view.findViewById(R.id.dituImage);
        openLayout = view.findViewById(R.id.open_layout);
        closeImg = view.findViewById(R.id.close_img);
        pingbiImg = view.findViewById(R.id.pingbi_img);
        openSHouCangImg = view.findViewById(R.id.open_list);
        shouCangLayout = view.findViewById(R.id.yincang_layout);
        openImg = view.findViewById(R.id.open_img);
        changeRoleImg = view.findViewById(R.id.change_role_img);
        changeRoleImg.setOnClickListener(this);

        openImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shouCangLayout.setVisibility(View.GONE);
                openLayout.setVisibility(View.VISIBLE);
            }
        });

        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shouCangLayout.setVisibility(View.VISIBLE);
                openLayout.setVisibility(View.GONE);
            }
        });

        pingbiImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //已屏蔽记录
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    Intent intent = new Intent(getActivity(), PingBiGangActivity.class);
                    startActivity(intent);
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
            }
        });

        ImageView selectView = view.findViewById(R.id.selectView);
        selectView.setOnClickListener(this);
        llSearchView = view.findViewById(R.id.llSearchView);
        llSearchView.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);

        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }

        LinearLayout addView = view.findViewById(R.id.addView);
        tvCity = view.findViewById(R.id.tvCity);
        addView.setOnClickListener(this);

        tvCity.setText(SPTool.getSessionValue(AppSP.sCity));

        setListeners();

        fragments = new ArrayList<>();
        fragments.add(new ShouYe1Fragment());
        fragments.add(new ShouYe2Fragment());
        fragments.add(new ShouYe3Fragment());
        //fragments.add(new ShouYe3Fragment());

        adapter = new MyPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());


        //list = new ArrayList<>();
        //list1 = new ArrayList<>();
        mDragCardsView = view.findViewById(R.id.dragCardsView);
        fl_list = view.findViewById(R.id.fl_list);

        fl_list.setOnClickListener(this);
        openSHouCangImg.setOnClickListener(this);


        //getDataList
        recyclerViewKa = view.findViewById(R.id.recyclerViewKa);
        NewAllList = new ArrayList<>();
        CardSetting setting = new CardSetting();
        setting.setSwipeListener(new OnSwipeCardListener<ShouYeQiuZhiZheBean.DataListBean>() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float dx, float dy, int direction) {
                switch (direction) {
                    case ReItemTouchHelper.DOWN:
                        Log.e("aaa", "swiping direction=down");
                        break;
                    case ReItemTouchHelper.UP:
                        Log.e("aaa", "swiping direction=up");
                        break;
                    case ReItemTouchHelper.LEFT:
                        Log.e("aaa", "swiping direction=left");
                        break;
                    case ReItemTouchHelper.RIGHT:
                        Log.e("aaa", "swiping direction=right");
                        break;
                }
            }

            @Override
            public void onSwipedOut(RecyclerView.ViewHolder viewHolder, ShouYeQiuZhiZheBean.DataListBean o, int direction) {
                switch (direction) {
                    case ReItemTouchHelper.DOWN:
                        Log.i(TAG, "onSwipedOut: 向下");
                        //不喜欢
                        buXiHuan(o.getId());
                        break;
                    case ReItemTouchHelper.UP:
                        Log.i(TAG, "onSwipedOut:上 ");
                        //切换
                        buXiHuan(o.getId());
                        break;
                    case ReItemTouchHelper.LEFT:
                        Log.i(TAG, "onSwipedOut: 左--------------");
                        //不喜欢
                        buXiHuan(o.getId());
                        break;
                    case ReItemTouchHelper.RIGHT:
                        Log.i(TAG, "onSwipedOut: 右++++++++++++++++++");
                        //喜欢
                        xiHuan(o.getId());
                        break;
                }
            }

            @Override
            public void onSwipedClear() {
                //Toast.makeText(getActivity(), "cards are consumed", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onSwipedClear: 开始加载下一页");
                recyclerViewKa.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (nowPageIndex < totalPage) {
                            nowPageIndex++;
                            Log.e(TAG, "onLoadMore: http 加载下一页了");
                            getDataList("1", "", SPTool.getSessionValue(AppSP.sStringJ), SPTool.getSessionValue(AppSP.sStringW), cityId, String.valueOf(nowPageIndex), AppSP.pageCount);
                            recyclerViewKa.getAdapter().notifyDataSetChanged();
                        } else {
                            Log.e(TAG, "onLoadMore: http  相等不可刷新");
                            dituImage.setVisibility(View.GONE);
                        }
                    }
                }, 100);


            }
        });
        CardTouchHelperCallback helperCallback = new CardTouchHelperCallback(recyclerViewKa, NewAllList, setting);
        mReItemTouchHelper = new ReItemTouchHelper(helperCallback);
        CardLayoutManager layoutManager = new CardLayoutManager(mReItemTouchHelper, setting);
        recyclerViewKa.setLayoutManager(layoutManager);
        cardAdapter = new CardAdapterNew(getActivity(), NewAllList);
        recyclerViewKa.setAdapter(cardAdapter);


        getDataList("1", "", SPTool.getSessionValue(AppSP.sStringJ), SPTool.getSessionValue(AppSP.sStringW), cityId, String.valueOf(nowPageIndex), AppSP.pageCount);

        return view;

    }


    //通过监听viewpager滑动改变Checked的属性
    private void setListeners() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tv1.setTextColor(getResources().getColor(R.color.txt_c333));
                        tv2.setTextColor(getResources().getColor(R.color.txt_lv2));
                        tv3.setTextColor(getResources().getColor(R.color.txt_lv2));

                        tv1.setTextSize(18);
                        tv2.setTextSize(16);
                        tv3.setTextSize(16);

                        tv1.setTypeface(null, Typeface.BOLD);
                        tv2.setTypeface(null, Typeface.NORMAL);
                        tv3.setTypeface(null, Typeface.NORMAL);


                        break;
                    case 1:
                        tv2.setTextColor(getResources().getColor(R.color.txt_c333));
                        tv1.setTextColor(getResources().getColor(R.color.txt_lv2));
                        tv3.setTextColor(getResources().getColor(R.color.txt_lv2));

                        tv3.setTextSize(16);
                        tv1.setTextSize(16);
                        tv2.setTextSize(18);

                        tv2.setTypeface(null, Typeface.BOLD);
                        tv1.setTypeface(null, Typeface.NORMAL);
                        tv3.setTypeface(null, Typeface.NORMAL);

                        break;
                    case 2:
                        tv3.setTextColor(getResources().getColor(R.color.txt_c333));
                        tv1.setTextColor(getResources().getColor(R.color.txt_lv2));
                        tv2.setTextColor(getResources().getColor(R.color.txt_lv2));

                        tv2.setTextSize(16);
                        tv1.setTextSize(16);
                        tv3.setTextSize(18);

                        tv3.setTypeface(null, Typeface.BOLD);
                        tv1.setTypeface(null, Typeface.NORMAL);
                        tv2.setTypeface(null, Typeface.NORMAL);

                        break;


                    default:
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                //推荐
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv2:
                //最新
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv3:
                //最近
                viewPager.setCurrentItem(2);
                break;
            case R.id.llSearchView:
                //搜索
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.addView:
                //选择城市
                startActivity(new Intent(getActivity(), SelectCityPro1ListActivity.class));
                break;
            case R.id.selectView:
                //切换卡片
                if (kaPian) {
                    viewType1.setVisibility(View.VISIBLE);
                    viewType2.setVisibility(View.GONE);
                } else {
                    viewType1.setVisibility(View.GONE);
                    viewType2.setVisibility(View.VISIBLE);
                }
                kaPian = !kaPian;
                break;
            case R.id.fl_list:
            case R.id.open_list:
                //我的收藏岗位
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    startActivity(new Intent(getActivity(), MyShouCangGangActivity.class));
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }
                break;
            case R.id.change_role_img:
                if (SPTool.getSessionValue(AppSP.USER_HR_PERMISSION, false)) {
                    EventBus.getDefault().post(new MessageEvent(14, null, null, null, null, null, null));
                }
                break;

        }
    }


    //-------------------------TODO 卡片----------------------------------------
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
        params.put("pageSize", pageSize);
        //params.put("pageSize", "5");
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.zhiWeiPageList, params, new SpotsCallBack<ShouYeQiuZhiZheBean>(getActivity()) {
            @Override
            public void onSuccess(Response response, ShouYeQiuZhiZheBean resultBean) {
                if (resultBean.getDataList() != null) {
                    totalPage = resultBean.getTotalPage();
                    Log.e(TAG, "onSuccess: http 收到消息更新卡片" + totalPage);
                    if (resultBean.getDataList().size() == 0) {
                        //没有数据
                        dituImage.setVisibility(View.GONE);
                    } else {
                        //有数据
                        NewAllList.clear();
                        NewAllList.addAll(resultBean.getDataList());
                        cardAdapter.notifyDataSetChanged();
                        //TODO   有数据
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
       /* mCardAdapter = new CardsAdapter(getContext(), list);
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
        });*/
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

            if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                xiHuan(list1.get(position).getId());
            }


        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
       /* if (isVisibleToUser) {
            Log.e(TAG, "setUserVisibleHint:http 页面 000000000");
            if (fl_list != null) {
                fl_list.setImageResource(R.drawable.shoucang_hezi);
            }
        } else {
            Log.e(TAG, "setUserVisibleHint:http页面 111111");
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        if (fl_list != null) {
            fl_list.setImageResource(R.drawable.shoucang_hezi);
        }
    }

    private void xiHuan(String pid) {

        if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
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
                    fl_list.setImageResource(R.drawable.shoucang_hong);

                }

                @Override
                public void onError(Response response, int code, Exception e) {

                }
            });
        }


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
            if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                buXiHuan(list1.get(position).getId());
            }


        }

    }

    private void buXiHuan(String pid) {
        if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
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

    }
    //-------------------------TODO 卡片----------------------------------------


}
