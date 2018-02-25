package com.example.liqfo.catalogtest.Detalization;

import com.example.liqfo.catalogtest.BaseView;
import com.example.liqfo.catalogtest.DataObjects;

public interface DetalizationView extends BaseView {

    void showCurrentObject(DataObjects objects);

    void activateEdit();

    void updateObject();
}
