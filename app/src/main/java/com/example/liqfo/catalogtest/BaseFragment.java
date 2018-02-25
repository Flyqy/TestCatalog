package com.example.liqfo.catalogtest;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.MvpAppCompatFragment;

public class BaseFragment extends MvpAppCompatFragment implements BaseView{

    //Region BaseView
    private IdleActivity getBaseActivity() {
        return (IdleActivity) getActivity();
    }

    @Override
    public void showError(int stringId) {
        getBaseActivity().showError(stringId);
    }

    @Override
    public void showError(String message) {
        getBaseActivity().showError(message);
    }

    @Override
    public void showMessage(int stringId) {
        getBaseActivity().showMessage(stringId);
    }

    @Override
    public void showMessage(String message) {
        getBaseActivity().showMessage(message);
    }

    @Override
    public void showToast(int stringId) {
        getBaseActivity().showToast(stringId);
    }

    @Override
    public void showToast(String message) {
        getBaseActivity().showToast(message);
    }

    @Override
    public void showProgress(int stringId) {
        getBaseActivity().showProgress(stringId);
    }

    @Override
    public void showProgress(String message) {
        getBaseActivity().showProgress(message);
    }

    @Override
    public void hideProgress() {
        getBaseActivity().hideProgress();
    }

    @Override
    public void close() {
        getBaseActivity().close();
    }
    //endregion


    @Override
    public void onStop() {
        getBaseActivity().closeDialogs();
        super.onStop();
    }

    protected void setTitle(@StringRes int stringResId) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(stringResId);
        }
    }

    protected void setTitle(@NonNull String title) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    protected void setSubtitle(@StringRes int stringResId) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(stringResId);
        }
    }

    protected void setSubtitle(@NonNull String title) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(title);
        }
    }

    protected void resetSubtitle() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(null);
        }
    }

    @Nullable
    protected ActionBar getActionBar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            return activity.getSupportActionBar();
        }
        return null;
    }
}
