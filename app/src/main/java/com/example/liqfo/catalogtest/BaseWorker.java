package com.example.liqfo.catalogtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.liqfo.catalogtest.Database.BaseHelper;
import com.example.liqfo.catalogtest.Database.DataCursorWrapper;

import java.util.ArrayList;
import java.util.List;

import static com.example.liqfo.catalogtest.Database.DbSchema.RealtyTable;

public class BaseWorker {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public BaseWorker(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new BaseHelper(context.getApplicationContext())
                .getWritableDatabase();
    }

    private static ContentValues getContentValues(DataObjects dataObjects) {
        ContentValues cv = new ContentValues();
        cv.put(RealtyTable.Cols.ID, dataObjects.getId());
        cv.put(RealtyTable.Cols.FLOOR, dataObjects.getFloor());
        cv.put(RealtyTable.Cols.ROOMS, dataObjects.getRooms());
        cv.put(RealtyTable.Cols.PRICE, dataObjects.getPrice());
        cv.put(RealtyTable.Cols.AREA, dataObjects.getArea());
        cv.put(RealtyTable.Cols.ADDRESS, dataObjects.getAddress());

        return cv;
    }

    private DataCursorWrapper queryData(String whereClaues, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                RealtyTable.NAME,
                null,
                whereClaues,
                whereArgs,
                null,
                null,
                null
        );

        return new DataCursorWrapper(cursor);
    }

    public void addObject(DataObjects d) {
        ContentValues cv = getContentValues(d);
        mDatabase.insert(RealtyTable.NAME, null, cv);
    }

    public void updateObject(DataObjects d) {
        String id = d.getId();
        ContentValues cv = getContentValues(d);

        mDatabase.update(RealtyTable.NAME, cv,
                RealtyTable.Cols.ID + " = ?",
                new String[]{id});
    }

    public List<DataObjects> getObjects() {
        List<DataObjects> dataObjects = new ArrayList<>();

        DataCursorWrapper cursor = queryData(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                dataObjects.add(cursor.getObject());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return dataObjects;
    }

    public DataObjects getObjectById(String id){
        DataCursorWrapper cursor = queryData(RealtyTable.Cols.ID + " = ?",
                new String[] {id});
        try {
            if (cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getObject();
        } finally {
            cursor.close();
        }
    }

}
