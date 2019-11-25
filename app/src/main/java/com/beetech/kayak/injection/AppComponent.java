package com.beetech.kayak.injection;

import android.content.Context;

import com.beetech.kayak.App;
import com.beetech.kayak.network.request.Apis;
import com.beetech.kayak.utils.rx.RxSchedulers;
import com.beetech.kayak.utils.sharedpreference.RxPreferenceHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, RxModule.class, SharedPreferenceModule.class})
public interface AppComponent {
    @ApplicationContext
    Context getAppContext();

    App getApp();

    Apis appApis();

    RxSchedulers rxSchedulers();

    RxPreferenceHelper getPreference();
}