package com.lx.zhaopin.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.lx.zhaopin.R;

public class CustomTextView extends AppCompatTextView {
    private GradientDrawable normalGD;
    private GradientDrawable pressedGD;
    private StateListDrawable selector;

    private int normalTextColor;
    private int selectedTextColor;
    private int pressedSolidColor;
    private int pressedStrokeWidth;
    private int pressedStrokeColor;
    private int normalSolidColor;
    private int normalStrokeWidth;
    private int normalStrokeColor;
    private int radius;
    private String defineId;


    public CustomTextView(Context context) {
        super(context);

    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributeSet(context, attrs);
    }

    private CustomTextView(Context context,
                          int normalTextColor,
                          int selectedTextColor,
                          int pressedSolidColor,
                          int pressedStrokeWidth,
                          int pressedStrokeColor,
                          int normalSolidColor,
                          int normalStrokeWidth,
                          int normalStrokeColor,
                          int radius) {
        super(context);
        this.normalTextColor = normalTextColor;
        this.selectedTextColor = selectedTextColor;
        this.pressedSolidColor = pressedSolidColor;
        this.pressedStrokeWidth = pressedStrokeWidth;
        this.pressedStrokeColor = pressedStrokeColor;
        this.normalSolidColor = normalSolidColor;
        this.normalStrokeWidth = normalStrokeWidth;
        this.normalStrokeColor = normalStrokeColor;
        this.radius = radius;
        setSates();
    }

    private void setAttributeSet(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        radius = a.getDimensionPixelSize(R.styleable.CustomTextView_background_corner_radius, 0);
        normalTextColor = a.getColor(R.styleable.CustomTextView_textNormalTextColor, Color.TRANSPARENT);
        selectedTextColor = a.getColor(R.styleable.CustomTextView_textSelectedTextColor, normalTextColor);
        normalSolidColor = a.getColor(R.styleable.CustomTextView_bcNormalSolidColor, Color.TRANSPARENT);
        pressedSolidColor = a.getColor(R.styleable.CustomTextView_bcSelectedSolidColor, normalSolidColor);
        normalStrokeColor = a.getColor(R.styleable.CustomTextView_bcNormalStrokeColor, Color.TRANSPARENT);
        normalStrokeWidth = a.getDimensionPixelSize(R.styleable.CustomTextView_bcNormalStrokeWidth, 0);
        a.recycle();

        setSates();

    }


    private void setSates(){
        selector = new StateListDrawable();
        normalGD = new GradientDrawable();
        pressedGD = new GradientDrawable();

        //set selected state
        setPressedState();

        //set normal state
        setNormalState();

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
     */
    private void setNormalState() {

        //设置正常状态下填充色
        normalGD.setColor(normalSolidColor);
        //设置圆角
        setRadius(normalGD);

        if (normalStrokeColor != 0 && normalStrokeWidth != 0){
            normalGD.setStroke(normalStrokeWidth, normalStrokeColor);
        }

        //normal drawable
        LayerDrawable normalLayerDrawable = new LayerDrawable(new Drawable[]{normalGD});
        //设置正常状态下的drawable
        selector.addState(new int[]{}, normalLayerDrawable);
    }

    /**
     * 设置按下状态drawable
     */
    private void setPressedState() {
        //设置按下填充色
        pressedGD.setColor(pressedSolidColor);
        //设置圆角
        setRadius(pressedGD);

        if (pressedStrokeColor != 0 && pressedStrokeWidth != 0){
            pressedGD.setStroke(pressedStrokeWidth, pressedStrokeColor);
        }

        LayerDrawable pressedLayerDrawable = new LayerDrawable(new Drawable[]{pressedGD});
        selector.addState(new int[]{android.R.attr.state_selected}, pressedLayerDrawable);
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

    public String getDefineId() {
        return defineId;
    }

    public void setDefineId(String defineId) {
        this.defineId = defineId;
    }

    public static class Builder {
        private int normalTextColor;
        private int selectedTextColor;
        private int pressedSolidColor;
        private int pressedStrokeWidth;
        private int pressedStrokeColor;
        private int normalSolidColor;
        private int normalStrokeWidth;
        private int normalStrokeColor;
        private int radius;
        private Context context;

        public Builder(){

        }

        public Builder setNormalTextColor(int normalTextColor) {
            this.normalTextColor = normalTextColor;
            return this;
        }

        public Builder setSelectedTextColor(int selectedTextColor) {
            this.selectedTextColor = selectedTextColor;
            return this;
        }

        public Builder setPressedSolidColor(int pressedSolidColor) {
            this.pressedSolidColor = pressedSolidColor;
            return this;
        }

        public Builder setPressedStrokeWidth(int pressedStrokeWidth) {
            this.pressedStrokeWidth = pressedStrokeWidth;
            return this;
        }

        public Builder setPressedStrokeColor(int pressedStrokeColor) {
            this.pressedStrokeColor = pressedStrokeColor;
            return this;
        }

        public Builder setNormalSolidColor(int normalSolidColor) {
            this.normalSolidColor = normalSolidColor;
            return this;
        }

        public Builder setNormalStrokeWidth(int normalStrokeWidth) {
            this.normalStrokeWidth = normalStrokeWidth;
            return this;
        }

        public Builder setNormalStrokeColor(int normalStrokeColor) {
            this.normalStrokeColor = normalStrokeColor;
            return this;
        }

        public Builder setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public CustomTextView createView() {
            return new CustomTextView(context,
                    normalTextColor,
                    selectedTextColor,
                    pressedSolidColor,
                    pressedStrokeWidth,
                    pressedStrokeColor,
                    normalSolidColor,
                    normalStrokeWidth,
                    normalStrokeColor,
                    radius);
        }
    }
}
