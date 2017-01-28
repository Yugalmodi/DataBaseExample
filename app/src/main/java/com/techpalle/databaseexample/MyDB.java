package com.techpalle.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 28-Dec-16.
 */
public class MyDB {
    SQLiteDatabase sqLiteDatabase;
    MyHelper myHelper;

    public MyDB(Context c){
        myHelper = new MyHelper(c, "login.db", null, 1);
    }

    public void open(){
        sqLiteDatabase = myHelper.getWritableDatabase();
    }

    public void insertValues(String name, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("email", email);
        cv.put("password", password);
        sqLiteDatabase.insert("user_login_details", null, cv);
    }

    public Cursor query(){
        Cursor cursor = null;
        cursor = sqLiteDatabase.query("user_login_details", null, null, null, null, null, null);
        return cursor;
    }

    public Cursor queryLogin(String email){
        Cursor cursor = null;
        cursor = sqLiteDatabase.query("user_login_details", null, "email =?", new String[]{email}, null, null, null);
        return cursor;
    }

    public void close(){
        sqLiteDatabase.close();
    }

    private class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table user_login_details(_id integer primary key, name text, email text, password text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
