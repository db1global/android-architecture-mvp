package br.com.db1.mvp.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import br.com.db1.mvp.util.LogUtils;

/**
 * Created by andre.moraes on 16/02/2018.
 */
public abstract class BaseFragment extends Fragment implements IView {

    private static final String TAG = BaseFragment.class.getSimpleName();
    private static final int DEFAULT_EMPTY_INT = 0;

    protected abstract
    @LayoutRes
    int getContentViewRes();

    protected abstract void initializeComponents();

    protected void terminateComponents() {
    }

    private BaseViewDecorator viewDecorator;

    protected void setViewDecorator(BaseViewDecorator viewDecorator) {
        this.viewDecorator = viewDecorator;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentViewRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new RuntimeException();
        } else {
            viewDecorator = new BaseViewDecorator(getContext());
        }
        initializeComponents();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        terminateComponents();
    }

    protected void validateView() throws IllegalViewStateException {
        if (!isAdded() || viewDecorator == null) {
            throw new IllegalViewStateException("Fragment is not in a valid state.");
        }
    }

    @Override
    public void showMessage(@StringRes int titleRes, @StringRes int messageRes,
                            @Nullable DialogInterface.OnClickListener onEvent) {
        try {
            validateView();
            viewDecorator.showMessage(titleRes, messageRes, onEvent);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showMessage(@StringRes int titleRes, @NonNull String message,
                            @Nullable DialogInterface.OnClickListener onEvent) {
        try {
            validateView();
            viewDecorator.showMessage(titleRes, message, onEvent);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showMessage(int titleRes, int messageRes) {
        try {
            validateView();
            viewDecorator.showMessage(titleRes, messageRes);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showMessage(int titleRes, @NonNull String message) {
        try {
            validateView();
            viewDecorator.showMessage(titleRes, message);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showYesNo(@StringRes int titleRes, @StringRes int messageRes,
                          @StringRes int positiveLabel,
                          @Nullable DialogInterface.OnClickListener onPositiveEvent,
                          @StringRes int negativeLabel,
                          @Nullable DialogInterface.OnClickListener onNegativeEvent) {
        try {
            validateView();
            viewDecorator.showYesNo(titleRes, messageRes,
                    positiveLabel, onPositiveEvent,
                    negativeLabel, onNegativeEvent);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }


    @Override
    public void showYesNo(@StringRes int titleRes, @NonNull String message,
                          @StringRes int positiveLabel,
                          @Nullable DialogInterface.OnClickListener onPositiveEvent,
                          @StringRes int negativeLabel,
                          @Nullable DialogInterface.OnClickListener onNegativeEvent) {
        try {
            validateView();
            viewDecorator.showYesNo(titleRes, message,
                    positiveLabel, onPositiveEvent,
                    negativeLabel, onNegativeEvent);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showMessage(@DrawableRes int iconRes, @StringRes int titleRes,
                            @StringRes int messageRes,
                            @Nullable DialogInterface.OnClickListener onPositiveEvent) {
        try {
            validateView();
            viewDecorator.showMessage(iconRes, titleRes, messageRes, onPositiveEvent);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showMessage(@DrawableRes int iconRes, @StringRes int titleRes,
                            @NonNull String message,
                            @Nullable DialogInterface.OnClickListener onPositiveEvent) {
        try {
            validateView();
            viewDecorator.showMessage(iconRes, titleRes, message, onPositiveEvent);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showMessageWithoutTitle(@StringRes int messageRes, @StringRes int buttonLabel, @Nullable DialogInterface.OnClickListener onEvent) {
        try {
            validateView();
            viewDecorator.showMessageWithoutTitle(messageRes, buttonLabel, onEvent);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showMessageWithoutTitle(@NonNull String message, @StringRes int buttonLabel, @Nullable DialogInterface.OnClickListener onEvent) {
        try {
            validateView();
            viewDecorator.showMessageWithoutTitle(message, buttonLabel, onEvent);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showProgress(String message) {
        try {
            validateView();
            viewDecorator.showProgress(message);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showProgress(@StringRes int messageRes) {
        try {
            validateView();
            viewDecorator.showProgress(messageRes);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void hideProgress() {
        try {
            validateView();
            viewDecorator.hideProgress();

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showEmptyState() {
        try {
            validateView();
            viewDecorator.showEmptyState();
        } catch (IllegalViewStateException e) {
            LogUtils.info(TAG, e);
        } catch (IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void hideEmptyState() {
        try {
            validateView();
            viewDecorator.hideEmptyState();
        } catch (IllegalViewStateException e) {
            LogUtils.info(TAG, e);
        } catch (IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    public void closeKeyBoard(View view) {
        Context context = getContext();
        if (context != null) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), DEFAULT_EMPTY_INT);
            }
        }
    }
}