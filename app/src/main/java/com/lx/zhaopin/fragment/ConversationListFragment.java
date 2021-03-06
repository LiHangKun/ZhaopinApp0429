package com.lx.zhaopin.fragment;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lx.zhaopin.R;
import com.lx.zhaopin.adapter.ConversationListAdapter;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.rong.common.RLog;
import io.rong.eventbus.EventBus;
import io.rong.imkit.R.bool;
import io.rong.imkit.R.drawable;
import io.rong.imkit.R.id;
import io.rong.imkit.R.layout;
import io.rong.imkit.R.string;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.IHistoryDataResultCallback;
import io.rong.imkit.manager.InternalModuleManager;
import io.rong.imkit.model.Event.AudioListenedEvent;
import io.rong.imkit.model.Event.ClearConversationEvent;
import io.rong.imkit.model.Event.ConnectEvent;
import io.rong.imkit.model.Event.ConversationNotificationEvent;
import io.rong.imkit.model.Event.ConversationRemoveEvent;
import io.rong.imkit.model.Event.ConversationTopEvent;
import io.rong.imkit.model.Event.ConversationUnreadEvent;
import io.rong.imkit.model.Event.CreateDiscussionEvent;
import io.rong.imkit.model.Event.DraftEvent;
import io.rong.imkit.model.Event.MessageDeleteEvent;
import io.rong.imkit.model.Event.MessageLeftEvent;
import io.rong.imkit.model.Event.MessageRecallEvent;
import io.rong.imkit.model.Event.MessageSentStatusUpdateEvent;
import io.rong.imkit.model.Event.MessagesClearEvent;
import io.rong.imkit.model.Event.OnMessageSendErrorEvent;
import io.rong.imkit.model.Event.OnReceiveMessageEvent;
import io.rong.imkit.model.Event.PublicServiceFollowableEvent;
import io.rong.imkit.model.Event.QuitDiscussionEvent;
import io.rong.imkit.model.Event.QuitGroupEvent;
import io.rong.imkit.model.Event.ReadReceiptEvent;
import io.rong.imkit.model.Event.RemoteMessageRecallEvent;
import io.rong.imkit.model.Event.SyncReadStatusEvent;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.utilities.OptionsPopupDialog;
import io.rong.imkit.utilities.OptionsPopupDialog.OnOptionsItemClickedListener;
import io.rong.imkit.voiceMessageDownload.AutoDownloadEntry;
import io.rong.imkit.voiceMessageDownload.AutoDownloadEntry.DownloadPriority;
import io.rong.imkit.voiceMessageDownload.HQVoiceMsgDownloadManager;
import io.rong.imkit.widget.RongSwipeRefreshLayout;
import io.rong.imkit.widget.RongSwipeRefreshLayout.OnFlushListener;
import io.rong.imkit.widget.RongSwipeRefreshLayout.OnLoadListener;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.ConnectionStatusListener.ConnectionStatus;
import io.rong.imlib.RongIMClient.ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message.MessageDirection;
import io.rong.imlib.model.Message.SentStatus;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ReadReceiptMessage;
import io.rong.push.RongPushClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConversationListFragment extends UriFragment implements OnItemClickListener, OnItemLongClickListener, ConversationListAdapter.OnPortraitItemClick, OnLoadListener, OnFlushListener {
    public static final String TAG = ConversationListFragment.class.getSimpleName();
    private List<ConversationListFragment.ConversationConfig> mConversationsConfig;
    private ConversationListFragment mThis;
    private ConversationListAdapter mAdapter;
    private ListView mList;
    private RongSwipeRefreshLayout mRefreshLayout;
    private View netWorkBar;
    private View headerNetWorkView;
    private ImageView headerNetWorkImage;
    private TextView headerNetWorkText;
    private boolean isShowWithoutConnected = false;
    private int leftOfflineMsg = 0;
    private boolean enableAutomaticDownloadMsg;
    private long timestamp = 0L;
    private int pageSize = 100;
    private static final int REQUEST_MSG_DOWNLOAD_PERMISSION = 1001;
    private ArrayList<Message> cacheEventList = new ArrayList();


    public ConversationListFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mThis = this;
        this.mConversationsConfig = new ArrayList();
        EventBus.getDefault().register(this);
        InternalModuleManager.getInstance().onLoaded();
        String rid = SPTool.getSessionValue("rid");
        setUserInfo(rid);
    }


    private void setUserInfo(final String userId) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("userId", userId);
        Log.e("setuserinfo", "mid ="+SPTool.getSessionValue(AppSP.UID)+",userId="+userId);
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.getRongUserInfo, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    protected void initFragment(Uri uri) {
        RLog.d(TAG, "initFragment " + uri);
        if (this.mConversationsConfig == null) {
            this.mConversationsConfig = new ArrayList();
        }

        ConversationType[] defConversationType = new ConversationType[]{ConversationType.PRIVATE, ConversationType.GROUP, ConversationType.DISCUSSION, ConversationType.SYSTEM, ConversationType.CUSTOMER_SERVICE, ConversationType.CHATROOM, ConversationType.PUBLIC_SERVICE, ConversationType.APP_PUBLIC_SERVICE, ConversationType.ENCRYPTED};
        this.timestamp = 0L;
        this.isShowWithoutConnected = false;
        this.leftOfflineMsg = 0;
        this.mConversationsConfig.clear();
        ConversationType[] var3 = defConversationType;
        int var4 = defConversationType.length;

        int var5;
        for(var5 = 0; var5 < var4; ++var5) {
            ConversationType conversationType = var3[var5];
            if (uri.getQueryParameter(conversationType.getName()) != null) {
                ConversationListFragment.ConversationConfig config = new ConversationListFragment.ConversationConfig();
                config.conversationType = conversationType;
                config.isGathered = "true".equals(uri.getQueryParameter(conversationType.getName()));
                this.mConversationsConfig.add(config);
            }
        }

        if (this.mConversationsConfig.size() == 0) {
            String type = uri.getQueryParameter("type");
            ConversationType[] var10 = defConversationType;
            var5 = defConversationType.length;

            for(int var11 = 0; var11 < var5; ++var11) {
                ConversationType conversationType = var10[var11];
                if (conversationType.getName().equals(type)) {
                    ConversationListFragment.ConversationConfig config = new ConversationListFragment.ConversationConfig();
                    config.conversationType = conversationType;
                    config.isGathered = false;
                    this.mConversationsConfig.add(config);
                    break;
                }
            }
        }

        this.mAdapter.clear();
        if (RongIMClient.getInstance().getCurrentConnectionStatus().equals(ConnectionStatus.DISCONNECTED)) {
            RLog.d(TAG, "RongCloud haven't been connected yet, so the conversation list display blank !!!");
            this.isShowWithoutConnected = true;
        } else {
            this.getConversationList(this.getConfigConversationTypes(), false);

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layout.rc_fr_conversationlist, container, false);
        View emptyView = this.findViewById(view, id.rc_conversation_list_empty_layout);
        //TextView emptyText = (TextView)this.findViewById(view, id.rc_empty_tv);
//        if (this.getActivity() != null) {
//            emptyText.setText(this.getActivity().getResources().getString(string.rc_conversation_list_empty_prompt));
//        }

        this.mList = (ListView)this.findViewById(view, id.rc_list);
        this.mRefreshLayout = (RongSwipeRefreshLayout)this.findViewById(view, id.rc_refresh);
        this.mList.setEmptyView(emptyView);
        this.inflateHeaderView();
        this.mList.setOnItemClickListener(this);
        this.mList.setOnItemLongClickListener(this);
        if (this.mAdapter == null) {
            this.mAdapter = this.onResolveAdapter(this.getActivity());
        }

        this.mAdapter.setOnPortraitItemClick(this);
        this.mRefreshLayout.setCanRefresh(false);
        this.mRefreshLayout.setCanLoading(true);
        this.mRefreshLayout.setOnLoadListener(this);
        this.mRefreshLayout.setOnFlushListener(this);
        this.mList.setAdapter(this.mAdapter);
        if (this.getContext() != null) {
            Resources resources = this.getContext().getResources();
            this.enableAutomaticDownloadMsg = resources.getBoolean(bool.rc_enable_automatic_download_voice_msg);
        }

        this.headerNetWorkView = this.findViewById(view, id.rc_status_bar);
        this.headerNetWorkImage = (ImageView)this.findViewById(view, id.rc_status_bar_image);
        this.headerNetWorkText = (TextView)this.findViewById(view, id.rc_status_bar_text);
        return view;
    }

    public void onResume() {
        super.onResume();
        String rid = SPTool.getSessionValue("rid");
        setUserInfo(rid);
        RLog.d(TAG, "onResume " + RongIM.getInstance().getCurrentConnectionStatus());
        if (this.getResources().getBoolean(bool.rc_wipe_out_notification_message)) {
            RongPushClient.clearAllNotifications(this.getActivity());
        }

        this.setNotificationBarVisibility(RongIM.getInstance().getCurrentConnectionStatus());
    }

    private void inflateHeaderView() {
        List<View> headerViews = this.onAddHeaderView();
        if (headerViews != null && headerViews.size() > 0) {
            Iterator var2 = headerViews.iterator();

            while(var2.hasNext()) {
                View headerView = (View)var2.next();
                this.mList.addHeaderView(headerView);
            }
        }

    }

    private void getConversationList(ConversationType[] conversationTypes, final boolean isLoadMore) {
        this.getConversationList(conversationTypes, new IHistoryDataResultCallback<List<Conversation>>() {
            public void onResult(List<Conversation> data) {
                if (data != null && data.size() > 0) {
                    ConversationListFragment.this.makeUiConversationList(data);
                    RLog.d(ConversationListFragment.TAG, "getConversationList : listSize = " + data.size());
                    ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                    ConversationListFragment.this.onUnreadCountChanged();
                    ConversationListFragment.this.updateConversationReadReceipt(ConversationListFragment.this.cacheEventList);
                } else {
                    ConversationListFragment.this.isShowWithoutConnected = true;
                }

                ConversationListFragment.this.onFinishLoadConversationList(ConversationListFragment.this.leftOfflineMsg);
                if (!isLoadMore) {
                    ConversationListFragment.this.mRefreshLayout.setRefreshing(false);
                } else {
                    if (data == null) {
                        ConversationListFragment.this.mRefreshLayout.setLoadMoreFinish(false);
                    } else if (data.size() > 0 && data.size() <= ConversationListFragment.this.pageSize) {
                        ConversationListFragment.this.mRefreshLayout.setLoadMoreFinish(false);
                    } else if (data.size() == 0) {
                        ConversationListFragment.this.mRefreshLayout.setLoadMoreFinish(false);
                        ConversationListFragment.this.mRefreshLayout.setCanLoading(false);
                    } else {
                        ConversationListFragment.this.mRefreshLayout.setLoadMoreFinish(false);
                    }

                }
            }

            public void onError() {
                RLog.e(ConversationListFragment.TAG, "getConversationList Error");
                ConversationListFragment.this.onFinishLoadConversationList(ConversationListFragment.this.leftOfflineMsg);
                ConversationListFragment.this.isShowWithoutConnected = true;
                ConversationListFragment.this.mRefreshLayout.setLoadMoreFinish(false);
            }
        }, isLoadMore);
    }

    public void getConversationList(ConversationType[] conversationTypes, final IHistoryDataResultCallback<List<Conversation>> callback, boolean isLoadMore) {
        long lTimestamp = isLoadMore ? this.timestamp : 0L;
        RongIMClient.getInstance().getConversationListByPage(new ResultCallback<List<Conversation>>() {
            public void onSuccess(List<Conversation> conversations) {
                if (ConversationListFragment.this.getActivity() != null && !ConversationListFragment.this.getActivity().isFinishing()) {
                    if (callback != null) {
                        List<Conversation> resultConversations = new ArrayList();
                        if (conversations != null) {
                            ConversationListFragment.this.timestamp = ((Conversation)conversations.get(conversations.size() - 1)).getSentTime();
                            Iterator var3 = conversations.iterator();

                            while(var3.hasNext()) {
                                Conversation conversation = (Conversation)var3.next();
                                if (!ConversationListFragment.this.shouldFilterConversation(conversation.getConversationType(), conversation.getTargetId())) {
                                    resultConversations.add(conversation);
                                }
                            }
                        }

                        callback.onResult(resultConversations);
                    }

                }
            }

            public void onError(ErrorCode e) {
                if (callback != null) {
                    callback.onError();
                }

            }
        }, lTimestamp, this.pageSize, conversationTypes);
    }

    public void focusUnreadItem() {
        if (this.mList != null && this.mAdapter != null) {
            int first = this.mList.getFirstVisiblePosition();
            int last = this.mList.getLastVisiblePosition();
            int count = this.mAdapter.getCount();
            first = Math.max(0, first - this.mList.getHeaderViewsCount());
            last -= this.mList.getHeaderViewsCount();
            last = Math.min(count - 1, last);
            int visibleCount = last - first + 1;
            if (visibleCount < count) {
                int index;
                if (last < count - 1) {
                    index = first + 1;
                } else {
                    index = 0;
                }

                if (!this.selectNextUnReadItem(index, count)) {
                    this.selectNextUnReadItem(0, count);
                }
            }

        }
    }

    private boolean selectNextUnReadItem(int startIndex, int totalCount) {
        int index = -1;

        for(int i = startIndex; i < totalCount; ++i) {
            UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(i);
            if (uiConversation == null || uiConversation.getUnReadMessageCount() > 0) {
                index = i;
                break;
            }
        }

        if (index >= 0 && index < totalCount) {
            this.mList.setSelection(index + this.mList.getHeaderViewsCount());
            return true;
        } else {
            return false;
        }
    }

    private String content = null;
    private void setNotificationBarVisibility(ConnectionStatus status) {
        if (!this.getResources().getBoolean(bool.rc_is_show_warning_notification)) {
            RLog.e(TAG, "rc_is_show_warning_notification is disabled.");
        } else {

            if (status.equals(ConnectionStatus.NETWORK_UNAVAILABLE)) {
                content = this.getResources().getString(string.rc_notice_network_unavailable);
            } else if (status.equals(ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT)) {
                content = this.getResources().getString(string.rc_notice_tick);
            } else if (status.equals(ConnectionStatus.CONNECTED)) {
                this.headerNetWorkView.setVisibility(View.GONE);
            } else if (status.equals(ConnectionStatus.DISCONNECTED)) {
                content = this.getResources().getString(string.rc_notice_disconnect);
            } else if (status.equals(ConnectionStatus.CONNECTING)) {
                content = this.getResources().getString(string.rc_notice_connecting);
            }

            if (content != null && this.headerNetWorkView != null) {
                if (this.headerNetWorkView.getVisibility() == View.GONE) {
                    this.getHandler().postDelayed(new Runnable() {
                        public void run() {
                            if (!RongIMClient.getInstance().getCurrentConnectionStatus().equals(ConnectionStatus.CONNECTED)) {
                                ConversationListFragment.this.headerNetWorkView.setVisibility(View.VISIBLE);
                                ConversationListFragment.this.headerNetWorkText.setText(content);
                                if (RongIMClient.getInstance().getCurrentConnectionStatus().equals(ConnectionStatus.CONNECTING)) {
                                    ConversationListFragment.this.headerNetWorkImage.setImageResource(drawable.rc_notification_connecting_animated);
                                } else {
                                    ConversationListFragment.this.headerNetWorkImage.setImageResource(drawable.rc_notification_network_available);
                                }
                            }

                        }
                    }, 4000L);
                } else {
                    this.headerNetWorkText.setText(content);
                    if (RongIMClient.getInstance().getCurrentConnectionStatus().equals(ConnectionStatus.CONNECTING)) {
                        this.headerNetWorkImage.setImageResource(drawable.rc_notification_connecting_animated);
                    } else {
                        this.headerNetWorkImage.setImageResource(drawable.rc_notification_network_available);
                    }
                }
            }

        }
    }

    public boolean onBackPressed() {
        return false;
    }

    protected List<View> onAddHeaderView() {
        List<View> headerViews = new ArrayList();
        return headerViews;
    }

    /** @deprecated */
    @Deprecated
    public void setAdapter(ConversationListAdapter adapter) {
        this.mAdapter = adapter;
        if (this.mList != null) {
            this.mList.setAdapter(adapter);
        }

    }

    public ConversationListAdapter onResolveAdapter(Context context) {
        this.mAdapter = new ConversationListAdapter(context);
        return this.mAdapter;
    }

    public void onEventMainThread(SyncReadStatusEvent event) {
        ConversationType conversationType = event.getConversationType();
        String targetId = event.getTargetId();
        RLog.d(TAG, "SyncReadStatusEvent " + conversationType + " " + targetId);
        int position;
        if (this.getGatherState(conversationType)) {
            position = this.mAdapter.findGatheredItem(conversationType);
        } else {
            position = this.mAdapter.findPosition(conversationType, targetId);
        }

        if (position >= 0) {
            UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(position);
            uiConversation.clearUnRead(conversationType, targetId);
            this.mAdapter.notifyDataSetChanged();
        }

        this.onUnreadCountChanged();
    }

    public void onEventMainThread(ReadReceiptEvent event) {
        ConversationType conversationType = event.getMessage().getConversationType();
        String targetId = event.getMessage().getTargetId();
        int originalIndex = this.mAdapter.findPosition(conversationType, targetId);
        boolean gatherState = this.getGatherState(conversationType);
        RLog.d(TAG, "ReadReceiptEvent. targetId:" + event.getMessage().getTargetId() + ";originalIndex:" + originalIndex);
        if (!gatherState) {
            if (originalIndex >= 0) {
                UIConversation conversation = (UIConversation)this.mAdapter.getItem(originalIndex);
                ReadReceiptMessage content = (ReadReceiptMessage)event.getMessage().getContent();
                if (content.getLastMessageSendTime() >= conversation.getSyncReadReceiptTime() && conversation.getConversationSenderId().equals(RongIMClient.getInstance().getCurrentUserId())) {
                    conversation.setSentStatus(SentStatus.READ);
                    conversation.setSyncReadReceiptTime(event.getMessage().getSentTime());
                    this.mAdapter.getView(originalIndex, this.mList.getChildAt(originalIndex - this.mList.getFirstVisiblePosition() + this.mList.getHeaderViewsCount()), this.mList);
                    return;
                }
            }

            this.cacheEventList.add(event.getMessage());
        }

    }

    private void updateConversationReadReceipt(ArrayList<Message> cacheEventList) {
        Iterator iterator = cacheEventList.iterator();

        while(true) {
            while(true) {
                Message message;
                int originalIndex;
                boolean gatherState;
                do {
                    do {
                        if (!iterator.hasNext()) {
                            return;
                        }

                        message = (Message)iterator.next();
                        ConversationType conversationType = message.getConversationType();
                        String targetId = message.getTargetId();
                        originalIndex = this.mAdapter.findPosition(conversationType, targetId);
                        gatherState = this.getGatherState(conversationType);
                    } while(gatherState);
                } while(originalIndex < 0);

                UIConversation conversation = (UIConversation)this.mAdapter.getItem(originalIndex);
                ReadReceiptMessage content = (ReadReceiptMessage)message.getContent();
                if (content.getLastMessageSendTime() >= conversation.getSyncReadReceiptTime() && conversation.getConversationSenderId().equals(RongIMClient.getInstance().getCurrentUserId())) {
                    conversation.setSentStatus(SentStatus.READ);
                    conversation.setSyncReadReceiptTime(content.getLastMessageSendTime());
                    this.mAdapter.getView(originalIndex, this.mList.getChildAt(originalIndex - this.mList.getFirstVisiblePosition() + this.mList.getHeaderViewsCount()), this.mList);
                    iterator.remove();
                } else if (content.getLastMessageSendTime() < conversation.getUIConversationTime()) {
                    RLog.d(TAG, "remove cache event. id:" + message.getTargetId());
                    iterator.remove();
                }
            }
        }
    }

    public void onEventMainThread(AudioListenedEvent event) {
        Message message = event.getMessage();
        ConversationType conversationType = message.getConversationType();
        String targetId = message.getTargetId();
        RLog.d(TAG, "Message: " + message.getObjectName() + " " + conversationType + " " + message.getSentStatus());
        if (this.isConfigured(conversationType)) {
            boolean gathered = this.getGatherState(conversationType);
            int position = gathered ? this.mAdapter.findGatheredItem(conversationType) : this.mAdapter.findPosition(conversationType, targetId);
            if (position >= 0) {
                UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(position);
                if (message.getMessageId() == uiConversation.getLatestMessageId()) {
                    uiConversation.updateConversation(message, gathered);
                    this.mAdapter.getView(position, this.mList.getChildAt(position - this.mList.getFirstVisiblePosition() + this.mList.getHeaderViewsCount()), this.mList);
                }
            }
        }

    }

    public boolean shouldUpdateConversation(Message message, int left) {
        return true;
    }

    public boolean shouldFilterConversation(ConversationType type, String targetId) {
        return false;
    }

    public void onUnreadCountChanged() {
    }

    public void onFinishLoadConversationList(int leftOfflineMsg) {
    }

    public void onUIConversationCreated(UIConversation uiConversation) {
    }

    public void updateListItem(UIConversation uiConversation) {
        int position = this.mAdapter.findPosition(uiConversation.getConversationType(), uiConversation.getConversationTargetId());
        if (position >= 0) {
            this.mAdapter.getView(position, this.mList.getChildAt(position - this.mList.getFirstVisiblePosition() + this.mList.getHeaderViewsCount()), this.mList);
        }

    }

    public void onEventMainThread(OnReceiveMessageEvent event) {
        if (event.isOffline()) {
            if (event.getLeft() == 0) {
                this.getConversationList(this.getConfigConversationTypes(), false);
            }

        } else {
            this.leftOfflineMsg = event.getLeft();
            Message message = event.getMessage();
            String targetId = message.getTargetId();
            ConversationType conversationType = message.getConversationType();
            if (!this.shouldFilterConversation(conversationType, targetId)) {
                if (event.getLeft() == 0 && !event.hasPackage() && this.enableAutomaticDownloadMsg) {
                    HQVoiceMsgDownloadManager.getInstance().enqueue(this, new AutoDownloadEntry(message, DownloadPriority.NORMAL));
                }

                RLog.d(TAG, "OnReceiveMessageEvent: " + message.getObjectName() + " " + event.getLeft() + " " + conversationType + " " + targetId);
                if (this.isConfigured(message.getConversationType()) && this.shouldUpdateConversation(event.getMessage(), event.getLeft())) {
                    if (message.getMessageId() > 0) {
                        boolean gathered = this.getGatherState(conversationType);
                        int position;
                        if (gathered) {
                            position = this.mAdapter.findGatheredItem(conversationType);
                        } else {
                            position = this.mAdapter.findPosition(conversationType, targetId);
                        }

                        UIConversation uiConversation;
                        int index;
                        if (position < 0) {
                            uiConversation = UIConversation.obtain(this.getActivity(), message, gathered);
                            this.onUIConversationCreated(uiConversation);
                            index = this.getPosition(uiConversation);
                            this.mAdapter.add(uiConversation, index);
                            this.mAdapter.notifyDataSetChanged();
                        } else {
                            uiConversation = (UIConversation)this.mAdapter.getItem(position);
                            if (event.getMessage().getSentTime() > uiConversation.getUIConversationTime()) {
                                uiConversation.updateConversation(message, gathered);
                                this.mAdapter.remove(position);
                                index = this.getPosition(uiConversation);
                                this.mAdapter.add(uiConversation, index);
                                this.mAdapter.notifyDataSetChanged();
                            } else {
                                RLog.i(TAG, "ignore update message " + event.getMessage().getObjectName());
                            }
                        }

                        RLog.i(TAG, "conversation unread count : " + uiConversation.getUnReadMessageCount() + " " + conversationType + " " + targetId);
                    }

                    if (event.getLeft() == 0) {
                        this.syncUnreadCount();
                    }

                    this.updateConversationReadReceipt(this.cacheEventList);
                }

            }
        }
    }

    public void onEventMainThread(MessageLeftEvent event) {
        if (event.left == 0) {
            this.syncUnreadCount();
        }

    }

    private void syncUnreadCount() {
        if (this.mAdapter.getCount() > 0) {
            for(int i = 0; i < this.mAdapter.getCount(); ++i) {
                final UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(i);
                ConversationType conversationType = uiConversation.getConversationType();
                String targetId = uiConversation.getConversationTargetId();
                if (this.getGatherState(conversationType)) {
                    this.mAdapter.findGatheredItem(conversationType);
                    RongIMClient.getInstance().getUnreadCount(new ResultCallback<Integer>() {
                        public void onSuccess(Integer integer) {
                            uiConversation.setUnReadMessageCount(integer);
                            ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                            ConversationListFragment.this.onUnreadCountChanged();
                        }

                        public void onError(ErrorCode e) {
                        }
                    }, new ConversationType[]{conversationType});
                } else {
                    this.mAdapter.findPosition(conversationType, targetId);
                    RongIMClient.getInstance().getUnreadCount(conversationType, targetId, new ResultCallback<Integer>() {
                        public void onSuccess(Integer integer) {
                            uiConversation.setUnReadMessageCount(integer);
                            ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                            ConversationListFragment.this.onUnreadCountChanged();
                        }

                        public void onError(ErrorCode e) {
                        }
                    });
                }
            }
        }

    }

    public void onEventMainThread(MessageRecallEvent event) {
        RLog.d(TAG, "MessageRecallEvent");
        int count = this.mAdapter.getCount();

        for(int i = 0; i < count; ++i) {
            UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(i);
            if (event.getMessageId() == uiConversation.getLatestMessageId()) {
                boolean gatherState = ((UIConversation)this.mAdapter.getItem(i)).getConversationGatherState();
                final String targetId = ((UIConversation)this.mAdapter.getItem(i)).getConversationTargetId();
                if (gatherState) {
                    RongIM.getInstance().getConversationList(new ResultCallback<List<Conversation>>() {
                        public void onSuccess(List<Conversation> conversationList) {
                            if (ConversationListFragment.this.getActivity() != null && !ConversationListFragment.this.getActivity().isFinishing()) {
                                if (conversationList != null && conversationList.size() > 0) {
                                    UIConversation uiConversation = ConversationListFragment.this.makeUIConversation(conversationList);
                                    int oldPos = ConversationListFragment.this.mAdapter.findPosition(uiConversation.getConversationType(), targetId);
                                    if (oldPos >= 0) {
                                        UIConversation originalConversation = (UIConversation) ConversationListFragment.this.mAdapter.getItem(oldPos);
                                        uiConversation.setExtra(originalConversation.getExtra());
                                        ConversationListFragment.this.mAdapter.remove(oldPos);
                                    }

                                    int newIndex = ConversationListFragment.this.getPosition(uiConversation);
                                    ConversationListFragment.this.mAdapter.add(uiConversation, newIndex);
                                    ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                                }

                            }
                        }

                        public void onError(ErrorCode e) {
                        }
                    }, new ConversationType[]{uiConversation.getConversationType()});
                } else {
                    RongIM.getInstance().getConversation(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), new ResultCallback<Conversation>() {
                        public void onSuccess(Conversation conversation) {
                            if (conversation != null) {
                                UIConversation uiConversation = UIConversation.obtain(ConversationListFragment.this.getActivity(), conversation, false);
                                int pos = ConversationListFragment.this.mAdapter.findPosition(conversation.getConversationType(), conversation.getTargetId());
                                if (pos >= 0) {
                                    UIConversation originalConversation = (UIConversation) ConversationListFragment.this.mAdapter.getItem(pos);
                                    uiConversation.setExtra(originalConversation.getExtra());
                                    ConversationListFragment.this.mAdapter.remove(pos);
                                }

                                int newPosition = ConversationListFragment.this.getPosition(uiConversation);
                                ConversationListFragment.this.mAdapter.add(uiConversation, newPosition);
                                ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                            }

                        }

                        public void onError(ErrorCode e) {
                        }
                    });
                }
                break;
            }
        }

    }

    public void onEventMainThread(RemoteMessageRecallEvent event) {
        RLog.d(TAG, "RemoteMessageRecallEvent");
        ConversationType conversationType = event.getConversationType();
        final String targetId = event.getTargetId();
        int position = this.mAdapter.findPosition(conversationType, targetId);
        if (position == -1) {
            RLog.d(TAG, "ConversationListFragment UI unprepared!");
        } else {
            UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(position);
            boolean gatherState = uiConversation.getConversationGatherState();
            if (gatherState) {
                RongIM.getInstance().getConversationList(new ResultCallback<List<Conversation>>() {
                    public void onSuccess(List<Conversation> conversationList) {
                        if (ConversationListFragment.this.getActivity() != null && !ConversationListFragment.this.getActivity().isFinishing()) {
                            if (conversationList != null && conversationList.size() > 0) {
                                UIConversation uiConversation = ConversationListFragment.this.makeUIConversation(conversationList);
                                int oldPos = ConversationListFragment.this.mAdapter.findPosition(uiConversation.getConversationType(), targetId);
                                if (oldPos >= 0) {
                                    UIConversation originalConversation = (UIConversation) ConversationListFragment.this.mAdapter.getItem(oldPos);
                                    uiConversation.setExtra(originalConversation.getExtra());
                                    ConversationListFragment.this.mAdapter.remove(oldPos);
                                }

                                int newIndex = ConversationListFragment.this.getPosition(uiConversation);
                                ConversationListFragment.this.mAdapter.add(uiConversation, newIndex);
                                ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                                ConversationListFragment.this.onUnreadCountChanged();
                            }

                        }
                    }

                    public void onError(ErrorCode e) {
                    }
                }, new ConversationType[]{((UIConversation)this.mAdapter.getItem(position)).getConversationType()});
            } else {
                RongIM.getInstance().getConversation(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), new ResultCallback<Conversation>() {
                    public void onSuccess(Conversation conversation) {
                        if (conversation != null) {
                            UIConversation newConversation = UIConversation.obtain(ConversationListFragment.this.getActivity(), conversation, false);
                            int pos = ConversationListFragment.this.mAdapter.findPosition(conversation.getConversationType(), conversation.getTargetId());
                            if (pos >= 0) {
                                UIConversation originalConversation = (UIConversation) ConversationListFragment.this.mAdapter.getItem(pos);
                                newConversation.setExtra(originalConversation.getExtra());
                                ConversationListFragment.this.mAdapter.remove(pos);
                            }

                            int newPosition = ConversationListFragment.this.getPosition(newConversation);
                            ConversationListFragment.this.mAdapter.add(newConversation, newPosition);
                            ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                            ConversationListFragment.this.onUnreadCountChanged();
                        }

                    }

                    public void onError(ErrorCode e) {
                    }
                });
            }

        }
    }

    public void onEventMainThread(Message message) {
        ConversationType conversationType = message.getConversationType();
        String targetId = message.getTargetId();
        RLog.d(TAG, "Message: " + message.getObjectName() + " " + message.getMessageId() + " " + conversationType + " " + message.getSentStatus());
        if (!this.shouldFilterConversation(conversationType, targetId)) {
            boolean gathered = this.getGatherState(conversationType);
            if (this.isConfigured(conversationType) && message.getMessageId() > 0) {
                int position = gathered ? this.mAdapter.findGatheredItem(conversationType) : this.mAdapter.findPosition(conversationType, targetId);
                UIConversation uiConversation;
                if (position < 0) {
                    uiConversation = UIConversation.obtain(this.getActivity(), message, gathered);
                    this.onUIConversationCreated(uiConversation);
                    int index = this.getPosition(uiConversation);
                    this.mAdapter.add(uiConversation, index);
                    this.mAdapter.notifyDataSetChanged();
                } else {
                    uiConversation = (UIConversation)this.mAdapter.getItem(position);
                    long covTime = uiConversation.getUIConversationTime();
                    if (uiConversation.getLatestMessageId() == message.getMessageId() && uiConversation.getSentStatus() == SentStatus.SENDING && message.getSentStatus() == SentStatus.SENT && message.getMessageDirection() == MessageDirection.SEND) {
                        covTime -= RongIMClient.getInstance().getDeltaTime();
                    }

                    if (covTime <= message.getSentTime() || uiConversation.getLatestMessageId() < 0) {
                        this.mAdapter.remove(position);
                        uiConversation.updateConversation(message, gathered);
                        int index = this.getPosition(uiConversation);
                        this.mAdapter.add(uiConversation, index);
                        if (position == index) {
                            this.mAdapter.getView(index, this.mList.getChildAt(index - this.mList.getFirstVisiblePosition() + this.mList.getHeaderViewsCount()), this.mList);
                        } else {
                            this.mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

        }
    }

    public void onEventMainThread(MessageSentStatusUpdateEvent event) {
        Message message = event.getMessage();
        if (message != null && !message.getMessageDirection().equals(MessageDirection.RECEIVE)) {
            ConversationType conversationType = message.getConversationType();
            String targetId = message.getTargetId();
            RLog.d(TAG, "MessageSentStatusUpdateEvent: " + event.getMessage().getTargetId() + " " + conversationType + " " + event.getSentStatus());
            boolean gathered = this.getGatherState(conversationType);
            if (!gathered) {
                if (this.isConfigured(conversationType) && message.getMessageId() > 0) {
                    int position = this.mAdapter.findPosition(conversationType, targetId);
                    UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(position);
                    if (message.getMessageId() == uiConversation.getLatestMessageId()) {
                        this.mAdapter.remove(position);
                        uiConversation.updateConversation(message, gathered);
                        int index = this.getPosition(uiConversation);
                        this.mAdapter.add(uiConversation, index);
                        if (position == index) {
                            this.mAdapter.getView(index, this.mList.getChildAt(index - this.mList.getFirstVisiblePosition() + this.mList.getHeaderViewsCount()), this.mList);
                        } else {
                            this.mAdapter.notifyDataSetChanged();
                        }
                    }
                }

            }
        } else {
            RLog.e(TAG, "MessageSentStatusUpdateEvent message is null or direction is RECEIVE");
        }
    }

    public void onEventMainThread(ConnectionStatus status) {
        RLog.d(TAG, "ConnectionStatus, " + status.toString());
        this.setNotificationBarVisibility(status);
        if (status.equals(ConnectionStatus.CONNECTED) && this.isShowWithoutConnected) {
            this.getConversationList(this.getConfigConversationTypes(), false);
            this.isShowWithoutConnected = false;
        }

    }

    public void onEventMainThread(ConnectEvent event) {
        RLog.d(TAG, "ConnectEvent :" + RongIMClient.getInstance().getCurrentConnectionStatus());
        if (this.isShowWithoutConnected) {
            this.getConversationList(this.getConfigConversationTypes(), false);
            this.isShowWithoutConnected = false;
        }

    }

    public void onEventMainThread(final CreateDiscussionEvent createDiscussionEvent) {
        RLog.d(TAG, "createDiscussionEvent");
        final String targetId = createDiscussionEvent.getDiscussionId();
        if (this.isConfigured(ConversationType.DISCUSSION)) {
            RongIMClient.getInstance().getConversation(ConversationType.DISCUSSION, targetId, new ResultCallback<Conversation>() {
                public void onSuccess(Conversation conversation) {
                    if (conversation != null) {
                        int position;
                        if (ConversationListFragment.this.getGatherState(ConversationType.DISCUSSION)) {
                            position = ConversationListFragment.this.mAdapter.findGatheredItem(ConversationType.DISCUSSION);
                        } else {
                            position = ConversationListFragment.this.mAdapter.findPosition(ConversationType.DISCUSSION, targetId);
                        }

                        conversation.setConversationTitle(createDiscussionEvent.getDiscussionName());
                        UIConversation uiConversation;
                        if (position < 0) {
                            uiConversation = UIConversation.obtain(ConversationListFragment.this.getActivity(), conversation, ConversationListFragment.this.getGatherState(ConversationType.DISCUSSION));
                            ConversationListFragment.this.onUIConversationCreated(uiConversation);
                            int index = ConversationListFragment.this.getPosition(uiConversation);
                            ConversationListFragment.this.mAdapter.add(uiConversation, index);
                            ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                        } else {
                            uiConversation = (UIConversation) ConversationListFragment.this.mAdapter.getItem(position);
                            uiConversation.updateConversation(conversation, ConversationListFragment.this.getGatherState(ConversationType.DISCUSSION));
                            ConversationListFragment.this.mAdapter.getView(position, ConversationListFragment.this.mList.getChildAt(position - ConversationListFragment.this.mList.getFirstVisiblePosition() + ConversationListFragment.this.mList.getHeaderViewsCount()), ConversationListFragment.this.mList);
                        }
                    }

                }

                public void onError(ErrorCode e) {
                }
            });
        }

    }

    public void onEventMainThread(DraftEvent draft) {
        ConversationType conversationType = draft.getConversationType();
        String targetId = draft.getTargetId();
        RLog.i(TAG, "Draft : " + conversationType);
        this.getConversationList(this.getConfigConversationTypes(), false);
    }

    public void onEventMainThread(Group groupInfo) {
        RLog.d(TAG, "Group: " + groupInfo.getName() + " " + groupInfo.getId());
        int count = this.mAdapter.getCount();
        if (groupInfo.getName() != null) {
            for(int i = 0; i < count; ++i) {
                UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(i);
                uiConversation.updateConversation(groupInfo);
            }

            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(Discussion discussion) {
        RLog.d(TAG, "Discussion: " + discussion.getName() + " " + discussion.getId());
        if (this.isConfigured(ConversationType.DISCUSSION)) {
            int position;
            if (this.getGatherState(ConversationType.DISCUSSION)) {
                position = this.mAdapter.findGatheredItem(ConversationType.DISCUSSION);
            } else {
                position = this.mAdapter.findPosition(ConversationType.DISCUSSION, discussion.getId());
            }

            if (position >= 0) {
                for(int i = 0; i <= position; ++i) {
                    UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(i);
                    uiConversation.updateConversation(discussion);
                }

                this.mAdapter.notifyDataSetChanged();
            }
        }

    }

    public void onEventMainThread(GroupUserInfo groupUserInfo) {
        RLog.d(TAG, "GroupUserInfo " + groupUserInfo.getGroupId() + " " + groupUserInfo.getUserId() + " " + groupUserInfo.getNickname());
        if (groupUserInfo.getNickname() != null && groupUserInfo.getGroupId() != null) {
            int count = this.mAdapter.getCount();

            for(int i = 0; i < count; ++i) {
                UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(i);
                if (!this.getGatherState(ConversationType.GROUP) && uiConversation.getConversationTargetId().equals(groupUserInfo.getGroupId()) && uiConversation.getConversationSenderId().equals(groupUserInfo.getUserId())) {
                    uiConversation.updateConversation(groupUserInfo);
                }
            }

            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(UserInfo userInfo) {
        RLog.i(TAG, "UserInfo " + userInfo.getUserId() + " " + userInfo.getName());
        int count = this.mAdapter.getCount();

        for(int i = 0; i < count && userInfo.getName() != null; ++i) {
            UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(i);
            if (uiConversation.hasNickname(userInfo.getUserId())) {
                RLog.i(TAG, "has nick name");
            } else {
                uiConversation.updateConversation(userInfo);
            }
        }

        this.mAdapter.notifyDataSetChanged();
    }

    public void onEventMainThread(PublicServiceProfile profile) {
        RLog.d(TAG, "PublicServiceProfile");
        int count = this.mAdapter.getCount();
        boolean gatherState = this.getGatherState(profile.getConversationType());

        for(int i = 0; i < count; ++i) {
            UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(i);
            if (uiConversation.getConversationType().equals(profile.getConversationType()) && uiConversation.getConversationTargetId().equals(profile.getTargetId()) && !gatherState) {
                uiConversation.setUIConversationTitle(profile.getName());
                uiConversation.setIconUrl(profile.getPortraitUri());
                this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition() + this.mList.getHeaderViewsCount()), this.mList);
                break;
            }
        }

    }

    public void onEventMainThread(PublicServiceFollowableEvent event) {
        RLog.d(TAG, "PublicServiceFollowableEvent");
        if (!event.isFollow()) {
            int originalIndex = this.mAdapter.findPosition(event.getConversationType(), event.getTargetId());
            if (originalIndex >= 0) {
                this.mAdapter.remove(originalIndex);
                this.mAdapter.notifyDataSetChanged();
            }
        }

    }

    public void onEventMainThread(ConversationUnreadEvent unreadEvent) {
        RLog.d(TAG, "ConversationUnreadEvent");
        ConversationType conversationType = unreadEvent.getType();
        String targetId = unreadEvent.getTargetId();
        int position = this.getGatherState(conversationType) ? this.mAdapter.findGatheredItem(conversationType) : this.mAdapter.findPosition(conversationType, targetId);
        if (position >= 0) {
            UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(position);
            uiConversation.clearUnRead(conversationType, targetId);
            this.mAdapter.notifyDataSetChanged();
        }

        this.onUnreadCountChanged();
    }

    public void onEventMainThread(ConversationTopEvent setTopEvent) {
        RLog.d(TAG, "ConversationTopEvent");
        ConversationType conversationType = setTopEvent.getConversationType();
        String targetId = setTopEvent.getTargetId();
        int position = this.mAdapter.findPosition(conversationType, targetId);
        if (position >= 0 && !this.getGatherState(conversationType)) {
            UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(position);
            if (uiConversation.isTop() != setTopEvent.isTop()) {
                uiConversation.setTop(!uiConversation.isTop());
                this.mAdapter.remove(position);
                int index = this.getPosition(uiConversation);
                this.mAdapter.add(uiConversation, index);
                if (index == position) {
                    this.mAdapter.getView(index, this.mList.getChildAt(index - this.mList.getFirstVisiblePosition() + this.mList.getHeaderViewsCount()), this.mList);
                } else {
                    this.mAdapter.notifyDataSetChanged();
                }
            }
        }

    }

    public void onEventMainThread(ConversationRemoveEvent removeEvent) {
        RLog.d(TAG, "ConversationRemoveEvent");
        ConversationType conversationType = removeEvent.getType();
        this.removeConversation(conversationType, removeEvent.getTargetId());
    }

    public void onEventMainThread(ClearConversationEvent clearConversationEvent) {
        RLog.d(TAG, "ClearConversationEvent");
        List<ConversationType> typeList = clearConversationEvent.getTypes();

        for(int i = this.mAdapter.getCount() - 1; i >= 0; --i) {
            if (typeList.indexOf(((UIConversation)this.mAdapter.getItem(i)).getConversationType()) >= 0) {
                this.mAdapter.remove(i);
            }
        }

        this.mAdapter.notifyDataSetChanged();
        this.onUnreadCountChanged();
    }

    public void onEventMainThread(MessageDeleteEvent event) {
        RLog.d(TAG, "MessageDeleteEvent");
        int count = this.mAdapter.getCount();

        for(int i = 0; i < count; ++i) {
            if (event.getMessageIds().contains(((UIConversation)this.mAdapter.getItem(i)).getLatestMessageId())) {
                boolean gatherState = ((UIConversation)this.mAdapter.getItem(i)).getConversationGatherState();
                final String targetId = ((UIConversation)this.mAdapter.getItem(i)).getConversationTargetId();
                if (gatherState) {
                    RongIM.getInstance().getConversationList(new ResultCallback<List<Conversation>>() {
                        public void onSuccess(List<Conversation> conversationList) {
                            if (ConversationListFragment.this.getActivity() != null && !ConversationListFragment.this.getActivity().isFinishing()) {
                                if (conversationList != null && conversationList.size() != 0) {
                                    UIConversation uiConversation = ConversationListFragment.this.makeUIConversation(conversationList);
                                    int oldPos = ConversationListFragment.this.mAdapter.findPosition(uiConversation.getConversationType(), targetId);
                                    if (oldPos >= 0) {
                                        UIConversation originalConversation = (UIConversation) ConversationListFragment.this.mAdapter.getItem(oldPos);
                                        uiConversation.setExtra(originalConversation.getExtra());
                                        ConversationListFragment.this.mAdapter.remove(oldPos);
                                    }

                                    int newIndex = ConversationListFragment.this.getPosition(uiConversation);
                                    ConversationListFragment.this.mAdapter.add(uiConversation, newIndex);
                                    ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                                }
                            }
                        }

                        public void onError(ErrorCode e) {
                        }
                    }, new ConversationType[]{((UIConversation)this.mAdapter.getItem(i)).getConversationType()});
                } else {
                    RongIM.getInstance().getConversation(((UIConversation)this.mAdapter.getItem(i)).getConversationType(), ((UIConversation)this.mAdapter.getItem(i)).getConversationTargetId(), new ResultCallback<Conversation>() {
                        public void onSuccess(Conversation conversation) {
                            if (conversation == null) {
                                RLog.d(ConversationListFragment.TAG, "onEventMainThread getConversation : onSuccess, conversation = null");
                            } else {
                                UIConversation uiConversation = UIConversation.obtain(ConversationListFragment.this.getActivity(), conversation, false);
                                int pos = ConversationListFragment.this.mAdapter.findPosition(conversation.getConversationType(), conversation.getTargetId());
                                if (pos >= 0) {
                                    UIConversation originalConversation = (UIConversation) ConversationListFragment.this.mAdapter.getItem(pos);
                                    uiConversation.setExtra(originalConversation.getExtra());
                                    ConversationListFragment.this.mAdapter.remove(pos);
                                }

                                int newIndex = ConversationListFragment.this.getPosition(uiConversation);
                                ConversationListFragment.this.mAdapter.add(uiConversation, newIndex);
                                ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                            }
                        }

                        public void onError(ErrorCode e) {
                        }
                    });
                }
                break;
            }
        }

    }

    public void onEventMainThread(ConversationNotificationEvent notificationEvent) {
        int originalIndex = this.mAdapter.findPosition(notificationEvent.getConversationType(), notificationEvent.getTargetId());
        if (originalIndex >= 0) {
            UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(originalIndex);
            if (!uiConversation.getNotificationStatus().equals(notificationEvent.getStatus())) {
                uiConversation.setNotificationStatus(notificationEvent.getStatus());
                this.mAdapter.getView(originalIndex, this.mList.getChildAt(originalIndex - this.mList.getFirstVisiblePosition() + this.mList.getHeaderViewsCount()), this.mList);
            }

            this.onUnreadCountChanged();
        }

    }

    public void onEventMainThread(MessagesClearEvent clearMessagesEvent) {
        RLog.d(TAG, "MessagesClearEvent");
        ConversationType conversationType = clearMessagesEvent.getType();
        String targetId = clearMessagesEvent.getTargetId();
        int position = this.getGatherState(conversationType) ? this.mAdapter.findGatheredItem(conversationType) : this.mAdapter.findPosition(conversationType, targetId);
        if (position >= 0) {
            UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(position);
            uiConversation.clearLastMessage();
            this.mAdapter.getView(position, this.mList.getChildAt(position - this.mList.getFirstVisiblePosition() + this.mList.getHeaderViewsCount()), this.mList);
        }

    }

    public void onEventMainThread(OnMessageSendErrorEvent sendErrorEvent) {
        Message message = sendErrorEvent.getMessage();
        ConversationType conversationType = message.getConversationType();
        String targetId = message.getTargetId();
        if (this.isConfigured(conversationType)) {
            boolean gathered = this.getGatherState(conversationType);
            int index = gathered ? this.mAdapter.findGatheredItem(conversationType) : this.mAdapter.findPosition(conversationType, targetId);
            if (index >= 0) {
                UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(index);
                message.setSentStatus(SentStatus.FAILED);
                uiConversation.updateConversation(message, gathered);
                this.mAdapter.notifyDataSetChanged();
            }
        }

    }

    public void onEventMainThread(QuitDiscussionEvent event) {
        RLog.d(TAG, "QuitDiscussionEvent");
        this.removeConversation(ConversationType.DISCUSSION, event.getDiscussionId());
    }

    public void onEventMainThread(QuitGroupEvent event) {
        RLog.d(TAG, "QuitGroupEvent");
        this.removeConversation(ConversationType.GROUP, event.getGroupId());
    }

    private void removeConversation(final ConversationType conversationType, String targetId) {
        boolean gathered = this.getGatherState(conversationType);
        int index;
        if (gathered) {
            index = this.mAdapter.findGatheredItem(conversationType);
            if (index >= 0) {
                RongIM.getInstance().getConversationList(new ResultCallback<List<Conversation>>() {
                    public void onSuccess(List<Conversation> conversationList) {
                        if (ConversationListFragment.this.getActivity() != null && !ConversationListFragment.this.getActivity().isFinishing()) {
                            int oldPos = ConversationListFragment.this.mAdapter.findGatheredItem(conversationType);
                            if (oldPos >= 0) {
                                ConversationListFragment.this.mAdapter.remove(oldPos);
                                if (conversationList != null && conversationList.size() > 0) {
                                    UIConversation uiConversation = ConversationListFragment.this.makeUIConversation(conversationList);
                                    int newIndex = ConversationListFragment.this.getPosition(uiConversation);
                                    ConversationListFragment.this.mAdapter.add(uiConversation, newIndex);
                                }

                                ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                                ConversationListFragment.this.onUnreadCountChanged();
                            }

                        }
                    }

                    public void onError(ErrorCode e) {
                    }
                }, new ConversationType[]{conversationType});
            }
        } else {
            index = this.mAdapter.findPosition(conversationType, targetId);
            if (index >= 0) {
                this.mAdapter.remove(index);
                this.mAdapter.notifyDataSetChanged();
                this.onUnreadCountChanged();
            }
        }

    }

    public void onPortraitItemClick(View v, UIConversation data) {
        ConversationType type = data.getConversationType();
        if (this.getGatherState(type)) {
            RongIM.getInstance().startSubConversationList(this.getActivity(), type);
        } else {
            if (RongContext.getInstance().getConversationListBehaviorListener() != null) {
                boolean isDefault = RongContext.getInstance().getConversationListBehaviorListener().onConversationPortraitClick(this.getActivity(), type, data.getConversationTargetId());
                if (isDefault) {
                    return;
                }
            }

            data.setUnReadMessageCount(0);
            RongIM.getInstance().startConversation(this.getActivity(), type, data.getConversationTargetId(), data.getUIConversationTitle());
        }

    }

    public boolean onPortraitItemLongClick(View v, UIConversation data) {
        ConversationType type = data.getConversationType();
        if (RongContext.getInstance().getConversationListBehaviorListener() != null) {
            boolean isDealt = RongContext.getInstance().getConversationListBehaviorListener().onConversationPortraitLongClick(this.getActivity(), type, data.getConversationTargetId());
            if (isDealt) {
                return true;
            }
        }

        if (!this.getGatherState(type)) {
            this.buildMultiDialog(data);
            return true;
        } else {
            this.buildSingleDialog(data);
            return true;
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Long previousClickTimestamp = (Long)view.getTag(R.id.rc_debounceClick_last_timestamp);
        long currentTimestamp = SystemClock.uptimeMillis();
        view.setTag(R.id.rc_debounceClick_last_timestamp, currentTimestamp);
        if (previousClickTimestamp == null || Math.abs(currentTimestamp - previousClickTimestamp) > 1000L) {
            int realPosition = position - this.mList.getHeaderViewsCount();
            if (realPosition >= 0 && realPosition < this.mAdapter.getCount()) {
                UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(realPosition);
                ConversationType conversationType = uiConversation.getConversationType();
                if (this.getGatherState(conversationType)) {
                    RongIM.getInstance().startSubConversationList(this.getActivity(), conversationType);
                } else {
                    if (RongContext.getInstance().getConversationListBehaviorListener() != null && RongContext.getInstance().getConversationListBehaviorListener().onConversationClick(this.getActivity(), view, uiConversation)) {
                        return;
                    }

                    uiConversation.setUnReadMessageCount(0);
                    RongIM.getInstance().startConversation(this.getActivity(), conversationType, uiConversation.getConversationTargetId(), uiConversation.getUIConversationTitle());
                }
            }
        }

    }

    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        int realPosition = position - this.mList.getHeaderViewsCount();
        if (realPosition >= 0 && realPosition < this.mAdapter.getCount()) {
            UIConversation uiConversation = (UIConversation)this.mAdapter.getItem(realPosition);
            if (RongContext.getInstance().getConversationListBehaviorListener() != null) {
                boolean isDealt = RongContext.getInstance().getConversationListBehaviorListener().onConversationLongClick(this.getActivity(), view, uiConversation);
                if (isDealt) {
                    return true;
                }
            }

            if (!this.getGatherState(uiConversation.getConversationType())) {
                this.buildMultiDialog(uiConversation);
                return true;
            } else {
                this.buildSingleDialog(uiConversation);
                return true;
            }
        } else {
            return false;
        }
    }

    private void buildMultiDialog(final UIConversation uiConversation) {
        String[] items = new String[2];
        if (uiConversation.isTop()) {
            items[0] = RongContext.getInstance().getString(string.rc_conversation_list_dialog_cancel_top);
        } else {
            items[0] = RongContext.getInstance().getString(string.rc_conversation_list_dialog_set_top);
        }

        items[1] = RongContext.getInstance().getString(string.rc_conversation_list_dialog_remove);
        OptionsPopupDialog.newInstance(this.getActivity(), items).setOptionsPopupDialogListener(new OnOptionsItemClickedListener() {
            public void onOptionsItemClicked(int which) {
                if (which == 0) {
                    RongIM.getInstance().setConversationToTop(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), !uiConversation.isTop(), new ResultCallback<Boolean>() {
                        public void onSuccess(Boolean aBoolean) {
                            if (uiConversation.isTop()) {
                                Toast.makeText(RongContext.getInstance(), ConversationListFragment.this.getString(string.rc_conversation_list_popup_cancel_top), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RongContext.getInstance(), ConversationListFragment.this.getString(string.rc_conversation_list_dialog_set_top), Toast.LENGTH_SHORT).show();
                            }

                        }

                        public void onError(ErrorCode e) {
                        }
                    });
                } else if (which == 1) {
                    RongIM.getInstance().removeConversation(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), (ResultCallback)null);
                }

            }
        }).show();
    }

    private void buildSingleDialog(final UIConversation uiConversation) {
        String[] items = new String[]{RongContext.getInstance().getString(string.rc_conversation_list_dialog_remove)};
        OptionsPopupDialog.newInstance(this.getActivity(), items).setOptionsPopupDialogListener(new OnOptionsItemClickedListener() {
            public void onOptionsItemClicked(int which) {
                RongIM.getInstance().getConversationList(new ResultCallback<List<Conversation>>() {
                    public void onSuccess(List<Conversation> conversations) {
                        if (conversations != null && conversations.size() > 0) {
                            Iterator var2 = conversations.iterator();

                            while(var2.hasNext()) {
                                Conversation conversation = (Conversation)var2.next();
                                RongIM.getInstance().removeConversation(conversation.getConversationType(), conversation.getTargetId(), (ResultCallback)null);
                            }
                        }

                    }

                    public void onError(ErrorCode errorCode) {
                    }
                }, new ConversationType[]{uiConversation.getConversationType()});
                int position = ConversationListFragment.this.mAdapter.findGatheredItem(uiConversation.getConversationType());
                ConversationListFragment.this.mAdapter.remove(position);
                ConversationListFragment.this.mAdapter.notifyDataSetChanged();
            }
        }).show();
    }

    private void makeUiConversationList(List<Conversation> conversationList) {
        Iterator var3 = conversationList.iterator();

        while(var3.hasNext()) {
            Conversation conversation = (Conversation)var3.next();
            ConversationType conversationType = conversation.getConversationType();
            String targetId = conversation.getTargetId();
            boolean gatherState = this.getGatherState(conversationType);
            UIConversation uiConversation;
            int originalIndex;
            if (gatherState) {
                originalIndex = this.mAdapter.findGatheredItem(conversationType);
                if (originalIndex >= 0) {
                    uiConversation = (UIConversation)this.mAdapter.getItem(originalIndex);
                    uiConversation.updateConversation(conversation, true);
                } else {
                    uiConversation = UIConversation.obtain(this.getActivity(), conversation, true);
                    this.onUIConversationCreated(uiConversation);
                    this.mAdapter.add(uiConversation);
                }
            } else {
                originalIndex = this.mAdapter.findPosition(conversationType, targetId);
                int index;
                if (originalIndex < 0) {
                    uiConversation = UIConversation.obtain(this.getActivity(), conversation, false);
                    this.onUIConversationCreated(uiConversation);
                    index = this.getPosition(uiConversation);
                    this.mAdapter.add(uiConversation, index);
                } else {
                    uiConversation = (UIConversation)this.mAdapter.getItem(originalIndex);
                    if (uiConversation.getUIConversationTime() <= conversation.getSentTime()) {
                        this.mAdapter.remove(originalIndex);
                        uiConversation.updateConversation(conversation, false);
                        index = this.getPosition(uiConversation);
                        this.mAdapter.add(uiConversation, index);
                    } else {
                        uiConversation.setUnReadMessageCount(conversation.getUnreadMessageCount());
                    }
                }
            }
        }

    }

    private UIConversation makeUIConversation(List<Conversation> conversations) {
        int unreadCount = 0;
        boolean isMentioned = false;
        Conversation newest = (Conversation)conversations.get(0);

        Conversation conversation;
        for(Iterator var5 = conversations.iterator(); var5.hasNext(); unreadCount += conversation.getUnreadMessageCount()) {
            conversation = (Conversation)var5.next();
            if (newest.isTop()) {
                if (conversation.isTop() && conversation.getSentTime() > newest.getSentTime()) {
                    newest = conversation;
                }
            } else if (conversation.isTop() || conversation.getSentTime() > newest.getSentTime()) {
                newest = conversation;
            }

            if (conversation.getMentionedCount() > 0) {
                isMentioned = true;
            }
        }

        UIConversation uiConversation = UIConversation.obtain(this.getActivity(), newest, this.getGatherState(newest.getConversationType()));
        uiConversation.setUnReadMessageCount(unreadCount);
        uiConversation.setTop(false);
        uiConversation.setMentionedFlag(isMentioned);
        return uiConversation;
    }

    private int getPosition(UIConversation uiConversation) {
        int count = this.mAdapter.getCount();
        int position = 0;

        for(int i = 0; i < count; ++i) {
            if (uiConversation.isTop()) {
                if (!((UIConversation)this.mAdapter.getItem(i)).isTop() || ((UIConversation)this.mAdapter.getItem(i)).getUIConversationTime() <= uiConversation.getUIConversationTime()) {
                    break;
                }

                ++position;
            } else {
                if (!((UIConversation)this.mAdapter.getItem(i)).isTop() && ((UIConversation)this.mAdapter.getItem(i)).getUIConversationTime() <= uiConversation.getUIConversationTime()) {
                    break;
                }

                ++position;
            }
        }

        return position;
    }

    private boolean isConfigured(ConversationType conversationType) {
        for(int i = 0; i < this.mConversationsConfig.size(); ++i) {
            if (conversationType.equals(((ConversationListFragment.ConversationConfig)this.mConversationsConfig.get(i)).conversationType)) {
                return true;
            }
        }

        return false;
    }

    public boolean getGatherState(ConversationType conversationType) {
        Iterator var2 = this.mConversationsConfig.iterator();

        ConversationListFragment.ConversationConfig config;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            config = (ConversationListFragment.ConversationConfig)var2.next();
        } while(!config.conversationType.equals(conversationType));

        return config.isGathered;
    }

    private ConversationType[] getConfigConversationTypes() {
        ConversationType[] conversationTypes = new ConversationType[this.mConversationsConfig.size()];

        for(int i = 0; i < this.mConversationsConfig.size(); ++i) {
            conversationTypes[i] = ((ConversationListFragment.ConversationConfig)this.mConversationsConfig.get(i)).conversationType;
        }

        return conversationTypes;
    }

    public void onLoad() {
        String rid = SPTool.getSessionValue("rid");
        setUserInfo(rid);
        this.getConversationList(this.getConfigConversationTypes(), true);
    }

    public void onFlush() {
        String rid = SPTool.getSessionValue("rid");
        setUserInfo(rid);
        this.getConversationList(this.getConfigConversationTypes(), false);
    }

    public void onDestroy() {
        EventBus.getDefault().unregister(this.mThis);
        this.cacheEventList.clear();
        super.onDestroy();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001 && grantResults.length > 0 && grantResults[0] == 0) {
            HQVoiceMsgDownloadManager.getInstance().resumeDownloadService();
        }

    }

    private class ConversationConfig {
        ConversationType conversationType;
        boolean isGathered;

        private ConversationConfig() {
        }
    }
}

