package com.kidd.projectbase.view.impl;

import android.support.annotation.NonNull;
import android.widget.Button;

import com.kidd.projectbase.R;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.DaggerTestViewComponent;
import com.kidd.projectbase.injection.TestViewModule;
import com.kidd.projectbase.presenter.TestPresenter;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.view.TestView;

import javax.inject.Inject;

import butterknife.BindView;

public final class TestFragment extends BaseFragment<TestPresenter, TestView> implements TestView {
    @Inject
    PresenterFactory<TestPresenter> mPresenterFactory;

    @BindView(R.id.btn)
    Button btn;
    // Your presenter is available using the mPresenter variable

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public void initView() {
        super.initView();
        btn.setOnClickListener(v->{
            getViewController().addFragment(Test2Fragment.class,null);
        });
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerTestViewComponent.builder()
                .appComponent(parentComponent)
                .testViewModule(new TestViewModule())
                .build()
                .inject(this);
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_test;
    }

    @NonNull
    @Override
    protected PresenterFactory<TestPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
