package com.kidd.projectbase.injection;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kidd.projectbase.BuildConfig;
import com.kidd.projectbase.network.NetworkCheckerInterceptor;
import com.kidd.projectbase.network.request.Apis;
import com.kidd.projectbase.utils.Define;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    Apis provideApiService(OkHttpClient client, RxJava2CallAdapterFactory rxAdapter) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();

        return retrofit.create(Apis.class);
    }

    @Provides
    OkHttpClient provideHttpClient(@ApplicationContext Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        NetworkCheckerInterceptor networkCheckerInterceptor = new NetworkCheckerInterceptor(context);

        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .build();
                    return chain.proceed(request);
                })
                .addInterceptor(loggingInterceptor)
                .addInterceptor(networkCheckerInterceptor)
//                .addInterceptor(new TokenInterceptor(messageRepository))
                .connectTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .cache(cache)
                .build();
    }

    @Provides
    HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

//    @Provides
//    @Singleton
//    OtherApiInterface provideOtherApiInterface(@ApplicationContext Context context, RxJava2CallAdapterFactory rxAdapter) {
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        NetworkCheckerInterceptor networkCheckerInterceptor = new NetworkCheckerInterceptor(context);
//
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BuildConfig.FLAVOR.equals("aic") ? StringUtil.UA : StringUtil.UD)
//                .client(new OkHttpClient.Builder()
//                        .addInterceptor(networkCheckerInterceptor)
//                        .connectTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                        .readTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                        .build())
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(rxAdapter)
//                .build();
//
//        return retrofit.create(OtherApiInterface.class);
//    }


    @Provides
    Cache provideCache(File file) {
        return new Cache(file, 10 * 10 * 1000);
    }

    @Provides
    File provideCacheFile(Context context) {
        return context.getFilesDir();
    }

    @Provides
    RxJava2CallAdapterFactory provideRxAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    GsonConverterFactory provideGsonClient() {
        return GsonConverterFactory.create();
    }
}
