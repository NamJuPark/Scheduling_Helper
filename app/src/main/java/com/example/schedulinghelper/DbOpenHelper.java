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
    private static final String DATABASE_NAME = "SHDB";
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
                    + "id text primary key,"
                    + "title text,"
                    + "memo text,"
                    + "priority integer,"
                    + "done integer"
                    + ");";
            String tue = "Create table if not exists tue ("
                    + "id text primary key,"
                    + "title text,"
                    + "memo text,"
                    + "priority integer,"
                    + "done integer"
                    + ");";
            String wed = "Create table if not exists wed ("
                    + "id text primary key,"
                    + "title text,"
                    + "memo text,"
                    + "priority integer,"
                    + " done integer"
                    + ");";
            String thu = "Create table if not exists thu ("
                    + "id text primary key,"
                    + "title text,"
                    + "memo text,"
                    + "priority integer,"
                    + " done integer"
                    + ");";
            String fri = "Create table if not exists fri ("
                    + "id text primary key,"
                    + "title text,"
                    + "memo text,"
                    + "priority integer,"
                    + " done integer"
                    + ");";
            String sat = "Create table if not exists sat ("
                    + "id text primary key,"
                    + "title text,"
                    + "memo text,"
                    + "priority integer,"
                    + " done integer"
                    + ");";
            String sun = "Create table if not exists sun ("
                    + "id text primary key,"
                    + "title text,"
                    + "memo text,"
                    + "priority integer,"
                    + " done integer"
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

    public void UPDATE(int day, int done, String id) {
        if(day == 0) mDB.execSQL("Update mon set done = " + done + " where id = '"+id+ "';");
        else if(day ==1) mDB.execSQL("Update tue set done = " + done + " where id = '"+id+ "';");
        else if(day == 2) mDB.execSQL("Update wed set done = " + done + " where id = '"+id+ "';");
        else if(day == 3) mDB.execSQL("Update thu set done = " + done + " where id = '"+id+ "';");
        else if(day == 4) mDB.execSQL("Update fri set done = " + done + " where id = '"+id+ "';");
        else if(day == 5) mDB.execSQL("Update sat set done = " + done + " where id = '"+id+ "';");
        else if(day == 6) mDB.execSQL("Update sun set done = " + done + " where id = '"+id+ "';");
    }

    public void INSERTInMon(String id, String title, String memo, int priority, int done) {
        mDB.execSQL("insert into mon values ('" + id + "','" + title + "','" + memo + "'," + priority + ",0);");
    }

    public void INSERTInTue(String id, String title, String memo, int priority, int done) {
        mDB.execSQL("insert into tue values ('" + id + "','" + title + "','" + memo + "'," + priority + ",0);");
    }

    public void INSERTInWed(String id, String title, String memo, int priority, int done) {
        mDB.execSQL("insert into wed values ('" + id + "','" + title + "','" + memo + "'," + priority + ",0);");
    }

    public void INSERTInThu(String id, String title, String memo, int priority, int done) {
        mDB.execSQL("insert into thu values ('" + id + "','" + title + "','" + memo + "'," + priority + ",0);");
    }

    public void INSERTInFri(String id, String title, String memo, int priority, int done) {
        mDB.execSQL("insert into fri values('" + id + "','" + title + "','" + memo + "'," + priority + ",0);");
    }

    public void INSERTInSat(String id, String title, String memo, int priority, int done) {
        mDB.execSQL("insert into sat values ('" + id + "','" + title + "','" + memo + "'," + priority + ",0);");
    }

    public void INSERTInSun(String id, String title, String memo, int priority, int done) {
        mDB.execSQL("insert into sun values ('" + id + "','" + title + "','" + memo + "'," + priority + ",0);");
    }

    public void deleteInMon(String title) {
        mDB.execSQL("Delete from mon where id = "+  title);
    }

    public void deleteInTue(String title) {
        mDB.execSQL("Delete from tue where id = "+  title);
    }

    public void deleteInWed(String title) {
        mDB.execSQL("Delete from wed where id = "+  title);
    }

    public void deleteInThu(String title) {
        mDB.execSQL("Delete from thu where id = "+  title);
    }

    public void deleteInFri(String title) {
        mDB.execSQL("Delete from fri where id = "+  title);
    }

    public void deleteInSat(String title) {
        mDB.execSQL("Delete from sat where id = "+  title);
    }

    public void deleteInSun(String title) {
        mDB.execSQL("Delete from sun where id = "+  title);
    }


    public Cursor getAllMon() {
        return mDB.rawQuery("Select * FROM mon", null);
    }

    public Cursor getAllTue() {
        return mDB.rawQuery("Select * FROM tue", null);
    }

    public Cursor getAllWed() {
        return mDB.rawQuery("Select * FROM wed", null);
    }

    public Cursor getAllThu() {
        return mDB.rawQuery("Select * FROM thu", null);
    }

    public Cursor getAllFri() {
        return mDB.rawQuery("Select * FROM fri", null);
    }

    public Cursor getAllSat() {
        return mDB.rawQuery("Select * FROM sat", null);
    }

    public Cursor getAllSun() {
        return mDB.rawQuery("Select * FROM sun", null);
    }
}