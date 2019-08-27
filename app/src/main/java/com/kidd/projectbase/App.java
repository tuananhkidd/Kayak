package com.kidd.projectbase;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;

import com.deploygate.sdk.DeployGate;
import com.facebook.stetho.Stetho;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.AppModule;
import com.kidd.projectbase.injection.DaggerAppComponent;

public final class App extends Application {
    private AppComponent mAppComponent;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        context = mAppComponent.getAppContext();

        // Debug tool
        Stetho.initializeWithDefaults(this);

        // Deploy tool
        DeployGate.install(this);
    }

    public static Context getContext() {
        return context;
    }


    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}