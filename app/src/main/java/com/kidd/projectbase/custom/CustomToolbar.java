package com.kidd.projectbase.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.kidd.projectbase.R;

public class CustomToolbar extends RelativeLayout {
    private Context mContext;
    private ImageView btnLeft;
    private ImageView btnRight;
    private TextView tvTitle;

    private OnClickLeftButtonListener onClickLeftButtonListener;
    private OnClickRightButtonListener onClickRightButtonListener;

    public void setOnClickLeftButtonListener(OnClickLeftButtonListener onClickLeftButtonListener) {
        this.onClickLeftButtonListener = onClickLeftButtonListener;
    }

    public void setOnClickRightButtonListener(OnClickRightButtonListener onClickRightButtonListener) {
        this.onClickRightButtonListener = onClickRightButtonListener;
    }

    public CustomToolbar(Context context) {
        super(context);
        init(context);
    }

    public CustomToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setParam(attrs);
    }

    public CustomToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setParam(attrs);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this, true);

        mContext = context;
        btnLeft = view.findViewById(R.id.btn_left);
        btnRight = view.findViewById(R.id.btn_right);
        tvTitle = view.findViewById(R.id.tv_title);

        btnLeft.setOnClickListener(v -> {
            if (onClickLeftButtonListener != null) {
                onClickLeftButtonListener.onClick();
            }
        });

        btnRight.setOnClickListener(v -> {
            if (onClickRightButtonListener != null) {
                onClickRightButtonListener.onClick();
            }
        });
    }

    public interface OnClickLeftButtonListener {
        void onClick();
    }

    public interface OnClickRightButtonListener {
        void onClick();
    }

    private void setParam(AttributeSet attrs) {
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomToolbar, 0, 0);

        String title = typedArray.getString(R.styleable.CustomToolbar_ct_title);
        tvTitle.setText(title);

        int leftDrawable = typedArray.getInt(R.styleable.CustomToolbar_ct_src_left, R.drawable.ic_arrow_left);
        btnLeft.setImageResource(leftDrawable);


        int rightDrawable = typedArray.getInt(R.styleable.CustomToolbar_ct_src_left, R.drawable.ic_arrow_right);
        btnRight.setImageResource(rightDrawable);

        boolean isVisible = typedArray.getBoolean(R.styleable.CustomToolbar_ct_visible_right, false);
        btnRight.setVisibility(isVisible ? View.VISIBLE: View.INVISIBLE);
    }

    public void setTvTitle(String title) {
        tvTitle.setText(title);
    }

    public void setLeftDrawable(@DrawableRes int leftDrawable) {
        btnLeft.setImageResource(leftDrawable);
    }

    public void setRightDrawable(@DrawableRes int rightDrawable) {
        btnRight.setImageResource(rightDrawable);
    }

}
