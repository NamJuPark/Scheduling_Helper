package com.example.schedulinghelper;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 박남주 on 2017-06-11.
 */

 public class ToDo implements Parcelable {

    private String title;
    private String memo;
    private int priority;
    boolean week[];

    ToDo(String title, String memo, int priority , boolean week[]){
        this.week = week;
        this.title = title;
        this.memo = memo;
        this.priority = priority;
    }

    protected ToDo(Parcel in) {
        title = in.readString();
        memo = in.readString();
        priority = in.readInt();
        week = in.createBooleanArray();
    }


    public static final Creator<ToDo> CREATOR = new Creator<ToDo>() {
        @Override
        public ToDo createFromParcel(Parcel in) {
            return new ToDo(in);
        }

        @Override
        public ToDo[] newArray(int size) {
            return new ToDo[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getMemo() {
        return memo;
    }

    public int getPriority() {
        return priority;
    }
    public boolean getWeek(int i) {
        return week[i];
    }
    public boolean[] getWeekArray() {return week;}
    public void setTitle(String title) {
        this.title = title;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    public void setWeek(int i, boolean b){
        this.week[i] = b;
    }
    public void setWeekArray(boolean[] weekArray) {
        this.week = weekArray;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(memo);
        parcel.writeInt(priority);
        parcel.writeBooleanArray(week);
    }


}