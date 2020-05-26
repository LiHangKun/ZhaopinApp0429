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

////所有人的灰底白字的提示 content 就是提示文本类型
//llView1 是  对方给您发了一份求职简历  带拒绝和同意 tvTitle  tvCancel  tvOk
//llView2 您已接收对方简历  tvTitle2
//llView3 所有的灰底白字  tvTitle3
//llView4 郑州立信科技向您发出面试邀约,点击查看  llViewGongSi  roundedImageView  tvTitle4  tvCancel4  tvOk4


@ProviderTag(messageContent = Custome3Message.class, showPortrait = false, centerInHorizontal = true, showSummaryWithName = false)
public class Custome3MessageItemProvider extends IContainerItemProvider.MessageProvider<Custome3Message> {

    private Context mContext;
    private static final String TAG = "Custome3MessageItemProv";

    @Override
    public void bindView(View view, int i, final Custome3Message custome3Message, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();

        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {
            //这是发送方
            Log.i(TAG, "onClick: 简历ID  + 这是发送方");

            if (custome3Message.getContent() != null) {
                Gson gson = new Gson();
                final RongMessageInBean rongMessageInBean = gson.fromJson(custome3Message.getContent(), RongMessageInBean.class);
                //  type 0 同意  1 拒绝
                String type = rongMessageInBean.getType();
                switch (type) {
                    case "1":
                        holder.llView3.setVisibility(View.VISIBLE);
                        holder.llView1.setVisibility(View.GONE);
                        holder.llView2.setVisibility(View.GONE);
                        holder.llView4.setVisibility(View.GONE);
                        holder.tvTitle3.setText("您已拒绝对方的简历");
                        break;
                    case "0":
                        //HR 同意
                        holder.llView2.setVisibility(View.VISIBLE);
                        holder.llView1.setVisibility(View.GONE);
                        holder.llView3.setVisibility(View.GONE);
                        holder.llView4.setVisibility(View.GONE);
                        holder.tvTitle2.setText("您已同意接收对方的简历,点击查看详情");

                        holder.tvTitle2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, RenCaiDetailActivity.class);
                                intent.putExtra("rid", rongMessageInBean.getLocatin());
                                mContext.startActivity(intent);
                            }
                        });

                        break;
                }
            }


        } else if (uiMessage.getMessageDirection() == Message.MessageDirection.RECEIVE) {
            //这是接收方
            if (custome3Message.getContent() != null) {
                Gson gson = new Gson();
                final RongMessageInBean rongMessageInBean = gson.fromJson(custome3Message.getContent(), RongMessageInBean.class);
                //  type 0 同意  1 拒绝
                String type = rongMessageInBean.getType();
                switch (type) {
                    case "1":
                        holder.llView3.setVisibility(View.VISIBLE);
                        holder.llView1.setVisibility(View.GONE);
                        holder.llView2.setVisibility(View.GONE);
                        holder.llView4.setVisibility(View.GONE);
                        holder.tvTitle3.setText("对方已拒绝您的简历");
                        break;
                    case "0":
                        holder.llView2.setVisibility(View.VISIBLE);
                        holder.llView1.setVisibility(View.GONE);
                        holder.llView3.setVisibility(View.GONE);
                        holder.llView4.setVisibility(View.GONE);
                        holder.tvTitle2.setText("对方已接收您的简历");
                        break;
                }
            }
        }


    }

    @Override
    public Spannable getContentSummary(Custome3Message custome3Message) {
        return null;
    }

    @Override
    public void onItemClick(View view, int i, Custome3Message custome3Message, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.aa_rong_message1_layout, null);
        ViewHolder holder = new ViewHolder();
        holder.tvTitle = view.findViewById(R.id.tvTitle);
        holder.tvTitle3 = view.findViewById(R.id.tvTitle3);
        holder.tvTitle2 = view.findViewById(R.id.tvTitle2);

        holder.llView1 = view.findViewById(R.id.llView1);
        holder.llView2 = view.findViewById(R.id.llView2);
        holder.llView3 = view.findViewById(R.id.llView3);
        holder.llView4 = view.findViewById(R.id.llView4);

        view.setTag(holder);

        return view;
    }

    class ViewHolder {
        TextView tvTitle, tvTitle3, tvTitle2;
        LinearLayout llView1, llView2, llView3, llView4;
    }
}
