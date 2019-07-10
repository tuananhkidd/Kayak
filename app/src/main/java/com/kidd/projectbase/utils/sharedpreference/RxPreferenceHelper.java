package com.kidd.projectbase.utils.sharedpreference;

import javax.inject.Singleton;

@Singleton
public interface RxPreferenceHelper {

    void saveString(String key, String value);

    String getString(String key);

    void saveBoolean(String key, boolean value);

    boolean getBoolean(String key);
}
