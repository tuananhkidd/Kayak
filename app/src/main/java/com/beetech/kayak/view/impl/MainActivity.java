package com.beetech.kayak.view.impl;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.beetech.kayak.R;
import com.beetech.kayak.base.ViewController;
import com.beetech.kayak.injection.AppComponent;
import com.beetech.kayak.injection.DaggerMainViewComponent;
import com.beetech.kayak.injection.MainViewModule;
import com.beetech.kayak.presenter.MainPresenter;
import com.beetech.kayak.presenter.loader.PresenterFactory;
import com.beetech.kayak.utils.ToastUtil;
import com.beetech.kayak.view.MainView;

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

    public static final int PERMISSION_CAMERA_CODE = 1996;


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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED ) {
            getViewController().addFragment(HomeFragment.class, null);
        } else {
            String[] permissions = new String[]{Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this,permissions, PERMISSION_CAMERA_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_CAMERA_CODE){
            if(grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                getViewController().addFragment(HomeFragment.class, null);
            }else {
                ToastUtil.show("Permission Deny!");
            }
        }
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
