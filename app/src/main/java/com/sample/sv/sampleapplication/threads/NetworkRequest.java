package com.sample.sv.sampleapplication.threads;

public class NetworkRequest {
    private AppExecutors appExecutors;

    public NetworkRequest(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    private void loadData() {
        appExecutors.getIoExecutor().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
