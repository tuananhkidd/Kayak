package com.beetech.kayak.interactor.impl;

import javax.inject.Inject;

import com.beetech.kayak.interactor.MainInteractor;
import com.beetech.kayak.network.response.BaseResponse;
import com.beetech.kayak.network.response.CarResponse;

import java.util.List;

import io.reactivex.Single;

public final class MainInteractorImpl implements MainInteractor {
    @Inject
    public MainInteractorImpl() {

    }


    @Override
    public Single<BaseResponse<List<CarResponse>>> getListCar() {
        return null;
    }
}