package com.example.android.managerparcauto1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dragos Andrei Olaru on 22.01.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "AUTOTURISME";

    // Table columns
    public static final String _ID = "_id";
    public static final String NR_INM = "nr_inmatriculare";
    public static final String MARCA = "marca";
    public static final String TIP = "tip_auto";
    public static final String DATA="data_inmatriculare";
    public static final String SOFER="sofer";

    // Database Information
    static final String DB_NAME = "JURNAL_AUTO.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + _ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NR_INM + " TEXT NOT NULL, "
            + MARCA + " TEXT NOT NULL, "
            + TIP + " TEXT NOT NULL, "
            + DATA + " TEXT NOT NULL, "
            + SOFER + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
