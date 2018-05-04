package br.com.db1.mvp.presenter;

import br.com.db1.mvp.view.IView;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by andre.moraes on 16/02/2018.
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

    private V view;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void attachView(V view) {
        if (this.view == null) {
            this.view = view;
        } else {
            clearDisposable();
        }
    }

    @Override
    public void detachView() {
        view = null;
        clearDisposable();
    }

    @Override
    public boolean isViewAttached() {
        return this.view != null;
    }

    private void clearDisposable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    @Override
    public V getView() {
        return view;
    }

}