package com.zjy.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zjy on 2017/10/31.
 */

public class MyService extends Service {
    private static final String TAG = "zjy";

    private MyBinder mBinder = new MyBinder();
    private OnTestListener mListener;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {

        public void testMethod(String msg) {
            Log.d(TAG, "receive message from activity: "+msg);
            mListener.onTest("hi, activity");
        }

        public void setOnTestListener(OnTestListener listener) {
            mListener = listener;
        }
    }

    public interface OnTestListener {
        void onTest(String str);
    }

}
