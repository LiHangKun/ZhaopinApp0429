package com.lx.zhaopin.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.PingJiaImageAdapter;
import com.lx.zhaopin.base.BaseActivity;
import com.lx.zhaopin.bean.ShouYeQiuZhiZheBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.http.SpotsCallBack;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;
import okhttp3.Response;

public class TestTanTanActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private String cityId = "";
    private int nowPageIndex = 1;
    private int totalPage = 1;
    private static final String TAG = "TestTanTanActivity";
    private List<ShouYeQiuZhiZheBean.DataListBean> allList;
    private MyAdapter myAdapter;

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContainer(R.layout.testtantan_activity);
        recyclerView = findViewById(R.id.recyclerView);

        allList = new ArrayList<>();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), allList);
        cardCallback.setOnSwipedListener(new OnSwipeListener<ShouYeQiuZhiZheBean.DataListBean>() {

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
            public void onSwiped(RecyclerView.ViewHolder viewHolder, ShouYeQiuZhiZheBean.DataListBean o, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                //myHolder.dislikeImageView.setAlpha(0f);
                //myHolder.likeImageView.setAlpha(0f);

                if (direction == CardConfig.SWIPED_LEFT) {
                    Toast.makeText(TestTanTanActivity.this, "11111111111", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TestTanTanActivity.this, "222222222222222222222222", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(TestTanTanActivity.this, "加载更多数据", Toast.LENGTH_SHORT).show();
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nowPageIndex++;
                        getDataList("1", "", SPTool.getSessionValue(AppSP.sStringJ), SPTool.getSessionValue(AppSP.sStringW), cityId, String.valueOf(nowPageIndex), AppSP.pageCount);
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                }, 1000L);
            }

        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);

        getDataList("1", "", SPTool.getSessionValue(AppSP.sStringJ), SPTool.getSessionValue(AppSP.sStringW), cityId, String.valueOf(nowPageIndex), AppSP.pageCount);
    }

    private void getDataList(String dataType, String name, String lng, String lat, final String cityId, String pageNo, String pageSize) {

        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("dataType", dataType);
        params.put("name", name);
        params.put("lng", lng);
        params.put("lat", lat);
        params.put("cityId", cityId);
        params.put("pageNo", pageNo);
        params.put("pageSize", "200");
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.zhiWeiPageList, params, new SpotsCallBack<ShouYeQiuZhiZheBean>(mContext) {
            @Override
            public void onSuccess(Response response, ShouYeQiuZhiZheBean resultBean) {
                Log.e(TAG, "onSuccess: http 收到消息更新卡片");
                if (resultBean.getDataList() != null) {
                    totalPage = resultBean.getTotalPage();
                    if (resultBean.getDataList().size() == 0) {
                        //没有数据
                    } else {
                        //有数据
                        allList.addAll(resultBean.getDataList());
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });


    }


    private class MyAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            RoundedImageView avatarImageView = ((MyViewHolder) holder).roundedImageView;
            ImageView imageState = ((MyViewHolder) holder).imageState;
            TextView tv1 = ((MyViewHolder) holder).tv1;
            TextView tv2 = ((MyViewHolder) holder).tv2;
            TextView tv3 = ((MyViewHolder) holder).tv3;
            TextView tv4 = ((MyViewHolder) holder).tv4;
            TextView tv5 = ((MyViewHolder) holder).tv5;
            TextView tv6 = ((MyViewHolder) holder).tv6;
            TextView tv7 = ((MyViewHolder) holder).tv7;
            TextView tv8 = ((MyViewHolder) holder).tv8;
            TextView tv9 = ((MyViewHolder) holder).tv9;
            TextView tv10 = ((MyViewHolder) holder).tv10;
            TextView tv11 = ((MyViewHolder) holder).tv11;
            TextView tv12 = ((MyViewHolder) holder).tv12;
            RecyclerView recyclerViewGongSi = ((MyViewHolder) holder).recyclerViewGongSi;


            Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                    .error(R.mipmap.imageerror)).load(allList.get(position).getCompany().getLogo()).into(avatarImageView);

            tv1.setText(allList.get(position).getName());

            String positionType = allList.get(position).getPositionType();
            switch (positionType) {
                case "1":
                case "2":
                    imageState.setVisibility(View.INVISIBLE);
                    tv1.setTextColor(mContext.getResources().getColor(R.color.txt_lv7));
                    break;
                case "3":
                    imageState.setVisibility(View.VISIBLE);
                    tv1.setTextColor(mContext.getResources().getColor(R.color.white));
                    break;

            }
            tv2.setText(allList.get(position).getMinSalary() + "K - " + allList.get(position).getMaxSalary() + "K");
            tv3.setText(allList.get(position).getCity().getName() + allList.get(position).getDistrict().getName());
            tv4.setText(allList.get(position).getExperienceYear().getName() );
            tv5.setText(allList.get(position).getEducation().getName());
            tv6.setText(allList.get(position).getDuty());
            tv7.setText(allList.get(position).getCity().getName() + allList.get(position).getDistrict().getName() + allList.get(position).getLocation());
            tv8.setText(allList.get(position).getCompany().getName());
            tv9.setText(allList.get(position).getCompany().getFinancingName());
            tv10.setText(allList.get(position).getCompany().getIndustry2Name());
            tv11.setText(allList.get(position).getCompany().getStaffNum());
            tv12.setText(allList.get(position).getCompany().getService());

            //列表的展示形式
            List<String> gongSiImageList = new ArrayList<>();
            String images = allList.get(position).getCompany().getImages();
            String[] split = images.split("\\|");
            for (int i = 0; i < split.length; i++) {
                gongSiImageList.add(split[i]);
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//
            recyclerViewGongSi.setLayoutManager(linearLayoutManager);
            PingJiaImageAdapter pingJiaImageAdapter = new PingJiaImageAdapter(mContext, gongSiImageList);
            recyclerViewGongSi.setAdapter(pingJiaImageAdapter);
            pingJiaImageAdapter.setOnItemClickListener(new PingJiaImageAdapter.OnItemClickListener() {
                @Override
                public void OnItemClickListener(int position) {
                    //showImage(new ImageView(getActivity()), position);
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
            ImageView imageState;
            RecyclerView recyclerViewGongSi;
            TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12;

            MyViewHolder(View itemView) {
                super(itemView);
                roundedImageView = itemView.findViewById(R.id.roundedImageView);
                imageState = itemView.findViewById(R.id.imageState);
                tv1 = itemView.findViewById(R.id.tv1);
                tv2 = itemView.findViewById(R.id.tv2);

                tv3 = itemView.findViewById(R.id.tv3);
                tv4 = itemView.findViewById(R.id.tv4);
                tv5 = itemView.findViewById(R.id.tv5);
                tv6 = itemView.findViewById(R.id.tv6);
                tv7 = itemView.findViewById(R.id.tv7);

                tv8 = itemView.findViewById(R.id.tv8);
                tv9 = itemView.findViewById(R.id.tv9);
                tv10 = itemView.findViewById(R.id.tv10);
                tv11 = itemView.findViewById(R.id.tv11);
                tv12 = itemView.findViewById(R.id.tv12);
                recyclerViewGongSi = itemView.findViewById(R.id.recyclerView);
            }

        }
    }


}
