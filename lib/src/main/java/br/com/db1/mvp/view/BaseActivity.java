package br.com.db1.mvp.view;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import br.com.db1.mvp.util.LogUtils;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by andre.moraes on 16/02/2018.
 */
public abstract class BaseActivity extends AppCompatActivity implements IView {

    private static final String TAG = BaseActivity.class.getSimpleName();

    private static int runningActivities = 0;

    protected abstract @LayoutRes
    int getContentViewRes();

    protected abstract void initializeComponents();

    protected void terminateComponents() {
    }

    private BaseViewDecorator viewDecorator;

    protected Boolean isStatusBarTransparent() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        setContentView(getContentViewRes());
        ButterKnife.bind(this);

        viewDecorator = new BaseViewDecorator(this);
        setupStatusBarTransparency(isStatusBarTransparent());

        initializeComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (runningActivities == 0) {
            didEnterForeground();
        }
        incrementRunningActivities();
    }

    private static void incrementRunningActivities() {
        runningActivities++;
    }

    @Override
    protected void onStop() {
        super.onStop();
        decrementRunningActivities();
    }

    private static void decrementRunningActivities() {
        runningActivities--;
    }

    @Override
    protected void onDestroy() {
        terminateComponents();
        clearDisposable();
        super.onDestroy();
    }

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private void clearDisposable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    protected void didEnterForeground() {
    }

    public static boolean appIsInForeground() {
        return runningActivities > 0;
    }

    protected void validateView() throws IllegalViewStateException {
        if (!appIsInForeground() || viewDecorator == null) {
            throw new IllegalViewStateException("Activity is not in a valid state.");
        }
    }

    private void setupStatusBarTransparency(Boolean statusBarTransparent) {
        if (statusBarTransparent && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.TRANSPARENT);
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
    public void showMessage(@StringRes int titleRes, @StringRes int messageRes) {
        try {
            validateView();
            viewDecorator.showMessage(titleRes, messageRes);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showMessage(@StringRes int titleRes, @NonNull String message) {
        try {
            validateView();
            viewDecorator.showMessage(titleRes, message);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }


    @Override
    public void showMessage(@DrawableRes int iconRes, @StringRes int titleRes,
                            @StringRes int messageRes,
                            @Nullable DialogInterface.OnClickListener onEvent) {
        try {
            validateView();
            viewDecorator.showMessage(iconRes, titleRes, messageRes, onEvent);

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void showMessage(@DrawableRes int iconRes, @StringRes int titleRes,
                            @NonNull String message,
                            @Nullable DialogInterface.OnClickListener onEvent) {
        try {
            validateView();
            viewDecorator.showMessage(iconRes, titleRes, message, onEvent);

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

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

    @Override
    public void hideEmptyState() {
        try {
            validateView();
            viewDecorator.hideEmptyState();

        } catch (IllegalViewStateException | IllegalStateException e) {
            LogUtils.error(TAG, e);
        }
    }

}