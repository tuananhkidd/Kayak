package com.kidd.projectbase.utils.rx;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppRxSchedulers implements RxSchedulers {
    private static Executor sBackgroundExecutor = Executors.newCachedThreadPool();
    private static Scheduler sBackgroundSchedules = Schedulers.from(sBackgroundExecutor);
    private static Executor sInternetExecutor = Executors.newCachedThreadPool();
    private static Scheduler sInternetSchedules = Schedulers.from(sInternetExecutor);

    @Override
    public Scheduler runOnBackground() {
        return sBackgroundSchedules;
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler compute() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler androidThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler internet() {
        return sInternetSchedules;
    }
}
