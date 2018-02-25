package com.example.liqfo.catalogtest.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.liqfo.catalogtest.Database.DbSchema.*;

public class BaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "catalogBase.db";

    public BaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + RealtyTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                RealtyTable.Cols.ID + ", " +
                RealtyTable.Cols.FLOOR + ", " +
                RealtyTable.Cols.ROOMS + ", " +
                RealtyTable.Cols.PRICE + ", " +
                RealtyTable.Cols.AREA + ", " +
                RealtyTable.Cols.ADDRESS + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
