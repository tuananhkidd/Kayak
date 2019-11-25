package com.beetech.kayak.custom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import com.beetech.kayak.R;

public class LoadingDialog {

    private static boolean shown = false;

    private AlertDialog dialog = null;

    private static LoadingDialog instance = null;

    private Context context;

    public static LoadingDialog getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            instance = new LoadingDialog(context);
            return instance;
        }
    }

    private LoadingDialog(Context context) {
        this.context = context;
        if (context != null && !LoadingDialog.isShown()) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater li = LayoutInflater.from(context);
            View dialogView = li.inflate(R.layout.layout_loading, null);
            dialogBuilder.setView(dialogView);
            dialogBuilder.setCancelable(false);
            dialog = dialogBuilder.create();
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
//            dialog.setOnKeyListener((dialog, keyCode, event) -> {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    // we cannot close dialog when we press back button
//                }
//                return false;
//            });
        }
    }

    public void show() {
        if (!((Activity) context).isFinishing()) {
            if (!LoadingDialog.isShown() && dialog != null) {
                forceShown();
                dialog.show();
            }
        }
    }

    public void hidden() {
        if (LoadingDialog.isShown() && dialog != null && dialog.isShowing()) {
            initialize();
            dialog.dismiss();
        }
    }

    private static boolean isShown() {
        return shown;
    }

    private static void forceShown() {
        shown = true;
    }

    private static void initialize() {
        shown = false;
    }

    public void destroyLoadingDialog() {
        if (instance != null && instance.dialog != null) {
            instance.dialog.dismiss();
        }
        instance = null;
    }
}
