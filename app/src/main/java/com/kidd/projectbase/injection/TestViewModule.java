package com.kidd.projectbase.injection;

import android.support.annotation.NonNull;

import com.kidd.projectbase.interactor.TestInteractor;
import com.kidd.projectbase.interactor.impl.TestInteractorImpl;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.presenter.TestPresenter;
import com.kidd.projectbase.presenter.impl.TestPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class TestViewModule {
    @Provides
    public TestInteractor provideInteractor() {
        return new TestInteractorImpl();
    }

    @Provides
    public PresenterFactory<TestPresenter> providePresenterFactory(@NonNull final TestInteractor interactor) {
        return new PresenterFactory<TestPresenter>() {
            @NonNull
            @Override
            public TestPresenter create() {
                return new TestPresenterImpl(interactor);
            }
        };
    }
}
