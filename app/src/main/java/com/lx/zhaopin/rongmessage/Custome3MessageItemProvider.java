package com.lx.zhaopin.rongmessage;

//参考  CustomeGroupTipMessageItemProvider

import android.content.Context;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lx.zhaopin.R;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

////所有人的灰底白字的提示 content 就是提示文本类型
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
        } else if (uiMessage.getMessageDirection() == Message.MessageDirection.RECEIVE) {
            //这是接收方
            Log.i(TAG, "onClick: 简历ID  + 这是接收方");
        }

        //所有人的灰底白字的提示 content 就是提示文本类型
        if (custome3Message.getContent() != null) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.aa_rong_message3_layout, null);
        ViewHolder holder = new ViewHolder();
        holder.tvTitle = view.findViewById(R.id.tvTitle);


        view.setTag(holder);

        return view;
    }

    class ViewHolder {
        TextView tvTitle;
    }
}
