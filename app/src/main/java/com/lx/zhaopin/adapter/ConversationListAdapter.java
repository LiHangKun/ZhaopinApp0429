package com.lx.zhaopin.adapter;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lx.zhaopin.R;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MainActivity;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;
import com.lx.zhaopin.view.XmCircleImageview;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.rong.common.RLog;
import io.rong.eventbus.EventBus;
import io.rong.imkit.R.dimen;
import io.rong.imkit.R.drawable;
import io.rong.imkit.R.id;
import io.rong.imkit.R.string;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.Event.MessageDeleteEvent;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.model.UIConversation.UnreadRemindType;
import io.rong.imkit.widget.ProviderContainerView;
import io.rong.imkit.widget.adapter.BaseAdapter;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import okhttp3.Request;
import okhttp3.Response;

public class ConversationListAdapter extends BaseAdapter<UIConversation> {
    private static final String TAG = "ConversationListAdapter";
    LayoutInflater mInflater;
    Context mContext;
    private ConversationListAdapter.OnPortraitItemClick mOnPortraitItemClick;

    public long getItemId(int position) {
        UIConversation conversation = (UIConversation) this.getItem(position);
        return conversation == null ? 0L : (long) conversation.hashCode();
    }

    public ConversationListAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    public int findGatheredItem(ConversationType type) {
        int index = this.getCount();
        int position = -1;

        while (index-- > 0) {
            UIConversation uiConversation = (UIConversation) this.getItem(index);
            if (uiConversation.getConversationType().equals(type)) {
                position = index;
                break;
            }
        }

        return position;
    }

    public int findPosition(ConversationType type, String targetId) {
        int index = this.getCount();
        int position = -1;

        while (index-- > 0) {
            if (((UIConversation) this.getItem(index)).getConversationType().equals(type) && ((UIConversation) this.getItem(index)).getConversationTargetId().equals(targetId)) {
                position = index;
                break;
            }
        }

        return position;
    }

    protected View newView(Context context, int position, ViewGroup group) {
        View result = this.mInflater.inflate(R.layout.item_conversation_new, (ViewGroup) null);
        ConversationListAdapter.ViewHolder holder = new ConversationListAdapter.ViewHolder();
        holder.layout = this.findViewById(result, id.rc_item_conversation);
        holder.leftImageLayout = this.findViewById(result, id.rc_item1);
        holder.rightImageLayout = this.findViewById(result, id.rc_item2);
        holder.leftUnReadView = this.findViewById(result, id.rc_unread_view_left);
        holder.rightUnReadView = this.findViewById(result, id.rc_unread_view_right);
        holder.leftImageView = (XmCircleImageview) this.findViewById(result, id.rc_left);
        holder.rightImageView = (XmCircleImageview) this.findViewById(result, id.rc_right);
        holder.contentView = (ProviderContainerView) this.findViewById(result, id.rc_content);
        holder.unReadMsgCount = (TextView) this.findViewById(result, id.rc_unread_message);
        holder.unReadMsgCountRight = (TextView) this.findViewById(result, id.rc_unread_message_right);
        holder.unReadMsgCountIcon = (ImageView) this.findViewById(result, id.rc_unread_message_icon);
        holder.unReadMsgCountRightIcon = (ImageView) this.findViewById(result, id.rc_unread_message_icon_right);
        holder.senderNameTv = result.findViewById(R.id.sender_name_tv);
        holder.senderCompanyTv = result.findViewById(R.id.sender_company_tv);
        holder.senderContentTv = result.findViewById(R.id.sender_content_tv);
        holder.senderPosTv = result.findViewById(R.id.sender_pos_tv);
        holder.senderTimeTv = result.findViewById(R.id.sender_time_tv);
        result.setTag(holder);
        return result;
    }

