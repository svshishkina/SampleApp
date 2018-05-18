package com.sample.sv.sampleapplication.page.indicator;

import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.sv.sampleapplication.R;

public class LoopedCarouselAdapter extends RecyclerView.Adapter<LoopedCarouselAdapter.ImageViewHolder> {

    public static final int SIZE_FACTOR = 10000;
    @ColorRes private int[] colors = {};

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bind(colors[position % colors.length]);
    }

    @Override
    public int getItemCount() {
        return colors.length * SIZE_FACTOR;
    }

    public void setData(@ColorRes int[] colors) {
        this.colors = colors;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_iv);
            textView = itemView.findViewById(R.id.item_tv);
        }

        void bind(@ColorRes int color) {
            imageView.setBackgroundColor(imageView.getResources().getColor(color));
            textView.setText(String.format(itemView.getResources().getString(R.string.page_number), getAdapterPosition() % colors.length));
        }
    }
}
