package com.kidd.projectbase.view;


import androidx.annotation.UiThread;

@UiThread
public interface HomeView extends BaseView {

    void showUserInfo(String id, String userName, String avatar);
}