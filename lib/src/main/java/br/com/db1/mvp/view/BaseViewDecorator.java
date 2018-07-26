package br.com.db1.mvp.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;

import br.com.db1.mvp.R;
import br.com.db1.mvp.dialogs.DialogFactory;
import br.com.db1.mvp.util.StringUtils;

/**
 * Created by andre.moraes on 16/02/2018.
 */
public class BaseViewDecorator implements IView {

    private FragmentActivity parentActivity;
    private IEmptyStateComponent emptyStateComponent;
    private ProgressDialog progressDialog;
    private @LayoutRes
    int customGenericDialogLayout;
    private @LayoutRes
    int customProgressDialogLayout;

    public BaseViewDecorator(@NonNull FragmentActivity parentActivity) {
        this(parentActivity, null);
    }

    public BaseViewDecorator(@NonNull FragmentActivity parentActivity,
                             IEmptyStateComponent emptyStateComponent) {
        this.parentActivity = parentActivity;
        this.emptyStateComponent = emptyStateComponent;
        this.progressDialog = new ProgressDialog(parentActivity);
        this.progressDialog.setCanceledOnTouchOutside(false);
        this.customProgressDialogLayout = 0;
        this.customGenericDialogLayout = 0;
    }

    @Override
    public void showMessage(@StringRes int titleRes, @StringRes int messageRes,
                            @Nullable DialogInterface.OnClickListener onEvent) {
        showMessage(titleRes, parentActivity.getString(messageRes), onEvent);
    }

    @Override
    public void showMessage(@StringRes int titleRes, @StringRes int messageRes) {
        showMessage(titleRes, parentActivity.getString(messageRes), null);
    }

    @Override
    public void showMessage(@StringRes int titleRes, @NonNull String message) {
        Dialog dialog = DialogFactory.makeOneOptionDialog(parentActivity,
                parentActivity.getString(titleRes),
                message,
                parentActivity.getString(R.string.ok),
                null);
        if (customGenericDialogLayout != 0) {
            dialog.setContentView(customGenericDialogLayout);
        }
        dialog.show();
    }

    @Override
    public void showMessage(@StringRes int titleRes, @NonNull String message,
                            @Nullable DialogInterface.OnClickListener onEvent) {

        Dialog dialog = DialogFactory.makeOneOptionDialog(parentActivity,
                parentActivity.getString(titleRes),
                message,
                parentActivity.getString(R.string.ok),
                onEvent);
        if (customGenericDialogLayout != 0) {
            dialog.setContentView(customGenericDialogLayout);
        }
        dialog.show();
    }


    @Override
    public void showYesNo(@StringRes int titleRes, @StringRes int messageRes,
                          @StringRes int positiveLabel,
                          @Nullable DialogInterface.OnClickListener onPositiveEvent,
                          @StringRes int negativeLabel,
                          @Nullable DialogInterface.OnClickListener onNegativeEvent) {
        showYesNo(titleRes,
                parentActivity.getString(messageRes),
                positiveLabel, onPositiveEvent,
                negativeLabel, onNegativeEvent);
    }

    @Override
    public void showYesNo(@StringRes int titleRes, @NonNull String message,
                          @StringRes int positiveLabel,
                          @Nullable DialogInterface.OnClickListener onPositiveEvent,
                          @StringRes int negativeLabel,
                          @Nullable DialogInterface.OnClickListener onNegativeEvent) {
        Dialog dialog = DialogFactory.makeYesNoDialog(
                parentActivity,
                parentActivity.getString(titleRes),
                message,
                parentActivity.getString(positiveLabel),
                onPositiveEvent,
                parentActivity.getString(negativeLabel),
                onNegativeEvent);
        if (customGenericDialogLayout != 0) {
            dialog.setContentView(customGenericDialogLayout);
        }
        dialog.show();
    }

    @Override
    public void showMessage(@DrawableRes int iconRes, @StringRes int titleRes, @NonNull String message,
                            @Nullable DialogInterface.OnClickListener onEvent) {
        Dialog dialog = DialogFactory.makeDialogNoAction(parentActivity,
                parentActivity.getString(titleRes),
                message,
                iconRes);
        if (customGenericDialogLayout != 0) {
            dialog.setContentView(customGenericDialogLayout);
        }
        dialog.show();
    }

    @Override
    public void showMessageWithoutTitle(int messageRes,
                                        @StringRes int buttonLabel,
                                        @Nullable DialogInterface.OnClickListener onEvent) {
        Dialog dialog = DialogFactory.makeDialogNoTitle(
                parentActivity,
                parentActivity.getString(messageRes),
                parentActivity.getString(buttonLabel),
                onEvent);
        if (customGenericDialogLayout != 0) {
            dialog.setContentView(customGenericDialogLayout);
        }
        dialog.show();
    }

    @Override
    public void showMessageWithoutTitle(@NonNull String message,
                                        @StringRes int buttonLabel,
                                        @Nullable DialogInterface.OnClickListener onEvent) {
        Dialog dialog = DialogFactory.makeDialogNoTitle(
                parentActivity,
                message,
                parentActivity.getString(buttonLabel),
                onEvent);
        if (customGenericDialogLayout != 0) {
            dialog.setContentView(customGenericDialogLayout);
        }
        dialog.show();
    }

    @Override
    public void showMessage(@DrawableRes int iconRes,
                            @StringRes int titleRes,
                            @StringRes int messageRes,
                            @Nullable DialogInterface.OnClickListener onEvent) {
        Dialog dialog = DialogFactory.makeDialogNoAction(parentActivity,
                parentActivity.getString(titleRes),
                parentActivity.getString(messageRes),
                iconRes);
        if (customGenericDialogLayout != 0) {
            dialog.setContentView(customGenericDialogLayout);
        }
        dialog.show();
    }

    @Override
    public void showProgress(@Nullable String message) {
        progressDialog.setMessage(StringUtils.isNotEmpty(message) ? message :
                parentActivity.getString(R.string.progress_message_default));
        progressDialog.setCancelable(false);
        if (customProgressDialogLayout != 0) {
            progressDialog.setContentView(customGenericDialogLayout);
        }
        progressDialog.show();
    }

    @Override
    public void showProgress(@StringRes int messageRes) {
        progressDialog.setMessage(parentActivity.getString(messageRes));
        progressDialog.setCancelable(false);
        if (customProgressDialogLayout != 0) {
            progressDialog.setContentView(customGenericDialogLayout);
        }
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

    public void setProgressDialogLayout(@LayoutRes int layout) {
        this.customProgressDialogLayout = layout;
    }

    public void setGenericDialogLayout(@LayoutRes int layout) {
        this.customGenericDialogLayout = layout;
    }
}