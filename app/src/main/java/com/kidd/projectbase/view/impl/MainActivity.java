package com.kidd.projectbase.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.kidd.projectbase.R;
import com.kidd.projectbase.base.ViewController;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.DaggerMainViewComponent;
import com.kidd.projectbase.injection.MainViewModule;
import com.kidd.projectbase.presenter.MainPresenter;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.view.MainView;

import javax.inject.Inject;

public final class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView {
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;
    @Inject
    ViewController mViewController;


    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerMainViewComponent.builder()
                .appComponent(parentComponent)
                .mainViewModule(new MainViewModule(this,getRootViewId()))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        super.initView();
        getViewController().addFragment(TestFragment.class, null);
    }

    @Override
    protected int getRootViewId() {
        return R.id.rlMain;
    }

    @Override
    public ViewController getViewController() {
        return mViewController;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
