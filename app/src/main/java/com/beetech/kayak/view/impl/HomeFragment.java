package com.beetech.kayak.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.beetech.kayak.GlideApp;
import com.beetech.kayak.R;
import com.beetech.kayak.injection.AppComponent;
import com.beetech.kayak.injection.DaggerHomeViewComponent;
import com.beetech.kayak.injection.HomeViewModule;
import com.beetech.kayak.ocr.CameraData;
import com.beetech.kayak.presenter.HomePresenter;
import com.beetech.kayak.presenter.loader.PresenterFactory;
import com.beetech.kayak.view.HomeView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public final class HomeFragment extends BaseFragment<HomePresenter, HomeView> implements HomeView {
    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;

    public final static int PHOTO_GALLERY = 1;    // フォトギャラリーID
    public final static int REQUEST_CODE_UNITY_CAMERA = 1696;
    @BindView(R.id.img_preview)
    ImageView imgPreview;
    @BindView(R.id.btn_select)
    Button btnSelect;


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
        if (mPresenter != null) {
            new Handler().postDelayed(() -> {
                mPresenter.onActivityResult(requestCode, resultCode, data);
            }, 800);
        }
        if(requestCode == REQUEST_CODE_UNITY_CAMERA){
            if(resultCode == Activity.RESULT_OK){
                if(mPresenter!=null){
                    String base64 = data.getStringExtra("data_image");
                    mPresenter.processImageData(base64);
                }
            }
        }
    }

    @OnClick(R.id.btn_select)
    void onSelectGallery() {
        CameraData cameraData = CameraData.getInstance();
        cameraData.setSdcard(true);

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, PHOTO_GALLERY);

        //start unity camera
    }

    @Override
    public void showPreviewImage(Bitmap photoUri) {
        GlideApp.with(getActivity())
                .load(photoUri)
                .into(imgPreview);
    }

    @NonNull
    @Override
    protected PresenterFactory<HomePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
