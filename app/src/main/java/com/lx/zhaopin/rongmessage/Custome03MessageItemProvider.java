package com.lx.zhaopin.rongmessage;

//参考  CustomeGroupTipMessageItemProvider

import android.content.Context;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lx.zhaopin.R;
import com.lx.zhaopin.utils.ToastFactory;
import com.makeramen.roundedimageview.RoundedImageView;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

////所有人的灰底白字的提示 content 就是提示文本类型
//llView1 是  对方给您发了一份求职简历  带拒绝和同意 tvTitle  tvCancel  tvOk
//llView2 您已接收对方简历  tvTitle2
//llView3 所有的灰底白字  tvTitle3
//llView4 郑州立信科技向您发出面试邀约,点击查看  llViewGongSi  roundedImageView  tvTitle4  tvCancel4  tvOk4


@ProviderTag(messageContent = Custome03Message.class, showPortrait = false, centerInHorizontal = true, showSummaryWithName = false)
public class Custome03MessageItemProvider extends IContainerItemProvider.MessageProvider<Custome03Message> {

    private Context mContext;
    private static final String TAG = "Custome03MessageItemPro";

    @Override
    public void bindView(View view, int i, final Custome03Message custome03Message, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();

        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {
            //这是发送方
            Log.i(TAG, "onClick: 简历ID  + 这是发送方");
            holder.llView4.setVisibility(View.VISIBLE);
            holder.llView1.setVisibility(View.GONE);
            holder.llView3.setVisibility(View.GONE);
            holder.llView2.setVisibility(View.GONE);

            if (custome03Message.getContent() != null) {
                Gson gson = new Gson();
                final RongMessageInBean rongMessageInBean = gson.fromJson(custome03Message.getContent(), RongMessageInBean.class);
                Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                        .load(rongMessageInBean.getIcon()).into(holder.roundedImageView);
                holder.tvTitle4.setText(rongMessageInBean.getName());
                holder.llViewGongSi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastFactory.getToast(mContext, "进入公司详情---或者简历详情" + rongMessageInBean.getId()).show();
                    }
                });
                holder.tvCancel4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastFactory.getToast(mContext, "拒绝简历详情" + rongMessageInBean.getId()).show();
                    }
                });
                holder.tvOk4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastFactory.getToast(mContext, "拒绝简历详情" + rongMessageInBean.getId()).show();
                    }
                });

            }


        } else if (uiMessage.getMessageDirection() == Message.MessageDirection.RECEIVE) {
            //这是接收方
            Log.i(TAG, "onClick: 简历ID  + 这是接收方");
            holder.llView2.setVisibility(View.VISIBLE);
            holder.llView1.setVisibility(View.GONE);
            holder.llView3.setVisibility(View.GONE);
            holder.llView4.setVisibility(View.GONE);
            holder.tvTitle2.setText("您已向对方发送了面试邀请");
        }

        //所有人的灰底白字的提示 content 就是提示文本类型
       /* if (custome3Message.getContent() != null) {
            Gson gson = new Gson();
            RongMessageInBean rongMessageInBean = gson.fromJson(custome3Message.getContent(), RongMessageInBean.class);
            // 1 您的简历已成功发送给HR
            switch (rongMessageInBean.getType()) {
                case "1":
                    holder.tvTitle.setText("您的简历已成功发送给HR");
                    break;
                case "2":
                    holder.tvTitle.setText("对方已同意，您的简历已发送给对方");
                    break;
                case "3":
                    holder.tvTitle.setText("您已向HR发起面试邀约申请");
                    break;
                case "4":
                    holder.tvTitle.setText("您的面试邀约已取消");
                    break;
            }


        }*/


    }

    @Override
    public Spannable getContentSummary(Custome03Message custome03Message) {
        return null;
    }

    @Override
    public void onItemClick(View view, int i, Custome03Message custome03Message, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.aa_rong_message1_layout, null);
        ViewHolder holder = new ViewHolder();
        holder.tvTitle = view.findViewById(R.id.tvTitle);
        holder.tvTitle3 = view.findViewById(R.id.tvTitle3);
        holder.tvTitle4 = view.findViewById(R.id.tvTitle4);
        holder.tvCancel4 = view.findViewById(R.id.tvCancel4);
        holder.tvOk4 = view.findViewById(R.id.tvOk4);
        holder.tvTitle2 = view.findViewById(R.id.tvTitle2);

        holder.llView1 = view.findViewById(R.id.llView1);
        holder.llView2 = view.findViewById(R.id.llView2);
        holder.llView3 = view.findViewById(R.id.llView3);
        holder.llView4 = view.findViewById(R.id.llView4);
        holder.llViewGongSi = view.findViewById(R.id.llViewGongSi);
        holder.roundedImageView = view.findViewById(R.id.roundedImageView);

        view.setTag(holder);

        return view;
    }

    class ViewHolder {
        TextView tvTitle, tvTitle3, tvTitle4, tvCancel4, tvOk4, tvTitle2;
        LinearLayout llView1, llView2, llView3, llView4, llViewGongSi;
        RoundedImageView roundedImageView;
    }
}