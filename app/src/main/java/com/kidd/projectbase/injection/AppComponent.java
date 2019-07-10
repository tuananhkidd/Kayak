package com.kidd.projectbase.injection;

import android.content.Context;

import com.kidd.projectbase.App;
import com.kidd.projectbase.network.request.Apis;
import com.kidd.projectbase.utils.rx.RxSchedulers;
import com.kidd.projectbase.utils.sharedpreference.RxPreferenceHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, RxModule.class, SharedPreferenceModule.class})
public interface AppComponent {
    Context getAppContext();

    App getApp();

    Apis appApis();

    RxSchedulers rxSchedulers();

    RxPreferenceHelper getPreference();
}