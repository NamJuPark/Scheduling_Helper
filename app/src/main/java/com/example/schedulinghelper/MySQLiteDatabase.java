package com.example.schedulinghelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class MySQLiteDatabase extends SQLiteOpenHelper {

    public MySQLiteDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String mon = "create table if not exists mon(" +
                "id integer primary key autoincrement," +
                "title text not null," +
                "meno text" +
                "priority integer" +
                ");";
        db.execSQL(mon);

        String tue = "create table if not exists tue(" +
                "id integer primary key autoincrement," +
                "title text not null," +
                "meno text" +
                "priority integer" +
                ");";
        db.execSQL(tue);

        String wed = "create table if not exists wed(" +
                "id integer primary key autoincrement," +
                "title text not null," +
                "meno text" +
                "priority integer" +
                ");";
        db.execSQL(wed);
        String  thu= "create table if not exists thu(" +
                "id integer primary key autoincrement," +
                "title text not null," +
                "meno text" +
                "priority integer" +
                ");";
        db.execSQL(thu);
        String  fri= "create table if not exists fri(" +
                "id integer primary key autoincrement," +
                "title text not null," +
                "meno text" +
                "priority integer" +
                ");";
        db.execSQL(fri);
        String sat = "create table if not exists sat(" +
                "id integer primary key autoincrement," +
                "title text not null," +
                "meno text" +
                "priority integer" +
                ");";
        db.execSQL(sat);
        String sun = "create table if not exists sun(" +
                "id integer primary key autoincrement," +
                "title text not null," +
                "meno text" +
                "priority integer" +
                ");";
        db.execSQL(sun);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "drop table if exists mon;"+"drop table if exists tue;"+"drop table if exists wed;"
                +"drop table if exists thu;"+"drop table if exists fri;"+"drop table if exists sat;"
                        +"drop table if exists sun;";
        onCreate(db);
    }


}