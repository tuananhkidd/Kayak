package com.beetech.kayak.injection;

import androidx.annotation.NonNull;

import com.beetech.kayak.interactor.HomeInteractor;
import com.beetech.kayak.interactor.impl.HomeInteractorImpl;
import com.beetech.kayak.network.request.Apis;
import com.beetech.kayak.presenter.loader.PresenterFactory;
import com.beetech.kayak.presenter.HomePresenter;
import com.beetech.kayak.presenter.impl.HomePresenterImpl;
import com.beetech.kayak.utils.rx.RxSchedulers;

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
