package com.kidd.projectbase.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kidd.projectbase.R;
import com.kidd.projectbase.base.ViewController;
import com.kidd.projectbase.bus_event.NetworkChangeEvent;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.DaggerMainViewComponent;
import com.kidd.projectbase.injection.MainViewModule;
import com.kidd.projectbase.presenter.MainPresenter;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.service.LoginSocialEngine;
import com.kidd.projectbase.service.NetworkChangeReceiver;
import com.kidd.projectbase.utils.DeviceUtil;
import com.kidd.projectbase.view.MainView;
import com.zing.zalo.zalosdk.oauth.ZaloSDK;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;

public final class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView {
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;
    @Inject
    ViewController mViewController;
    @BindView(R.id.ln_retry)
    RelativeLayout rlRetry;
    @BindView(R.id.btn_retry)
    Button btnRetry;
    NetworkChangeReceiver receiver;

    private int countOnNetWorkEvent = 0;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerMainViewComponent.builder()
                .appComponent(parentComponent)
                .mainViewModule(new MainViewModule(this, getRootViewId()))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        super.initView();
        getViewController().addFragment(HomeFragment.class, null);
        EventBus.getDefault().register(this);
        DeviceUtil.getApplicationHashKey(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    @Override
    protected int getRootViewId() {
        return R.id.rlMain;
    }

    @Override
    public ViewController getViewController() {
        return mViewController;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void showLayoutRetry() {
//        super.showLayoutRetry();
        rlRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLayoutRetry() {
//        super.hideLayoutRetry();
        rlRetry.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onNetworkChangeEvent(NetworkChangeEvent networkChangeEvent) {
        if (countOnNetWorkEvent != 0) {
            if (networkChangeEvent.isNetWork()) {
                hideLayoutRetry();
                if (getViewController().getCurrentFragment() == null) {
                } else {
                    getViewController().getCurrentFragment().onRefreshData();
                }
            } else {
                showLayoutRetry();
            }
        }
//        countOnNetWorkEvent++;

        EventBus.getDefault().removeStickyEvent(networkChangeEvent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        ZaloSDK.Instance.onActivityResult(this,requestCode, resultCode, data);
        getViewController().getCurrentFragment().onActivityResult(requestCode,resultCode,data);
        if (resultCode == Activity.RESULT_OK)
            if (requestCode == LoginSocialEngine.GOOGLE_REQUEST_CODE_LOGIN) {
                try {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    BaseFragment currentFragment = getViewController().getCurrentFragment();
                    if (currentFragment instanceof HomeFragment) {
                        ((HomeFragment) currentFragment).showUserInfo(account.getId(), account.getDisplayName(), account.getPhotoUrl().toString());
                    }
                } catch (ApiException e) {
                    Log.w("HomeFrament", "signInResult:failed code=" + e.getStatusCode());
                }
            }
    }
}
