package br.com.db1.mvp.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import br.com.db1.mvp.R;
import br.com.db1.mvp.dialogs.DefaultDialogFactory;
import br.com.db1.mvp.dialogs.DialogFactory;
import br.com.db1.mvp.util.StringUtils;

/**
 * Created by andre.moraes on 16/02/2018.
 */
public class BaseViewDecorator implements IView {

    private Context context;
    private IEmptyStateComponent emptyStateComponent;
    private ProgressDialog progressDialog;
    private DialogFactory factory;

    public BaseViewDecorator(@NonNull Context context) {
        this(context, null, new DefaultDialogFactory());
    }

    public BaseViewDecorator(@NonNull Context context, DialogFactory factory) {
        this(context, null, factory);
    }

    public BaseViewDecorator(@NonNull Context context, IEmptyStateComponent emptyStateComponent) {
        this(context, emptyStateComponent, new DefaultDialogFactory());
    }

    public BaseViewDecorator(@NonNull Context context,
                             IEmptyStateComponent emptyStateComponent, DialogFactory factory) {
        this.context = context;
        this.emptyStateComponent = emptyStateComponent;
        this.factory = factory;
        this.progressDialog = factory.makeProgressDialog(context);
    }


    @Override
    public void showMessage(@StringRes int titleRes, @StringRes int messageRes,
                            @Nullable DialogInterface.OnClickListener onEvent) {
        showMessage(titleRes, context.getString(messageRes), onEvent);
    }

    @Override
    public void showMessage(@StringRes int titleRes, @StringRes int messageRes) {
        showMessage(titleRes, context.getString(messageRes), null);
    }

    @Override
    public void showMessage(@StringRes int titleRes, @NonNull String message) {
        Dialog dialog = factory.makeOneOptionDialog(context,
                context.getString(titleRes),
                message,
                context.getString(R.string.ok),
                null);
        dialog.show();
    }

    @Override
    public void showMessage(@StringRes int titleRes, @NonNull String message,
                            @Nullable DialogInterface.OnClickListener onEvent) {

        Dialog dialog = factory.makeOneOptionDialog(context,
                context.getString(titleRes),
                message,
                context.getString(R.string.ok),
                onEvent);
        dialog.show();
    }


    @Override
    public void showYesNo(@StringRes int titleRes, @StringRes int messageRes,
                          @StringRes int positiveLabel,
                          @Nullable DialogInterface.OnClickListener onPositiveEvent,
                          @StringRes int negativeLabel,
                          @Nullable DialogInterface.OnClickListener onNegativeEvent) {
        showYesNo(titleRes,
                context.getString(messageRes),
                positiveLabel, onPositiveEvent,
                negativeLabel, onNegativeEvent);
    }

    @Override
    public void showYesNo(@StringRes int titleRes, @NonNull String message,
                          @StringRes int positiveLabel,
                          @Nullable DialogInterface.OnClickListener onPositiveEvent,
                          @StringRes int negativeLabel,
                          @Nullable DialogInterface.OnClickListener onNegativeEvent) {
        Dialog dialog = factory.makeYesNoDialog(
                context,
                context.getString(titleRes),
                message,
                context.getString(positiveLabel),
                onPositiveEvent,
                context.getString(negativeLabel),
                onNegativeEvent);
        dialog.show();
    }

    @Override
    public void showMessage(@DrawableRes int iconRes, @StringRes int titleRes, @NonNull String message,
                            @Nullable DialogInterface.OnClickListener onEvent) {
        Dialog dialog = factory.makeOneOptionDialog(context,
                context.getString(titleRes),
                message,
                context.getString(R.string.ok),
                onEvent,
                iconRes);
        dialog.show();
    }

    @Override
    public void showMessageWithoutTitle(int messageRes,
                                        @StringRes int buttonLabel,
                                        @Nullable DialogInterface.OnClickListener onEvent) {
        Dialog dialog = factory.makeDialogNoTitle(
                context,
                context.getString(messageRes),
                context.getString(buttonLabel),
                onEvent);
        dialog.show();
    }

    @Override
    public void showMessageWithoutTitle(@NonNull String message,
                                        @StringRes int buttonLabel,
                                        @Nullable DialogInterface.OnClickListener onEvent) {
        Dialog dialog = factory.makeDialogNoTitle(
                context,
                message,
                context.getString(buttonLabel),
                onEvent);
        dialog.show();
    }

    @Override
    public void showMessage(@DrawableRes int iconRes,
                            @StringRes int titleRes,
                            @StringRes int messageRes,
                            @Nullable DialogInterface.OnClickListener onEvent) {
        Dialog dialog = factory.makeOneOptionDialog(context,
                context.getString(titleRes),
                context.getString(messageRes),
                context.getString(R.string.ok),
                onEvent,
                iconRes);
        dialog.show();
    }

    @Override
    public void showProgress(@Nullable String message) {
        progressDialog.setMessage(StringUtils.isNotEmpty(message) ? message :
                context.getString(R.string.progress_message_default));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void showProgress(@StringRes int messageRes) {
        progressDialog.setMessage(context.getString(messageRes));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showEmptyState() {
        if (emptyStateComponent != null) {
            emptyStateComponent.show();
        }
    }

    @Override
    public void hideEmptyState() {
        if (emptyStateComponent != null) {
            emptyStateComponent.hide();
        }
    }

}