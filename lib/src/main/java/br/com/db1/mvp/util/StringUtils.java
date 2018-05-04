package br.com.db1.mvp.util;

/**
 * Created by andre.moraes on 16/02/2018.
 */
public class StringUtils {

    private StringUtils() {
    }

    public static boolean isNotEmpty(String value) {
        return value != null && value.trim().length() > 0 && !"null".equals(value);
    }

}