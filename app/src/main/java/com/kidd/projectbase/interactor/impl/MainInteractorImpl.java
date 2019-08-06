package com.kidd.projectbase.interactor.impl;

import javax.inject.Inject;

import com.kidd.projectbase.interactor.MainInteractor;
import com.kidd.projectbase.network.request.Apis;
import com.kidd.projectbase.network.response.BaseResponse;
import com.kidd.projectbase.network.response.CarResponse;
import com.kidd.projectbase.utils.rx.AppRxSchedulers;
import com.kidd.projectbase.utils.rx.RxSchedulers;

import java.util.List;

import io.reactivex.Single;

public final class MainInteractorImpl implements MainInteractor {
    private Apis apis;
    private RxSchedulers rxSchedulers;

    @Inject
    public MainInteractorImpl(Apis apis,
                              RxSchedulers rxSchedulers) {
        this.apis = apis;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public Single<BaseResponse<List<CarResponse>>> getListCar() {
        return apis.getCar()
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.compute());
    }
}