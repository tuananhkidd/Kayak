package com.kidd.projectbase.view.impl;

import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.TextView;

import com.kidd.projectbase.R;
import com.kidd.projectbase.custom.tooltip.ToolTip;
import com.kidd.projectbase.custom.tooltip.ToolTipView;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.DaggerTest2ViewComponent;
import com.kidd.projectbase.injection.Test2ViewModule;
import com.kidd.projectbase.presenter.Test2Presenter;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.utils.ToastUtil;
import com.kidd.projectbase.view.Test2View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public final class Test2Fragment extends BaseFragment<Test2Presenter, Test2View> implements Test2View {
    @Inject
    PresenterFactory<Test2Presenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable
    @BindView(R.id.tv_test)
    TextView tvTest;
    public Test2Fragment() {
        // Required empty public constructor
    }

    @Override
    public void initView() {
        super.initView();
//        showLayoutRetry();
        tvTest.setOnClickListener(v->{
            ToolTip toolTip = new ToolTip.Builder()
                    .withText("Simple Tool Tip!")
                    .withTextSize(getResources().getDimension(R.dimen.text_size16))
                    .withPadding(16,16,16,16)
                    .withCornerRadius(getResources().getDimension(R.dimen.margin_8dp))
                    .build();
            ToolTipView toolTipView = new ToolTipView.Builder(getContext())
                    .withAnchor(tvTest)
                    .withToolTip(toolTip)
                    .withGravity(Gravity.BOTTOM)
                    .build();
            toolTipView.show();

            toolTipView.setOnToolTipClickedListener(new ToolTipView.OnToolTipClickedListener() {
                @Override
                public void onToolTipClicked(ToolTipView toolTipView) {
                    ToastUtil.show("ahihi");
                }
            });
        });

    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerTest2ViewComponent.builder()
                .appComponent(parentComponent)
                .test2ViewModule(new Test2ViewModule())
                .build()
                .inject(this);
    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_test2;
    }

    @NonNull
    @Override
    protected PresenterFactory<Test2Presenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @OnClick(R.id.tv_test)
    void onClickTest() {
        getViewController().addFragment(TestFragment.class, null);
    }

    @Override
    void onRetry() {
        ToastUtil.show("ahuhu");
        hideLayoutRetry();
    }

    @Override
    void onRefreshData() {

    }
}
