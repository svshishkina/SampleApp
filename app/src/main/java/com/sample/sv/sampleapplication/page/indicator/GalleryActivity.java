package com.sample.sv.sampleapplication.page.indicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sample.sv.sampleapplication.R;

public class GalleryActivity extends AppCompatActivity {

    int[] colors = {R.color.dark_blue, R.color.light_blue, R.color.blue, R.color.gray};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoopedCarouselView loopedCarouselView = findViewById(R.id.looped_carousel);
        loopedCarouselView.setData(colors);
    }
}
