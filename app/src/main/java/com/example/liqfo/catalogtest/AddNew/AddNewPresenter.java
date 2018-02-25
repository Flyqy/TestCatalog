package com.example.liqfo.catalogtest.AddNew;

import android.content.Context;
import android.text.TextUtils;

import com.example.liqfo.catalogtest.BasePresenter;
import com.example.liqfo.catalogtest.DataObjects;
import com.example.liqfo.catalogtest.Interactors.MainInteractor;
import com.example.liqfo.catalogtest.R;

import java.util.UUID;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AddNewPresenter extends BasePresenter<AddView> {

    private Context context;
    private MainInteractor mainInteractor;

    public AddNewPresenter(AddView view, Context context){
        super(view);
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainInteractor = MainInteractor.getInstance();
    }

    public void addNewItem(String address, String area, String price, String rooms, String floor){
        if (getView() != null) {
            if (!TextUtils.isEmpty(address)) {
                if (!TextUtils.isEmpty(area))
                    if (!TextUtils.isEmpty(price))
                        if (!TextUtils.isEmpty(rooms))
                            if (!TextUtils.isEmpty(floor)) {
                                DataObjects objects = new DataObjects();
                                objects.setId(UUID.randomUUID().toString());
                                objects.setAddress(address);
                                objects.setArea(area);
                                objects.setPrice(price);
                                objects.setRooms(rooms);
                                objects.setFloor(floor);
                                mainInteractor.addNewObject(objects)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Consumer<Boolean>() {
                                            @Override
                                            public void accept(Boolean aBoolean) throws Exception {
                                                if (getView() != null) {
                                                    getView().successAdding();
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
