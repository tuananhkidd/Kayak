package com.kidd.projectbase.view.impl;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;

import com.kidd.projectbase.R;
import com.kidd.projectbase.adapter.GalleryAdapter;
import com.kidd.projectbase.entity.ImageObject;
import com.kidd.projectbase.injection.AppComponent;
import com.kidd.projectbase.injection.DaggerTestViewComponent;
import com.kidd.projectbase.injection.TestViewModule;
import com.kidd.projectbase.presenter.TestPresenter;
import com.kidd.projectbase.presenter.loader.PresenterFactory;
import com.kidd.projectbase.utils.Constants;
import com.kidd.projectbase.utils.RecycleViewUtils;
import com.kidd.projectbase.utils.ToastUtil;
import com.kidd.projectbase.utils.Util;
import com.kidd.projectbase.view.TestView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public final class TestFragment extends BaseFragment<TestPresenter, TestView> implements TestView {
    @Inject
    PresenterFactory<TestPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public TestFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.rcvImageGallery)
    RecyclerView mRecycle;
    @BindView(R.id.pbGallery)
    ProgressBar mProgressBar;

    private ListenerChooseImage listenerChooseImage;

    private GalleryAdapter adapter;
    private ArrayList<ImageObject> lsImageChoosed = new ArrayList<>();
    private int numberImage = 3;
    private String type = Constants.VALUE_TYPE_CHOOSE_IMAGE_MUTIL;

    @Override
    public void initView() {
        super.initView();
//        mProgressBar.setVisibility(View.VISIBLE);
//        lsImageChoosed = new ArrayList<>();

//        new LoadImagePath().execute();
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
        getViewController().backFromAddFragment(null);
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

    @OnClick(R.id.btn_show)
    void onShow() {
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showLayoutRetry();
        }
    }

    private void loadUi(ArrayList<ImageObject> ls) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int widthScreen = Util.pxToDp(displayMetrics.widthPixels, getActivity());
        int realWidth = Util.dpToPx((widthScreen - 20) / 3, getActivity());

        adapter = new GalleryAdapter(ls, realWidth, getContext());
        RecycleViewUtils.showListRcvImageGallery(mRecycle, adapter, this::saveListChoose, numberImage, getContext());
    }

    public class LoadImagePath extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            return Util.getAllImagesPath(getActivity());
        }

        @Override
        protected void onPostExecute(ArrayList<String> ls) {
            super.onPostExecute(ls);
            mProgressBar.setVisibility(View.GONE);
            // update image in here
            ArrayList<ImageObject> lsImages = new ArrayList<>();
            for (int i = 0; i < ls.size(); i++) {
                lsImages.add(new ImageObject(i, ls.get(i), false));
            }
            loadUi(lsImages);
        }
    }

    private void saveListChoose(ImageObject imageObject) {
        switch (type) {
            case Constants.VALUE_TYPE_CHOOSE_IMAGE_MUTIL: {
                if (imageObject.isChoose()) {
                    lsImageChoosed.add(imageObject);
                } else {
                    for (int i = 0; i < lsImageChoosed.size(); i++) {
                        if (lsImageChoosed.get(i).getPath().equalsIgnoreCase(imageObject.getPath())) {
                            // change to fail
                            lsImageChoosed.remove(i);
                        }
                    }
                }
//                listenerChooseImage.tranferListImageChoose(lsImageChoosed);
                break;
            }
            case Constants.VALUE_TYPE_CHOOSE_IMAGE_SINGLE: {
                if (imageObject.isChoose()) {
                    lsImageChoosed.add(imageObject);
                }
//                listenerChooseImage.tranferListImageChoose(lsImageChoosed);
                break;
            }
        }

    }

    public void removeAllCheck() {
        ArrayList<ImageObject> lsImageNew = new ArrayList<>();
        ArrayList<String> lsUri = Util.getAllImagesPath(getActivity());
        for (int i = 0; i < lsUri.size(); i++) {
            lsImageNew.add(new ImageObject(i, lsUri.get(i), false));
        }
        adapter.setLsImages(lsImageNew);
        adapter.notifyDataSetChanged();
        lsImageChoosed.clear();
    }

    /**
     * Interface to transfer image choose to parent activity
     */
    public interface ListenerChooseImage {
        void tranferListImageChoose(ArrayList<ImageObject> lsImage);
    }

    public void setListenerChooseImage(ListenerChooseImage listenerChooseImage) {
        this.listenerChooseImage = listenerChooseImage;
    }

    @Override
    void onRetry() {
        ToastUtil.show("Test nao");
    }
}
