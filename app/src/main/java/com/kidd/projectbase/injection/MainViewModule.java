package com.kidd.projectbase.injection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.kidd.projectbase.base.ViewController;
import com.kidd.projectbase.interactor.MainInteractor;
import com.kidd.projectbase.interactor.impl.MainInteractorImpl;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.presenter.MainPresenter;
import com.kidd.projectbase.presenter.impl.MainPresenterImpl;

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
