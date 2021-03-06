package com.lx.zhaopin.hradapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.HRMessage2Bean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HRMessage2Adapter extends RecyclerView.Adapter<HRMessage2Adapter.ViewHolder> {


    private List<HRMessage2Bean.DataListBean> mData;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public HRMessage2Adapter() {
    }

    public HRMessage2Adapter(Context context, List<HRMessage2Bean.DataListBean> allList) {
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
                .load(mData.get(i).getJobhunter().getAvatar()).into(viewHolder.roundedImageView);
        viewHolder.tv1.setText(mData.get(i).getJobhunter().getName());
        viewHolder.tv2.setText(mData.get(i).getLastChatDate());
        viewHolder.positionName.setText(mData.get(i).getPositionName());

        if (mData.get(i).getUnreadCount().equals("0")) {
            viewHolder.tv4.setVisibility(View.GONE);
        } else {
            viewHolder.tv4.setText(mData.get(i).getUnreadCount());
        }

        String chatStatus = mData.get(i).getChatStatus();
        //聊天状态 chatStatus  1 已沟通 2 已投递 3 已预约 4 待面试 5 已到达 6 面试已取消 7 已反馈 8 同意入职 9 拒绝入职

        switch (chatStatus) {
            case "1":
                viewHolder.tv3.setText("已沟通");
                break;
            case "2":
                viewHolder.tv3.setText("已投递");
                break;
            case "3":
                viewHolder.tv3.setText("已预约");
                break;
            case "4":
                viewHolder.tv3.setText("待面试");
                break;
            case "5":
                viewHolder.tv3.setText("已到达");
                break;
            case "6":
                viewHolder.tv3.setText("面试已取消");
                break;
            case "7":
                viewHolder.tv3.setText("已反馈");
                break;
            case "8":
                viewHolder.tv3.setText("同意入职");
                break;
            case "9":
                viewHolder.tv3.setText("拒绝入职");
                break;
        }

        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClickListener(i,mData.get(i).getId());
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
        @BindView(R.id.tv4)
        TextView tv4;
        @BindView(R.id.positionName)
        TextView positionName;
        @BindView(R.id.llView)
        LinearLayout llView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void OnItemClickListener(int i,String id);
        void onItemLongClick(int i, String id);
    }

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        onItemClickListener = OnItemClickListener;
    }
}
