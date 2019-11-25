package com.beetech.kayak.view;


import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.UiThread;

@UiThread
public interface HomeView extends BaseView {
    Activity getActivity();

    void showPreviewImage(Bitmap photoUri);
}