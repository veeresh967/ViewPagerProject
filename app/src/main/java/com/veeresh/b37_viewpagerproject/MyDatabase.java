package com.veeresh.b37_viewpagerproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by skillgun on 06/09/2017.
 */

public class MyDatabase {
    private MyHelper mh;
    private SQLiteDatabase sdb;

    public MyDatabase(Context c){
        mh = new MyHelper(c, "techpalle.db", null, 1);
    }
    public void open(){
        sdb = mh.getWritableDatabase(); //open database connection
    }
    //insert movies
    public void insertMovies(String actor, String actress, String moviename){
        ContentValues cv = new ContentValues();
        cv.put("actor",actor);
        cv.put("actress",actress);
        cv.put("moviename",moviename);
        sdb.insert("movies",null,cv);
    }
    //query movies
    public Cursor queryMovies(){
        Cursor c = sdb.query("movies", null, null, null, null, null, null);
        return c;
    }
    public void close(){
        sdb.close(); //close database connection
    }
    public class MyHelper extends SQLiteOpenHelper{
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table movies(_id integer primary key, actor text, actress text, moviename text);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        }
    }
}
