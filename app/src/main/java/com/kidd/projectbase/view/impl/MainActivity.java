package com.kidd.projectbase.view.impl;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.kidd.projectbase.R;
import com.kidd.projectbase.base.ViewController;
import com.kidd.projectbase.entity.ImageObject;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.DaggerMainViewComponent;
import com.kidd.projectbase.injection.MainViewModule;
import com.kidd.projectbase.presenter.MainPresenter;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.utils.Constants;
import com.kidd.projectbase.view.MainView;

import java.util.ArrayList;

import javax.inject.Inject;

public final class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView ,
        TestFragment.ListenerChooseImage{
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;
    @Inject
    ViewController mViewController;

    private TestFragment galleryFragment;
    private ArrayList<ImageObject> lsImageChoose;
    private String kind;
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
        if (isStoragePermissionGranted()) {
            galleryFragment = new TestFragment();
            galleryFragment.setListenerChooseImage(this);
            getViewController().addFragment(galleryFragment.getClass(), null);
        }
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
    public void tranferListImageChoose(ArrayList<ImageObject> lsImage) {
        if (kind.equalsIgnoreCase(Constants.VALUE_TYPE_CHOOSE_IMAGE_SINGLE)) {
            Intent intent = new Intent();
//            intent.putExtra(Constants.SEND_RESULT_CHOOSE_IMAGE, lsImage.get(0).getPath());
//            setResult(Constants.RESULT_CODE_CHOOSE_IMAGE_DONE, intent);
            finish();
        } else {
            lsImageChoose = lsImage;
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // load image in here
            galleryFragment = new TestFragment();
            galleryFragment.setListenerChooseImage(this);
            getViewController().addFragment(TestFragment.class, null);
        }
    }

}
