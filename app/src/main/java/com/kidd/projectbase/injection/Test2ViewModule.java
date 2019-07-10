package com.kidd.projectbase.injection;

import android.support.annotation.NonNull;

import com.kidd.projectbase.interactor.Test2Interactor;
import com.kidd.projectbase.interactor.impl.Test2InteractorImpl;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.presenter.Test2Presenter;
import com.kidd.projectbase.presenter.impl.Test2PresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class Test2ViewModule {
    @Provides
    public Test2Interactor provideInteractor() {
        return new Test2InteractorImpl();
    }

    @Provides
    public PresenterFactory<Test2Presenter> providePresenterFactory(@NonNull final Test2Interactor interactor) {
        return new PresenterFactory<Test2Presenter>() {
            @NonNull
            @Override
            public Test2Presenter create() {
                return new Test2PresenterImpl(interactor);
            }
        };
    }
}
