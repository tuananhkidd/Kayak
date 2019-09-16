package com.kidd.projectbase.injection;

import androidx.annotation.NonNull;

import com.kidd.projectbase.interactor.HomeInteractor;
import com.kidd.projectbase.interactor.impl.HomeInteractorImpl;
import com.kidd.projectbase.network.request.Apis;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.presenter.HomePresenter;
import com.kidd.projectbase.presenter.impl.HomePresenterImpl;
import com.kidd.projectbase.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public final class HomeViewModule {
    @Provides
    public HomeInteractor provideInteractor(RxSchedulers rxSchedulers, Apis apis) {
        return new HomeInteractorImpl(rxSchedulers, apis);
    }

    @Provides
    public PresenterFactory<HomePresenter> providePresenterFactory(@NonNull final HomeInteractor interactor) {
        return () -> new HomePresenterImpl(interactor);
    }
}
