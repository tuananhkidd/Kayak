package com.kidd.projectbase.presenter.impl;

import androidx.annotation.NonNull;

import com.kidd.projectbase.entity.login_response.InstagramLoginResponse;
import com.kidd.projectbase.interactor.HomeInteractor;
import com.kidd.projectbase.presenter.HomePresenter;
import com.kidd.projectbase.view.HomeView;

import javax.inject.Inject;

public final class HomePresenterImpl extends BasePresenterImpl<HomeView> implements HomePresenter {
    /**
     * The interactor
     */
    @NonNull
    private final HomeInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public HomePresenterImpl(@NonNull HomeInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }

    @Override
    public void loginInstagram(String token) {
        compositeDisposable.add(mInteractor.loginInstagram(token)
                .doOnSubscribe(disposable -> {
                    if (mView != null) {
                        mView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (mView != null) {
                        mView.hiddenLoading();
                    }
                })
                .subscribe(
                        response -> {
                            if (mView != null && response != null && response.getData() != null) {
                                InstagramLoginResponse instagramLoginResponse = response.getData();
                                mView.showUserInfo(instagramLoginResponse.getId(), instagramLoginResponse.getUserName(), instagramLoginResponse.getAvatar());
                            }
                        }
                        , throwable -> {

                        }
                ));
    }
}