package com.sample.sv.sampleapplication.threads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                System.out.println("Hello " + threadName);
            }
        });

        //создать ExecutorService на базе пула из пяти потоков
        ExecutorService es1 = Executors.newFixedThreadPool(5);
        //поместить задачу в очередь на выполнение
        Future<String> f1 = es1.submit(new CallableSample());
        while(!f1.isDone()) {
            //подождать пока задача не выполнится
        }
        try {
            //получить результат выполнения задачи
            System.out.println("task has been completed : " + f1.get());
        } catch (InterruptedException ie) {
            ie.printStackTrace(System.err);
        } catch (ExecutionException ee) {
            ee.printStackTrace(System.err);
        }
        es1.shutdown();
    }

    public class CallableSample implements Callable<String> {
        public String call() throws Exception {
            if(n < 5) {
               throw new IOException("error during task processing");
            }
            System.out.println("task is processing");
            return "result ";
        }
    }
}
