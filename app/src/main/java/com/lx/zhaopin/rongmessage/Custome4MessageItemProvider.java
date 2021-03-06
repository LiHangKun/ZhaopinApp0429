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

import com.google.gson.Gson;
import com.lx.zhaopin.R;
import com.makeramen.roundedimageview.RoundedImageView;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

//HR 看到求职者给我发送的一份求职简历,HR的操作是拒绝或者同意  showPortrait = false,
@ProviderTag(messageContent = Custome4Message.class, centerInHorizontal = true, showSummaryWithName = false)
public class Custome4MessageItemProvider extends IContainerItemProvider.MessageProvider<Custome4Message> {

    private Context mContext;
    private static final String TAG = "Custome4MessageItemProv";

    //llView1 是  对方给您发了一份求职简历  带拒绝和同意 tvTitle  tvCancel  tvOk
    //llView2 您已接收对方简历  tvTitle2
    //llView3 所有的灰底白字  tvTitle3
    //llView4 郑州立信科技向您发出面试邀约,点击查看  llViewGongSi  roundedImageView  tvTitle4  tvCancel4  tvOk4


    @Override
    public void bindView(View view, int i, final Custome4Message custome4Message, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();

        //  type 0 同意  1 拒绝

        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {
            //这是发送方
            Log.i(TAG, "onClick: 简历ID  + 这是发送方");
            //  type 1 同意  0 拒绝

            if (custome4Message.getContent() != null) {
                Gson gson = new Gson();
                final RongMessageInBean rongMessageInBean = gson.fromJson(custome4Message.getContent(), RongMessageInBean.class);

                try {
                    String type = rongMessageInBean.getType();
                    switch (type) {
                        case "0":
                            holder.llView3.setVisibility(View.VISIBLE);
                            holder.llView1.setVisibility(View.GONE);
                            holder.llView2.setVisibility(View.GONE);
                            holder.llView4.setVisibility(View.GONE);
                            holder.tvTitle3.setText("您已拒绝对方的面试邀请");
                            break;
                        case "1":
                            holder.llView2.setVisibility(View.VISIBLE);
                            holder.llView1.setVisibility(View.GONE);
                            holder.llView3.setVisibility(View.GONE);
                            holder.llView4.setVisibility(View.GONE);
                            holder.tvTitle2.setText("您已接受面试邀约");
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        } else if (uiMessage.getMessageDirection() == Message.MessageDirection.RECEIVE) {
            //这是接收方
            Log.i(TAG, "onClick: 简历ID  + 这是接收方");

            if (custome4Message.getContent() != null) {
                Gson gson = new Gson();
                final RongMessageInBean rongMessageInBean = gson.fromJson(custome4Message.getContent(), RongMessageInBean.class);
                //  type 1 同意  0 拒绝
                try {
                    String type = rongMessageInBean.getType();
                    switch (type) {
                        case "0":
                            holder.llView3.setVisibility(View.VISIBLE);
                            holder.llView1.setVisibility(View.GONE);
                            holder.llView2.setVisibility(View.GONE);
                            holder.llView4.setVisibility(View.GONE);
                            holder.tvTitle3.setText("对方已拒绝您的面试邀请");
                            break;
                        case "1":
                            holder.llView2.setVisibility(View.VISIBLE);
                            holder.llView1.setVisibility(View.GONE);
                            holder.llView3.setVisibility(View.GONE);
                            holder.llView4.setVisibility(View.GONE);
                            holder.tvTitle2.setText("对方已接受您的面试邀约");
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }


    }

    @Override
    public Spannable getContentSummary(Custome4Message custome4Message) {
        return null;
    }

    @Override
    public void onItemClick(View view, int i, Custome4Message custome4Message, UIMessage uiMessage) {

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
