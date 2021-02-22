package com.lx.zhaopin.hr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lin.cardlib.CardLayoutManager;
import com.lin.cardlib.CardSetting;
import com.lin.cardlib.CardTouchHelperCallback;
import com.lin.cardlib.OnSwipeCardListener;
import com.lin.cardlib.utils.ReItemTouchHelper;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.Login1PhoneCodeActivity;
import com.lx.zhaopin.activity.MyShouCangRenActivity;
import com.lx.zhaopin.activity.RenCaiDetailActivity;
import com.lx.zhaopin.activity.SelectCityPro1ListActivity;
import com.lx.zhaopin.adapter.HRCardAdapter;
import com.lx.zhaopin.adapter.HRCardAdapter1;
import com.lx.zhaopin.base.BaseFragment;
import com.lx.zhaopin.bean.GongSiZhiWeiBean;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.bean.RenCaiListBean;
import com.lx.zhaopin.bean.RenCaiListBean1;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.home3.RenCaiCardsAdapter;
import com.lx.zhaopin.hractivity.HRSearchActivity;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.FastBlurUtil;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.utils.ToastFactory;
import com.lx.zhaopin.utils.ViewUtil;
import com.lx.zhaopin.view.FlowLiner;
import com.lx.zhaopin.view.dragcard.DragCardsView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

//HR看到的首页
public class HRHome1Fragment extends BaseFragment implements View.OnClickListener {

    public ViewPager viewPager;
    private TextView tv1;
    private TextView tv2;
    private ArrayList<Fragment> fragments;
    private MyPagerAdapter adapter;
    private LinearLayout llSearchView;
    private TextView tvCity;

    private LinearLayout viewType1;
    private FrameLayout viewType2;


    private boolean kaPian = true;
    private boolean hasNo = false;
    private DragCardsView mDragCardsView;
    private List<RenCaiListBean1.DataListBean> list;
    private List<RenCaiListBean1.DataListBean> list1;
    private int total = 0, currentPositon = 0;
    private RenCaiCardsAdapter mCardAdapter;

    private int nowPageIndex = 1;
    private int totalPage = 1;
    private static final String TAG = "HRHome1Fragment";
    private String cityId = "";
    private RecyclerView recyclerViewKa;

    ReItemTouchHelper mReItemTouchHelper;

    private List<RenCaiListBean1.DataListBean> NewAllList;
    //private MyAdapter myAdapter;
    private ImageView dituImage;
    private HRCardAdapter1 hrCardAdapter;
    private ImageView fl_list;
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
                getDataList("1", "", String.valueOf(nowPageIndex), AppSP.pageCount);
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
        View view = View.inflate(container.getContext(), R.layout.hrhome1basefragment_layout, null);
        viewPager = view.findViewById(R.id.viewPager);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        dituImage = view.findViewById(R.id.dituImage);
        llSearchView = view.findViewById(R.id.llSearchView);
        llSearchView.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        changeRoleImg = view.findViewById(R.id.change_role_img);
        changeRoleImg.setOnClickListener(this);

        ImageView selectView = view.findViewById(R.id.selectView);
        fl_list = view.findViewById(R.id.fl_list);
        selectView.setOnClickListener(this);
        fl_list.setOnClickListener(this);

