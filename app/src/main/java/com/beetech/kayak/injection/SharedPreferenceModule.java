package com.beetech.kayak.injection;

import com.beetech.kayak.utils.sharedpreference.PreferencesHelper;
import com.beetech.kayak.utils.sharedpreference.RxPreferenceHelper;

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
