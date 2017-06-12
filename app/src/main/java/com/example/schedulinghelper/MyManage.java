package com.example.schedulinghelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 박남주 on 2017-06-08.
 */

public class MyManage {//싱글톤?
    private static MySQLiteDatabase database = null;
    private static SQLiteDatabase myDB2 = null;
    private static MyManage mInstance = null;

    public final static MyManage getInstance(Context context) {
        if (mInstance == null) mInstance = new MyManage(context);
        return mInstance;
    }

    private MyManage(Context context) {
        database = new MySQLiteDatabase(context, "myDB2", null, 1);
        myDB2 = database.getWritableDatabase();//db의 핸들러 가져오 open_랑 같음
    }

    public Cursor execSELECTStudent(String sql) {
        Cursor cursor = myDB2.rawQuery(sql, null);
        return cursor;
    }

    public void addData(String title, String memo, int priority, int date) {
        String sql = "";
        if (date == 0) {
            sql = "insert into mon values (null,'" + title + "','" + memo + "'," + priority + ");";
        } else if (date == 1) {
            sql = "insert into tue values (null,'" + title + "','" + memo + "','" + priority + "');";
        } else if (date == 2) {
            sql = "insert into wed values (null,'" + title + "','" + memo + "','" + priority + "');";
        } else if (date == 3) {
            sql = "insert into thu values (null,'" + title + "','" + memo + "','" + priority + "');";
        } else if (date == 4) {
            sql = "insert into fri values (null,'" + title + "','" + memo + "','" + priority + "');";
        } else if (date == 5) {
            sql = "insert into sat values (null,'" + title + "','" + memo + "','" + priority + "');";
        } else if (date == 6) {
            sql = "insert into sun values (null,'" + title + "','" + memo + "','" + priority + "');";
        }
        myDB2.execSQL(sql);
    }

    public void delData(int id, int date) {
        String sql = "";
        if (date == 0) {
            sql = "Delete from mon where id = " + "'" + id + "');";
        } else if (date == 1) {
            sql = "Delete from tue where id = " + "'" + id + "');";
        } else if (date == 2) {
            sql = "Delete from wed where id = " + "'" + id + "');";
        } else if (date == 3) {
            sql = "Delete from thu where id = " + "'" + id + "');";
        } else if (date == 4) {
            sql = "Delete from fri where id = " + "'" + id + "');";
        } else if (date == 5) {
            sql = "Delete from sat where id = " + "'" + id + "');";
        } else if (date == 6) {
            sql = "Delete from sun where id = " + "'" + id + "');";
        }

        myDB2.execSQL(sql);
    }

}
