package com.beetech.kayak.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.beetech.kayak.bus_event.NetworkChangeEvent;
import com.beetech.kayak.utils.DeviceUtil;

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
