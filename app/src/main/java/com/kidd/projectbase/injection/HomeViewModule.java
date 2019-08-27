package com.kidd.projectbase.injection;

import androidx.annotation.NonNull;

import com.kidd.projectbase.interactor.HomeInteractor;
import com.kidd.projectbase.interactor.impl.HomeInteractorImpl;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.presenter.HomePresenter;
import com.kidd.projectbase.presenter.impl.HomePresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class HomeViewModule {
    @Provides
    public HomeInteractor provideInteractor() {
        return new HomeInteractorImpl();
    }

    @Provides
    public PresenterFactory<HomePresenter> providePresenterFactory(@NonNull final HomeInteractor interactor) {
        return () -> new HomePresenterImpl(interactor);
    }
}
