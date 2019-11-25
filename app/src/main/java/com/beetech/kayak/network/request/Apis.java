package com.beetech.kayak.network.request;

import com.beetech.kayak.network.response.BaseResponse;
import com.beetech.kayak.network.response.CarResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;


public interface Apis {

    @GET("v1/cars")
    Single<BaseResponse<List<CarResponse>>> getCar();
}
