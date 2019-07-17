package com.kidd.projectbase.injection;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.kidd.projectbase.base.ViewController;
import com.kidd.projectbase.interactor.MainInteractor;
import com.kidd.projectbase.interactor.impl.MainInteractorImpl;
import com.kidd.projectbase.network.request.Apis;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.presenter.MainPresenter;
import com.kidd.projectbase.presenter.impl.MainPresenterImpl;
import com.kidd.projectbase.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public final class MainViewModule {
    private AppCompatActivity activity;
    private int layoutId;

    public MainViewModule(AppCompatActivity activity, int layoutId) {
        this.activity = activity;
        this.layoutId = layoutId;
    }

    @Provides
    public MainInteractor provideInteractor(Apis apis, RxSchedulers rxSchedulers) {
        return new MainInteractorImpl(apis, rxSchedulers);
    }


    @Provides
    public PresenterFactory<MainPresenter> providePresenterFactory(@NonNull final MainInteractor interactor) {
        return () -> new MainPresenterImpl(interactor);
    }

    @Provides
    public ViewController provideViewController() {
        return new ViewController(activity.getSupportFragmentManager(), layoutId);
    }
}
