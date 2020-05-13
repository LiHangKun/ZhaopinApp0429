package com.lx.zhaopin.hr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lx.zhaopin.R;

public class HRMessage3Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       /* TextView textView = new TextView(container.getContext());
        textView.setText("系统消息----333");
        textView.setTextSize(50);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(container.getContext().getResources().getColor(R.color.mainColor));*/

        View view = View.inflate(container.getContext(), R.layout.message3fragment_layout, null);

        return view;

    }
}
