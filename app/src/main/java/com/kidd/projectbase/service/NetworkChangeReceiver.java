package com.kidd.projectbase.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.kidd.projectbase.bus_event.NetworkChangeEvent;
import com.kidd.projectbase.utils.DeviceUtil;

import org.greenrobot.eventbus.EventBus;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "Network is turned ON/OF", Toast.LENGTH_SHORT).show();
        if (DeviceUtil.hasNetworkConnection(context)) {
            EventBus.getDefault().postSticky(new NetworkChangeEvent(true));
        } else {
            EventBus.getDefault().postSticky(new NetworkChangeEvent(false));
        }
    }
}
