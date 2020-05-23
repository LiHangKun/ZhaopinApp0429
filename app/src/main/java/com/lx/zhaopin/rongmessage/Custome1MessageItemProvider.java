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

import com.lx.zhaopin.R;
import com.lx.zhaopin.utils.ToastFactory;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;


@ProviderTag(messageContent = Custome1Message.class, showPortrait = false, centerInHorizontal = true, showSummaryWithName = false)
public class Custome1MessageItemProvider extends IContainerItemProvider.MessageProvider<Custome1Message> {

    private Context mContext;
    private static final String TAG = "Custome1MessageItemProv";

    @Override
    public void bindView(View view, int i, final Custome1Message custome1Message, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();

        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {
            //这是发送方
            Log.i(TAG, "onClick: 简历ID  + 这是发送方");
        } else if (uiMessage.getMessageDirection() == Message.MessageDirection.RECEIVE) {
            //这是接收方
            Log.i(TAG, "onClick: 简历ID  + 这是接收方");
        }

        //  content   就是简历的ID
        if (custome1Message.getContent() != null) {
            //拒绝
            holder.tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "onClick: 拒绝的简历ID" + custome1Message.getContent());
                    ToastFactory.getToast(mContext, "拒绝的简历ID " + custome1Message.getContent()).show();
                }
            });

            holder.tvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "onClick: 同意的简历ID---------->" + custome1Message.getContent());
                    ToastFactory.getToast(mContext, "同意的简历ID ---------->" + custome1Message.getContent()).show();
                }
            });

        }


    }

    @Override
    public Spannable getContentSummary(Custome1Message custome1Message) {
        return null;
    }

    @Override
    public void onItemClick(View view, int i, Custome1Message custome1Message, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.aa_rong_message1_layout, null);
        ViewHolder holder = new ViewHolder();
        holder.llView = view.findViewById(R.id.llView);
        holder.tvTitle = view.findViewById(R.id.tvTitle);
        holder.tvCancel = view.findViewById(R.id.tvCancel);
        holder.tvOk = view.findViewById(R.id.tvOk);

        view.setTag(holder);

        return view;
    }

    class ViewHolder {
        LinearLayout llView;
        TextView tvTitle, tvCancel, tvOk;
    }
}
