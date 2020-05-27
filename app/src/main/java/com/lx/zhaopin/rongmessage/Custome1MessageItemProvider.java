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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.RenCaiDetailActivity;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.utils.SPTool;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

//HR 看到求职者给我发送的一份求职简历,HR的操作是拒绝或者同意
@ProviderTag(messageContent = Custome1Message.class, centerInHorizontal = true, showSummaryWithName = false)
public class Custome1MessageItemProvider extends IContainerItemProvider.MessageProvider<Custome1Message> {

    private Context mContext;
    private static final String TAG = "Custome1MessageItemProv";

    @Override
    public void bindView(View view, int i, final Custome1Message custome1Message, final UIMessage uiMessage) {
        final ViewHolder holder = (ViewHolder) view.getTag();


        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {
            //这是发送方
            Log.i(TAG, "onClick: 简历ID  + 这是发送方");
            if (custome1Message.getContent() != null) {
                holder.llView1.setVisibility(View.GONE);
                holder.llView2.setVisibility(View.GONE);
                holder.llView3.setVisibility(View.VISIBLE);
                holder.llView4.setVisibility(View.GONE);

                //您的简历已成功发送给HR
                holder.tvTitle3.setText("您的简历已成功发送给HR");

            }
        } else if (uiMessage.getMessageDirection() == Message.MessageDirection.RECEIVE) {
            //这是接收方
            if (custome1Message.getContent() != null) {
                Gson gson = new Gson();

                holder.llView1.setVisibility(View.VISIBLE);
                holder.llView2.setVisibility(View.GONE);
                holder.llView3.setVisibility(View.GONE);
                holder.llView4.setVisibility(View.GONE);


                final RongMessageInBean rongMessageInBean = gson.fromJson(custome1Message.getContent(), RongMessageInBean.class);


                //拒绝  //查看简历,拒绝和同意  //  type 0 同意  1 拒绝
                holder.tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RongUtil.jujueAndTongYi(uiMessage.getTargetId(), "1", SPTool.getSessionValue(AppSP.USER_ICON), uiMessage.getTargetId());
                    }
                });

                //同意
                holder.tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RongUtil.jujueAndTongYi(uiMessage.getTargetId(), "0", SPTool.getSessionValue(AppSP.USER_ICON), uiMessage.getTargetId());
                    }
                });

                holder.llView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(mContext, RenCaiDetailActivity.class);
                        intent.putExtra("rid", rongMessageInBean.getId());
                        mContext.startActivity(intent);


                    }
                });

            }

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
        holder.tvTitle = view.findViewById(R.id.tvTitle);
        holder.tvCancel = view.findViewById(R.id.tvCancel);
        holder.tvOk = view.findViewById(R.id.tvOk);


        holder.llView1 = view.findViewById(R.id.llView1);
        holder.llView2 = view.findViewById(R.id.llView2);
        holder.llView3 = view.findViewById(R.id.llView3);
        holder.llView4 = view.findViewById(R.id.llView4);


        holder.tvTitle3 = view.findViewById(R.id.tvTitle3);
        holder.caoZuoView = view.findViewById(R.id.caoZuoView);

        view.setTag(holder);

        return view;
    }

    class ViewHolder {
        LinearLayout llView1, llView2, llView3, llView4;
        TextView tvTitle, tvCancel, tvOk, tvTitle3;
        RelativeLayout caoZuoView;
    }
}
