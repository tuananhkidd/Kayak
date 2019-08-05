package com.kidd.projectbase.network.request;

import com.kidd.projectbase.network.response.BaseResponse;
import com.kidd.projectbase.network.response.CarResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Apis {

    @GET("v1/cars1")
    Single<BaseResponse<CarResponse>> getCar();

}
