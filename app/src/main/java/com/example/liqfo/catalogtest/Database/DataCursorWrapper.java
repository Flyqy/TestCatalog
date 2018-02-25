package com.example.liqfo.catalogtest.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.liqfo.catalogtest.DataObjects;

import static com.example.liqfo.catalogtest.Database.DbSchema.RealtyTable;

public class DataCursorWrapper extends CursorWrapper {
    public DataCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public DataObjects getObject(){
        String id = getString(getColumnIndex(RealtyTable.Cols.ID));
        String floor = getString(getColumnIndex(RealtyTable.Cols.FLOOR));
        String rooms = getString(getColumnIndex(RealtyTable.Cols.ROOMS));
        String price = getString(getColumnIndex(RealtyTable.Cols.PRICE));
        String area = getString(getColumnIndex(RealtyTable.Cols.AREA));
        String address = getString(getColumnIndex(RealtyTable.Cols.ADDRESS));

        DataObjects dataObjects = new DataObjects();
        dataObjects.setId(id);
        dataObjects.setFloor(floor);
        dataObjects.setRooms(rooms);
        dataObjects.setPrice(price);
        dataObjects.setArea(area);
        dataObjects.setAddress(address);

        return dataObjects;
    }
}
