package com.lx.zhaopin.other;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.zhaopin.utils.DisplayUtil;
import com.lx.zhaopin.view.CustomTextView;

import java.util.List;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.CustomViewHolder> {
    private List<String> strs;
    private Context mContext;

    public SkillsAdapter(Context context, List<String> strs) {
        this.strs = strs;
        mContext = context;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CustomTextView textView = new CustomTextView.Builder().setContext(mContext)
                .setRadius(DisplayUtil.dip2px(mContext, 8))
                .setNormalStrokeColor(Color.parseColor("#1678FF"))
                .setNormalStrokeWidth(DisplayUtil.dip2px(mContext, 1))
                .createView();
        textView.setTextColor(Color.parseColor("#1678FF"));
        textView.setTextSize(10);
        textView.setPadding(DisplayUtil.dip2px(mContext, 18),
                DisplayUtil.dip2px(mContext, 1),
                DisplayUtil.dip2px(mContext, 18),
                DisplayUtil.dip2px(mContext, 1));
        return new CustomViewHolder(textView);
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, int i) {
        viewHolder.textView.setText(strs.get(i));
    }


    @Override
    public int getItemCount() {
        if (strs == null)
            return 0;
        return strs.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = (TextView) itemView;
        }
    }
}
