package com.lx.zhaopin.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.lx.zhaopin.R;

public class CustomTextView extends AppCompatTextView {
    private GradientDrawable normalGD;
    private GradientDrawable pressedGD;
    private StateListDrawable selector;

    private int strokeWidth;
    private int strokeColor;
    private int radius;
    private int pressedSolidColor;
    private int normalSolidColor;


    public CustomTextView(Context context) {
        this(context, null);

    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributeSet(context, attrs);
    }


    private void setAttributeSet(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
//        int solid = a.getColor(R.styleable.customTextView_textSolidColor, Color.TRANSPARENT);
        radius = a.getDimensionPixelSize(R.styleable.CustomTextView_background_corner_radius, 0);
        int normalTextColor = a.getColor(R.styleable.CustomTextView_textNormalTextColor, Color.TRANSPARENT);
        int selectedTextColor = a.getColor(R.styleable.CustomTextView_textSelectedTextColor, normalTextColor);
        normalSolidColor = a.getColor(R.styleable.CustomTextView_bcNormalSolidColor, Color.TRANSPARENT);
        pressedSolidColor = a.getColor(R.styleable.CustomTextView_bcSelectedSolidColor, normalSolidColor);
        int normalStrokeColor = a.getColor(R.styleable.CustomTextView_bcNormalStrokeColor, Color.TRANSPARENT);
        int normalStrokeWidth = a.getDimensionPixelSize(R.styleable.CustomTextView_bcNormalStrokeWidth, 0);
        a.recycle();

        selector = new StateListDrawable();
        normalGD = new GradientDrawable();
        pressedGD = new GradientDrawable();

        //set selected state
        setPressedState(pressedSolidColor);

        //set normal state
        setNormalState(normalSolidColor);

        setNormalStroke(strokeColor, strokeWidth);
        //设置selector
        setBackgroundDrawable(selector);


        if (normalTextColor != 0 && selectedTextColor != 0) {
            //设置state_selected状态时，和正常状态时文字的颜色
            setClickable(true);
            ColorStateList textColorSelect = null;
            int[][] states = new int[3][1];
            states[0] = new int[]{android.R.attr.state_selected};
            states[1] = new int[]{android.R.attr.state_pressed};
            states[2] = new int[]{};
            textColorSelect = new ColorStateList(states, new int[]{selectedTextColor, selectedTextColor, normalTextColor});
            setTextColor(textColorSelect);
        } else {
            setClickable(false);
        }
    }

    /**
     * 设置正常状态下drawable
     *
     * @param normalSolid 正常状态下填充颜色
     */
    private void setNormalState(int normalSolid) {

        //设置正常状态下填充色
        normalGD.setColor(normalSolid);
        //设置圆角
        setRadius(normalGD);

        //normal drawable
        LayerDrawable normalLayerDrawable = new LayerDrawable(new Drawable[]{normalGD});
        //设置正常状态下的drawable
        selector.addState(new int[]{}, normalLayerDrawable);
    }

    private void setNormalStroke(int color, int width) {
        if (color != Color.TRANSPARENT) {
            normalGD.setStroke(width, color);
        }
    }

    /**
     * 设置按下状态drawable
     *
     * @param pressedSolid 按下状态填充色
     */
    private void setPressedState(int pressedSolid) {

        if (pressedSolid != Color.TRANSPARENT) {
            //设置按下填充色
            pressedGD.setColor(pressedSolid);
            //设置圆角
            setRadius(pressedGD);

            LayerDrawable pressedLayerDrawable = new LayerDrawable(new Drawable[]{pressedGD});
            selector.addState(new int[]{android.R.attr.state_selected}, pressedLayerDrawable);
        }
    }

    /**
     * 设置角度
     *
     * @param drawable drawable
     */
    private void setRadius(GradientDrawable drawable) {
        if (radius != 0) {
            drawable.setCornerRadius(radius);
        }
    }

    public void setRadius(int radius) {
        if (radius != 0) {
            this.radius = radius;
            setRadius(normalGD);
            setRadius(pressedGD);
        }
    }


    /**
     * 设置填充颜色
     *
     * @param colorId 颜色id
     */
    public void setSolidColor(int colorId) {
        normalGD.setColor(colorId);
        setBackgroundDrawable(normalGD);
    }

    public void setPressedStrokeWidth(int width) {
        this.strokeWidth = width;
        setPressedStroke();
    }

    public void setPressedStrokeColor(int color) {
        this.strokeColor = color;
        setPressedStroke();
    }

    private void setPressedStroke() {
        if (strokeColor != 0 && strokeWidth != 0) {
            pressedGD.setStroke(strokeWidth, strokeColor);
            //set selected state
            if (pressedSolidColor == 0) {
                LayerDrawable pressedLayerDrawable = new LayerDrawable(new Drawable[]{pressedGD});
                selector.addState(new int[]{android.R.attr.state_selected}, pressedLayerDrawable);
            }
        }
    }


    /**
     * 设置textView选中状态颜色
     *
     * @param normalTextColor   正常状态颜色
     * @param selectedTextColor 按下状态颜色
     */
    public void setSelectedTextColor(int normalTextColor, int selectedTextColor) {

        if (normalTextColor != 0 && selectedTextColor != 0) {
            //设置state_selected状态时，和正常状态时文字的颜色
            setClickable(true);
            int[][] states = new int[3][1];
            states[0] = new int[]{android.R.attr.state_selected};
            states[1] = new int[]{android.R.attr.state_pressed};
            states[2] = new int[]{};
            ColorStateList textColorSelect = new ColorStateList(states, new int[]{selectedTextColor, selectedTextColor, normalTextColor});
            setTextColor(textColorSelect);
        } else {
            setClickable(false);
        }

    }
}
