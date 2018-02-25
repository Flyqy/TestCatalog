package com.example.liqfo.catalogtest.Detalization;

import android.content.Context;
import android.text.TextUtils;

import com.example.liqfo.catalogtest.BasePresenter;
import com.example.liqfo.catalogtest.DataObjects;
import com.example.liqfo.catalogtest.Interactors.MainInteractor;
import com.example.liqfo.catalogtest.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DetalizationPresenter extends BasePresenter<DetalizationView> {

    private MainInteractor mainInteractor;
    private Context context;

    public DetalizationPresenter(DetalizationView view, Context context) {
        super(view);
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainInteractor = MainInteractor.getInstance();
    }

    public void getCurrentObject(String id) {
        mainInteractor.getCurrentObject(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DataObjects>() {
                    @Override
                    public void accept(DataObjects dataObjects) throws Exception {
                        if (getView() != null) {
                            getView().showCurrentObject(dataObjects);
                        }
                    }
                });
    }

    public void onEditClick() {
        if (getView() != null) {
            getView().activateEdit();
        }
    }

    public void updateObjects(String id, String address, String area, String price, String rooms, String floor) {
        if (getView() != null) {
            if (!TextUtils.isEmpty(address)) {
                if (!TextUtils.isEmpty(area))
                    if (!TextUtils.isEmpty(price))
                        if (!TextUtils.isEmpty(rooms))
                            if (!TextUtils.isEmpty(floor)) {
                                DataObjects objects = new DataObjects();
                                objects.setId(id);
                                objects.setAddress(address);
                                objects.setArea(area);
                                objects.setPrice(price);
                                objects.setRooms(rooms);
                                objects.setFloor(floor);
                                mainInteractor.updateObject(objects)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Consumer<Boolean>() {
                                            @Override
                                            public void accept(Boolean aBoolean) throws Exception {
                                                if (getView() != null) {
                                                    getView().updateObject();
                                                }
                                            }
                                        });
                            } else getView().showMessage(R.string.floor_error);
                        else getView().showMessage(R.string.rooms_error);
                    else getView().showMessage(R.string.price_error);
                else getView().showMessage(R.string.area_error);
            } else getView().showMessage(R.string.address_error);
        }
    }
}