    private void setUserInfo(final String userId, final TextView compayTv, final TextView PosTv) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("userId", userId);
        Log.e("setUserInfo mid=" + SPTool.getSessionValue(AppSP.UID), "userId=" + userId);
        OkHttpHelper.getInstance().post(mContext, NetClass.BASE_URL + NetCuiMethod.getRongUserInfo, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                Log.e(TAG, "companyName=" + resultBean.getCompanyName());
                Log.e(TAG, "positionName=" + resultBean.getPositionName());
                if (!TextUtils.isEmpty(resultBean.getCompanyName())) {
                    compayTv.setText(resultBean.getCompanyName());
                    PosTv.setText("," + resultBean.getPositionName());
                } else {
                    PosTv.setText(resultBean.getPositionName());
                }

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    /*
     * 将时间戳转换为时间
     */
    public String stampToDate(long timeMillis){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd ");
        Date date = new Date(timeMillis);
        return simpleDateFormat.format(date);
    }

    protected void bindView(View v, int position, final UIConversation data) {
        ConversationListAdapter.ViewHolder holder = (ConversationListAdapter.ViewHolder) v.getTag();
        if (data != null) {
            IContainerItemProvider provider = RongContext.getInstance().getConversationTemplate(data.getConversationType().getName());
            if (provider == null) {
                RLog.e("ConversationListAdapter", "provider is null");
            } else {
                holder.senderNameTv.setText(data.getUIConversationTitle());
                holder.senderContentTv.setText(data.getConversationContent().toString());
                setUserInfo(data.getConversationSenderId(), holder.senderCompanyTv, holder.senderPosTv);
                holder.senderTimeTv.setText(stampToDate(data.getUIConversationTime()));
//                View view = holder.contentView.inflate(provider);
//                provider.bindView(view, position, data);
                if (data.isTop()) {
                    holder.layout.setBackgroundDrawable(this.mContext.getResources().getDrawable(drawable.rc_item_top_list_selector));
                } else {
                    holder.layout.setBackgroundDrawable(this.mContext.getResources().getDrawable(drawable.rc_item_list_selector));
                }

                ConversationProviderTag tag = RongContext.getInstance().getConversationProviderTag(data.getConversationType().getName());
                int defaultId;
                if (data.getConversationType().equals(ConversationType.GROUP)) {
                    defaultId = drawable.rc_default_group_portrait;
                } else if (data.getConversationType().equals(ConversationType.DISCUSSION)) {
                    defaultId = drawable.rc_default_discussion_portrait;
                } else {
                    defaultId = drawable.rc_default_portrait;
                }

                if (tag.portraitPosition() == 1) {
                    holder.leftImageLayout.setVisibility(View.VISIBLE);
                    holder.leftImageLayout.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            if (ConversationListAdapter.this.mOnPortraitItemClick != null) {
                                ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemClick(v, data);
                            }

                        }
                    });
                    holder.leftImageLayout.setOnLongClickListener(new OnLongClickListener() {
                        public boolean onLongClick(View v) {
                            if (ConversationListAdapter.this.mOnPortraitItemClick != null) {
                                ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemLongClick(v, data);
                            }

                            return true;
                        }
                    });
                    if (data.getIconUrl() != null) {
                        Log.e(TAG, "uri=" + data.getIconUrl().toString());
                        Glide.with(mContext).load(data.getIconUrl().toString()).into(holder.leftImageView);
                    }

                    if (data.getUnReadMessageCount() > 0) {
                        holder.unReadMsgCountIcon.setVisibility(View.VISIBLE);
                        this.setUnReadViewLayoutParams(holder.leftUnReadView, data.getUnReadType());
                        if (data.getUnReadType().equals(UnreadRemindType.REMIND_WITH_COUNTING)) {
                            if (data.getUnReadMessageCount() > 99) {
                                holder.unReadMsgCount.setText(this.mContext.getResources().getString(string.rc_message_unread_count));
                            } else {
                                holder.unReadMsgCount.setText(Integer.toString(data.getUnReadMessageCount()));
                            }

                            holder.unReadMsgCount.setVisibility(View.VISIBLE);
                            holder.unReadMsgCountIcon.setImageResource(drawable.rc_unread_count_bg);
                        } else {
                            holder.unReadMsgCount.setVisibility(View.GONE);
                            holder.unReadMsgCountIcon.setImageResource(drawable.rc_unread_remind_list_count);
                        }
                    } else {
                        holder.unReadMsgCountIcon.setVisibility(View.GONE);
                        holder.unReadMsgCount.setVisibility(View.GONE);
                    }

                    holder.rightImageLayout.setVisibility(View.GONE);
                } else if (tag.portraitPosition() == 2) {
                    holder.rightImageLayout.setVisibility(View.VISIBLE);
                    holder.rightImageLayout.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            if (ConversationListAdapter.this.mOnPortraitItemClick != null) {
                                ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemClick(v, data);
                            }

                        }
                    });
                    holder.rightImageLayout.setOnLongClickListener(new OnLongClickListener() {
                        public boolean onLongClick(View v) {
                            if (ConversationListAdapter.this.mOnPortraitItemClick != null) {
                                ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemLongClick(v, data);
                            }

                            return true;
                        }
                    });

                    Glide.with(mContext).load(data.getIconUrl().toString()).into(holder.rightImageView);

                    if (data.getUnReadMessageCount() > 0) {
                        holder.unReadMsgCountRightIcon.setVisibility(View.VISIBLE);
                        this.setUnReadViewLayoutParams(holder.rightUnReadView, data.getUnReadType());
                        if (data.getUnReadType().equals(UnreadRemindType.REMIND_WITH_COUNTING)) {
                            holder.unReadMsgCount.setVisibility(View.VISIBLE);
                            if (data.getUnReadMessageCount() > 99) {
                                holder.unReadMsgCountRight.setText(this.mContext.getResources().getString(string.rc_message_unread_count));
                            } else {
                                holder.unReadMsgCountRight.setText(Integer.toString(data.getUnReadMessageCount()));
                            }

                            holder.unReadMsgCountRightIcon.setImageResource(drawable.rc_unread_count_bg);
                        } else {
                            holder.unReadMsgCount.setVisibility(View.GONE);
                            holder.unReadMsgCountRightIcon.setImageResource(drawable.rc_unread_remind_without_count);
                        }
                    } else {
                        holder.unReadMsgCountIcon.setVisibility(View.GONE);
                        holder.unReadMsgCount.setVisibility(View.GONE);
                    }

                    holder.leftImageLayout.setVisibility(View.GONE);
                } else {
                    if (tag.portraitPosition() != 3) {
                        throw new IllegalArgumentException("the portrait position is wrong!");
                    }

                    holder.rightImageLayout.setVisibility(View.GONE);
                    holder.leftImageLayout.setVisibility(View.GONE);
                }

                MessageContent content = data.getMessageContent();
                if (content != null && content.isDestruct()) {
                    RongIMClient.getInstance().getMessage(data.getLatestMessageId(), new ResultCallback<Message>() {
                        public void onSuccess(Message message) {
                            if (message == null) {
                                EventBus.getDefault().post(new MessageDeleteEvent(new int[]{data.getLatestMessageId()}));
                            }

                        }

                        public void onError(ErrorCode e) {
                        }
                    });
                }

            }
        }
    }

    protected void setUnReadViewLayoutParams(View view, UnreadRemindType type) {
        MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
        Context context = view.getContext();
        if (type == UnreadRemindType.REMIND_WITH_COUNTING) {
            params.width = (int) context.getResources().getDimension(dimen.rc_dimen_size_18);
            params.height = (int) context.getResources().getDimension(dimen.rc_dimen_size_18);
            params.leftMargin = (int) this.mContext.getResources().getDimension(dimen.rc_dimen_size_44);
            params.topMargin = (int) context.getResources().getDimension(dimen.rc_dimen_size_5);
        } else {
            params.width = (int) context.getResources().getDimension(dimen.rc_dimen_size_9);
            params.height = (int) context.getResources().getDimension(dimen.rc_dimen_size_9);
            params.leftMargin = (int) context.getResources().getDimension(dimen.rc_dimen_size_50);
            params.topMargin = (int) context.getResources().getDimension(dimen.rc_dimen_size_7);
        }

        view.setLayoutParams(params);
    }

    public void setOnPortraitItemClick(ConversationListAdapter.OnPortraitItemClick onPortraitItemClick) {
        this.mOnPortraitItemClick = onPortraitItemClick;
    }

    public interface OnPortraitItemClick {
        void onPortraitItemClick(View var1, UIConversation var2);

        boolean onPortraitItemLongClick(View var1, UIConversation var2);
    }

    protected class ViewHolder {
        public View layout;
        public View leftImageLayout;
        public View rightImageLayout;
        public View leftUnReadView;
        public View rightUnReadView;
        public XmCircleImageview leftImageView;
        public TextView unReadMsgCount;
        public ImageView unReadMsgCountIcon;
        public XmCircleImageview rightImageView;
        public TextView unReadMsgCountRight;
        public ImageView unReadMsgCountRightIcon;
        public ProviderContainerView contentView;
        public TextView senderNameTv;
        public TextView senderCompanyTv;
        public TextView senderPosTv;
        public TextView senderContentTv;
        public TextView senderTimeTv;

        protected ViewHolder() {
        }
    }
}
