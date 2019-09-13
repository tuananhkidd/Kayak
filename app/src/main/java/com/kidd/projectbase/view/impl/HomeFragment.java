package com.kidd.projectbase.view.impl;

import android.animation.Animator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.kidd.projectbase.R;
import com.kidd.projectbase.custom.CustomToolbar;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.DaggerHomeViewComponent;
import com.kidd.projectbase.injection.HomeViewModule;
import com.kidd.projectbase.presenter.HomePresenter;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.view.HomeView;

import javax.inject.Inject;

import butterknife.BindView;

public final class HomeFragment extends BaseFragment<HomePresenter, HomeView> implements HomeView {
    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;

    @BindView(R.id.iv)
    ImageView ivAnim;
    @BindView(R.id.container)
    View container;
    @BindView(R.id.toolbar)
    CustomToolbar toolbar;
    // Your presenter is available using the mPresenter variable

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerHomeViewComponent.builder()
                .appComponent(parentComponent)
                .homeViewModule(new HomeViewModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        super.initView();


        toolbar.setOnClickLeftButtonListener(() -> {
            getActivity().finish();
        });

        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ivAnim.animate()
                                .translationY(-100)
                                .scaleY(0.6f)
                                .scaleX(0.6f)
                                .setDuration(500)
                                .start();
                        Log.d("ahihi", "onTouch: down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("ahihi", "onTouch: move");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("ahihi", "onTouch: up");
                        ivAnim.animate()
                                .translationY(0)
                                .scaleY(1f)
                                .scaleX(1f)
                                .setDuration(500)
                                .start();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    void onRetry() {

    }

    @Override
    void onRefreshData() {

    }

    @NonNull
    @Override
    protected PresenterFactory<HomePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
