package com.sample.sv.sampleapplication.threads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

public class LoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(EXAMPLE_LOADER_ID, Bundle.EMPTY, this);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        ExampleLoader loader = null;
        if (id == EXAMPLE_LOADER_ID) {
            loader = new ExampleLoader(MainActivity.this);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (loader.getId() == EXAMPLE_LOADER_ID) {
            updateUI(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}


