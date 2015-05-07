package cn.hongjing.superflashlight;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by l on 2015/5/5.
 */
public class Settings extends PoliceLight implements SeekBar.OnSeekBarChangeListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSeekBarPoliceLight.setOnSeekBarChangeListener(this);
        mSeekBarWarningLight.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekbar_warning_light:
                mCurrentWarningLightInterval = progress + 100;
                break;
            case R.id.seekbar_police_light:
                mCurrentPoliceLightInterval = progress + 50;
                break;
            default:
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private boolean shortcutInScreen(){
        Cursor cursor = getContentResolver().query(
                Uri.parse("content://com.cyanogenmod.trebuchet.settings/favorites"),
                null,
                "intent like ?",
                new String[]{"%component=cn.hongjing.superflashlight/.MainActivity%" },
                null);
        if(cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public void onClick_AddShortcut(View view){
        if(!shortcutInScreen()) {
            //设置快捷方式的的名字和图标
            Intent installShortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            installShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "超级手电筒");
            Parcelable icon = Intent.ShortcutIconResource.fromContext(this, R.drawable.icon);
            //设置点击快捷方式后进入的页面
            Intent flashlightIntent = new Intent();
            flashlightIntent.setClassName("cn.hongjing.superflashlight", "cn.hongjing.superflashlight.MainActivity");
            flashlightIntent.setAction(Intent.ACTION_MAIN);
            flashlightIntent.addCategory(Intent.CATEGORY_LAUNCHER);

            installShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, flashlightIntent);
            //发送广播
            sendBroadcast(installShortcut);
            Toast.makeText(this, "已成功将快捷方式添加到桌面！", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"快捷方式已经存在",Toast.LENGTH_LONG).show();
        }
    }

    public void onClick_RemoveShortcut(View view){
        if(shortcutInScreen()) {
            Intent uninstallShortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
            uninstallShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "超级手电筒");

            Intent flashlightIntent = new Intent();
            flashlightIntent.setClassName("cn.hongjing.superflashlight", "cn.hongjing.superflashlight.MainActivity");
            flashlightIntent.setAction(Intent.ACTION_MAIN);
            flashlightIntent.addCategory(Intent.CATEGORY_LAUNCHER);

            uninstallShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, flashlightIntent);
            sendBroadcast(uninstallShortcut);
        }
        else{
            Toast.makeText(this,"桌面上没有快捷方式，无法删除",Toast.LENGTH_LONG).show();
        }
    }
}























