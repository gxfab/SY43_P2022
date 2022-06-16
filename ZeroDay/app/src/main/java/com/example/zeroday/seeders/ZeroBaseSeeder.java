package com.example.zeroday.seeders;

import android.content.Context;

import com.example.zeroday.services.ZeroBaseServices;

public abstract class ZeroBaseSeeder<S extends ZeroBaseServices> {

    protected S service;
    protected Context context;

    public ZeroBaseSeeder(Context context){
        this.context = context;
    }

    public void run(){
        this.seed();
    }
//
//    public S getService() {
//        return service;
//    }
//
//    public void setService(S service) {
//        this.service = service;
//    }

    protected abstract void seed();
    

}
