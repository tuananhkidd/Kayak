package com.beetech.kayak.interactor;

import com.beetech.kayak.network.response.BaseResponse;
import com.beetech.kayak.network.response.CarResponse;

import java.util.List;

import io.reactivex.Single;

public interface MainInteractor extends BaseInteractor {
    Single<BaseResponse<List<CarResponse>>> getListCar();
}