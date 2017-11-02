package com.zjy.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    private static final String TAG = "zjy";

    private Messenger mMessenger;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //4.Activity里面绑定Service的时候使用传过来的Binder创建一个Messenger对象
            mMessenger = new Messenger(service);
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
                Message msg = new Message();
                msg.what = 1;

                Bundle bundle = new Bundle();
                bundle.putString("string", "hi, service");
                msg.setData(bundle);
                //8.发送消息的时候携带一个Messenger对象
                msg.replyTo = new Messenger(mGetReplyMsg);

                try {
                    //5.Activity里面使用这个Messenger对象给Service发消息
                    mMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //7.Activity里面实现一个Handler用来接收Service回复的消息
    private Handler mGetReplyMsg = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //11.处理Service回复的消息
            if (msg.what==2) {
                Bundle bundle = msg.getData();
                Log.d(TAG, "receive message from service: "+bundle.getString("string"));
            }
        }
    };
}
