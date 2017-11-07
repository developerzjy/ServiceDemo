// IAnimal.aidl
package com.zjy.servicedemo;

// Declare any non-default types here with import statements

interface IAnimal {
    IBinder queryAnimal(int animalCode);
}
