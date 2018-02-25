package com.example.liqfo.catalogtest.Fragments.ObjectList;

import android.content.Context;

import com.example.liqfo.catalogtest.BasePresenter;
import com.example.liqfo.catalogtest.DataObjects;
import com.example.liqfo.catalogtest.Interactors.MainInteractor;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ObjectsPresenter extends BasePresenter<ObjectsView> {

    private Context context;
    private MainInteractor mainInteractor;

    public ObjectsPresenter() {
    }

    public void setContext(Context context){
        this.context = context;
        mainInteractor = MainInteractor.getInstance();
        mainInteractor.setContext(context);
    }

    public void getUserName() {
        mainInteractor.getUserName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (getView() != null) {
                            getView().showSubtitle(s);
                        }
                    }
                });
    }

    public void onLogoutClick(){
        if (getView() != null) {
            getView().showDialogExit();
        }
    }

    public void positiveLogout(){
        if (getView() != null){
            getView().logout();
        }
    }

    public void getObjectList(){
        mainInteractor.getObjectsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DataObjects>>() {
                    @Override
                    public void accept(List<DataObjects> dataObjects) throws Exception {
                        if (getView() != null){
                            getView().showDataItems(dataObjects);
                        }
                    }
                });
    }

    public void addNewItems(){
        mainInteractor.addNewItems();
    }

    public void clickOnItem(String id){
        if (getView() != null){
            getView().showDetail(id);
        }
    }

    public void onAddClick(){
        if (getView() != null){
            getView().showAddNew();
        }
    }

}
