package com.beetech.kayak.interactor.impl;

import com.beetech.kayak.interactor.HomeInteractor;
import com.beetech.kayak.network.request.Apis;
import com.beetech.kayak.utils.rx.RxSchedulers;

import javax.inject.Inject;

public final class HomeInteractorImpl implements HomeInteractor {
    private RxSchedulers rxSchedulers;
    private Apis apis;

    @Inject
    public HomeInteractorImpl(RxSchedulers rxSchedulers, Apis apis) {
        this.apis = apis;
        this.rxSchedulers = rxSchedulers;
    }
}