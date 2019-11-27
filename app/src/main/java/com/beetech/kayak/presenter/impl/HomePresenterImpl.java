package com.beetech.kayak.presenter.impl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;

import com.beetech.kayak.interactor.HomeInteractor;
import com.beetech.kayak.ocr.CameraData;
import com.beetech.kayak.ocr.CameraProgress;
import com.beetech.kayak.presenter.HomePresenter;
import com.beetech.kayak.view.HomeView;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;
import static com.beetech.kayak.view.impl.HomeFragment.PHOTO_GALLERY;

public final class HomePresenterImpl extends BasePresenterImpl<HomeView> implements HomePresenter {
    /**
     * The interactor
     */
    @NonNull
    private final HomeInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public HomePresenterImpl(@NonNull HomeInteractor interactor) {
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
        CameraData cameraData = CameraData.getInstance();
        cameraData.destroy();

        super.onPresenterDestroyed();
    }

    @Override
    public void processImageData(String base64String) {
        try {
            byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            CameraData cameraData = CameraData.getInstance();
            cameraData.init();
            if (bitmap == null || mView == null)
                return;
            mView.showPreviewImage(bitmap);

            cameraData.setBitmapData(bitmap);

            CameraProgress progress = new CameraProgress(mView.getActivity(), false);
            progress.setProgressTitle("Processing");        // プログレスタイトル
            progress.setProgressMessage("Please Wait...");    // プログレスメッセージ
            progress.setProgressCountUp(1);                    // カウントアップ値
            progress.setProgressCountUpMills(250);            // カウントアップ間隔(ミリ秒)
            progress.setProgressMaxCount(100);                // 最大カウント値
//                progress.execute("request");
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("ahihi", "error " + e.getMessage());
        }

    }
}