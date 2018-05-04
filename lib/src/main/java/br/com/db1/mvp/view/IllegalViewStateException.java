package br.com.db1.mvp.view;

/**
 * Created by andre.moraes on 16/02/2018.
 */
public class IllegalViewStateException extends Exception {

    public IllegalViewStateException(String message) {
        super(message);
    }

}