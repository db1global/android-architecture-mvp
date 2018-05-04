package br.com.db1.mvp.presenter;

import br.com.db1.mvp.view.IView;

/**
 * Created by andre.moraes on 16/02/2018.
 */
public interface IPresenter<V extends IView> {

    void attachView(V view);

    void detachView();

    boolean isViewAttached();

    V getView();

}