package com.sample.sv.sampleapplication.threads;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private Executor ioExecutor;
    private Executor mainThread;

    public AppExecutors(Executor ioExecutor, Executor mainThread) {
        this.ioExecutor = Executors.newFixedThreadPool(3);
        this.mainThread = new MainThreadExecutor();
    }

    public Executor getIoExecutor() {
        return this.ioExecutor;
    }

    public Executor getMainThreadExecutor() {
        return this.mainThread;
    }

    private class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
