package com.kidd.projectbase.network.request;

import com.kidd.projectbase.entity.login_response.InstagramLoginResponse;
import com.kidd.projectbase.network.response.BaseResponse;
import com.kidd.projectbase.network.response.CarResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface Apis {

    @GET("v1/cars")
    Single<BaseResponse<List<CarResponse>>> getCar();

    @GET
    Single<BaseResponse<InstagramLoginResponse>> loginInstagram(@Url String url,
                                                                @Query("access_token") String token);
}
