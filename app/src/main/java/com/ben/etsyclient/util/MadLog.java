package com.ben.etsyclient.util;

import android.util.Log;

public class MadLog {

    private static boolean status;

    private static final String printAttribute = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";

    public static void log(Object obj, String msg) {
        if (status) {
            Log.d(obj.getClass().getSimpleName(), printAttribute + msg);
        }
    }

    public static void error(Object obj, String msg) {
        if (status) {
            Log.d(obj.getClass().getSimpleName(), printAttribute + msg);
        }
    }

    public static void info(Object obj, String msg) {
        if (status) {
            Log.d(obj.getClass().getSimpleName(), printAttribute + msg);
        }
    }

    public static void setDebugMode(boolean status) {
        MadLog.status = status;
    }
}
