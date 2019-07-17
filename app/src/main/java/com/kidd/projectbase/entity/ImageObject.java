package com.kidd.projectbase.entity;

public class ImageObject {
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
}
