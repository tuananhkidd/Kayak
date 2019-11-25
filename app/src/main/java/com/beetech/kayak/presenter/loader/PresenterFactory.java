package com.beetech.kayak.presenter.loader;


import androidx.annotation.NonNull;

import com.beetech.kayak.presenter.BasePresenter;

/**
 * Factory to implement to create a presenter
 */
public interface PresenterFactory<T extends BasePresenter> {
    @NonNull
    T create();
}
