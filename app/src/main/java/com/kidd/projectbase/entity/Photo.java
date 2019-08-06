package com.kidd.projectbase.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Photo implements Parcelable {
    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
    private String path;
    private long createdTime;
    private int position;
    private int year;
    private int month;
    private int day;
    private boolean isChoose = false;

    public Photo() {
    }

    public Photo(String path, long createdTime) {
        this.path = path;
        this.createdTime = createdTime;
    }

    protected Photo(Parcel in) {
        path = in.readString();
        createdTime = in.readLong();
        position = in.readInt();
        year = in.readInt();
        month = in.readInt();
        day = in.readInt();
        isChoose = in.readByte() != 0;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(path);
        parcel.writeLong(createdTime);
        parcel.writeInt(position);
        parcel.writeInt(year);
        parcel.writeInt(month);
        parcel.writeInt(day);
        parcel.writeByte((byte) (isChoose ? 1 : 0));
    }
}
