package com.hdmi;

import android.app.Activity;
import android.hardware.display.DisplayManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.Scanner;

public class MainActivity extends Activity {


    TextView tv, tv1, tv2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DisplayManager displayManager = (DisplayManager) getSystemService(DISPLAY_SERVICE);
                Display[] displays = displayManager.getDisplays();

                Display display = getWindowManager().getDefaultDisplay();

                Log.d("hello", "1:" + getWindowManager().getDefaultDisplay());

                Log.d("hello", "2:" + display);

                Log.d("hello", "3:" + displays);

                Log.d("hello", "4:" + displayManager.hashCode());

                Log.d("hello", "5:" + isHdmiSwitchSet());

                tv.setText("" + displayManager.hashCode());
                tv1.setText("" + display);
                tv2.setText("" + displays);

            }
        });


    }

    private void initView() {
        tv = (TextView) findViewById(R.id.hdmi);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
    }

    private boolean isHdmiSwitchSet() {


        // The file '/sys/devices/virtual/switch/hdmi/state' holds an int -- if it's 1 then an HDMI device is connected.
        // An alternative file to check is '/sys/class/switch/hdmi/state' which exists instead on certain devices.
        File switchFile = new File("/sys/devices/virtual/switch/hdmi/state");
        if (!switchFile.exists()) {
            switchFile = new File("/sys/class/switch/hdmi/state");
        }
        try {
            Scanner switchFileScanner = new Scanner(switchFile);
            int switchValue = switchFileScanner.nextInt();
            switchFileScanner.close();
            return switchValue > 0;
        } catch (Exception e) {
            return false;
        }
    }


}
