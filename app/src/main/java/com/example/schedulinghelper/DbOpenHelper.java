package com.example.schedulinghelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by 박남주 on 2017-06-13.
 */

public class DbOpenHelper {
    private static final String DATABASE_NAME = "MengmojangDB";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String mon = "Create table if not exists mon ("
                    + "title text primary key,"
                    + "memo text,"
                    + "priority integer"
                    + ");";
            String tue = "Create table if not exists tue ("
                    + "title text primary key,"
                    + "memo text,"
                    + "priority integer"
                    + ");";
            String wed = "Create table if not exists wed ("
                    + "title text primary key,"
                    + "memo text,"
                    + "priority integer"
                    + ");";
            String thu = "Create table if not exists thu ("
                    + "title text primary key,"
                    + "memo text,"
                    + "priority integer"
                    + ");";
            String fri = "Create table if not exists fri ("
                    + "title text primary key,"
                    + "memo text,"
                    + "priority integer"
                    + ");";
            String sat = "Create table if not exists sat ("
                    + "title text primary key,"
                    + "memo text,"
                    + "priority integer"
                    + ");";
            String sun = "Create table if not exists sun ("
                    + "title text primary key,"
                    + "memo text,"
                    + "priority integer"
                    + ");";
            db.execSQL(mon);
            db.execSQL(tue);
            db.execSQL(wed);
            db.execSQL(thu);
            db.execSQL(fri);
            db.execSQL(sat);
            db.execSQL(sun);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop table if exists mon");
            db.execSQL("Drop table if exists tue");
            db.execSQL("Drop table if exists wed");
            db.execSQL("Drop table if exists thu");
            db.execSQL("Drop table if exists fri");
            db.execSQL("Drop table if exists sat");
            db.execSQL("Drop table if exists sun");
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context) {
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDB.close();
    }

    public void INSERTInMon(String title, String memo, int priority) {
        mDB.execSQL ("insert into mon values ('" + title + "','" + memo + "'," + priority + ");");
     //   mDB.execSQL("insert into mon values ('title','memo','0');", null);

    }

    public void INSERTInTue(String title, String memo, int priority) {
        mDB.execSQL("insert into tue values ('" + title + "','" + memo + "'," + priority + ");");
    }

    public void INSERTInWed(String title, String memo, int priority) {
        mDB.execSQL("insert into wed values ('" + title + "','" + memo + "'," + priority + ");");
    }

    public void INSERTInThu(String title, String memo, int priority) {
        mDB.execSQL("insert into thu values ('" + title + "','" + memo + "'," + priority + ");");
    }

    public void INSERTInFri(String title, String memo, int priority) {
        mDB.execSQL("insert into fri values ('" + title + "','" + memo + "'," + priority + ");");
    }

    public void INSERTInSat(String title, String memo, int priority) {
        mDB.execSQL("insert into sat values ('" + title + "','" + memo + "'," + priority + ");");
    }

    public void INSERTInSun(String title, String memo, int priority) {
        mDB.execSQL("insert into sun values ('" + title + "','" + memo + "'," + priority + ");");
    }

    public void deleteInMon(String title) {
        mDB.delete("mon", "title = ?", new String[]{title});
    }

    public void deleteInTue(String title) {
        mDB.delete("tue", "title ='" + title + "'", null);
    }

    public void deleteInWed(String title) {
        mDB.delete("wed", "title ='" + title + "'", null);
    }

    public void deleteInThu(String title) {
        mDB.delete("thu", "title ='" + title + "'", null);
    }

    public void deleteInFri(String title) {
        mDB.delete("fri", "title ='" + title + "'", null);
    }

    public void deleteInSat(String title) {
        mDB.delete("sat", "title ='" + title + "'", null);
    }

    public void deleteInSun(String title) {
        mDB.delete("sun", "title ='" + title + "'", null);
    }


    public Cursor sel() {
        return mDB.rawQuery("select * from mon;",null);
    }


    public Cursor getAllMon() {
//        return mDB.query("mon", null, null, null, null, null, "title desc");
        return mDB.rawQuery("Select * FROM mon", null);
    }

    public Cursor getAllTue() {
        return mDB.query("tue", null, null, null, null, null, "title desc");
    }

    public Cursor getAllWed() {
        return mDB.query("wed", null, null, null, null, null, "title desc");
    }

    public Cursor getAllThu() {
        return mDB.query("thu", null, null, null, null, null, "title desc");
    }

    public Cursor getAllFri() {
        return mDB.query("fri", null, null, null, null, null, "title desc");
    }

    public Cursor getAllSat() {
        return mDB.query("sat", null, null, null, null, null, "title desc");
    }

    public Cursor getAllSun() {
        return mDB.query("sun", null, null, null, null, null, "title desc");
    }
}