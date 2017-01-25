package com.example.android.managerparcauto1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Dragos Andrei Olaru on 22.01.2017.
 */

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertAuto(Auto aut) {
        ContentValues contentValue = new ContentValues();

        contentValue.put(DatabaseHelper.NR_INM, aut.getNr_inm());
        contentValue.put(DatabaseHelper.MARCA, aut.getMarca());
        contentValue.put(DatabaseHelper.TIP, aut.getTip());
        contentValue.put(DatabaseHelper.DATA, aut.getData());
        contentValue.put(DatabaseHelper.SOFER, aut.getSofer());

        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

//    public void insert(String nr_inm, String marca, String tip, String data, String sofer) {
//        ContentValues contentValue = new ContentValues();
//
//        contentValue.put(DatabaseHelper.NR_INM, nr_inm);
//        contentValue.put(DatabaseHelper.MARCA, marca);
//        contentValue.put(DatabaseHelper.TIP, tip);
//        contentValue.put(DatabaseHelper.DATA, data);
//        contentValue.put(DatabaseHelper.SOFER, sofer);
//
//        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
//    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NR_INM, DatabaseHelper.MARCA,
                DatabaseHelper.TIP, DatabaseHelper.DATA, DatabaseHelper.SOFER};
        Cursor cursor;
        cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateAuto(long _id, Auto aut) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.NR_INM, aut.getNr_inm());
        values.put(DatabaseHelper.MARCA, aut.getMarca());
        values.put(DatabaseHelper.TIP, aut.getTip());
        values.put(DatabaseHelper.DATA, aut.getData());
        values.put(DatabaseHelper.SOFER, aut.getSofer());
        ;

        int i = database.update(DatabaseHelper.TABLE_NAME, values, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

//    public int update(long _id, String nr_inm, String marca, String tip, String data, String sofer) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseHelper.NR_INM, nr_inm);
//        contentValues.put(DatabaseHelper.MARCA, marca);
//        contentValues.put(DatabaseHelper.TIP, tip);
//        contentValues.put(DatabaseHelper.DATA, data);
//        contentValues.put(DatabaseHelper.SOFER, sofer);
//
//        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
//        return i;
//    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}