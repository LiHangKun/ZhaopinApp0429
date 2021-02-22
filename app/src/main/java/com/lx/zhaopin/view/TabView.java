package com.lx.zhaopin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lx.zhaopin.R;
import com.lx.zhaopin.utils.DisplayUtil;

public class TabView extends RelativeLayout {
    private Context mContext;
    private ImageView iconImg;
    private TextView tabNameTextView;
    private TextView tabMessageNum;
    private Runnable runnable;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setClipChildren(false);
        setClipToPadding(false);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_tab_view, this);
        iconImg = view.findViewById(R.id.tab_img);
        tabNameTextView = view.findViewById(R.id.tab_name);
        getAttr(attrs);
    }

    public void setValues(int iconId, String tabName, TextView tabMessageNum) {
        iconImg.setImageResource(iconId);
        tabNameTextView.setText(tabName);
        this.tabMessageNum = tabMessageNum;
        runnable = new Runnable() {
            @Override
            public void run() {
                tabNameTextView.setVisibility(VISIBLE);
            }
        };
    }

    public void setSelected(boolean isSelected) {
        if (isSelected) {
            iconImg.animate().translationXBy(-DisplayUtil.dip2px(mContext, 20)).scaleX(0.8f).scaleY(0.8f).setDuration(200).start();
            if (tabMessageNum != null && tabMessageNum.getVisibility() == VISIBLE) {
                tabMessageNum.animate().translationX(0).setDuration(200).start();
            }
            postDelayed(runnable, 200);
        } else {
            if (runnable != null)
                removeCallbacks(runnable);
            if (iconImg.getTranslationX() != 0) {
                tabNameTextView.setVisibility(GONE);
                iconImg.animate().translationX(0).scaleX(1f).scaleY(1f).setDuration(200).start();

                if (tabMessageNum != null && tabMessageNum.getVisibility() == VISIBLE) {
                    tabMessageNum.animate().translationXBy(-DisplayUtil.dip2px(mContext, 30)).setDuration(200).start();
                }
            }

        }
    }

    private void getAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.TabView);
        int iconId = typedArray.getResourceId(R.styleable.TabView_icon, 0);
        String tabName = typedArray.getString(R.styleable.TabView_tabName);
        iconImg.setImageResource(iconId);
        tabNameTextView.setText(tabName);
    }
}
