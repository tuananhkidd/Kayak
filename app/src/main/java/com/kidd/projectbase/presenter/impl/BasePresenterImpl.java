package com.kidd.projectbase.presenter.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kidd.projectbase.presenter.BasePresenter;
import com.kidd.projectbase.view.BaseView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Abstract presenter implementation that contains base implementation for other presenters.
 * Subclasses must call super for all {@link BasePresenter} method overriding.
 */
abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {
    /**
     * The view
     */
    @Nullable
    protected V mView;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onViewAttached(@NonNull V view) {
        mView = view;
    }


    @Override
    public void onStart(boolean viewCreated) {
        if (viewCreated && mView != null) {
            mView.initView();
            mView.initData();
        }
    }

    @Override
    public void onStop() {

    }


    @Override
    public void onViewDetached() {
        mView = null;
    }

    @Override
    public void onPresenterDestroyed() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

//    @Nullable
////    protected ErrorResponse handleError(Throwable throwable) {
////        return handleError(throwable, true);
////    }
////
////    @Nullable
////    protected ErrorResponse handleError(Throwable throwable, boolean isShowDialog) {
////        if (mView == null)
////            return null;
////        ErrorResponse errorResponse = new ErrorResponse();
////        if (throwable instanceof NetworkCheckerInterceptor.NoConnectivityException) {
////            // Handle error no net working connection
////            if (isShowDialog)
////                mView.showErrorDialog(throwable.getMessage());
////            errorResponse.setCode(Define.Api.HttpCode.NETWORK_NOT_CONNECT);
////            errorResponse.setMessage(throwable.getMessage());
////        } else if (throwable instanceof ConnectException
////                || throwable instanceof SocketTimeoutException
////                || throwable instanceof UnknownHostException
////                || throwable instanceof IOException) {
////            if (isShowDialog)
////                mView.showErrorDialog(R.string.internet_timeout);
////            errorResponse.setCode(Define.Api.HttpCode.CANNOT_CONNECT_TO_SERVER);
////            errorResponse.setMessage(throwable.getMessage());
////        } else if (throwable instanceof HttpException) {
////            HttpException httpException = (HttpException) throwable;
////            try {
////                String errorBody = httpException.response().errorBody().string();
////                Gson gson = new GsonBuilder().create();
////                ApiObjectResponse apiObjectResponse = gson.fromJson(errorBody, ApiObjectResponse.class);
////                if (apiObjectResponse != null) {
////                    errorResponse = apiObjectResponse.getError();
////                } else {
////                    errorResponse.setCode(httpException.code());
////                }
////            } catch (Exception e) {
////                errorResponse.setCode(httpException.code());
////                e.printStackTrace();
////            }
////        } else {
////            // TODO handle another error
////        }
////        if (isShowDialog)
////            showError(errorResponse);
////        return errorResponse;
////    }
////
////    protected void showError(ErrorResponse error) {
////        // navigate from fragment to view
////        // handle details error
////    }
}