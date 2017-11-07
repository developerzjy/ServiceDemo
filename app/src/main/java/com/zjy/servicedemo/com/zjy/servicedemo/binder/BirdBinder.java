package com.zjy.servicedemo.com.zjy.servicedemo.binder;

import android.os.RemoteException;
import android.util.Log;

import com.zjy.servicedemo.IBird;

/**
 * Created by zjy on 2017/11/7.
 */

public class BirdBinder extends IBird.Stub{

    private static final String TAG = "zjy";

    @Override
    public void fly() throws RemoteException {
        Log.d(TAG, "I'm bird, I can fly.");
    }
}
