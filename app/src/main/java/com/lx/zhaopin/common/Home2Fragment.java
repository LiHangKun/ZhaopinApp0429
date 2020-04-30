package com.lx.zhaopin.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.base.BaseFragment;

public class Home2Fragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(container.getContext());
        textView.setTextSize(50);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(container.getContext().getResources().getColor(R.color.mainColor));
        textView.setText("2");

        return textView;

    }
}
