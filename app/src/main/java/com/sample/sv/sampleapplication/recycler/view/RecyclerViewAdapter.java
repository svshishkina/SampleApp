package com.sample.sv.sampleapplication.recycler.view;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sample.sv.sampleapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ImageViewHolder> {

    private List<User> users = new ArrayList<>();
    private OnClickListener onClickListener;

    public interface OnClickListener {
        void onClick(User user);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.second_demo_item_layout, parent, false), onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setData(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public void addItem(User user) {
        users.add(2, user);
        notifyItemInserted(2);
    }

    public void removeItem() {
        users.remove(2);
        notifyItemRemoved(2);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private User user;

        ImageViewHolder(View itemView, final OnClickListener onClickListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(user);
                        Snackbar.make(v, String.format(v.getResources().getString(R.string.full_name), user.getName(), user.getSurname()), Snackbar.LENGTH_LONG).show();
                    }
                }
            });
        }

        void bind(final User user) {
            this.user = user;
            textView.setText(String.format(itemView.getResources().getString(R.string.full_name), user.getName(), user.getSurname()));
        }
    }
}
