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
import com.lx.zhaopin.activity.RenCaiDetailActivity;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

//HR 看到求职者给我发送的一份求职简历,HR的操作是拒绝或者同意

//llView1 是  对方给您发了一份求职简历  带拒绝和同意 tvTitle  tvCancel  tvOk
//llView2 您已接收对方简历  tvTitle2
//llView3 所有的灰底白字  tvTitle3
//llView4 郑州立信科技向您发出面试邀约,点击查看  llViewGongSi  roundedImageView  tvTitle4  tvCancel4  tvOk4


@ProviderTag(messageContent = Custome01Message.class, centerInHorizontal = true, showSummaryWithName = false)
public class Custome01MessageItemProvider extends IContainerItemProvider.MessageProvider<Custome01Message> {

    private Context mContext;
    private static final String TAG = "Custome01MessageItemPro";

    @Override
    public void bindView(View view, int i, final Custome01Message custome01Message, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();

        holder.llView1.setVisibility(View.GONE);
        holder.llView2.setVisibility(View.GONE);
        holder.llView3.setVisibility(View.VISIBLE);
        holder.llView4.setVisibility(View.GONE);

        if (uiMessage.getMessageDirection() == Message.MessageDirection.RECEIVE) {
            //这是发送方
            Log.i(TAG, "onClick: 简历ID  + 这是发送方");
            if (custome01Message.getContent() != null) {
                holder.llView1.setVisibility(View.GONE);
                holder.llView2.setVisibility(View.GONE);
                holder.llView3.setVisibility(View.VISIBLE);
                holder.llView4.setVisibility(View.GONE);

                //您的简历已成功发送给HR
                holder.tvTitle3.setText("对方已同意,您的简历已发送给对方");

            }
        } else if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {
            //这是接收方
            Log.i(TAG, "onClick: 简历ID  + 这是接收方");

            if (custome01Message.getContent() != null) {
                Gson gson = new Gson();

                holder.llView1.setVisibility(View.GONE);
                holder.llView2.setVisibility(View.VISIBLE);
                holder.llView3.setVisibility(View.GONE);
                holder.llView4.setVisibility(View.GONE);


                final RongMessageInBean rongMessageInBean = gson.fromJson(custome01Message.getContent(), RongMessageInBean.class);

                //您的简历已成功发送给HR
                holder.tvTitle2.setText("您已接收对方简历,点击查看详情");
                holder.tvTitle2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, RenCaiDetailActivity.class);
                        intent.putExtra("rid", rongMessageInBean.getLocatin());
                        mContext.startActivity(intent);
                    }
                });

            }


        }


    }

    @Override
    public Spannable getContentSummary(Custome01Message custome01Message) {
        return null;
    }

    @Override
    public void onItemClick(View view, int i, Custome01Message custome01Message, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.aa_rong_message1_layout, null);
        ViewHolder holder = new ViewHolder();
        holder.llView1 = view.findViewById(R.id.llView1);
        holder.tvTitle = view.findViewById(R.id.tvTitle);
        holder.tvCancel = view.findViewById(R.id.tvCancel);
        holder.tvOk = view.findViewById(R.id.tvOk);
        holder.tvTitle2 = view.findViewById(R.id.tvTitle2);


        holder.llView2 = view.findViewById(R.id.llView2);
        holder.llView3 = view.findViewById(R.id.llView3);
        holder.llView4 = view.findViewById(R.id.llView4);
        holder.tvTitle3 = view.findViewById(R.id.tvTitle3);

        view.setTag(holder);

        return view;
    }

    class ViewHolder {
        LinearLayout llView1, llView2, llView3, llView4;
        TextView tvTitle, tvCancel, tvOk, tvTitle3, tvTitle2;
    }
}
