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

//HR 看到求职者给我发送的一份求职简历,HR的操作是拒绝或者同意  showPortrait = false,
@ProviderTag(messageContent = Custome4Message.class, centerInHorizontal = true, showSummaryWithName = false)
public class Custome4MessageItemProvider extends IContainerItemProvider.MessageProvider<Custome4Message> {

    private Context mContext;
    private static final String TAG = "Custome4MessageItemProv";

    @Override
    public void bindView(View view, int i, final Custome4Message custome4Message, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();

        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {
            //这是发送方
            Log.i(TAG, "onClick: 简历ID  + 这是发送方");
        } else if (uiMessage.getMessageDirection() == Message.MessageDirection.RECEIVE) {
            //这是接收方
            Log.i(TAG, "onClick: 简历ID  + 这是接收方");
        }

        //  content   就是简历的ID
        if (custome4Message.getContent() != null) {
            Gson gson = new Gson();
            final RongMessageInBean rongMessageInBean = gson.fromJson(custome4Message.getContent(), RongMessageInBean.class);
            Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.mipmap.imageerror).error(R.mipmap.imageerror))
                    .load(rongMessageInBean.getIcon()).into(holder.roundedImageView);

            holder.tvTitle.setText(rongMessageInBean.getName() + "向您发出面试邀请,\n点击查看");
            //拒绝
            holder.tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "onClick: 拒绝的简历ID" + rongMessageInBean.getId());
                    ToastFactory.getToast(mContext, "拒绝的简历ID " + rongMessageInBean.getId()).show();
                }
            });

            //同意
            holder.tvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "onClick: 同意的简历ID---------->" + rongMessageInBean.getId());
                    ToastFactory.getToast(mContext, "同意的简历ID ---------->" + rongMessageInBean.getId()).show();
                }
            });

            //点击查看面试详情
            holder.llView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "onClick: 同意的简历ID-------点击查看面试详情--->" + rongMessageInBean.getId());
                    ToastFactory.getToast(mContext, "同意的简历ID ---------->点击查看面试详情" + rongMessageInBean.getId()).show();
                }
            });


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
        View view = LayoutInflater.from(context).inflate(R.layout.aa_rong_message4_layout, null);
        ViewHolder holder = new ViewHolder();
        holder.llView = view.findViewById(R.id.llView);
        holder.tvTitle = view.findViewById(R.id.tvTitle);
        holder.tvCancel = view.findViewById(R.id.tvCancel);
        holder.tvOk = view.findViewById(R.id.tvOk);
        holder.roundedImageView = view.findViewById(R.id.roundedImageView);

        view.setTag(holder);

        return view;
    }

    class ViewHolder {
        LinearLayout llView;
        RoundedImageView roundedImageView;
        TextView tvTitle, tvCancel, tvOk;
    }
}
