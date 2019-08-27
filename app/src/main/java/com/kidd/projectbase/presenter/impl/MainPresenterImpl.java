package com.kidd.projectbase.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kidd.projectbase.network.NetworkCheckerInterceptor;
import com.kidd.projectbase.presenter.MainPresenter;
import com.kidd.projectbase.utils.ToastUtil;
import com.kidd.projectbase.view.MainView;
import com.kidd.projectbase.interactor.MainInteractor;

import javax.inject.Inject;

public final class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final MainInteractor mInteractor;
    private int countOnCreate = 0;

    // The view is available using the mView variable

    @Inject
    public MainPresenterImpl(@NonNull MainInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        if (viewCreated) {
            testApi();
        }
        countOnCreate++;
    }

    @Override
    public int getCountOnCreate() {
        return countOnCreate;
    }

    @Override
    public void testApi() {
        compositeDisposable.add(mInteractor.getListCar()
                .doOnSubscribe(
                        disposable -> {
                            if (mView != null) {
                                mView.showLoading();
                            }
                        }
                )
                .doFinally(() -> {
                    if (mView != null) {
                        mView.hiddenLoading();
                    }
                })
                .subscribe(
                        response -> {
                            if (response.getStatus() == 200) {
                                Log.v("ahihi", response.getData().toString());
                            } else {
                                if (mView != null) {
                                    mView.showLayoutRetry();
                                }

                            }
                        },
                        throwable -> {
                            if (mView != null) {
                                mView.showLayoutRetry();
                            }

                        }
                ));
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
}