package com.zjy.servicedemo.com.zjy.servicedemo.binder;

import android.os.RemoteException;
import android.util.Log;

import com.zjy.servicedemo.IMonkey;

/**
 * Created by zjy on 2017/11/7.
 */

public class MonkeyBinder extends IMonkey.Stub {
    private static final String TAG = "zjy";

    @Override
    public void climbTree() throws RemoteException {
        Log.d(TAG, "I'm monkey, I can climb the tree.");
    }
}
