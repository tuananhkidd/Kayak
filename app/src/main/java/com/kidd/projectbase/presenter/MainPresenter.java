package com.kidd.projectbase.presenter;

import com.kidd.projectbase.view.MainView;

public interface MainPresenter extends BasePresenter<MainView> {
    void testApi();
    int getCountOnCreate();
}