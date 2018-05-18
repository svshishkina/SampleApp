package com.sample.sv.sampleapplication.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sample.sv.sampleapplication.R;

public class ActivityPermission extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_LOCATION = 0;

    private View layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        layout = findViewById(R.id.root_layout);

        findViewById(R.id.button_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermission();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(layout, "Разрешение выдано",
                        Snackbar.LENGTH_SHORT)
                        .show();
            } else {
                Snackbar.make(layout, "Разрешение отклонено",
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void getPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //Разрешение уже есть
            Snackbar.make(layout,
                    "Разрешение выдано",
                    Snackbar.LENGTH_SHORT).show();
        } else {
            //Запрашиваем разрешение
            requestPermission();
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            //Разрешение уже однажды запрашивалось и пользователь его отклонил,
            // необходимы дополнительные объяснения
            Snackbar.make(layout, "Необходим доступ к вашей геопозиции, т.к. ...",
                    Snackbar.LENGTH_INDEFINITE).setAction("Ок", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(ActivityPermission.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            PERMISSION_REQUEST_LOCATION);
                }
            }).show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_LOCATION);
        }
    }
}
