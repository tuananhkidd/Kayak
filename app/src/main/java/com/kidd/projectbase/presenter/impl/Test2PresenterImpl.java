package com.kidd.projectbase.presenter.impl;

import android.support.annotation.NonNull;

import com.kidd.projectbase.presenter.Test2Presenter;
import com.kidd.projectbase.view.Test2View;
import com.kidd.projectbase.interactor.Test2Interactor;

import javax.inject.Inject;

public final class Test2PresenterImpl extends BasePresenterImpl<Test2View> implements Test2Presenter {
    /**
     * The interactor
     */
    @NonNull
    private final Test2Interactor mInteractor;

    // The view is available using the mView variable

    @Inject
    public Test2PresenterImpl(@NonNull Test2Interactor interactor) {
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
}