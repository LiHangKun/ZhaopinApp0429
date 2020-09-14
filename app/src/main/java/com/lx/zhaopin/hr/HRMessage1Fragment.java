package com.lx.zhaopin.hr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.activity.ShenQingHRListActivity;
import com.lx.zhaopin.bean.PhoneStateBean;
import com.lx.zhaopin.common.AppSP;
import com.lx.zhaopin.common.MessageEvent;
import com.lx.zhaopin.fragment.ConversationListFragment;
import com.lx.zhaopin.http.BaseCallback;
import com.lx.zhaopin.http.OkHttpHelper;
import com.lx.zhaopin.net.NetClass;
import com.lx.zhaopin.net.NetCuiMethod;
import com.lx.zhaopin.utils.SPTool;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import io.rong.imlib.model.Conversation;
import okhttp3.Request;
import okhttp3.Response;

public class HRMessage1Fragment extends Fragment implements View.OnClickListener {

    private LinearLayout llView;
    private TextView tv2;
    private TextView tv3;

    private static final String TAG = "HRMessage1Fragment";
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false)
    public void getEventmessage(MessageEvent event) {
        int messageType = event.getMessageType();
        switch (messageType) {
            case 13:
                getUnMessageNumber();
                Log.e(TAG, "getEventmessage: http 更新未读消息的数量");
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.message1fragment_layout, null);

        if (!EventBus.getDefault().isRegistered(this)) {//判断是否已经注册了（避免崩溃）
            EventBus.getDefault().register(this); //向EventBus注册该对象，使之成为订阅者
        }

        llView = view.findViewById(R.id.llView);
        llView.setOnClickListener(this);

        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);

        ConversationListFragment listFragment = new ConversationListFragment();
        Uri uri;
        uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("/conversationlist")
                .scheme("rong")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .build();
        listFragment.setUri(uri);
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.frame1, listFragment);
        transaction.commit();


        return view;

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (!TextUtils.isEmpty(SPTool.getSessionValue(AppSP.UID))) {
                getUnMessageNumber();
            }
        }
    }

    private void getUnMessageNumber() {
        Map<String, String> params = new HashMap<>();
        params.put("mid", SPTool.getSessionValue(AppSP.UID));
        params.put("hr", "1");
        OkHttpHelper.getInstance().post(getActivity(), NetClass.BASE_URL + NetCuiMethod.newMessageCount, params, new BaseCallback<PhoneStateBean>() {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, PhoneStateBean resultBean) {
                /*messageNumberTv1.setText(resultBean.getChatApplyCount());
                messageNumberTv2.setText(resultBean.getJobFeedbackCount());
                messageNumberTv3.setText(resultBean.getSystemMessageCount());*/

                if (resultBean.getChatApplyCount().equals("0")) {
                    tv3.setVisibility(View.INVISIBLE);
                    llView.setVisibility(View.VISIBLE);
                } else {
                    tv3.setText(resultBean.getChatApplyCount());
                    tv2.setText(resultBean.getChatApplyText());
                    llView.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llView:
                startActivity(new Intent(getActivity(), ShenQingHRListActivity.class));
                break;
        }
    }
}
