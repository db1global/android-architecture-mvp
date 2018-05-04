package br.com.db1.mvp.view;

import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

/**
 * Created by vinicius.camargo on 17/04/2018.
 */
public interface IView {


    void showMessage(@StringRes int titleRes, @StringRes int messageRes,
                     @Nullable DialogInterface.OnClickListener onEvent);

    void showMessage(@StringRes int titleRes, @NonNull String message,
                     @Nullable DialogInterface.OnClickListener onEvent);

    void showMessage(@StringRes int titleRes, @StringRes int messageRes);

    void showMessage(@StringRes int titleRes, @NonNull String message);

    void showMessage(@DrawableRes int iconRes, @StringRes int titleRes, @StringRes int messageRes,
                     @Nullable DialogInterface.OnClickListener onEvent);

    void showMessage(@DrawableRes int iconRes, @StringRes int titleRes, @NonNull String message,
                     @Nullable DialogInterface.OnClickListener onEvent);

    void showMessageWithoutTitle(@StringRes int messageRes,
                                 @StringRes int buttonLabel,
                                 @Nullable DialogInterface.OnClickListener onEvent);

    void showMessageWithoutTitle(@NonNull String message,
                                 @StringRes int buttonLabel,
                                 @Nullable DialogInterface.OnClickListener onEvent);

    void showYesNo(@StringRes int titleRes, @StringRes int messageRes,
                   @StringRes int positiveLabel,
                   @Nullable DialogInterface.OnClickListener onPositiveEvent,
                   @StringRes int negativeLabel,
                   @Nullable DialogInterface.OnClickListener onNegativeEvent);

    void showYesNo(@StringRes int titleRes, @NonNull String message,
                   @StringRes int positiveLabel,
                   @Nullable DialogInterface.OnClickListener onPositiveEvent,
                   @StringRes int negativeLabel,
                   @Nullable DialogInterface.OnClickListener onNegativeEvent);


    void showProgress(@Nullable String message);

    void showProgress(@StringRes int messageRes);

    void hideProgress();

    void showEmptyState();

    void hideEmptyState();

}