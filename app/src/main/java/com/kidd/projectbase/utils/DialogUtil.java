package com.kidd.projectbase.utils;

import android.app.Dialog;
import android.content.Context;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;


public class DialogUtil {
    public static Dialog createSimpleOkErrorDialog(Context context, String title, String message, String okMessage) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(okMessage, null);
        return alertDialog.create();
    }

    public static Dialog createSimpleOkErrorDialog(Context context,
                                                   @StringRes int titleResource,
                                                   @StringRes int messageResource,
                                                   @StringRes int okMessage) {

        return createSimpleOkErrorDialog(context,
                context.getString(titleResource),
                context.getString(messageResource),
                context.getString(okMessage));
    }

    public static Dialog createGenericErrorDialog(Context context, String title, String message, String okMessage) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(okMessage, null);
        return alertDialog.create();
    }

    public static Dialog createGenericErrorDialog(Context context,
                                                  @StringRes int titleResource,
                                                  @StringRes int messageResource,
                                                  @StringRes int okResource) {
        return createGenericErrorDialog(context, context.getString(titleResource), context.getString(messageResource), context.getString(okResource));
    }

    public static Dialog createSimpleDialog(Context context,
                                            String title,
                                            String message,
                                            String negativeMessage,
                                            String positiveMessage,
                                            Dialog.OnClickListener negativeListener,
                                            Dialog.OnClickListener positiveListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(negativeMessage, negativeListener)
                .setPositiveButton(positiveMessage, positiveListener);
        return alertDialog.create();

    }

    public static Dialog createSimpleDialog(Context context,
                                            @StringRes int title,
                                            @StringRes int message,
                                            @StringRes int negativeMessage,
                                            @StringRes int positiveMessage,
                                            Dialog.OnClickListener negativeListener,
                                            Dialog.OnClickListener positiveListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(title))
                .setMessage(context.getString(message))
                .setNegativeButton(context.getString(negativeMessage), negativeListener)
                .setPositiveButton(context.getString(positiveMessage), positiveListener);
        return alertDialog.create();

    }

}
