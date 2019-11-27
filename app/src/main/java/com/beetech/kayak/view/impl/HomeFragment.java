package com.beetech.kayak.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.beetech.kayak.GlideApp;
import com.beetech.kayak.R;
import com.beetech.kayak.bus_event.CardRecognizeSuccessEvent;
import com.beetech.kayak.custom.InformationTextView;
import com.beetech.kayak.injection.AppComponent;
import com.beetech.kayak.injection.DaggerHomeViewComponent;
import com.beetech.kayak.injection.HomeViewModule;
import com.beetech.kayak.ocr.CameraData;
import com.beetech.kayak.presenter.HomePresenter;
import com.beetech.kayak.presenter.loader.PresenterFactory;
import com.beetech.kayak.view.HomeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import jp.co.panasonic.pstc.ocr.card.CardRecog;

public final class HomeFragment extends BaseFragment<HomePresenter, HomeView> implements HomeView {
    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;

    public final static int PHOTO_GALLERY = 1;    // フォトギャラリーID
    public final static int REQUEST_CODE_UNITY_CAMERA = 1696;
    @BindView(R.id.img_preview)
    ImageView imgPreview;
    @BindView(R.id.btn_select)
    Button btnSelect;
    @BindView(R.id.tv_name)
    InformationTextView tvName;
    @BindView(R.id.tv_company)
    InformationTextView tvCompany;
    @BindView(R.id.tv_address)
    InformationTextView tvAddress;
    @BindView(R.id.tv_phone_number)
    InformationTextView tvPhoneNumber;
    @BindView(R.id.tv_fax)
    InformationTextView tvFax;
    @BindView(R.id.tv_email)
    InformationTextView tvEmail;
    @BindView(R.id.tv_url)
    InformationTextView tvUrl;


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
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean backPressed() {
        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_UNITY_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                if (mPresenter != null) {
                    String base64 = data.getStringExtra("data_image");
                    Log.v("ahihi", "data : " + base64);
//                    mPresenter.processImageData(base64);
                }
            }
        }
    }

    @OnClick(R.id.btn_select)
    void onSelectGallery() {
        CameraData cameraData = CameraData.getInstance();
        cameraData.setSdcard(true);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, PHOTO_GALLERY);

        //start unity camera
    }

    @Override
    public void showPreviewImage(Bitmap photoUri) {
        GlideApp.with(getActivity())
                .load(photoUri)
                .into(imgPreview);
    }

    //region show information card
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCartRecognizeSuccessEvent(CardRecognizeSuccessEvent event) {
        CardRecog cardRecog = event.getRecog();
        if (cardRecog != null) {
            showInfo(cardRecog);
        }
    }

    private void showInfo(CardRecog cardRecog) {
        StringBuilder fullName = new StringBuilder();
        if (cardRecog.getFname() != null) {
            fullName.append(cardRecog.getFname().getText()).append(" ");
        }
        if (cardRecog.getLname() != null) {
            fullName.append(cardRecog.getLname().getText());
        }

        tvName.setDetail(fullName.toString());

        if (cardRecog.getCompany() != null) {
            tvCompany.setDetail(cardRecog.getCompany().getText());
        }
        StringBuilder address = new StringBuilder();
        if (cardRecog.getAddress1() != null) {
            address.append(cardRecog.getAddress1().getText()).append("\n");
        }
        if (cardRecog.getAddress2() != null) {
            address.append(cardRecog.getAddress2().getText());
        }

        tvAddress.setDetail(address.toString());

        StringBuilder phone = new StringBuilder();
        if (cardRecog.getTel1() != null) {
            phone.append(cardRecog.getTel1().getText()).append("\n");
        }
        if (cardRecog.getTel2() != null) {
            phone.append(cardRecog.getTel2().getText());
        }

        tvPhoneNumber.setDetail(phone.toString());

        StringBuilder fax = new StringBuilder();
        if (cardRecog.getFax1() != null) {
            fax.append(cardRecog.getTel1().getText()).append("\n");
        }
        if (cardRecog.getFax2() != null) {
            fax.append(cardRecog.getFax2().getText());
        }

        tvFax.setDetail(fax.toString());

        StringBuilder email = new StringBuilder();
        if (cardRecog.getMail1() != null) {
            email.append(cardRecog.getMail1().getText()).append("\n");
        }
        if (cardRecog.getMail2() != null) {
            email.append(cardRecog.getMail2().getText());
        }

        tvEmail.setDetail(email.toString());

        StringBuilder url = new StringBuilder();
        if (cardRecog.getUrl1() != null) {
            url.append(cardRecog.getUrl1().getText()).append("\n");
        }
        if (cardRecog.getUrl2() != null) {
            url.append(cardRecog.getUrl2().getText());
        }

        tvUrl.setDetail(url.toString());

    }

    private void showDoubleInformation(String infor1,String infor2,InformationTextView tv){
        StringBuilder infor = new StringBuilder();
        if (!TextUtils.isEmpty(infor1)) {
            infor.append(infor1).append("\n");
        }
        if (!TextUtils.isEmpty(infor2)) {
            infor.append(infor2);
        }

        tv.setDetail(infor.toString());
    }
    //endregion

    @NonNull
    @Override
    protected PresenterFactory<HomePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
