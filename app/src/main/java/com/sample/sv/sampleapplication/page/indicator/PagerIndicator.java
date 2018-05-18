package com.sample.sv.sampleapplication.page.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import com.sample.sv.sampleapplication.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class PagerIndicator extends View {

    private static final int DEFAULT_DOT_RADIUS_DP = 4;
    private static final int DEFAULT_DOTS_PADDING_DP = 8;
    private static final int DEFAULT_SIDES_PADDING = 4;
    private static final int DEFAULT_SELECTED_COLOR = R.color.dark_green;
    private static final int DEFAULT_UNSELECTED_COLOR = R.color.red;

    private int count = 0;
    @ColorRes
    private int unselectedColor = DEFAULT_UNSELECTED_COLOR;
    @ColorRes
    private int selectedColor = DEFAULT_SELECTED_COLOR;
    private int dotRadiusDp = DEFAULT_DOT_RADIUS_DP;
    private int dotPaddingDp = DEFAULT_DOTS_PADDING_DP;
    private int sidesPaddingDp = DEFAULT_SIDES_PADDING;
    private int selectedPosition;

    private RecyclerView recyclerView;
    private Paint paint;
    private RecyclerViewScrollListener recyclerViewScrollListener = new RecyclerViewScrollListener();

    private class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == SCROLL_STATE_IDLE) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                setSelection(layoutManager.findFirstVisibleItemPosition() % count);
            }
        }

        private void setSelection(int position) {
            if (position < 0) {
                position = 0;
            }
            selectedPosition = position;
            invalidate();
        }
    }

    public PagerIndicator(Context context) {
        this(context, null);
    }

    public PagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initAttrs(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dotDiameterDp = dotRadiusDp * 2;

        int widthDp = (dotDiameterDp * count) + (dotPaddingDp * (count - 1));
        int widthPx = DensityUtils.dpToPx(getContext(), widthDp);

        int heightDp = sidesPaddingDp + dotDiameterDp;
        int heightPx = DensityUtils.dpToPx(getContext(), heightDp);

        int measuredWidth = reconcileSize(widthPx, widthMeasureSpec);
        int measuredHeight = reconcileSize(heightPx, heightMeasureSpec);

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawIndicatorView(canvas);
    }

    public void addRecyclerView(@Nullable RecyclerView recyclerView) {
        if (recyclerView != null) {
            this.recyclerView = recyclerView;
            this.recyclerView.addOnScrollListener(recyclerViewScrollListener);
        }
    }

    public void releaseRecyclerView() {
        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(recyclerViewScrollListener);
            recyclerView = null;
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setUnselectedColor(int unselectedColor) {
        this.unselectedColor = unselectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void setDotRadiusDp(int dotRadiusDp) {
        this.dotRadiusDp = dotRadiusDp;
    }

    public void setDotPaddingDp(int dotPaddingDp) {
        this.dotPaddingDp = dotPaddingDp;
    }

    public void setSidesPaddingDp(int sidesPaddingDp) {
        this.sidesPaddingDp = sidesPaddingDp;
    }

    private void init() {
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(unselectedColor));
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.PagerIndicator);
            try {
                selectedColor = attributes.getResourceId(R.styleable.PagerIndicator_piv_selected_color, DEFAULT_SELECTED_COLOR);
                unselectedColor = attributes.getResourceId(R.styleable.PagerIndicator_piv_unselected_color, DEFAULT_UNSELECTED_COLOR);
                dotRadiusDp = attributes.getDimensionPixelOffset(R.styleable.PagerIndicator_piv_radius, DEFAULT_DOT_RADIUS_DP);
                dotPaddingDp = attributes.getDimensionPixelOffset(R.styleable.PagerIndicator_piv_dot_padding, DEFAULT_DOTS_PADDING_DP);
                sidesPaddingDp = attributes.getDimensionPixelOffset(R.styleable.PagerIndicator_piv_sides_padding, DEFAULT_SIDES_PADDING);
            } finally {
                attributes.recycle();
            }
        }

    }

    private void drawIndicatorView(@NonNull Canvas canvas) {
        List<Pair> dotList = createDotList();
        for (int i = 0; i < count; i++) {
            int x = (int) dotList.get(i).first;
            int y = (int) dotList.get(i).second;
            drawCircle(canvas, i, x, y);
        }
    }

    @NonNull
    private List<Pair> createDotList() {
        List<Pair> dotList = new ArrayList<>();

        int width = 0;
        int heightCenter = getHeight() / 2;

        int dotRadiusPx = DensityUtils.dpToPx(getContext(), dotRadiusDp);
        int dotPaddingPx = DensityUtils.dpToPx(getContext(), dotPaddingDp);

        for (int i = 0; i < count; i++) {
            width += dotRadiusPx;

            Pair pair = new Pair<>(width, heightCenter);
            dotList.add(pair);

            width += dotRadiusPx + dotPaddingPx;
        }

        return dotList;
    }

    /**
     * @param x The x-coordinate of the center of the cirle to be drawn
     * @param y The y-coordinate of the center of the cirle to be drawn
     */
    private void drawCircle(@NonNull Canvas canvas, int position, int x, int y) {
        int radius = DensityUtils.dpToPx(getContext(), dotRadiusDp);
        int color = getResources().getColor(unselectedColor);

        if (position == selectedPosition) {
            color =  getResources().getColor(selectedColor);
        }

        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);
    }

    private int reconcileSize(int contentSize, int measureSpec) {
        final int mode = MeasureSpec.getMode(measureSpec);
        final int specSize = MeasureSpec.getSize(measureSpec);
        switch(mode) {
            case MeasureSpec.EXACTLY:
                return specSize;
            case MeasureSpec.AT_MOST:
                if (contentSize < specSize) {
                    return contentSize;
                } else {
                    return specSize;
                }
            case MeasureSpec.UNSPECIFIED:
            default:
                return contentSize;
        }
    }
}