        viewType1 = view.findViewById(R.id.ViewType1);
        viewType2 = view.findViewById(R.id.ViewType2);


        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }

        LinearLayout addView = view.findViewById(R.id.addView);
        tvCity = view.findViewById(R.id.tvCity);
        addView.setOnClickListener(this);

        tvCity.setText(SPTool.getSessionValue(AppSP.sCity));

        setListeners();

        fragments = new ArrayList<>();
        fragments.add(new HRShouYe1Fragment());
        fragments.add(new HRShouYe2Fragment());

        adapter = new MyPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());


        list = new ArrayList<>();
        list1 = new ArrayList<>();
        mDragCardsView = view.findViewById(R.id.dragCardsView);


        //RenCaiListBean
        /*allList = new ArrayList<>();
        recyclerViewKa.setItemAnimator(new DefaultItemAnimator());
        myAdapter = new MyAdapter();
        recyclerViewKa.setAdapter(myAdapter);


        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerViewKa.getAdapter(), allList);
        cardCallback.setOnSwipedListener(new OnSwipeListener<RenCaiListBean.DataListBean>() {

            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {

                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    //myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    //myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    //myHolder.dislikeImageView.setAlpha(0f);
                    //myHolder.likeImageView.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, RenCaiListBean.DataListBean o, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                //myHolder.dislikeImageView.setAlpha(0f);
                //myHolder.likeImageView.setAlpha(0f);

                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    if (direction == CardConfig.SWIPED_LEFT) {
                        //不喜欢
                        buXiHuan(o.getId());
                    } else {
                        //喜欢
                        xiHuan(o.getId());
                    }
                }


            }

            @Override
            public void onSwipedClear() {
                recyclerViewKa.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (nowPageIndex < totalPage) {
                            nowPageIndex++;
                            Log.e(TAG, "onLoadMore: http 加载下一页了");
                            getDataList("1", "", String.valueOf(nowPageIndex), AppSP.pageCount);
                            recyclerViewKa.getAdapter().notifyDataSetChanged();
                        } else {
                            Log.e(TAG, "onLoadMore: http  相等不可刷新");
                            dituImage.setVisibility(View.GONE);
                        }


                    }
                }, 1000L);
            }

        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerViewKa, touchHelper);
        recyclerViewKa.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerViewKa);*/


        recyclerViewKa = view.findViewById(R.id.recyclerViewKa);
        NewAllList = new ArrayList<>();
        CardSetting setting = new CardSetting();
        setting.setSwipeListener(new OnSwipeCardListener<RenCaiListBean1.DataListBean>() {
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
            public void onSwipedOut(RecyclerView.ViewHolder viewHolder, RenCaiListBean1.DataListBean o, int direction) {
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
                            getDataList("1", "", String.valueOf(nowPageIndex), AppSP.pageCount);
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
        hrCardAdapter = new HRCardAdapter1(getActivity(), NewAllList);
        recyclerViewKa.setAdapter(hrCardAdapter);


        getDataList("1", "", String.valueOf(nowPageIndex), AppSP.pageCount);


        return view;

    }


    /*private class MyAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_rencai22, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            RoundedImageView roundedImageView = ((MyViewHolder) holder).roundedImageView;
            TextView tv1 = ((MyViewHolder) holder).tv1;
            TextView tv2 = ((MyViewHolder) holder).tv2;
            TextView tv3 = ((MyViewHolder) holder).tv3;
            TextView tv4 = ((MyViewHolder) holder).tv4;
            TextView tv5 = ((MyViewHolder) holder).tv5;
            TextView tv6 = ((MyViewHolder) holder).tv6;
            TextView tv7 = ((MyViewHolder) holder).tv7;
            TextView tv8 = ((MyViewHolder) holder).tv8;
            TextView tv9 = ((MyViewHolder) holder).tv9;
            LinearLayout onClickView = ((MyViewHolder) holder).onClickView;

            FlowLiner flowLiner1 = ((MyViewHolder) holder).flowLiner1;
            FlowLiner flowLiner2 = ((MyViewHolder) holder).flowLiner2;

            if (allList.get(position).getExperienceWorkList() != null) {
                if (allList.get(position).getExperienceWorkList().size() != 0) {
                    tv6.setText(allList.get(position).getExperienceWorkList().get(0).getCompanyName());
                    tv7.setText(allList.get(position).getExperienceWorkList().get(0).getPositionName());
                    tv8.setText(allList.get(position).getExperienceWorkList().get(0).getExperience());
                    String skills = allList.get(position).getExperienceWorkList().get(0).getSkills();


                    if (!TextUtils.isEmpty(skills)) {
                        String[] split = skills.split(",");
                        List<String> flowData = new ArrayList<>();
                        for (int i = 0; i < split.length; i++) {
                            flowData.add(split[i]);
                        }

                        flowLiner1.removeAllViews();
                        for (int i = 0; i < flowData.size(); i++) {
                            final TextView radioButton = new TextView(getActivity());
                            FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
                            layoutParams.setMargins(0, 0, ViewUtil.dp2px(getActivity(), 10), ViewUtil.dp2px(getActivity(), 10));
                            radioButton.setLayoutParams(layoutParams);
                            final String str = flowData.get(i);
                            radioButton.setText(str);
                            radioButton.setGravity(Gravity.CENTER);
                            radioButton.setTextSize(13);
                            radioButton.setPadding(ViewUtil.dp2px(getActivity(), 10), ViewUtil.dp2px(getActivity(), 6), ViewUtil.dp2px(getActivity(), 10), ViewUtil.dp2px(getActivity(), 6));
                            radioButton.setTextColor(getActivity().getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
                            //radioButton.setBackgroundResource(R.drawable.search_selector);
                            radioButton.setBackgroundResource(R.drawable.button_shape03);
                            radioButton.setFocusable(true);
                            radioButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            flowLiner1.addView(radioButton);
                        }
                    } else {
                        flowLiner1.setVisibility(View.GONE);
                        tv6.setText("");
                        tv7.setText("");
                        tv8.setText("");
                    }
                }
            }

            if (allList.get(position).getResumeSkillList() != null) {
                if (allList.get(position).getResumeSkillList().size() != 0) {
                    List<String> flowData2 = new ArrayList<>();
                    for (int i = 0; i < allList.get(position).getResumeSkillList().size(); i++) {
                        flowData2.add(allList.get(position).getResumeSkillList().get(i).getName());
                    }
                    flowLiner2.removeAllViews();
                    for (int i = 0; i < flowData2.size(); i++) {
                        final TextView radioButton = new TextView(getActivity());
                        FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(0, 0, ViewUtil.dp2px(getActivity(), 10), ViewUtil.dp2px(getActivity(), 10));
                        radioButton.setLayoutParams(layoutParams);
                        final String str = flowData2.get(i);
                        radioButton.setText(str);
                        radioButton.setGravity(Gravity.CENTER);
                        radioButton.setTextSize(13);
                        radioButton.setPadding(ViewUtil.dp2px(getActivity(), 10), ViewUtil.dp2px(getActivity(), 6), ViewUtil.dp2px(getActivity(), 10), ViewUtil.dp2px(getActivity(), 6));
                        radioButton.setTextColor(getActivity().getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
                        //radioButton.setBackgroundResource(R.drawable.search_selector);
                        radioButton.setBackgroundResource(R.drawable.button_shape03);
                        radioButton.setFocusable(true);
                        radioButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        flowLiner2.addView(radioButton);
                    }
                }
            } else {
                flowLiner2.setVisibility(View.GONE);
            }


            Glide.with(getActivity()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                    .error(R.mipmap.imageerror)).load(allList.get(position).getAvatar()).into(roundedImageView);

            tv1.setText(allList.get(position).getName());


            tv2.setText(allList.get(position).getEducation().getName());
            tv3.setText(allList.get(position).getAge() + "岁");
            tv4.setText(allList.get(position).getWorkYears() + "年");
            tv5.setText(allList.get(position).getLatestCity().getName());

            tv9.setText(allList.get(position).getExperienceEducation().getSchool());


            onClickView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    *//*if (itemCliCkListener != null) {
                        itemCliCkListener.onItemClickListener(position, allList.get(position).getId());
                    }*//*

                    Intent intent = new Intent(getActivity(), RenCaiDetailActivity.class);
                    intent.putExtra("rid", allList.get(position).getId());
                    startActivity(intent);


                }
            });


        }

        @Override
        public int getItemCount() {
            Log.i(TAG, "getItemCount: 集合的个数" + allList.size());
            return allList == null ? 0 : allList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            RoundedImageView roundedImageView;
            LinearLayout onClickView;
            FlowLiner flowLiner1, flowLiner2;
            TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;

            MyViewHolder(View itemView) {
                super(itemView);
                roundedImageView = itemView.findViewById(R.id.roundedImageView);

                tv1 = itemView.findViewById(R.id.tv1);
                tv2 = itemView.findViewById(R.id.tv2);
                tv3 = itemView.findViewById(R.id.tv3);
                tv4 = itemView.findViewById(R.id.tv4);
                tv5 = itemView.findViewById(R.id.tv5);
                tv6 = itemView.findViewById(R.id.tv6);
                tv7 = itemView.findViewById(R.id.tv7);
                tv8 = itemView.findViewById(R.id.tv8);
                tv9 = itemView.findViewById(R.id.tv9);
                flowLiner1 = itemView.findViewById(R.id.flowLiner1);
                flowLiner2 = itemView.findViewById(R.id.flowLiner2);


                onClickView = itemView.findViewById(R.id.onClickView);
            }

        }


    }*/


    //通过监听viewpager滑动改变Checked的属性
    private void setListeners() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tv1.setTextColor(getResources().getColor(R.color.txt_c333));
                        tv2.setTextColor(getResources().getColor(R.color.txt_lv2));

                        tv1.setTextSize(18);
                        tv2.setTextSize(16);

                        tv1.setTypeface(null, Typeface.BOLD);
                        tv2.setTypeface(null, Typeface.NORMAL);


                        break;
                    case 1:
                        tv2.setTextColor(getResources().getColor(R.color.txt_c333));
                        tv1.setTextColor(getResources().getColor(R.color.txt_lv2));

                        tv1.setTextSize(16);
                        tv2.setTextSize(18);

                        tv2.setTypeface(null, Typeface.BOLD);
                        tv1.setTypeface(null, Typeface.NORMAL);

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


    private PopupWindow popupWindow1;
    private View popupView1;
    private TranslateAnimation animation1;
    /*在子日记的pop上 点击发布 在次弹出发布页面*/

    /**
     * 设置手机屏幕亮度变暗
     */
    private void lightoff() {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.3f;
        getActivity().getWindow().setAttributes(lp);
    }

    /**
     * 设置手机屏幕亮度显示正常
     */
    private void lighton() {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 1f;
        getActivity().getWindow().setAttributes(lp);
    }

    private void AllGangwei() {
        if (popupWindow1 == null) {
            popupView1 = View.inflate(getActivity(), R.layout.pop_layout_allgangwei_list22, null);
            FlowLiner flowLiner = popupView1.findViewById(R.id.flowLiner);
            popupWindow1 = new PopupWindow(popupView1, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    lighton();
                    popupWindow1 = null;
                }
            });


            getShaiXuan(flowLiner);


            // 设置背景图片， 必须设置，不然动画没作用
            popupWindow1.setBackgroundDrawable(new BitmapDrawable());
            popupWindow1.setFocusable(true);
            // 设置点击popupwindow外屏幕其它地方消失
            popupWindow1.setOutsideTouchable(true);
            // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
            animation1 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
            animation1.setInterpolator(new AccelerateInterpolator());
            animation1.setDuration(200);
            popupView1.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow1.dismiss();
                    lighton();
                    popupWindow1 = null;
                }
            });
        }

        // 在点击之后设置popupwindow的销毁
        if (popupWindow1.isShowing()) {
            popupWindow1.dismiss();
            lighton();
            popupWindow1 = null;
        }

        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
        popupWindow1.showAtLocation(getActivity().findViewById(R.id.setting), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //popupWindow1.showAtLocation(findViewById(R.id.setting), Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupView1.startAnimation(animation1);

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
            case R.id.llSearchView:
                //搜索
                startActivity(new Intent(getActivity(), HRSearchActivity.class));
                break;
            case R.id.addView:
                //选择城市
                startActivity(new Intent(getActivity(), SelectCityPro1ListActivity.class));
                break;
            case R.id.selectView:
                //切换
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
                if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                    startActivity(new Intent(getActivity(), MyShouCangRenActivity.class));
                } else {
                    ToastFactory.getToast(getActivity(), "请先登录").show();
                    startActivity(new Intent(getActivity(), Login1PhoneCodeActivity.class));
                    return;
                }

                break;
            case R.id.change_role_img:
                EventBus.getDefault().post(new MessageEvent(14, null, null, null, null, null, null));
                break;
        }
    }

    private void getShaiXuan(final FlowLiner flowLiner) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("type", "2");
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.gongSiAllZhiWei, params, new BaseCallback<GongSiZhiWeiBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, GongSiZhiWeiBean resultBean) {
                List<String> flowData = new ArrayList<>();
                final List<String> flowIDData = new ArrayList<>();
                List<GongSiZhiWeiBean.DataListBean> dataList = resultBean.getDataList();

                for (int i = 0; i < dataList.size(); i++) {
                    flowData.add(dataList.get(i).getName());
                    flowIDData.add(dataList.get(i).getId());
                }

                flowLiner.removeAllViews();
                for (int i = 0; i < flowData.size(); i++) {
                    final TextView radioButton = new TextView(getActivity());
                    FlowLiner.LayoutParams layoutParams = new FlowLiner.LayoutParams(FlowLiner.LayoutParams.WRAP_CONTENT, FlowLiner.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0, 0, ViewUtil.dp2px(getActivity(), 10), ViewUtil.dp2px(getActivity(), 10));
                    radioButton.setLayoutParams(layoutParams);
                    final String str = flowData.get(i);
                    radioButton.setText(str);
                    radioButton.setGravity(Gravity.CENTER);
                    radioButton.setTextSize(13);
                    radioButton.setPadding(ViewUtil.dp2px(getActivity(), 10), ViewUtil.dp2px(getActivity(), 6), ViewUtil.dp2px(getActivity(), 10), ViewUtil.dp2px(getActivity(), 6));
                    radioButton.setTextColor(getActivity().getResources().getColorStateList(R.color.radio_text_selector_primary_4d4d4d));
                    //radioButton.setBackgroundResource(R.drawable.search_selector);
                    radioButton.setBackgroundResource(R.drawable.button_shape03);
                    radioButton.setFocusable(true);
                    final int finalI = i;
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String idName = flowIDData.get(finalI);
                            Log.i(TAG, "onClick: 名称和ID " + idName);
                            EventBus.getDefault().post(new MessageEvent(12, idName, null, null, null, null, null));
                            popupWindow1.dismiss();

                        }
                    });
                    flowLiner.addView(radioButton);
                }


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }


    //-------------------------TODO 卡片----------------------------------------
    //职位分页列表求职者
    private void getDataList(String dataType, String name, String pageNo, String pageSize) {
        Log.e(TAG, "getdata");
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("dataType", dataType);
        params.put("name", name);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.HRSouRenCai, params, new SpotsCallBack<RenCaiListBean1>(getActivity()) {
            @Override
            public void onSuccess(Response response, RenCaiListBean1 resultBean) {
                if (resultBean.getDataList() != null) {
                    totalPage = resultBean.getTotalPage();
                    if (resultBean.getDataList().size() == 0) {
                        //没有数据
                        dituImage.setVisibility(View.GONE);
                    } else {
                        //有数据
                        NewAllList.clear();
                        NewAllList.addAll(resultBean.getDataList());
                        hrCardAdapter.notifyDataSetChanged();
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
//        mCardAdapter = new RenCaiCardsAdapter(getContext(), list);
//        mDragCardsView.setAdapter(mCardAdapter);
//        mDragCardsView.setOnItemClickListener(new DragCardsView.OnItemClickListener() {
//            @Override
//            public void onItemClicked(int itemPosition, Object dataObject) {
//
//                //ToastFactory.getToast(getActivity(), "处理点击事件" + list1.get(currentPositon).getId()).show();
//                //TODO  处理点击事件
//                Intent intent = new Intent(getActivity(), RenCaiDetailActivity.class);
//                intent.putExtra("rid", list1.get(currentPositon).getId());
//                startActivity(intent);
//
//            }
//        });
//        mDragCardsView.setFlingListener(new DragCardsView.onDragListener() {
//
//            @Override
//            public void removeFirstObjectInAdapter(boolean isLeft) {
//                // TODO Auto-generated method stub
//                if (isLeft) {
//                    browseyujian(currentPositon);
//                    next();
//                } else {
//                    sendLove(currentPositon);
//                    next();
//                }
//                if (list.size() > 0) {
//                    list.remove(0);
//                }
//                mCardAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onSelectLeft(double distance) {
//                // TODO Auto-generated method stub
//
//
//            }
//
//
//            @Override
//            public void onSelectRight(double distance) {
//                // TODO Auto-generated method stub
//
//            }
//
//
//            @Override
//            public void onCardReturn() {
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void onCardMoveDistance(double distance) {
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void onAdapterAboutToEmpty(int itemsInAdapter) {
//                // TODO Auto-generated method stub
////                ToastUtil.show("需要补牌了");
//
//            }
//        });
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
    public void onResume() {
        super.onResume();
        if (fl_list != null) {
            fl_list.setImageResource(R.drawable.shoucang_hezi);
        }
    }


    private void xiHuan(String pid) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("rid", pid);
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.ShouCangRenCai, params, new BaseCallback<PhoneStateBean>() {
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
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("rid", pid);
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.HR_PingBi_renCaiDetail, params, new BaseCallback<PhoneStateBean>() {
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
    //-------------------------TODO 卡片----------------------------------------


}
