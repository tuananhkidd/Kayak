package com.kidd.projectbase.view;

import java.util.List;

public interface LoadMoreBaseView<T> extends BaseView {
    void showLoadmoreProgress();

    void hideLoadmoreProgress();

    void enableLoadmore(boolean enableLoadmore);

    void showLayoutNoResult(boolean visible);

    void showData(List<T> data);
}
