package com.beetech.kayak.presenter;

import android.app.Activity;
import android.content.Intent;

import com.beetech.kayak.view.HomeView;

public interface HomePresenter extends BasePresenter<HomeView> {
    void onActivityResult(int requestCode, int resultCode, Intent data);
}