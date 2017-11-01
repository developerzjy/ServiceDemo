package com.zjy.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity{

    private static final String TAG = "zjy";

    private IMyAidlInterface mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IMyAidlInterface.Stub.asInterface(service);

            try{
                mService.registerListener(new IMyCallbackListener.Stub() {
                    @Override
                    public void onRespond(String str) throws RemoteException {
                        Log.d(TAG, "receive message from service: "+str);
                    }
                });
            } catch (Exception e){
                e.printStackTrace();
            }
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
                try {
                    mService.testMethod("hi, service");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
