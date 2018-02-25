package com.example.liqfo.catalogtest.Fragments.ObjectList;

import com.example.liqfo.catalogtest.BaseView;
import com.example.liqfo.catalogtest.DataObjects;

import java.util.List;

public interface ObjectsView extends BaseView {

    void showSubtitle(String username);

    void showDialogExit();

    void logout();

    void showDataItems(List<DataObjects> dataObjects);

    void showDetail(String id);

    void showAddNew();
}
