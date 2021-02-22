package com.lx.zhaopin.other;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.util.ConvertUtils;
import cn.addapp.pickers.util.LogUtils;

public class WheelListView extends ListView implements AbsListView.OnScrollListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    public static final int SMOOTH_SCROLL_DURATION = 50;
    public static final int SECTION_DELAY = 300;
    public static final int TEXT_SIZE = 16;
    public static final float TEXT_ALPHA = 0.8F;
    public static final int TEXT_COLOR_FOCUS = -16611122;
    public static final int TEXT_COLOR_NORMAL = -4473925;
    public static final int ITEM_OFF_SET = 2;
    public static final int ITEM_HEIGHT = 40;
    public static final int ITEM_PADDING_TOP_BOTTOM = 5;
    public static final int ITEM_PADDING_LEFT_RIGHT = 10;
    public static final int ITEM_MARGIN = 5;
    public static final int ITEM_TAG_IMAGE = 100;
    public static final int ITEM_TAG_TEXT = 101;
    private static final int MATCH_PARENT = -1;
    private static final int WRAP_CONTENT = -2;
    private int itemHeightPixels = 0;
    private int currentPosition = -1;
    private WheelListView.WheelAdapter adapter = new WheelListView.WheelAdapter();
    private WheelListView.OnWheelChangeListener onWheelChangeListener;
    private int textSize = 16;
    private int textColorNormal = -4473925;
    private int textColorFocus = -16611122;
    private LineConfig lineConfig = null;

    public WheelListView(Context context) {
        super(context);
        this.init();
    }

    public WheelListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public WheelListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {
        this.setVerticalScrollBarEnabled(false);
        this.setScrollingCacheEnabled(false);
        this.setCacheColorHint(0);
        this.setFadingEdgeLength(0);
        this.setOverScrollMode(2);
        this.setLayoutParams(new LayoutParams(-2, -2));
        this.setDividerHeight(0);
        this.setOnScrollListener(this);
        this.setOnTouchListener(this);
        if (Build.VERSION.SDK_INT >= 21) {
            this.setNestedScrollingEnabled(true);
        }

        if (!this.isInEditMode()) {
            this.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        super.setAdapter(this.adapter);
    }

    private void changeBackground() {
        int wheelSize = this.adapter.getWheelSize();
        if (null == this.lineConfig) {
            this.lineConfig = new LineConfig();
        }

        this.lineConfig.setWidth(this.getWidth());
        this.lineConfig.setHeight(this.itemHeightPixels * wheelSize);
        this.lineConfig.setWheelSize(wheelSize);
        this.lineConfig.setItemHeight(this.itemHeightPixels);
        WheelListView.WheelDrawable holoWheelDrawable = new WheelListView.HoloWheelDrawable(this.lineConfig);
        Object drawable;
        if (this.lineConfig.isShadowVisible()) {
            WheelListView.WheelDrawable shadowWheelDrawable = new WheelListView.ShadowWheelDrawable(this.lineConfig);
            if (this.lineConfig.isVisible()) {
                drawable = new LayerDrawable(new Drawable[]{shadowWheelDrawable, holoWheelDrawable});
            } else {
                drawable = shadowWheelDrawable;
            }
        } else if (this.lineConfig.isVisible()) {
            drawable = holoWheelDrawable;
        } else {
            drawable = new WheelListView.WheelDrawable(this.lineConfig);
        }

        if (Build.VERSION.SDK_INT >= 16) {
            super.setBackground((Drawable) drawable);
        } else {
            super.setBackgroundDrawable((Drawable) drawable);
        }

    }

    private void _setItems(List<String> list) {
        if (null != list && list.size() != 0) {
            this.currentPosition = -1;
            this.adapter.setData(list);
        } else {
            throw new IllegalArgumentException("data are empty");
        }
    }

    public void setItems(List<String> list) {
        this._setItems(list);
        this.setSelectedIndex(0);
    }

    public void setItems(String[] list) {
        this.setItems(Arrays.asList(list));
    }

    public void setItems(List<String> list, int index) {
        this._setItems(list);
        this.setSelectedIndex(index);
    }

    public void setItems(List<String> list, String item) {
        this._setItems(list);
        this.setSelectedItem(item);
    }

    public void setItems(String[] list, int index) {
        this.setItems(Arrays.asList(list), index);
    }

    public void setItems(String[] list, String item) {
        this.setItems(Arrays.asList(list), item);
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setUnSelectedTextColor(@ColorInt int unSelectedTextColor) {
        if (unSelectedTextColor != 0) {
            this.textColorNormal = unSelectedTextColor;
            this.refreshCurrentPosition();
        }

    }

    public void setSelectedTextColor(@ColorInt int selectedTextColor) {
        if (selectedTextColor != 0) {
            this.textColorFocus = selectedTextColor;
            this.refreshCurrentPosition();
        }

    }

    public void setOffset(@IntRange(from = 1L, to = 3L) int offset) {
        if (offset >= 1 && offset <= 3) {
            int wheelSize = offset * 2 + 1;
            this.adapter.setWheelSize(wheelSize);
        } else {
            throw new IllegalArgumentException("Offset must between 1 and 3");
        }
    }

    public void setCanLoop(boolean canLoop) {
        this.adapter.setLoop(canLoop);
    }

    public int getSelectedIndex() {
        return this.getCurrentPosition();
    }

    public void setSelectedIndex(int index) {
        final int realPosition = this.getRealPosition(index);
        this.postDelayed(new Runnable() {
            public void run() {
                WheelListView.super.setSelection(realPosition);
                WheelListView.this.refreshCurrentPosition();
            }
        }, 300L);
    }

    public String getSelectedItem() {
        return this.adapter.getItem(this.getCurrentPosition());
    }

    public void setSelectedItem(String item) {
        this.setSelectedIndex(this.adapter.getData().indexOf(item));
    }

    private int getRealPosition(int position) {
        int realCount = this.adapter.getRealCount();
        if (realCount == 0) {
            return 0;
        } else if (this.adapter.isLoop()) {
            int d = 1073741823 / realCount;
            return position + d * realCount - this.adapter.getWheelSize() / 2;
        } else {
            return position;
        }
    }

    public int getCurrentPosition() {
        return this.currentPosition == -1 ? 0 : this.currentPosition;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setAdapter(ListAdapter adapter) {
        if (adapter != null && adapter instanceof WheelListView.WheelAdapter) {
            this.adapter = (WheelListView.WheelAdapter) adapter;
            super.setAdapter(this.adapter);
        } else {
            throw new IllegalArgumentException("please invoke setItems");
        }
    }

    private void onSelectedCallback() {
        int index = this.getSelectedIndex();
        String item = this.getSelectedItem();
        if (null != this.onWheelChangeListener) {
            this.onWheelChangeListener.onItemSelected(index, item);
        }

    }

    private int obtainSmoothDistance(float scrollDistance) {
        if (Math.abs(scrollDistance) <= 2.0F) {
            return (int) scrollDistance;
        } else if (Math.abs(scrollDistance) < 12.0F) {
            return scrollDistance > 0.0F ? 2 : -2;
        } else {
            return (int) (scrollDistance / 6.0F);
        }
    }

    private void refreshCurrentPosition() {
        if (this.getChildAt(0) != null && this.itemHeightPixels != 0) {
            int firstPosition = this.getFirstVisiblePosition();
            if (this.adapter.isLoop() && firstPosition == 0) {
                LogUtils.verbose("is loop and first visible position is 0");
            } else {
                int position;
                if (Math.abs(this.getChildAt(0).getY()) <= (float) (this.itemHeightPixels / 2)) {
                    position = firstPosition;
                } else {
                    position = firstPosition + 1;
                }

                int offset = (this.adapter.getWheelSize() - 1) / 2;
                int curPosition = position + offset;
                this.refreshVisibleItems(firstPosition, curPosition, offset);
                if (this.adapter.isLoop()) {
                    position = curPosition % this.adapter.getRealCount();
                }

                if (position != this.currentPosition) {
                    this.currentPosition = position;
                    this.onSelectedCallback();
                }
            }
        }
    }

    private void refreshVisibleItems(int firstPosition, int curPosition, int offset) {
        for (int i = curPosition - offset; i <= curPosition + offset; ++i) {
            View itemView = this.getChildAt(i - firstPosition);
            if (itemView != null) {
                TextView textView = (TextView) itemView.findViewWithTag(101);
                this.refreshTextView(i, curPosition, itemView, textView);
            }
        }

    }

    /**
    * 改变字体大小
     */
    private void refreshTextView(int position, int curPosition, View itemView, TextView textView) {
        if (curPosition == position) {
            this.setTextView(itemView, textView, this.textColorFocus, (float) this.textSize, 0);
        } else {
            int delta = Math.abs(position - curPosition);
            this.setTextView(itemView, textView, this.textColorNormal, (float) this.textSize, delta);
        }

    }

    private void setTextView(View itemView, TextView textView, int textColor, float textSize, int delta) {
        textView.setTextColor(textColor);
        textView.setTextSize(textSize - delta * 2);
    }

    public void onGlobalLayout() {
        if (Build.VERSION.SDK_INT >= 16) {
            this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } else {
            this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }

        int childCount = this.getChildCount();
        if (childCount > 0 && this.itemHeightPixels == 0) {
            this.itemHeightPixels = this.getChildAt(0).getHeight();
            LogUtils.verbose(this, "itemHeightPixels=" + this.itemHeightPixels);
            if (this.itemHeightPixels == 0) {
                return;
            }

            int wheelSize = this.adapter.getWheelSize();
            ViewGroup.LayoutParams params = this.getLayoutParams();
            params.height = this.itemHeightPixels * wheelSize;
            this.refreshVisibleItems(this.getFirstVisiblePosition(), this.getCurrentPosition() + wheelSize / 2, wheelSize / 2);
            this.changeBackground();
        }

    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                LogUtils.verbose(this, "press down: currentPosition=" + this.currentPosition);
                break;
            case 1:
                LogUtils.verbose(this, "press up: currentItem=" + this.getSelectedItem());
        }

        return false;
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == 0) {
            View itemView = this.getChildAt(0);
            if (itemView != null) {
                float deltaY = itemView.getY();
                if ((int) deltaY != 0 && this.itemHeightPixels != 0) {
                    int d;
                    if (Math.abs(deltaY) < (float) (this.itemHeightPixels / 2)) {
                        d = this.obtainSmoothDistance(deltaY);
                        this.smoothScrollBy(d, 50);
                    } else {
                        d = this.obtainSmoothDistance((float) this.itemHeightPixels + deltaY);
                        this.smoothScrollBy(d, 50);
                    }

                }
            }
        }
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (visibleItemCount != 0) {
            this.refreshCurrentPosition();
        }

    }

    protected void onMeasure(int widthSpec, int heightSpec) {
        int width = this.getLayoutParams().width;
        if (width == -2) {
            super.onMeasure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), heightSpec);
        } else {
            super.onMeasure(widthSpec, heightSpec);
        }

    }

    public void setOnWheelChangeListener(WheelListView.OnWheelChangeListener onWheelChangeListener) {
        this.onWheelChangeListener = onWheelChangeListener;
    }

    public void setLineConfig(LineConfig lineConfig) {
        this.lineConfig = lineConfig;
    }

    private static class ShadowWheelDrawable extends WheelListView.WheelDrawable {
        private static final int[] SHADOWS_COLORS = new int[]{-6710887, 11184810, 11184810};
        private GradientDrawable topShadow;
        private GradientDrawable bottomShadow;
        private Paint bgPaint;
        private Paint paint;
        private Paint dividerPaint;
        private int wheelSize;
        private int itemHeight;

        public ShadowWheelDrawable(LineConfig config) {
            super(config);
            this.topShadow = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, SHADOWS_COLORS);
            this.bottomShadow = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, SHADOWS_COLORS);
            this.wheelSize = config.getWheelSize();
            this.itemHeight = config.getItemHeight();
            this.init();
        }

        private void init() {
            this.bgPaint = new Paint(1);
            this.bgPaint.setColor(0);
            this.paint = new Paint(1);
            this.paint.setColor(-254816305);
            this.dividerPaint = new Paint(1);
            this.dividerPaint.setColor(-4868683);
            this.dividerPaint.setStrokeWidth(2.0F);
        }

        public void draw(Canvas canvas) {
            canvas.drawRect(0.0F, 0.0F, (float) this.width, (float) this.height, this.bgPaint);
            if (this.itemHeight != 0) {
                canvas.drawRect(0.0F, (float) (this.itemHeight * (this.wheelSize / 2)), (float) this.width, (float) (this.itemHeight * (this.wheelSize / 2 + 1)), this.paint);
                canvas.drawLine(0.0F, (float) (this.itemHeight * (this.wheelSize / 2)), (float) this.width, (float) (this.itemHeight * (this.wheelSize / 2)), this.dividerPaint);
                canvas.drawLine(0.0F, (float) (this.itemHeight * (this.wheelSize / 2 + 1)), (float) this.width, (float) (this.itemHeight * (this.wheelSize / 2 + 1)), this.dividerPaint);
                this.topShadow.setBounds(0, 0, this.width, this.itemHeight);
                this.topShadow.draw(canvas);
                this.bottomShadow.setBounds(0, this.height - this.itemHeight, this.width, this.height);
                this.bottomShadow.draw(canvas);
            }

        }
    }

    private static class HoloWheelDrawable extends WheelListView.WheelDrawable {
        private Paint bgPaint;
        private Paint paint;
        private int wheelSize;
        private int itemHeight;
        private float ratio;
        private LineConfig lineConfig;

        public HoloWheelDrawable(LineConfig config) {
            super(config);
            this.lineConfig = config;
            this.wheelSize = config.getWheelSize();
            this.itemHeight = config.getItemHeight();
            this.init(config);
        }

        private void init(LineConfig config) {
            this.bgPaint = new Paint(1);
            this.bgPaint.setColor(0);
            this.paint = new Paint(1);
            this.paint.setStrokeWidth(config.getThick());
            this.paint.setColor(config.getColor());
            this.paint.setAlpha(config.getAlpha());
        }

        public void draw(Canvas canvas) {
            canvas.drawRect(0.0F, 0.0F, (float) this.width, (float) this.height, this.bgPaint);
            if (this.lineConfig.isVisible() && this.itemHeight != 0) {
                canvas.drawLine((float) this.width * this.ratio, (float) (this.itemHeight * (this.wheelSize / 2)), (float) this.width * (1.0F - this.ratio), (float) (this.itemHeight * (this.wheelSize / 2)), this.paint);
                canvas.drawLine((float) this.width * this.ratio, (float) (this.itemHeight * (this.wheelSize / 2 + 1)), (float) this.width * (1.0F - this.ratio), (float) (this.itemHeight * (this.wheelSize / 2 + 1)), this.paint);
            }

        }
    }

    private static class WheelDrawable extends Drawable {
        protected int width;
        protected int height;
        private Paint bgPaint;

        public WheelDrawable(LineConfig config) {
            this.width = config.getWidth();
            this.height = config.getHeight();
            this.init();
        }

        private void init() {
            this.bgPaint = new Paint(1);
            this.bgPaint.setColor(0);
        }

        public void draw(Canvas canvas) {
            canvas.drawRect(0.0F, 0.0F, (float) this.width, (float) this.height, this.bgPaint);
        }

        public void setAlpha(int alpha) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }

        public int getOpacity() {
            return PixelFormat.UNKNOWN;
        }
    }

    private static class WheelAdapter extends BaseAdapter {
        private List<String> data;
        private boolean isLoop;
        private int wheelSize;

        private WheelAdapter() {
            this.data = new ArrayList();
            this.isLoop = false;
            this.wheelSize = 5;
        }

        public final int getRealCount() {
            return this.data.size();
        }

        public final int getCount() {
            if (this.isLoop) {
                return 2147483647;
            } else {
                return this.data.size() > 0 ? this.data.size() + this.wheelSize - 1 : 0;
            }
        }

        public final long getItemId(int position) {
            if (this.isLoop) {
                return this.data.size() > 0 ? (long) (position % this.data.size()) : (long) position;
            } else {
                return (long) position;
            }
        }

        public final String getItem(int position) {
            if (this.isLoop) {
                return this.data.size() > 0 ? (String) this.data.get(position % this.data.size()) : null;
            } else {
                if (this.data.size() <= position) {
                    position = this.data.size() - 1;
                }

                return (String) this.data.get(position);
            }
        }

        public boolean areAllItemsEnabled() {
            return false;
        }

        public boolean isEnabled(int position) {
            return false;
        }

        public final View getView(int position, View convertView, ViewGroup parent) {
            if (this.isLoop) {
                position %= this.data.size();
            } else if (position < this.wheelSize / 2) {
                position = -1;
            } else if (position >= this.wheelSize / 2 + this.data.size()) {
                position = -1;
            } else {
                position -= this.wheelSize / 2;
            }

            WheelListView.WheelAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new WheelListView.WheelAdapter.ViewHolder();
                holder.itemView = new WheelListView.ItemView(parent.getContext());
                convertView = holder.itemView;
                ((View) convertView).setTag(holder);
            } else {
                holder = (WheelListView.WheelAdapter.ViewHolder) ((View) convertView).getTag();
            }

            if (!this.isLoop) {
                holder.itemView.setVisibility(position == -1 ? INVISIBLE : VISIBLE);
            }

            if (position == -1) {
                position = 0;
            }

            holder.itemView.setText((CharSequence) this.data.get(position));
            return (View) convertView;
        }

        public final WheelListView.WheelAdapter setLoop(boolean loop) {
            if (loop != this.isLoop) {
                this.isLoop = loop;
                super.notifyDataSetChanged();
            }

            return this;
        }

        public final WheelListView.WheelAdapter setWheelSize(int wheelSize) {
            if ((wheelSize & 1) == 0) {
                throw new IllegalArgumentException("wheel size must be an odd number.");
            } else {
                this.wheelSize = wheelSize;
                super.notifyDataSetChanged();
                return this;
            }
        }

        public final WheelListView.WheelAdapter setData(List<String> list) {
            this.data.clear();
            if (null != list) {
                this.data.addAll(list);
            }

            super.notifyDataSetChanged();
            return this;
        }

        public List<String> getData() {
            return this.data;
        }

        public int getWheelSize() {
            return this.wheelSize;
        }

        public boolean isLoop() {
            return this.isLoop;
        }

        /**
         * @deprecated
         */
        @Deprecated
        public final void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        /**
         * @deprecated
         */
        @Deprecated
        public final void notifyDataSetInvalidated() {
            super.notifyDataSetInvalidated();
        }

        private static class ViewHolder {
            WheelListView.ItemView itemView;

            private ViewHolder() {
            }
        }
    }

    private static class ItemView extends LinearLayout {
        private ImageView imageView;
        private TextView textView;

        public ItemView(Context context) {
            super(context);
            this.init(context);
        }

        public ItemView(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.init(context);
        }

        public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            this.init(context);
        }

        private void init(Context context) {
            this.setOrientation(LinearLayout.HORIZONTAL);
            int paddingTopBottom = ConvertUtils.toPx(context, 5.0F);
            int paddingLeftRight = ConvertUtils.toPx(context, 10.0F);
            this.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
            this.setGravity(17);
            int height = ConvertUtils.toPx(context, 40.0F);
            this.setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, height));
            this.imageView = new ImageView(this.getContext());
            this.imageView.setTag(100);
            this.imageView.setVisibility(VISIBLE);
            android.widget.LinearLayout.LayoutParams imageParams = new android.widget.LinearLayout.LayoutParams(-2, -2);
            imageParams.rightMargin = ConvertUtils.toPx(context, 5.0F);
            this.addView(this.imageView, imageParams);
            this.textView = new TextView(this.getContext());
            this.textView.setTag(101);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setSingleLine(true);
            this.textView.setIncludeFontPadding(false);
            this.textView.setGravity(17);
            this.textView.setTextColor(-16777216);
            android.widget.LinearLayout.LayoutParams textParams = new android.widget.LinearLayout.LayoutParams(-2, -2);
            this.addView(this.textView, textParams);
        }

        public void setText(CharSequence text) {
            this.textView.setText(text);
        }

        public void setImage(@DrawableRes int resId) {
            this.imageView.setVisibility(VISIBLE);
            this.imageView.setImageResource(resId);
        }
    }

    public interface OnWheelChangeListener {
        void onItemSelected(int var1, String var2);
    }
}
