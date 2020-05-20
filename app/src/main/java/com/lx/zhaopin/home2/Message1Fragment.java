package com.lx.zhaopin.home2;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lx.zhaopin.R;
import com.lx.zhaopin.rong.ConversationListAdapterEx;
import com.lx.zhaopin.rong.MyPagerAdapter;

import java.util.ArrayList;

import io.rong.imkit.RongContext;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

public class Message1Fragment extends Fragment {

    private ConversationListFragment mConversationListFragment = null;
    private ArrayList<Fragment> fragments;
    private MyPagerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.message1fragment_layout, null);

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



       /* ViewPager viewPager = view.findViewById(R.id.viewPager);

        fragments = new ArrayList<>();
        Fragment conversationList = initConversationList();
        fragments.add(conversationList);
        adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        if (viewPager.getAdapter() == null) {
            viewPager.setAdapter(adapter);
        }*/



        return view;

    }


    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
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
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }
    }

}
