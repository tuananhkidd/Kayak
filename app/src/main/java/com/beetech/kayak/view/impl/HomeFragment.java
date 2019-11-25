package com.beetech.kayak.view.impl;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.beetech.kayak.R;
import com.beetech.kayak.injection.AppComponent;
import com.beetech.kayak.injection.DaggerHomeViewComponent;
import com.beetech.kayak.injection.HomeViewModule;
import com.beetech.kayak.presenter.HomePresenter;
import com.beetech.kayak.presenter.loader.PresenterFactory;
import com.beetech.kayak.view.HomeView;

import javax.inject.Inject;

public final class HomeFragment extends BaseFragment<HomePresenter, HomeView> implements HomeView {
    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerHomeViewComponent.builder()
                .appComponent(parentComponent)
                .homeViewModule(new HomeViewModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @NonNull
    @Override
    protected PresenterFactory<HomePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
