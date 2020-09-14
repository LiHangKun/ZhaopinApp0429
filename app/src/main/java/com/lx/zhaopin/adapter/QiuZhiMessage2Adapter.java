package com.lx.zhaopin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.QiuZhiFeedBean;
import com.lx.zhaopin.view.TimeUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QiuZhiMessage2Adapter extends RecyclerView.Adapter<QiuZhiMessage2Adapter.ViewHolder> {



    private List<QiuZhiFeedBean.DataListBean> mData;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public QiuZhiMessage2Adapter() {
    }

    public QiuZhiMessage2Adapter(Context context, List<QiuZhiFeedBean.DataListBean> allList) {
        mContext = context;
        mData = allList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_qiuzhi_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                .load(mData.get(i).getCompany().getLogo()).into(viewHolder.roundedImageView);
        viewHolder.tv1.setText(mData.get(i).getCompany().getName());
        viewHolder.tv2.setText(mData.get(i).getLastChatDate());

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(mData.get(i).getLastChatDate());
            long time = date.getTime();
            String yue = TimeUtils.getMonthTime_(time);
            String day = TimeUtils.getDayTime_(time);
            String minite = TimeUtils.getHourTime(time);
            viewHolder.tv2.setText(yue + "月" + day + "日 "+ minite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mData.get(i).getUnreadCount().equals("0")) {
            viewHolder.tv4.setVisibility(View.GONE);
        } else {
            viewHolder.tv4.setText(mData.get(i).getUnreadCount());
        }

        String chatStatus = mData.get(i).getChatStatus();
        //聊天状态 chatStatus  1 已沟通 2 已投递 3 已预约 4 待面试 5 已到达 6 面试已取消 7 已反馈 8 同意入职 9 拒绝入职
        if(!TextUtils.isEmpty(chatStatus)){
            int state=Integer.valueOf(chatStatus);
        }

        switch (chatStatus) {
            case "1":
                viewHolder.positionName.setText("已沟通");
                viewHolder.etcImg.setVisibility(View.GONE);
                viewHolder.firstLayout.setVisibility(View.GONE);
                viewHolder.secondLayout.setVisibility(View.GONE);
                break;
            case "2":
                viewHolder.positionName.setText("已投递");
                viewHolder.etcImg.setVisibility(View.GONE);
                viewHolder.firstLayout.setVisibility(View.VISIBLE);
                viewHolder.secondLayout.setVisibility(View.GONE);
                viewHolder.first1.setText("已沟通");
                break;
            case "3":
                viewHolder.positionName.setText("已预约");
                viewHolder.etcImg.setVisibility(View.GONE);
                viewHolder.firstLayout.setVisibility(View.VISIBLE);
                viewHolder.secondLayout.setVisibility(View.VISIBLE);
                viewHolder.first1.setText("已沟通");
                viewHolder.second.setText("已投递");
                break;
            case "4":
                viewHolder.positionName.setText("待面试");
                viewHolder.etcImg.setVisibility(View.VISIBLE);
                viewHolder.firstLayout.setVisibility(View.VISIBLE);
                viewHolder.secondLayout.setVisibility(View.VISIBLE);
                viewHolder.first1.setText("已投递");
                viewHolder.second.setText("已预约");
                break;
            case "5":
                viewHolder.positionName.setText("已到达");
                viewHolder.etcImg.setVisibility(View.VISIBLE);
                viewHolder.firstLayout.setVisibility(View.VISIBLE);
                viewHolder.secondLayout.setVisibility(View.VISIBLE);
                viewHolder.first1.setText("已预约");
                viewHolder.second.setText("待面试");
                break;
            case "6":
                viewHolder.positionName.setText("已取消");
                viewHolder.etcImg.setVisibility(View.VISIBLE);
                viewHolder.firstLayout.setVisibility(View.VISIBLE);
                viewHolder.secondLayout.setVisibility(View.VISIBLE);
                viewHolder.first1.setText("已预约");
                viewHolder.second.setText("待面试");
                break;
            case "7":
                viewHolder.positionName.setText("已反馈");
                viewHolder.etcImg.setVisibility(View.VISIBLE);
                viewHolder.firstLayout.setVisibility(View.VISIBLE);
                viewHolder.secondLayout.setVisibility(View.VISIBLE);
                viewHolder.first1.setText("待面试");
                viewHolder.second.setText("已取消");
                break;
            case "8":
                viewHolder.positionName.setText("同意入职");
                viewHolder.etcImg.setVisibility(View.VISIBLE);
                viewHolder.firstLayout.setVisibility(View.VISIBLE);
                viewHolder.secondLayout.setVisibility(View.VISIBLE);
                viewHolder.first1.setText("待面试");
                viewHolder.second.setText("已到达");
                break;
            case "9":
                viewHolder.positionName.setText("拒绝入职");
                viewHolder.etcImg.setVisibility(View.VISIBLE);
                viewHolder.firstLayout.setVisibility(View.VISIBLE);
                viewHolder.secondLayout.setVisibility(View.VISIBLE);
                viewHolder.first1.setText("待面试");
                viewHolder.second.setText("已到达");
                break;
        }

        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClickListener(i, mData.get(i).getId());
                }
            }
        });


        viewHolder.llView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemLongClick(i, mData.get(i).getId());
                }
                return false;//是 true 才可以  false 不可以
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.roundedImageView)
        RoundedImageView roundedImageView;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.tv3)
        TextView tv3;
        @BindView(R.id.positionName)
        TextView positionName;
        @BindView(R.id.tv4)
        TextView tv4;
        @BindView(R.id.llView)
        LinearLayout llView;
        @BindView(R.id.first1)
        TextView first1;
        @BindView(R.id.first_layout)
        LinearLayout firstLayout;
        @BindView(R.id.second)
        TextView second;
        @BindView(R.id.second_layout)
        LinearLayout secondLayout;
        @BindView(R.id.etc_img)
        ImageView etcImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void OnItemClickListener(int i, String id);

        void onItemLongClick(int i, String id);
    }

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        onItemClickListener = OnItemClickListener;
    }
}
