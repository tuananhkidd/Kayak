package com.kidd.projectbase.interactor;

import com.kidd.projectbase.entity.login_response.InstagramLoginResponse;
import com.kidd.projectbase.network.response.BaseResponse;

import io.reactivex.Single;

public interface HomeInteractor extends BaseInteractor {
    Single<BaseResponse<InstagramLoginResponse>> loginInstagram(String token);
}