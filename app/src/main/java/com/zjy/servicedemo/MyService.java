package com.zjy.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zjy on 2017/10/31.
 */

public class MyService extends Service {
    private static final String TAG = "zjy";

    private RemoteCallbackList<IMyCallbackListener> mListenerList = new RemoteCallbackList<>();
    
    private IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void testMethod(String msg) throws RemoteException {
            Log.d(TAG, "receive message from activity: "+msg);
            int count = mListenerList.beginBroadcast();
            for (int i = 0; i < count; i++) {
                mListenerList.getBroadcastItem(i).onRespond("hi, activity");
            }
            mListenerList.finishBroadcast();
        }

        @Override
        public void registerListener(IMyCallbackListener listener) throws RemoteException {
            mListenerList.register(listener);
        }

        @Override
        public void unregisterListener(IMyCallbackListener listener) throws RemoteException {
            mListenerList.unregister(listener);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
