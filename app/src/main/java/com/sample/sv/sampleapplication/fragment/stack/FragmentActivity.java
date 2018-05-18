package com.sample.sv.sampleapplication.fragment.stack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sample.sv.sampleapplication.R;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new FragmentA())
                .addToBackStack("");
        final int id = fragmentTransaction1.commit();

        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new FragmentB())
                .addToBackStack("");
        fragmentTransaction2.commit();

        FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new FragmentC())
                .addToBackStack("");
        fragmentTransaction3.commit();

        FragmentTransaction fragmentTransaction4 = fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new FragmentD())
                .addToBackStack("");
        fragmentTransaction4.commit();


        findViewById(R.id.pop_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStack(id, 0);
            }
        });

    }
}
