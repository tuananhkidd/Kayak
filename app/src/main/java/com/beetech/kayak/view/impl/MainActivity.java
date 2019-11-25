package com.beetech.kayak.view.impl;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.beetech.kayak.R;
import com.beetech.kayak.base.ViewController;
import com.beetech.kayak.injection.AppComponent;
import com.beetech.kayak.injection.DaggerMainViewComponent;
import com.beetech.kayak.injection.MainViewModule;
import com.beetech.kayak.presenter.MainPresenter;
import com.beetech.kayak.presenter.loader.PresenterFactory;
import com.beetech.kayak.service.NetworkChangeReceiver;
import com.beetech.kayak.view.MainView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;

public final class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView {
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;
    @Inject
    ViewController mViewController;
    @BindView(R.id.ln_retry)
    RelativeLayout rlRetry;
    @BindView(R.id.btn_retry)
    Button btnRetry;


    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerMainViewComponent.builder()
                .appComponent(parentComponent)
                .mainViewModule(new MainViewModule(this, getRootViewId()))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        super.initView();
        getViewController().addFragment(HomeFragment.class, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
