package com.kidd.projectbase.interactor;

import com.kidd.projectbase.network.response.BaseResponse;
import com.kidd.projectbase.network.response.CarResponse;

import io.reactivex.Single;

public interface MainInteractor extends BaseInteractor {
    Single<BaseResponse<CarResponse>> getListCar();
}