package com.beetech.kayak.utils.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.beetech.kayak.injection.ApplicationContext;
import com.beetech.kayak.utils.Constants;

import javax.inject.Inject;

public class PreferencesHelper implements RxPreferenceHelper {

    private SharedPreferences mPrefs;
    private static PreferencesHelper sPreferencesHelper;

    public static PreferencesHelper getInstance(Context context) {
        if (sPreferencesHelper == null) {
            sPreferencesHelper = new PreferencesHelper(context);
        }
        return sPreferencesHelper;
    }

    @Inject
    PreferencesHelper(@ApplicationContext Context context) {
        mPrefs = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }

    @Override
    public String getString(String key) {
        return mPrefs.getString(key, "");
    }

    @Override
    public void saveBoolean(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
    }

    @Override
    public boolean getBoolean(String key) {
        return mPrefs.getBoolean(key, false);
    }
}
