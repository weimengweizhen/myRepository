package cn.hongjing.superflashlight;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by l on 2015/4/28.
 */
public class BaseActivity  extends Activity {

    protected int mCurrentWarningLightInterval =500;
    protected int mCurrentPoliceLightInterval = 100;

    protected ImageView mImageViewFlashlight;
    protected ImageView mImageViewFlashlightController;
    protected ImageView mImageViewWarningLight1;
    protected ImageView mImageViewWarningLight2;
    protected EditText mEditTextMorseCode;
    protected ImageView mImageViewBulb;

    protected SeekBar mSeekBarWarningLight;
    protected SeekBar mSeekBarPoliceLight;
    protected Button mButtonAddShortcut;
    protected Button mButtonRemoveShortcut;

    protected Camera mCamera;
    protected Camera.Parameters mParameters;

    protected LinearLayout mUIMain;
    protected FrameLayout mUIFlashlight;
    protected LinearLayout mUIWarningLight;
    protected LinearLayout mUIMorse;
    protected FrameLayout mUIBulb;
    protected FrameLayout mUIColorLight;
    protected FrameLayout mUIPoliceLight;
    protected LinearLayout mUISettings;

    protected int mDefaultScreenBrightness;

    //记录按回退键的次数
    protected int mFinishCount = 0;

    protected SharedPreferences mSharedPreferences;

    protected enum UIType{
        UI_TYPE_MAIN, UI_TYPE_FLASHLIGHT, UI_TYPE_WARNINGLIGHT,
        UI_TYPE_MORSE, UI_TYPE_BLUE, UI_TYPE_COLOR,
        UI_TYPE_POLICE, UI_TYPE_SETTINGS
    }

    protected UIType mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
    protected UIType mLastUIType = UIType.UI_TYPE_FLASHLIGHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageViewFlashlight = (ImageView) findViewById(R.id.imageview_flashlight);
        mImageViewFlashlightController = (ImageView) findViewById(R.id.imageview_flashlight_controller);
        mUIMain = (LinearLayout) findViewById(R.id.linearlayout_main);
        mUIFlashlight = (FrameLayout) findViewById(R.id.framelayout_flashlight);
        mUIMorse = (LinearLayout) findViewById(R.id.linearlayout_morse);
        mUIBulb = (FrameLayout) findViewById(R.id.framelayout_bulb);
        mUIColorLight = (FrameLayout) findViewById(R.id.framelayout_color_light);
        mUIPoliceLight = (FrameLayout) findViewById(R.id.framelayout_police_light);
        mUISettings = (LinearLayout) findViewById(R.id.linearlayout_Settings);

        mImageViewWarningLight1 = (ImageView) findViewById(R.id.imageview_warning_light1);
        mImageViewWarningLight2 = (ImageView) findViewById(R.id.imageview_warning_light2);
        mUIWarningLight = (LinearLayout) findViewById(R.id.linearlayout__warning_light);
        mEditTextMorseCode = (EditText) findViewById(R.id.edittext_morse);
        mImageViewBulb = (ImageView) findViewById(R.id.imageView_bulb);

        mSeekBarPoliceLight = (SeekBar) findViewById(R.id.seekbar_police_light);
        mSeekBarWarningLight = (SeekBar) findViewById(R.id.seekbar_warning_light);
        mButtonAddShortcut = (Button) findViewById(R.id.button_add_shortcut);
        mButtonRemoveShortcut = (Button) findViewById(R.id.button_remove_shortcut);

        mSharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        mCurrentWarningLightInterval = mSharedPreferences.getInt(
                "warning_light_interval", 400);
        mCurrentPoliceLightInterval = mSharedPreferences.getInt(
                "police_light_interval", 300);


        mSeekBarPoliceLight.setProgress(mCurrentPoliceLightInterval-50);
        mSeekBarWarningLight.setProgress(mCurrentWarningLightInterval-100);

        mDefaultScreenBrightness = getScreenBrightness();


    }

    protected  void hideALLUI(){
        mUIMain.setVisibility(View.GONE);
        mUIFlashlight.setVisibility(View.GONE);
        mUIWarningLight.setVisibility(View.GONE);
        mUIMorse.setVisibility(View.GONE);
        mUIBulb.setVisibility(View.GONE);
        mUIColorLight.setVisibility(View.GONE);
        mUIPoliceLight.setVisibility(View.GONE);
        mUISettings.setVisibility(View.GONE);

    }

    protected void screenBrightness(float value){
        try{
            WindowManager.LayoutParams layout = getWindow().getAttributes();
            layout.screenBrightness = value;
            getWindow().setAttributes(layout);
        }catch(Exception e){

        }
    }

    protected int getScreenBrightness(){
        int value = 0;
        try{
            value = Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        }catch(Exception e){

        }
        return value;
    }

    @Override
    public void finish() {
        mFinishCount++;
        if(mFinishCount == 1){
            Toast.makeText(this, "再按一次退出!", Toast.LENGTH_LONG).show();
        }
        else if(mFinishCount == 2){
            super.finish();
        }
    }

    //所有的触摸事件(发生在窗口上的)发生后都会第一个调用此方法，
    //故可以在此方法中将mFinishCount清0
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mFinishCount = 0;
        return super.dispatchTouchEvent(ev);
    }
}



























