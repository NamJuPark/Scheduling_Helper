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

    private String id;
    private String title;
    private String memo;
    private int priority;
    private int done;
    private int day;

    ToDo(String id,String title, String memo, int priority,int done,int day){
        this.id = id;
        this.title = title;
        this.memo = memo;
        this.priority = priority;
        this.done = done;
        this.day = day;
    }

    protected ToDo(Parcel in) {
        id = in.readString();
        title = in.readString();
        memo = in.readString();
        priority = in.readInt();
        done = in.readInt();
        day = in.readInt();
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
    public void setTitle(String title) {
        this.title = title;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    public void setWeekArray(boolean[] weekArray) {}
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public int getDone() {return done;}
    public void setDone(int done) {this.done = done;}
    public int getDay() {return day;}
    public void setDay(int day) {this.day = day;}

    @Override
    public int describeContents() {return 0;}
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(memo);
        parcel.writeInt(priority);
        parcel.writeInt(done);
        parcel.writeInt(day);
    }


}