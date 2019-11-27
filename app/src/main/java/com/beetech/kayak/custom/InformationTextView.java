package com.beetech.kayak.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.beetech.kayak.R;

public class InformationTextView extends LinearLayout {
    private Context context;
    private TextView tvDetail;
    private TextView tvTitle;

    public InformationTextView(Context context) {
        super(context);
        init(context);
    }

    public InformationTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setParam(attrs);
    }

    public InformationTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setParam(attrs);
    }

    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_information_textview, this, true);
        tvDetail = view.findViewById(R.id.tv_detail);
        tvTitle = view.findViewById(R.id.tv_title);
    }

    private void setParam(AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.InformationTextView, 0, 0);

        String title = typedArray.getString(R.styleable.InformationTextView_itv_title);
        String detail = typedArray.getString(R.styleable.InformationTextView_itv_detail);

        setTitle(title);
        setDetail(detail);
    }

    public void setDetail(String tvDetail) {
        this.tvDetail.setText(tvDetail);
    }

    public void setTitle(String tvTitle) {
        this.tvTitle.setText(tvTitle);
    }
}
