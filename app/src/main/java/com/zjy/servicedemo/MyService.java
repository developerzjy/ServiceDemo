package com.zjy.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zjy.servicedemo.com.zjy.servicedemo.binder.BinderPool;

/**
 * Created by zjy on 2017/10/31.
 */

public class MyService extends Service {
    private static final String TAG = "zjy";

    private BinderPool.AnimalBinder mBinder = new BinderPool.AnimalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
