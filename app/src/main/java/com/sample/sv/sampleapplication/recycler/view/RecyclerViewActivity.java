package com.sample.sv.sampleapplication.recycler.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.sample.sv.sampleapplication.R;
import com.sample.sv.sampleapplication.recycler.view.animators.SlideInLeftAnimator;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_sample);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new SlideInLeftAnimator());

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setData(getUsers());
        adapter.setOnClickListener(new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(User user) {
                Log.i("User ", String.format(getResources().getString(R.string.full_name), user.getName(), user.getSurname()));
            }
        });

        FloatingActionButton fabAdd = findViewById(R.id.add_fab);
        FloatingActionButton fabRemove = findViewById(R.id.remove_fab);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(new User("Василий", "Петров"));
            }
        });

        fabRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeItem();
            }
        });

    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            users.add(new User(i + ": Name", "Surname"));
        }
        return users;
    }
}
