package com.xiaoying.lights;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {


    View mRedLight;

    View mBlueLight;

    LocalHandler mHandler = new LocalHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = 1;

        mRedLight = findViewById(R.id.view_red_light);
        mBlueLight = findViewById(R.id.view_blue_light);

        setColor(mBlueLight, Color.BLUE);
        setColor(mRedLight, Color.BLACK);

        mHandler.sendMessageDelayed(mHandler.obtainMessage(1, 0, 0), 100);

    }

    private void setColor(View view, int color) {
        if(null == view) {
            return;
        }
        view.setBackgroundColor(color);
    }

    private class LocalHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    setColor(mBlueLight, msg.arg1 == 0 ? Color.BLACK : Color.BLUE);
                    setColor(mRedLight, Color.BLACK);
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(msg.arg2 > 5 ? 2 : 1, msg.arg1 == 0 ? 1 : 0, msg.arg2 > 5 ? 0 : msg.arg2 + 1), 100);
                    break;
                case 2:
                    setColor(mBlueLight, Color.BLACK);
                    setColor(mRedLight, Color.RED);
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(1, 1, 0), 100);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
