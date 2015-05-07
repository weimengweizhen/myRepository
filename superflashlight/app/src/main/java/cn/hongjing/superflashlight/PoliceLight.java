package cn.hongjing.superflashlight;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import cn.hongjing.superflashlight.widget.HideTextView;

/**
 * Created by l on 2015/5/5.
 */
public class PoliceLight extends ColorLight{
    protected HideTextView mHideTextviewPoliceLight;
    protected boolean mPoliceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHideTextviewPoliceLight = (HideTextView) findViewById(R.id.textview_hide_police_light);
    }

    class PoliceThread extends Thread{
        @Override
        public void run() {
            mPoliceState = true;
            while(mPoliceState){
                mHandler.sendEmptyMessage(Color.BLUE);
                sleepEx(mCurrentPoliceLightInterval);
                mHandler.sendEmptyMessage(Color.BLACK);
                sleepEx(mCurrentPoliceLightInterval);
                mHandler.sendEmptyMessage(Color.RED);
                sleepEx(mCurrentPoliceLightInterval);
                mHandler.sendEmptyMessage(Color.BLACK);
                sleepEx(mCurrentPoliceLightInterval);
            }
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int color = msg.what;
            mUIPoliceLight.setBackgroundColor(color);
        }
    };
}
