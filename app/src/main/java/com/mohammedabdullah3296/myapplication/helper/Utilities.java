package com.mohammedabdullah3296.myapplication.helper;

import android.content.Context;

import java.util.Random;


public class Utilities {
    public static int randInt(int min, int max) {

        Random rand =  new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

        //   return internet status
    public static boolean checkConnection(Context mContext) {
            if (ConnectivityReceiver.isConnected()) {
               return  true;
            } else {
                return false;
            }

    }

}
