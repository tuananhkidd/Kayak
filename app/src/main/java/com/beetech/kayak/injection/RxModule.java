package com.beetech.kayak.injection;

import com.beetech.kayak.utils.rx.AppRxSchedulers;
import com.beetech.kayak.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {
    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}
