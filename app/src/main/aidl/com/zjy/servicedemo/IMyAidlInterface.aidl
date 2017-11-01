// IMyAidlInterface.aidl
package com.zjy.servicedemo;

// Declare any non-default types here with import statements
import com.zjy.servicedemo.IMyCallbackListener;

interface IMyAidlInterface {
    void testMethod(String msg);
    void registerListener(IMyCallbackListener listener);
    void unregisterListener(IMyCallbackListener listener);
}
