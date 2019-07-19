package com.kidd.projectbase.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageObject implements Parcelable {
    private int id;
    private String path;
    private boolean isChoose = false;

    public ImageObject() {
    }

    public ImageObject(int id, String path, boolean isChoose) {
        this.id = id;
        this.path = path;
        this.isChoose = isChoose;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.path);
        dest.writeByte(this.isChoose ? (byte) 1 : (byte) 0);
    }

    protected ImageObject(Parcel in) {
        this.id = in.readInt();
        this.path = in.readString();
        this.isChoose = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ImageObject> CREATOR = new Parcelable.Creator<ImageObject>() {
        @Override
        public ImageObject createFromParcel(Parcel source) {
            return new ImageObject(source);
        }

        @Override
        public ImageObject[] newArray(int size) {
            return new ImageObject[size];
        }
    };
}
