package com.example.shanzhenqiang.cloudeducationapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanzhenqiang on 2016/3/8.
 */
public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void add(List<User> users){
        db.beginTransaction();
        try {
            for (User user : users){
                db.execSQL("INSERT INTO user VALUES(null, ?, ?)", new Object[]{user.account,user.password});
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    public void updatePassword(User user){
        ContentValues cv = new ContentValues();
        cv.put("password", user.password);
        db.update("user", cv, "account = ?", new String[]{user.account});
    }

    public void deleteOldUser(User user){
        db.delete("user", "account = ?", new String[]{String.valueOf(user.account)});
    }

    public List<User> query(){
        ArrayList<User> users = new ArrayList<User>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()){
            User user = new User();
            user._id = c.getInt(c.getColumnIndex("_id"));
            user.account = c.getString(c.getColumnIndex("account"));
            user.password = c.getString(c.getColumnIndex("password"));
            users.add(user);
        }
        c.close();
        return users;
    }

    public Cursor queryTheCursor(){
        Cursor c = db.rawQuery("SELECT * FROM user", null);
        return c;
    }

    public void closeDB(){
        db.close();
    }
}
