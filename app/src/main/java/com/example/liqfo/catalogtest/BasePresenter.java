package com.example.liqfo.catalogtest;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

public abstract class BasePresenter<V extends BaseView> {

    private V view;

    public BasePresenter() {
    }

    public BasePresenter(V view) {
        this.view = view;
    }

    public void onCreate() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onDestroy() {
        view = null;
    }

    public void attachView(V view) {
        this.view = view;
    }

    @Nullable
    public V getView() {
        return view;
    }

    public void close() {
        if (view != null) {
            view.close();
        }
    }

    //endregion

    //region Messages

    public void showError(@StringRes int stringId) {
        if (view != null) {
            view.showError(stringId);
        }
    }

    public void showError(String string) {
        if (view != null) {
            view.showError(string);
        }
    }

    public void showMessage(@StringRes int stringId) {
        if (view != null) {
            view.showMessage(stringId);
        }
    }

    public void showToast(@StringRes int stringId) {
        if (view != null) {
            view.showToast(stringId);
        }
    }

    //endregion

    //region Progress

    public void showProgress(@StringRes int stringId) {
        if (view != null) {
            view.showProgress(stringId);
        }
    }

    public void showProgress(String string) {
        if (view != null) {
            view.showProgress(string);
        }
    }

    public void hideProgress() {
        if (view != null) {
            view.hideProgress();
        }
    }

    //endregion
}
