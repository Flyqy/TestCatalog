package com.example.liqfo.catalogtest;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface BaseView extends MvpView{

    void showError(@StringRes int stringId);

    void showError(String message);

    void showMessage(@StringRes int stringId);

    void showMessage(String message);

    void showToast(@StringRes int stringId);

    void showToast(String message);

    void showProgress(@StringRes int stringId);

    void showProgress(String message);

    void hideProgress();

    void close();

}
