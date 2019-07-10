package com.kidd.projectbase.view.impl;

import android.support.annotation.NonNull;

import com.kidd.projectbase.R;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.DaggerTest2ViewComponent;
import com.kidd.projectbase.injection.Test2ViewModule;
import com.kidd.projectbase.presenter.Test2Presenter;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.view.Test2View;

import javax.inject.Inject;

public final class Test2Fragment extends BaseFragment<Test2Presenter, Test2View> implements Test2View {
    @Inject
    PresenterFactory<Test2Presenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public Test2Fragment() {
        // Required empty public constructor
    }


    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerTest2ViewComponent.builder()
                .appComponent(parentComponent)
                .test2ViewModule(new Test2ViewModule())
                .build()
                .inject(this);
    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_test2;
    }

    @NonNull
    @Override
    protected PresenterFactory<Test2Presenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
