package cn.hongjing.superflashlight;

import android.graphics.Color;
import android.view.View;


public class MainActivity extends Settings {

    public void onClick_TOFlashlight(View view){
        hideALLUI();
        mUIFlashlight.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_FLASHLIGHT;
        mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
    }
    public void onClick_ToWarningLight(View view){
        hideALLUI();
        mUIWarningLight.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_WARNINGLIGHT;
        mCurrentUIType = UIType.UI_TYPE_WARNINGLIGHT;
        screenBrightness(1f);
        new WarningLightThread().start();
    }

    public void onClick_ToMorse(View view){
        hideALLUI();
        mUIMorse.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_MORSE;
        mCurrentUIType = UIType.UI_TYPE_MORSE;
    }

    public void onClick_ToBulb(View view){
        hideALLUI();
        mUIBulb.setVisibility(View.VISIBLE);

        mHideTextViewBulb.hide();
        mHideTextViewBulb.setTextColor(Color.BLACK);
        mLastUIType = UIType.UI_TYPE_BLUE;
        mCurrentUIType = UIType.UI_TYPE_BLUE;
    }

    public void onClick_ToColor(View view){
        hideALLUI();
        mUIColorLight.setVisibility(View.VISIBLE);
        screenBrightness(1f);
        mLastUIType = UIType.UI_TYPE_COLOR;
        mCurrentUIType = UIType.UI_TYPE_COLOR;

        mHideTextViewColorLight.setTextColor(Color.rgb(255-Color.red(mCurrentColorLight),
                255-Color.green(mCurrentColorLight),
                255-Color.blue(mCurrentColorLight)));

    }

    public void onClick_ToPolice(View view){
        hideALLUI();
        mUIPoliceLight.setVisibility(View.VISIBLE);
        screenBrightness(1f);
        mLastUIType = UIType.UI_TYPE_POLICE;
        mCurrentUIType = UIType.UI_TYPE_POLICE;
        mHideTextviewPoliceLight.hide();
        new PoliceThread().start();
    }

    public void onClick_ToSettings(View view){
        hideALLUI();
        mUISettings.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_SETTINGS;
        mCurrentUIType = mLastUIType;
    }


    public void onClick_Controller(View view){
        hideALLUI();
        if(mCurrentUIType != UIType.UI_TYPE_MAIN){
            mUIMain.setVisibility(View.VISIBLE);
            mCurrentUIType = UIType.UI_TYPE_MAIN;
            mWarningLightFlicker = false;
            screenBrightness(mDefaultScreenBrightness / 255f);
            if(mBulbCrossFadeFlag){
                mDrawable.reverseTransition(0);
            }
            mBulbCrossFadeFlag = false;
            mPoliceState = false;

        }
        else{
            switch (mLastUIType){
                case UI_TYPE_FLASHLIGHT:
                    mUIFlashlight.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
                    break;
                case UI_TYPE_WARNINGLIGHT:
                    mUIWarningLight.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_WARNINGLIGHT;
//                    new WarningLightThread().start();
                    break;
                case UI_TYPE_MORSE:
                    mUIMorse.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_MORSE;
                    break;
                case UI_TYPE_BLUE:
                    mUIBulb.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_BLUE;
                    mHideTextViewBulb.hide();
                    break;
                case UI_TYPE_COLOR:
                    mUIColorLight.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_COLOR;
                    break;
                case UI_TYPE_POLICE:
                    mUIPoliceLight.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_POLICE;
                    new PoliceThread().start();
                    break;
                case UI_TYPE_SETTINGS:
                    mUISettings.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_SETTINGS;
                    break;
                default:
                    break;
            }
        }

    }


}
