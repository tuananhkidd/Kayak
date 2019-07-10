package com.kidd.projectbase.injection;

import com.kidd.projectbase.utils.rx.AppRxSchedulers;
import com.kidd.projectbase.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {
    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}
