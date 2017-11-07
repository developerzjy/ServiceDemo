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

import com.zjy.servicedemo.com.zjy.servicedemo.binder.BinderPool;

public class MainActivity extends Activity {

    private static final String TAG = "zjy";

    private BinderPool mBinderPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mBinderPool = BinderPool.getInstance(MainActivity.this);
                        IBird bird = IBird.Stub.asInterface(mBinderPool.queryAnimal(BinderPool.ANIMAL_CODE_BIRD));
                        IFish fish = IFish.Stub.asInterface(mBinderPool.queryAnimal(BinderPool.ANIMAL_CODE_FISH));
                        IMonkey monkey = IMonkey.Stub.asInterface(mBinderPool.queryAnimal(BinderPool.ANIMAL_CODE_MONKEY));

                        try {
                            bird.fly();
                            fish.swim();
                            monkey.climbTree();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });

    }

}
