package br.com.db1.mvp.util;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by andre.moraes on 16/02/2018.
 */
public class LogUtils {

    private LogUtils() {
    }

    public static void error(@NonNull String tag, @NonNull String message) {
        Log.e(tag, message);
    }

    public static void error(String tag, Throwable throwable) {
        Log.e(tag, Log.getStackTraceString(throwable));
    }

    public static void info(@NonNull String tag, @NonNull String message) {
        Log.i(tag, message);
    }

    public static void info(String tag, Throwable throwable) {
        Log.i(tag, Log.getStackTraceString(throwable));
    }
}