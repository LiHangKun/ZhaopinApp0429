package com.lx.zhaopin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.SystemMessageListBean;
import com.lx.zhaopin.view.XmCircleImageview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class Message3FragmentAdapter extends RecyclerView.Adapter<Message3FragmentAdapter.ViewHolder> {


    private static final String TAG = "Message3FragmentAdapter";
    private List<SystemMessageListBean.DataListBean> mData;
    private Context mContext;
    private OnItemClickListener itemClickListener;

    public Message3FragmentAdapter() {
    }

    public Message3FragmentAdapter(Context context, List<SystemMessageListBean.DataListBean> allList) {
        mContext = context;
        mData = allList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_systemmessage_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.tv2.setText(mData.get(i).getSendDate());
        viewHolder.tv3.setText(mData.get(i).getSubhead());


        String messageType = mData.get(i).getMessageType();
        // 1 系统消息 2 收到Offer 3 求职反馈 4 面试邀请 5 面试取消 6 面试超时  7 举报结果
        switch (messageType) {
            case "1":
                viewHolder.tv1.setText(mData.get(i).getTitle());
//                viewHolder.roundedImageView.setImageResource(R.drawable.xiaoxi_zhushou);
                break;
            case "2":
                viewHolder.tv1.setText(mData.get(i).getTitle());
//                viewHolder.roundedImageView.setImageResource(R.drawable.xiaoxi_xinshenq);
                break;
            case "3":
                viewHolder.tv1.setText(mData.get(i).getTitle());
//                viewHolder.roundedImageView.setImageResource(R.drawable.xiaoxi_xinshenq);
                break;
            case "4":
                viewHolder.tv1.setText(mData.get(i).getTitle());
//                viewHolder.roundedImageView.setImageResource(R.drawable.xiaoxi_zhushou);
                break;
            case "5":
                viewHolder.tv1.setText(mData.get(i).getTitle());
//                viewHolder.roundedImageView.setImageResource(R.drawable.xiaoxi_xinshenq);
                break;
            case "6":
                viewHolder.tv1.setText(mData.get(i).getTitle());
//                viewHolder.roundedImageView.setImageResource(R.drawable.xiaoxi_zhushou);
                break;
            case "7":
                viewHolder.tv1.setText(mData.get(i).getTitle());
//                viewHolder.roundedImageView.setImageResource(R.drawable.xiaoxi_zhushou);
                break;
        }

        try {
            String unread = mData.get(i).getUnread();
            Log.i(TAG, "onBindViewHolder: 是否已读" + unread + "<<<<<<-------");
            //1 是 0 否
            switch (unread) {
                case "0":
                    viewHolder.unTV.setVisibility(View.VISIBLE);
                    viewHolder.tv1.setTextColor(mContext.getResources().getColor(R.color.text_color));
                    break;
                case "1":
                    viewHolder.unTV.setVisibility(View.GONE);
                    viewHolder.tv1.setTextColor(mContext.getResources().getColor(R.color.zhiwei_location_text));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.OnItemClickListener(viewHolder.llView, i, mData.get(i).getCorrelation(), mData.get(i).getMessageType(), mData.get(i).getUrl(), mData.get(i).getTitle(), mData.get(i).getId());
                }
            }
        });


        viewHolder.llView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemLongClick(viewHolder.llView, i, mData.get(i).getCorrelation(), mData.get(i).getMessageType(), mData.get(i).getUrl(), mData.get(i).getTitle(), mData.get(i).getId());
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
        XmCircleImageview roundedImageView;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.tv3)
        TextView tv3;

        @BindView(R.id.unTV)
        TextView unTV;

        @BindView(R.id.llView)
        LinearLayout llView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void OnItemClickListener(View view, int i, String Correlation, String messageType, String url, String title, String messID);
        void onItemLongClick(View view, int i, String Correlation, String messageType, String url, String title, String messID);
    }

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        itemClickListener = OnItemClickListener;
    }

}
