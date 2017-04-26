package com.hdmi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by saurav on 21/4/17.
 */

public class HdmiListener extends BroadcastReceiver {


    private static String HDMIINTENT = "android.intent.action.HDMI_PLUGGED";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(HDMIINTENT)) {
            boolean state = intent.getBooleanExtra("state", false);

            if (state) {

                Log.d("HDMIListner", "BroadcastReceiver.onReceive() : Connected HDMI-TV");
                Toast.makeText(context, "HDMI Connected", Toast.LENGTH_LONG).show();
            } else {
                Log.d("HDMIListner", "HDMI >>: Disconnected HDMI-TV");
                Toast.makeText(context, "HDMI DisConnected>>", Toast.LENGTH_LONG).show();
            }
        }
    }
}

