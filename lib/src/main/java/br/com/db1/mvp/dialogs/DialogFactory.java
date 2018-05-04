package br.com.db1.mvp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by vinicius.camargo on 16/04/2018.
 */
public class DialogFactory {

    private DialogFactory() {
    }

    private static AlertDialog.Builder createBuilderDialog(Context context,
                                                           String message) {
        return new AlertDialog.Builder(context).setMessage(message);
    }

    public static Dialog makeOneOptionDialog(@NonNull Context context,
                                             String title,
                                             String message,
                                             String buttonOkText,
                                             DialogInterface.OnClickListener positiveListener) {
        return createBuilderDialog(context, message)
                .setTitle(title)
                .setPositiveButton(buttonOkText, positiveListener)
                .create();
    }

    public static Dialog makeDialogNoAction(@NonNull Context context,
                                            String title,
                                            String message,
                                            @DrawableRes int icon) {
        return createBuilderDialog(context, message)
                .setTitle(title)
                .setIcon(icon)
                .create();
    }

    public static Dialog makeDialogNoTitle(@NonNull Context context,
                                           String message,
                                           String buttonLabel,
                                           DialogInterface.OnClickListener onEvent) {
        return createBuilderDialog(context, message)
                .setPositiveButton(buttonLabel, onEvent)
                .create();
    }

    public static Dialog makeYesNoDialog(@NonNull Context context,
                                         String title,
                                         String message,
                                         String buttonYesText,
                                         DialogInterface.OnClickListener positiveListener,
                                         String buttonNoText,
                                         DialogInterface.OnClickListener negativeListener) {
        return createBuilderDialog(context, message)
                .setTitle(title)
                .setPositiveButton(buttonYesText, positiveListener)
                .setNegativeButton(buttonNoText, negativeListener)
                .create();
    }

}