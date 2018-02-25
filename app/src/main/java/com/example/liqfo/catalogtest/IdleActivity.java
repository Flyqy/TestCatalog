package com.example.liqfo.catalogtest;

import android.app.ProgressDialog;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;

public class IdleActivity extends MvpAppCompatActivity implements BaseView {

    private AlertDialog alertDialogError;
    private String currentErrorMessage;
    private AlertDialog alertDialogInfo;
    private String currentInfoMessage;
    private ProgressDialog progressDialog;

    //Override region

    @Override
    public void showError(int stringId) {
        showError(getString(stringId));
    }

    @Override
    public void showError(String message) {
        showError("Ошибка", message);
    }

    @Override
    public void showMessage(@StringRes int stringId) {
        showMessage("Информация!", getString(stringId));
    }

    @Override
    public void showMessage(String message) {
        showMessage("Информация!", message);
    }

    @Override
    public void showToast(int stringId) {
        showToast(getString(stringId));
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(@StringRes int stringId) {
        showProgress(getString(stringId));
    }

    @Override
    public void showProgress(String message) {
        if (progressDialog != null) {
            hideProgress();
        }
        progressDialog = new ProgressDialog(this, R.style.My_ProgressDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void close() {
        finish();
    }

    //endregion

    public void showError(String title, String message){
        try {
            message = " • " + message.replace("&5&6&7&8", "").trim();
            if (alertDialogError == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle(title)
                        .setMessage(message);
                alertDialogError = builder.create();
            }

            if (alertDialogError.isShowing()) {
                currentErrorMessage += "\n" + message;
            } else {
                currentErrorMessage = message;
                alertDialogError.show();
            }

            alertDialogError.setMessage(currentErrorMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String title, String message) {
        try {
            message = " • " + message;
            if (alertDialogInfo == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle(title)
                        .setMessage(message);
                alertDialogInfo = builder.create();
            }

            if (alertDialogInfo.isShowing()) {
                if (!message.equals(currentInfoMessage)) {
                    currentInfoMessage += "\n" + message;
                }
            } else {
                currentInfoMessage = message;
                alertDialogInfo.show();
            }

            alertDialogInfo.setMessage(currentInfoMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDialogs() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (alertDialogInfo != null && alertDialogInfo.isShowing()) {
            alertDialogInfo.dismiss();
        }
        if (alertDialogError != null && alertDialogError.isShowing()) {
            alertDialogError.dismiss();
        }
    }
}
