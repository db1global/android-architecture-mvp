package br.com.db1.mvp.dialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by vinicius.camargo on 27/07/2018
 */
public interface DialogFactory {

    ProgressDialog makeProgressDialog(@NonNull Context context);

    Dialog makeOneOptionDialog(@NonNull Context context,
                               String title,
                               String message,
                               String buttonOkText,
                               DialogInterface.OnClickListener positiveListener);

    Dialog makeDialogNoAction(@NonNull Context context,
                              String title,
                              String message,
                              @DrawableRes int icon);

    Dialog makeDialogNoTitle(@NonNull Context context,
                             String message,
                             String buttonLabel,
                             DialogInterface.OnClickListener onEvent);

    Dialog makeYesNoDialog(@NonNull Context context,
                           String title,
                           String message,
                           String buttonYesText,
                           DialogInterface.OnClickListener positiveListener,
                           String buttonNoText,
                           DialogInterface.OnClickListener negativeListener);
}
