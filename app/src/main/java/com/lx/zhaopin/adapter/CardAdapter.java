package com.lx.zhaopin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lin.cardlib.SwipeTouchLayout;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.GangWeiDetailActivity;
import com.lx.zhaopin.bean.ShouYeQiuZhiZheBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 求职者看到的界面
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {


    private List<ShouYeQiuZhiZheBean.DataListBean> allList;
    private RequestOptions mRequestOptions;
    private final Context mContext;

    public CardAdapter(Context context, List<ShouYeQiuZhiZheBean.DataListBean> cardBeanList) {
        mContext = context;
        allList = cardBeanList;
        /*mRequestOptions = new RequestOptions();
        mRequestOptions.placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror).diskCacheStrategy(DiskCacheStrategy.NONE);*/
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {
        /*final CardBean bean = mCardBeanList.get(position);
        Glide.with(holder.itemView).load(bean.getUrl()).apply(mRequestOptions).into(holder.img);
        holder.text.setText(bean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "click " + bean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(),"点击了 img",Toast.LENGTH_SHORT).show();
            }
        });*/


        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror)
                .error(R.mipmap.imageerror)).load(allList.get(position).getCompany().getLogo()).into(holder.roundedImageView);


        String positionType = allList.get(position).getPositionType();
        switch (positionType) {
            case "1":
            case "2":
                holder.imageState.setVisibility(View.INVISIBLE);
                holder.tv1Cui.setBackground(null);
                holder.tv1.setVisibility(View.GONE);
                holder.tv1Cui.setVisibility(View.VISIBLE);
                holder.tv1Cui.setText(allList.get(position).getName());
                holder.tv1Cui.setTextColor(mContext.getResources().getColor(R.color.txt_lv7));
                break;
            case "3":
                holder.imageState.setVisibility(View.VISIBLE);
                holder.tv1Cui.setVisibility(View.GONE);
                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv1.setText(allList.get(position).getName());
                holder.tv1.setBackground(mContext.getDrawable(R.drawable.biaoqian));
                holder.tv1.setTextColor(mContext.getResources().getColor(R.color.white));
                break;

        }
        holder.tv2.setText(allList.get(position).getMinSalary() + "K - " + allList.get(position).getMaxSalary() + "K");
        holder.tv3.setText(allList.get(position).getCity().getName() + allList.get(position).getDistrict().getName());
        holder.tv4.setText(allList.get(position).getExperienceYear().getName());
        holder.tv5.setText(allList.get(position).getEducation().getName());
        holder.tv6.setText(allList.get(position).getDuty());
        holder.tv7.setText(allList.get(position).getCity().getName() + allList.get(position).getDistrict().getName() + allList.get(position).getLocation());
        holder.tv8.setText(allList.get(position).getCompany().getName());
        holder.tv9.setText(allList.get(position).getCompany().getFinancingName());
        holder.tv10.setText(allList.get(position).getCompany().getIndustry2Name());
        holder.tv11.setText(allList.get(position).getCompany().getStaffNum());
        holder.tv12.setText(allList.get(position).getCompany().getService());

        //列表的展示形式
        List<String> gongSiImageList = new ArrayList<>();
        String images = allList.get(position).getCompany().getImages();
        String[] split = images.split("\\|");
        for (int i = 0; i < split.length; i++) {
            gongSiImageList.add(split[i]);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        PingJiaImageAdapter pingJiaImageAdapter = new PingJiaImageAdapter(mContext, gongSiImageList);
        holder.recyclerView.setAdapter(pingJiaImageAdapter);
        pingJiaImageAdapter.setOnItemClickListener(new PingJiaImageAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                //showImage(new ImageView(getActivity()), position);
            }
        });


        holder. tv1.setText(allList.get(position).getName());
        holder.relView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, GangWeiDetailActivity.class);
                intent.putExtra("pid", allList.get(position).getId());
                mContext.startActivity(intent);
            }
        });

    }

    private static final String TAG = "CardAdapter";

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: 集合" + allList.size());
        return allList == null ? 0 : allList.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv3)
        TextView tv3;
        @BindView(R.id.tv4)
        TextView tv4;
        @BindView(R.id.tv5)
        TextView tv5;
        @BindView(R.id.tv6)
        TextView tv6;
        @BindView(R.id.tv7)
        TextView tv7;
        @BindView(R.id.roundedImageView)
        RoundedImageView roundedImageView;
        @BindView(R.id.tv8)
        TextView tv8;
        @BindView(R.id.tv9)
        TextView tv9;
        @BindView(R.id.tv10)
        TextView tv10;
        @BindView(R.id.tv11)
        TextView tv11;
        @BindView(R.id.llViewGongSi)
        LinearLayout llViewGongSi;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        @BindView(R.id.gongSiImage1)
        ImageView gongSiImage1;
        @BindView(R.id.gongSiImage2)
        ImageView gongSiImage2;
        @BindView(R.id.allImageView)
        LinearLayout allImageView;
        @BindView(R.id.tv12)
        TextView tv12;
        @BindView(R.id.llViewALL)
        LinearLayout llViewALL;
        @BindView(R.id.imageState)
        ImageView imageState;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.tv1Cui)
        TextView tv1Cui;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.onClickView)
        SwipeTouchLayout onClickView;

        @BindView(R.id.relView1)
        RelativeLayout relView1;


        public CardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
