package com.lx.zhaopin.other;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private boolean isHor;

    public SpacesItemDecoration(int space, boolean isHor) {
        this.space = space;
        this.isHor = isHor;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (isHor) {
            if (position == 0) {//第一个
                outRect.left = 0;
                outRect.right = space;
            } else {
                outRect.left = space;
                outRect.right = space;
            }
        } else {
            if (position != 0) {
                outRect.top = space;
            }
        }

    }
}
