package com.beetech.kayak.injection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.beetech.kayak.base.ViewController;
import com.beetech.kayak.interactor.MainInteractor;
import com.beetech.kayak.interactor.impl.MainInteractorImpl;
import com.beetech.kayak.presenter.loader.PresenterFactory;
import com.beetech.kayak.presenter.MainPresenter;
import com.beetech.kayak.presenter.impl.MainPresenterImpl;

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
    public MainInteractor provideInteractor() {
        return new MainInteractorImpl();
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
