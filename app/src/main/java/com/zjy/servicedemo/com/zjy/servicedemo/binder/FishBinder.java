package com.zjy.servicedemo.com.zjy.servicedemo.binder;

import android.os.RemoteException;
import android.util.Log;

import com.zjy.servicedemo.IFish;

/**
 * Created by zjy on 2017/11/7.
 */

public class FishBinder extends IFish.Stub{

    private static final String TAG = "zjy";

    @Override
    public void swim() throws RemoteException {
        Log.d(TAG, "I'm fish, I can swim.");
    }
}
