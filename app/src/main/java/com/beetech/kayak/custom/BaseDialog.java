package com.beetech.kayak.custom;

import android.app.AlertDialog;
import android.content.Context;
import androidx.annotation.StringRes;

public class BaseDialog {

    public static String b = "46646a756";
    private static boolean shown = false;

    private AlertDialog dialog = null;
    private AlertDialog.Builder builder;

    private Context context;

    public BaseDialog(Context context) {
        builder = new AlertDialog.Builder(context)
                .setOnDismissListener(dialog1 -> {
                    initialize();
                });
    }

    public BaseDialog(Context context, String title, String message,
                      String positive, OnDialogListener onPositiveClick,
                      String negative, OnDialogListener onNegativeClick,
                      boolean isCancelable) {
        builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive, (dialog1, which) -> {
                    dialog1.cancel();
                    if (onPositiveClick != null) {
                        onPositiveClick.onClick();
                    }
                })
                .setNegativeButton(negative, (dialog1, which) -> {
                    dialog1.cancel();
                    if (onNegativeClick != null) {
                        onNegativeClick.onClick();
                    }
                })
                .setOnDismissListener(dialog1 -> {
                    initialize();
                })
                .setCancelable(isCancelable);
        dialog = builder.create();
        dialog.show();
    }

    public BaseDialog setTitle(String title) {
        builder.setTitle(title);
        return this;
    }

    public BaseDialog setTitle(@StringRes int title) {
        builder.setTitle(title);
        return this;
    }

    public BaseDialog setMessage(String message) {
        builder.setMessage(message);
        return this;
    }

    public BaseDialog setMessage(@StringRes int message) {
        builder.setMessage(message);
        return this;
    }

    public BaseDialog setPositiveButton(String label, OnDialogListener onDialogListener) {
        builder.setPositiveButton(label, (dialog, id) -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public BaseDialog setPositiveButton(@StringRes int label, OnDialogListener onDialogListener) {
        builder.setPositiveButton(label, (dialog, id) -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public BaseDialog setNegativeButton(String label, OnDialogListener onDialogListener) {
        builder.setNegativeButton(label, (dialog, which) -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public BaseDialog setNegativeButton(@StringRes int label, OnDialogListener onDialogListener) {
        builder.setNegativeButton(label, (dialog, which) -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public BaseDialog setCancelable(boolean isCancelable) {
        builder.setCancelable(isCancelable);
        builder.setOnCancelListener(dialog -> {
            initialize();
        });
        return this;
    }

    public BaseDialog setCancelable(boolean isCancelable, OnDialogListener onDialogListener) {
        builder.setCancelable(isCancelable);
        builder.setOnCancelListener(dialog -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public void show() {
        if (!isShown() && builder != null) {
            dialog = builder.create();
            dialog.show();
            forceShown();
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

    public interface OnDialogListener {
        void onClick();
    }
}
