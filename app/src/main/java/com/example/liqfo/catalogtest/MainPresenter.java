package com.example.liqfo.catalogtest;

import android.content.Context;

import com.example.liqfo.catalogtest.Interactors.MainInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainView>{

    private MainInteractor mainInteractor;
    private Context context;

    public MainPresenter(MainView view, Context context){
        super(view);
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainInteractor = MainInteractor.getInstance();
        mainInteractor.setContext(context);
    }

    public void checkStatusCheckIn(){
        mainInteractor.checkStatus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (getView() != null){
                            if (aBoolean){
                                getView().showCheckInFragment();
                            } else {
                                getView().showObjectsListFragment();
                            }
                        }
                    }
                });
    }

    public void setUserName(String userName) {
        mainInteractor.setUserName(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (getView() != null){
                            if (aBoolean){
                                getView().showObjectsListFragment();
                            } else {
                                getView().showError("Ошибка авторизации");
                            }
                        }
                    }
                });
    }

    public void onLogout(){
        mainInteractor.setUserName("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (getView() != null){
                            if (aBoolean){
                                getView().showCheckInFragment();
                            } else {
                                getView().showError("Error");
                            }
                        }
                    }
                });
    }
}
