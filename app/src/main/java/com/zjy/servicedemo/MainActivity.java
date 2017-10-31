package com.zjy.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    private static final String TAG = "zjy";
    private MyService.MyBinder mBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (MyService.MyBinder) service;
            mBinder.setOnTestListener(new MyService.OnTestListener() {
                @Override
                public void onTest(String str) {
                    Log.d(TAG, "receive message from service: "+str);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinder.testMethod("hi, service");
            }
        });
    }
}
