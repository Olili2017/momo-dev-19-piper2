package com.piper2.momo.android.digitalbursar.utils.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.telephony.TelephonyManager.*;

public class ConnectionManager {

    private NetworkInfo mNetworkInfo;
    private String currentNetworkTypeName = "";

    @SuppressLint("StaticFieldLeak")
    private static ConnectionManager connectionManager;

    private ConnectionManager(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        mNetworkInfo = connectivityManager.getActiveNetworkInfo();
    }

    public static synchronized ConnectionManager getInstance(Context context){
        if(connectionManager == null){
            connectionManager = new ConnectionManager(context);
        }
        return connectionManager;
    }

    public boolean hasInternetConnection(){
        return mNetworkInfo != null && mNetworkInfo.isConnected();
    }

    private int getConnectionSpeed(){

        int speed = InternetSpeed.UNKNOWN;

        if(!hasInternetConnection()){
            return InternetSpeed.NO_CONNECTION;
        }

        if(this.mNetworkInfo.getType() != ConnectivityManager.TYPE_WIFI){
            this.currentNetworkTypeName = mNetworkInfo.getSubtypeName();
            int currentNetworkType = mNetworkInfo.getSubtype();

            switch (currentNetworkType){
                case NETWORK_TYPE_GPRS:
                    speed = InternetSpeed.SLOW;
                    break;
                case NETWORK_TYPE_EDGE:
                    speed = InternetSpeed.SLOW;
                    break;
                case NETWORK_TYPE_UMTS:
                    speed = InternetSpeed.MEDIUM;
                    break;
                case NETWORK_TYPE_CDMA:
                    speed = InternetSpeed.SLOW;
                    break;
                case NETWORK_TYPE_EVDO_0:
                    speed = InternetSpeed.SLOW;
                    break;
                case NETWORK_TYPE_EVDO_A:
                    speed = InternetSpeed.MEDIUM;
                    break;
                case NETWORK_TYPE_1xRTT:
                    speed = InternetSpeed.SLOW;
                    break;
                case NETWORK_TYPE_HSDPA:
                    speed = InternetSpeed.MEDIUM;
                    break;
                case NETWORK_TYPE_HSUPA:
                    speed = InternetSpeed.FAST;
                    break;
                case NETWORK_TYPE_HSPA:
                    speed = InternetSpeed.FAST;
                    break;
                case NETWORK_TYPE_IDEN:
                    speed = InternetSpeed.SLOW;
                    break;
                case NETWORK_TYPE_EVDO_B:
                    speed = InternetSpeed.MEDIUM;
                    break;
                case NETWORK_TYPE_LTE:
                    speed = InternetSpeed.FAST;
                    break;
                case NETWORK_TYPE_EHRPD:
                    speed = InternetSpeed.SLOW;
                    break;
                default:
                    speed = InternetSpeed.UNKNOWN;
                    break;
            }
        }

        return speed;
    }

//    start properties

    public String getCurrentNetworkTypeName() {

        String name = "";

        if(currentNetworkTypeName.trim().isEmpty()){

            switch (getConnectionSpeed()){
                case InternetSpeed.NO_CONNECTION:
                    name = "unknown";
                    break;
                case InternetSpeed.UNKNOWN:
                    name = "unknown";
                    break;
                default:
                    name = currentNetworkTypeName;
                    break;
            }
        }

        return name;
    }


//    end properties


//    inner classes

    public static class InternetSpeed {
        static final int UNKNOWN = 0;
        static final int SLOW = 1;
        static final int MEDIUM = 2;
        static final int FAST = 3;
        static final int NO_CONNECTION = 4;
    }


}
