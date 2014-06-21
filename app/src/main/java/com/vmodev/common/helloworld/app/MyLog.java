package com.vmodev.common.helloworld.app;

import android.util.Log;

/**
 * Created by vu0nh_000 on 6/21/2014.
 */
public class MyLog {
    private static boolean enable = false;
    public static void i(String tag, String name){
        if(enable) {
            Log.i(tag, name);
        }
    }
}
