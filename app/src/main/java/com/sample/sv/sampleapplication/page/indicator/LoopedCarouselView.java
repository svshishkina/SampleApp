package com.sample.sv.sampleapplication.page.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.sample.sv.sampleapplication.R;

public class LoopedCarouselView extends FrameLayout {
    private PagerIndicator pageIndicatorView;
    private LoopedCarouselAdapter adapter;
    private LinearLayoutManager layoutManager;

    public LoopedCarouselView(Context context) {
        this(context, null);
    }

    public LoopedCarouselView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (pageIndicatorView != null) {
            pageIndicatorView.releaseRecyclerView();
        }
        super.onDetachedFromWindow();
    }

    public void setData(int[] colors) {
        pageIndicatorView.setCount(colors.length);
        adapter.setData(colors);
        adapter.notifyDataSetChanged();
        layoutManager.scrollToPosition(colors.length * (LoopedCarouselAdapter.SIZE_FACTOR / 2));
    }

    private void init() {
        inflate(getContext(), R.layout.looped_carousel_view, this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        pageIndicatorView = findViewById(R.id.pager_indicator);

        adapter = new LoopedCarouselAdapter();
        layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        pageIndicatorView.addRecyclerView(recyclerView);
    }
}
