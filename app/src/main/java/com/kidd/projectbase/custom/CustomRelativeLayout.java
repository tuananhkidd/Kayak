package com.kidd.projectbase.custom;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.RelativeLayout;

public class CustomRelativeLayout extends RelativeLayout {

    private int[] mInsets = new int[4];

    public CustomRelativeLayout(Context context) {
        super(context);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public final WindowInsets onApplyWindowInsets(WindowInsets insets) {
        mInsets[0] = insets.getSystemWindowInsetLeft();
        mInsets[1] = insets.getSystemWindowInsetTop();
        mInsets[2] = insets.getSystemWindowInsetRight();
        return super.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0,
                insets.getSystemWindowInsetBottom()));
    }
}