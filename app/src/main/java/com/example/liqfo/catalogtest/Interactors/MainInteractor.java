package com.example.liqfo.catalogtest.Interactors;

import android.content.Context;

import com.example.liqfo.catalogtest.BaseWorker;
import com.example.liqfo.catalogtest.DataObjects;
import com.example.liqfo.catalogtest.PinPreference.Preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class MainInteractor {

    private Preferences preferences;
    private BaseWorker baseWorker;

    public static MainInteractor getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static class InstanceHolder {
        private static final MainInteractor INSTANCE = new MainInteractor();
    }

    private MainInteractor() {

    }

    public void setContext(Context context) {
        preferences = new Preferences(context.getApplicationContext());
        baseWorker = new BaseWorker(context.getApplicationContext());
    }

    public Single<Boolean> checkStatus() {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> emitter) throws Exception {
                if (preferences.getUserName() != "") {
                    emitter.onSuccess(false);
                } else {
                    emitter.onSuccess(true);
                }
            }
        });
    }

    public Single<Boolean> setUserName(final String userName) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> emitter) throws Exception {
                preferences.setUserName(userName);
                emitter.onSuccess(true);
            }
        });
    }

    public Single<String> getUserName(){
        return Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                emitter.onSuccess(preferences.getUserName());
            }
        });
    }

    public Single<List<DataObjects>> getObjectsList(){
        return Single.create(new SingleOnSubscribe<List<DataObjects>>() {
            @Override
            public void subscribe(SingleEmitter<List<DataObjects>> emitter) throws Exception {
                emitter.onSuccess(baseWorker.getObjects());
            }
        });
    }

    public Single<DataObjects> getCurrentObject(final String id){
        return Single.create(new SingleOnSubscribe<DataObjects>() {
            @Override
            public void subscribe(SingleEmitter<DataObjects> emitter) throws Exception {
                emitter.onSuccess(baseWorker.getObjectById(id));
            }
        });
    }

    public Single<Boolean> updateObject(final DataObjects objects){
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> emitter) throws Exception {
                baseWorker.updateObject(objects);
                emitter.onSuccess(true);
            }
        });
    }

    public Single<Boolean> addNewObject(final DataObjects dataObjects){
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> emitter) throws Exception {
                baseWorker.addObject(dataObjects);
                emitter.onSuccess(true);
            }
        });
    }

    public void addNewItems() {
        List<DataObjects> dataObjectsList = new ArrayList<>();
        dataObjectsList = baseWorker.getObjects();

        if (dataObjectsList.size() == 0) {
            DataObjects dataObjects = new DataObjects();
            dataObjects.setId(UUID.randomUUID().toString());
            dataObjects.setAddress("ул.50 лет октября 64");
            dataObjects.setArea("500");
            dataObjects.setPrice("2000000");
            dataObjects.setRooms("5");
            dataObjects.setFloor("10");

            baseWorker.addObject(dataObjects);

            DataObjects dataObjects1 = new DataObjects();
            dataObjects1.setId(UUID.randomUUID().toString());
            dataObjects1.setAddress("ул.Малыгина 102");
            dataObjects1.setArea("36");
            dataObjects1.setPrice("2000000");
            dataObjects1.setRooms("1");
            dataObjects1.setFloor("5");

            baseWorker.addObject(dataObjects1);
        }
    }
}
