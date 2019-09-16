package com.kidd.projectbase.view.impl;

import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.facebook.CallbackManager;
import com.kidd.projectbase.R;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.DaggerHomeViewComponent;
import com.kidd.projectbase.injection.HomeViewModule;
import com.kidd.projectbase.presenter.HomePresenter;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.service.LoginSocialEngine;
import com.kidd.projectbase.view.HomeView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public final class HomeFragment extends BaseFragment<HomePresenter, HomeView> implements HomeView {
    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;

    @BindView(R.id.tv_info)
    TextView tvInfo;

    private CallbackManager callbackManager;

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
        callbackManager = CallbackManager.Factory.create();
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
    void onRetry() {

    }

    @Override
    void onRefreshData() {

    }

    @Override
    public void showUserInfo(String id, String userName, String avatar) {
        tvInfo.setText(id + "\n" + userName + "\n" + avatar);
    }

    @OnClick(R.id.btn_instagram_login)
    void loginInstagram() {
        LoginSocialEngine
                .getInstance()
                .setmPresenter(mPresenter)
                .loginInstagram(getContext());
    }

    @OnClick(R.id.btn_google_login)
    void loginGoogle() {
        LoginSocialEngine
                .getInstance()
                .loginGoogle(getActivity());
    }

    @OnClick(R.id.btn_zalo_login)
    void loginZalo() {
        LoginSocialEngine.getInstance().loginZalo(getActivity(), zaloLoginResponse -> {
            if (zaloLoginResponse != null) {
                showUserInfo(zaloLoginResponse.getId(), zaloLoginResponse.getName(), zaloLoginResponse.getPicture().getData().getUrl());
            }
        });
    }

    @OnClick(R.id.btn_fb_login)
    void fbLogin(){
        LoginSocialEngine.getInstance().loginFacebook(callbackManager,this);
    }

    @OnClick(R.id.btn_sign_out)
    void logout() {
        LoginSocialEngine
                .getInstance()
                .logout(getContext());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @NonNull
    @Override
    protected PresenterFactory<HomePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
