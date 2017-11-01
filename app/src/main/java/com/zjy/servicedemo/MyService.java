package com.zjy.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zjy on 2017/10/31.
 */

public class MyService extends Service {
    private static final String TAG = "zjy";

    private IMyCallbackListener mListener;
    
    private IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void testMethod(String msg) throws RemoteException {
            Log.d(TAG, "receive message from activity: "+msg);
            mListener.onRespond("hi, activity");
        }

        @Override
        public void registerListener(IMyCallbackListener listener) throws RemoteException {
            mListener = listener;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
