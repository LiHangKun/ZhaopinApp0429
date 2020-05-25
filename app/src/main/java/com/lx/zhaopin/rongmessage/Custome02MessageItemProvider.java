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
import com.lx.zhaopin.utils.ToastFactory;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

//HR 或求职者 界面的白底消息, type 1,查看详情; 可以点击 type 2 您已向对方发送面试邀约,不可以点击; 3 您已接受面试邀请 不可以点击
@ProviderTag(messageContent = Custome02Message.class,  centerInHorizontal = true, showSummaryWithName = false)
public class Custome02MessageItemProvider extends IContainerItemProvider.MessageProvider<Custome02Message> {

    private Context mContext;
    private static final String TAG = "Custome1MessageItemProv";

    @Override
    public void bindView(View view, int i, final Custome02Message custome02Message, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();

        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {
            //这是发送方
            Log.i(TAG, "onClick: 简历ID  + 这是发送方");
        } else if (uiMessage.getMessageDirection() == Message.MessageDirection.RECEIVE) {
            //这是接收方
            Log.i(TAG, "onClick: 简历ID  + 这是接收方");
        }

        //  content   就是简历的ID
        if (custome02Message.getContent() != null) {
            //拒绝
            if (custome02Message.getType() != null) {
                Gson gson = new Gson();
                final RongMessageInBean rongMessageInBean = gson.fromJson(custome02Message.getContent(), RongMessageInBean.class);
                switch (rongMessageInBean.getType()) {
                    case "1":
                        holder.tvTitle2.setText("您已接收对方简历，点击查看详情");
                        holder.llView2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.i(TAG, "onClick: 同意的简历ID---------->" + rongMessageInBean.getId());
                                ToastFactory.getToast(mContext, "同意的简历ID ----------> 跳转到人才详情" + rongMessageInBean.getId()).show();
                            }
                        });
                        break;
                    case "2":
                        holder.llView2.setClickable(false);
                        holder.tvTitle2.setText("您已向对方发送面试邀约");
                        break;
                    case "3":
                        holder.tvTitle2.setText("您已接受面试邀约");
                        holder.llView2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.i(TAG, "onClick: 同意的简历ID---------->" + rongMessageInBean.getId());
                                ToastFactory.getToast(mContext, "同意的简历ID ----------> 跳转到面试详情" + rongMessageInBean.getId()).show();
                            }
                        });

                        break;
                }
            }


        }


    }

    @Override
    public Spannable getContentSummary(Custome02Message custome02Message) {
        return null;
    }

    @Override
    public void onItemClick(View view, int i, Custome02Message custome02Message, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.aa_rong_message2_layout, null);
        ViewHolder holder = new ViewHolder();
        holder.llView2 = view.findViewById(R.id.llView2);
        holder.tvTitle2 = view.findViewById(R.id.tvTitle2);


        view.setTag(holder);

        return view;
    }

    class ViewHolder {
        LinearLayout llView2;
        TextView tvTitle2;
    }
}
