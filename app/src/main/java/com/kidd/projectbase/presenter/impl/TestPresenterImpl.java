package com.kidd.projectbase.presenter.impl;

import android.support.annotation.NonNull;

import com.kidd.projectbase.presenter.TestPresenter;
import com.kidd.projectbase.view.TestView;
import com.kidd.projectbase.interactor.TestInteractor;

import javax.inject.Inject;

public final class TestPresenterImpl extends BasePresenterImpl<TestView> implements TestPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final TestInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public TestPresenterImpl(@NonNull TestInteractor interactor) {
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