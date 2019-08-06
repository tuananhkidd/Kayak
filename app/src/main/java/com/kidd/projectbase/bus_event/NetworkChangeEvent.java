package com.kidd.projectbase.bus_event;

public class NetworkChangeEvent {
    private boolean isNetWork;

    public NetworkChangeEvent(boolean isNetWork) {
        this.isNetWork = isNetWork;
    }

    public boolean isNetWork() {
        return isNetWork;
    }

    public void setNetWork(boolean netWork) {
        isNetWork = netWork;
    }
}
