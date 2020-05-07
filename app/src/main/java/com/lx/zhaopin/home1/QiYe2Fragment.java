package com.lx.zhaopin.home1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lx.zhaopin.R;

public class QiYe2Fragment extends Fragment {

    private static final String TAG = "QiYe1Fragment";

    public static Fragment newInstance(String id) {
        QiYe2Fragment fragment = new QiYe2Fragment();
        String shopJiaID = id;
        Log.i(TAG, "newInstance: 企业ID" + shopJiaID);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.qiye2fragment_layout, null);


        return view;

    }

}
