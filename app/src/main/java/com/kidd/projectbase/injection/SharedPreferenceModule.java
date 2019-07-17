package com.kidd.projectbase.injection;

import android.content.Context;

import com.kidd.projectbase.utils.sharedpreference.PreferencesHelper;
import com.kidd.projectbase.utils.sharedpreference.RxPreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferenceModule {
    @Provides
    @Singleton
    public RxPreferenceHelper providePreferencesHelper(PreferencesHelper preferencesHelper) {
        return preferencesHelper;
    }
}
