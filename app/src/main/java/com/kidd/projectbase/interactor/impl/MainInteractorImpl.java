package com.kidd.projectbase.interactor.impl;

import javax.inject.Inject;

import com.kidd.projectbase.interactor.MainInteractor;
import com.kidd.projectbase.network.response.BaseResponse;
import com.kidd.projectbase.network.response.CarResponse;

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