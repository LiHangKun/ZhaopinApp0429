package com.lx.zhaopin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lx.zhaopin.R;

public class CustomLinearLayout extends LinearLayout {

    private int solidColor;
    private int strokeColor;
    private int radius;
    private int strokeWidth, strokeGapWidth;
    private Paint mPaint;
    private int w, h;

    public CustomLinearLayout(Context context) {
        super(context);

    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributeSet(context, attrs);
        init();
    }


    private void setAttributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLinearLayout);
        solidColor = typedArray.getColor(R.styleable.CustomLinearLayout_linearBcSolidColor, Color.TRANSPARENT);
        strokeColor = typedArray.getColor(R.styleable.CustomLinearLayout_linearBcStrokeColor, Color.TRANSPARENT);
        radius = typedArray.getDimensionPixelOffset(R.styleable.CustomLinearLayout_linearBc_corner_radius, 0);
        strokeGapWidth = typedArray.getDimensionPixelOffset(R.styleable.CustomLinearLayout_linearBcStrokeGapWidth, 0);
        strokeWidth = typedArray.getDimensionPixelOffset(R.styleable.CustomLinearLayout_linearBcStrokeWidth, 0);
        typedArray.recycle();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (h != oldh || w != oldw) {
            this.w = w;
            this.h = h;
            invalidate();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(solidColor);

        canvas.drawRoundRect(0, 0, w, h, radius, radius, mPaint);
        if (strokeWidth != 0) {
            mPaint.setColor(strokeColor);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setPathEffect(new DashPathEffect(new float[]{strokeWidth, strokeGapWidth}, 0));
            canvas.drawRoundRect(strokeWidth, strokeWidth, w-strokeWidth, h-strokeWidth, radius, radius, mPaint);
        }
        super.dispatchDraw(canvas);
    }
}
