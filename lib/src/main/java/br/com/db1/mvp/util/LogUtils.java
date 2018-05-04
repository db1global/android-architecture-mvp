package br.com.db1.mvp.util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

/**
 * Created by andre.moraes on 16/02/2018.
 */
public class LogUtils {

    private LogUtils() {
    }

    public static void error(@NonNull String tag, @NonNull String message) {
        Crashlytics.log(Log.ERROR, tag, message);
    }

    public static void error(String tag, Throwable throwable) {
        Crashlytics.log(Log.ERROR, tag, Log.getStackTraceString(throwable));
    }

}