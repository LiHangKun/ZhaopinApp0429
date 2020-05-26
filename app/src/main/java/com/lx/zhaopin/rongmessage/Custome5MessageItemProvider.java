package com.lx.zhaopin.rongmessage;

//参考  CustomeGroupTipMessageItemProvider

import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.MianShiDetailType1Activity;
import com.lx.zhaopin.utils.ToastFactory;
import com.makeramen.roundedimageview.RoundedImageView;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

//HR 看到求职者给我发送的一份求职简历,HR的操作是拒绝或者同意  showPortrait = false,
@ProviderTag(messageContent = Custome5Message.class, centerInHorizontal = true, showSummaryWithName = false)
public class Custome5MessageItemProvider extends IContainerItemProvider.MessageProvider<Custome5Message> {

    private Context mContext;
    private static final String TAG = "Custome5MessageItemProv";

    //llView1 是  对方给您发了一份求职简历  带拒绝和同意 tvTitle  tvCancel  tvOk
    //llView2 您已接收对方简历  tvTitle2  对号和白底黑字
    //llView3 所有的灰底白字  tvTitle3
    //llView4 郑州立信科技向您发出面试邀约,点击查看  llViewGongSi  roundedImageView  tvTitle4  tvCancel4  tvOk4


    @Override
    public void bindView(View view, int i, final Custome5Message custome5Message, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();

        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {
            //这是发送方
            Log.i(TAG, "onClick: 简历ID  + 这是发送方");

            holder.llView3.setVisibility(View.VISIBLE);
            holder.llView1.setVisibility(View.GONE);
            holder.llView2.setVisibility(View.GONE);
            holder.llView4.setVisibility(View.GONE);
            holder.tvTitle2.setText("您已取消面试");


        } else if (uiMessage.getMessageDirection() == Message.MessageDirection.RECEIVE) {
            //这是接收方
            Log.i(TAG, "onClick: 简历ID  + 这是接收方");

            holder.llView2.setVisibility(View.VISIBLE);
            holder.llView1.setVisibility(View.GONE);
            holder.llView3.setVisibility(View.GONE);
            holder.llView4.setVisibility(View.GONE);
            holder.tvTitle2.setText("对方已取消面试,点击查看详情");

            if (custome5Message.getContent() != null) {
                Gson gson = new Gson();

                holder.llView2.setVisibility(View.VISIBLE);
                holder.llView1.setVisibility(View.GONE);
                holder.llView3.setVisibility(View.GONE);
                holder.llView4.setVisibility(View.GONE);


                final RongMessageInBean rongMessageInBean = gson.fromJson(custome5Message.getContent(), RongMessageInBean.class);

                holder.tvTitle2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastFactory.getToast(mContext, "进入面试详情" + rongMessageInBean.getId()).show();
                        Intent intent = new Intent(mContext, MianShiDetailType1Activity.class);
                        intent.putExtra("interviewId", rongMessageInBean.getId());
                        mContext.startActivity(intent);
                    }
                });

            }


        }


    }

    @Override
    public Spannable getContentSummary(Custome5Message custome5Message) {
        return null;
    }

    @Override
    public void onItemClick(View view, int i, Custome5Message custome5Message, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.aa_rong_message1_layout, null);
        ViewHolder holder = new ViewHolder();
        holder.tvTitle = view.findViewById(R.id.tvTitle);
        holder.tvTitle2 = view.findViewById(R.id.tvTitle2);
        holder.tvTitle3 = view.findViewById(R.id.tvTitle3);
        holder.tvCancel = view.findViewById(R.id.tvCancel);
        holder.tvOk = view.findViewById(R.id.tvOk);
        holder.roundedImageView = view.findViewById(R.id.roundedImageView);


        holder.llView1 = view.findViewById(R.id.llView1);
        holder.llView2 = view.findViewById(R.id.llView2);
        holder.llView3 = view.findViewById(R.id.llView3);
        holder.llView4 = view.findViewById(R.id.llView4);


        view.setTag(holder);

        return view;
    }

    class ViewHolder {
        LinearLayout llView1, llView2, llView3, llView4;
        RoundedImageView roundedImageView;
        TextView tvTitle, tvCancel, tvOk, tvTitle2, tvTitle3;
    }
}
