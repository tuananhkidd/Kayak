package com.kidd.projectbase.interactor.impl;

import com.kidd.projectbase.App;
import com.kidd.projectbase.R;
import com.kidd.projectbase.entity.login_response.InstagramLoginResponse;
import com.kidd.projectbase.interactor.HomeInteractor;
import com.kidd.projectbase.network.request.Apis;
import com.kidd.projectbase.network.response.BaseResponse;
import com.kidd.projectbase.utils.rx.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.Single;

public final class HomeInteractorImpl implements HomeInteractor {
    private RxSchedulers rxSchedulers;
    private Apis apis;

    @Inject
    public HomeInteractorImpl(RxSchedulers rxSchedulers, Apis apis) {
        this.apis = apis;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public Single<BaseResponse<InstagramLoginResponse>> loginInstagram(String token) {
        return apis.loginInstagram(App.getContext().getString(R.string.get_user_info_url), token)
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.androidThread());
    }
}