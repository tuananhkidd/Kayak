package com.beetech.kayak.presenter;

import com.beetech.kayak.view.HomeView;

public interface HomePresenter extends BasePresenter<HomeView> {
    void processImageData(String base64String);
}