package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shanzhenqiang on 2016/3/8.
 */
public class DBHelper extends SQLiteOpenHelper{



    private static final String DATABASE_NAME = "myFirstDB.DB";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS user" + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, account VARCHAR, password VARCHAR)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("ALTER TABLE user ADD COLUMN other STRING");
    }
}
