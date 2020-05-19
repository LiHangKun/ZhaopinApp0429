package com.lx.zhaopin.hradapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.hr.HRSystemBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HRMessage3Adapter extends RecyclerView.Adapter<HRMessage3Adapter.ViewHolder> {


    private List<HRSystemBean.DataListBean> mData;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public HRMessage3Adapter() {
    }

    public HRMessage3Adapter(Context context, List<HRSystemBean.DataListBean> allList) {
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
        viewHolder.tv1.setText(mData.get(i).getTitle());
        viewHolder.tv2.setText(mData.get(i).getSendDate());
        viewHolder.tv3.setText(mData.get(i).getSubhead());
        viewHolder.unTV.setText("1");
        String MessageType = mData.get(i).getMessageType();
        //聊天状态 MessageType  1 系统消息 2 收到Offer 3 求职反馈 4 面试邀请
        //5 HR取消面试 6 面试超时 7 举报结果 8 求职者点击已到达 9 求职者同意沟通 10 求职者取消面试

        switch (MessageType) {
            case "1":
                //viewHolder.tv3.setText("已沟通");
                break;
            case "2":
                //viewHolder.tv3.setText("已投递");
                break;
            case "3":
                //viewHolder.tv3.setText("已预约");
                break;
            case "4":
                //viewHolder.tv3.setText("待面试");
                break;
            case "5":
                //viewHolder.tv3.setText("已到达");
                break;
            case "6":
                //viewHolder.tv3.setText("面试已取消");
                break;
            case "7":
                //viewHolder.tv3.setText("已反馈");
                break;
            case "8":
                //viewHolder.tv3.setText("同意入职");
                break;
            case "9":
                //viewHolder.tv3.setText("拒绝入职");
                break;
        }

        viewHolder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClickListener(viewHolder.llView, i, mData.get(i).getCorrelation(), mData.get(i).getMessageType(), mData.get(i).getUrl(), mData.get(i).getTitle(),mData.get(i).getId());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.roundedImageView)
        CircleImageView roundedImageView;
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
        void OnItemClickListener(View view, int i, String Correlation, String messageType, String url, String title,String messID);
    }

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        onItemClickListener = OnItemClickListener;
    }
}
