package com.example.android.presentation;

import android.Manifest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageService extends Service {

    private final static String DEBUG_TAG = ImageService.class.getSimpleName();
    private ImageView Imageview;
    private ImagePresentation myPresentation;
    private int photoIndex;
    private DisplayManager mDisplayManager;
    private final static String Image_ACTION = "com.image.changeimage";


    MsgReceiver receiver;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate() {
        super.onCreate();
        registMyReceiver();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("lt", "12345");
        if (intent != null) {
            photoIndex = intent.getIntExtra("parameter", 1);
        }
        updateContents();
        initViewSurface();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void initViewSurface() {
//        WindowManager mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        // »ñÈ¡LayoutParams¶ÔÏó
//        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
//        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
//        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        wmParams.format = PixelFormat.RGBA_8888;
//        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
//        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
//        wmParams.x = 0;
//        wmParams.y = 0;
//        // ¿í¶ÈºÍ¸ß¶ÈÎª0, ¿ÉÒÔ±ÜÃâÖ÷ÆÁÍË³öºó³öÏÖµÄÖ÷ÆÁ´¥ÃþºÍµã»÷ÎÊÌâ¡£
//        wmParams.width = 0;
//        wmParams.height = 0;
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout presentationLayout = (LinearLayout) inflater.inflate(R.layout.image, null);
        presentationLayout.setFocusable(false);
//        mWindowManager.addView(presentationLayout, wmParams);
    }

    private void registMyReceiver() {
        receiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Image_ACTION);
        registerReceiver(receiver, intentFilter);
    }

    //  ½ÓÊÕactivity ·¢ËÍ¹ýÀ´µÄ¹ã²¥£¬À´×÷ÏàÓ¦µÄ²¥·Å´¦Àí¡£Ä¿Ç°¾Í×öÁËÒ»¸öÇÐ»»¹¦ÄÜ¡£¿ÉÒÔ¼ÓÉÏÏÂÒ»ÇúºÍÔÝÍ£µÈÒ»Ð©¹¦ÄÜ¡£
    class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                myPresentation.dismiss();
                unregisterReceiver(receiver);
                stopSelf();
            }
        }
    }

    // »ñÈ¡ÏÔÊ¾Éè±¸¡£
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void updateContents() {
        mDisplayManager = (DisplayManager) getSystemService(
                Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays();
        Log.d("lt", "There are "+ displays.length + " screens");
        Log.d("00000", "4343453nums===" + displays.length);
        showPresentation(displays[1], photoIndex);
    }

    // ½«ÄÚÈÝÏÔÊ¾µ½displayÉÏÃæ¡£
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void showPresentation(Display display, int imageIndex) {
        myPresentation = new ImagePresentation(this, display);
//        myPresentation.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                // ¼àÌýÏûÊ§£¬±£´æµ±Ç°²¥·ÅÎ»ÖÃ¡£
//                sharedPreferences.edit().putInt("index", nowHdmiPosition).commit();
//                sharedPreferences.edit().putInt("position", mBackgroundPlayer.getCurrentPosition()).commit();
//            }
//        });
//        myPresentation.getWindow().setType(WindowManager.LayoutParams.TYPE_PHONE);
//        myPresentation.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        Imageview = myPresentation.getImage();
        Log.d("lt", "the current picture should be " + imageIndex);
        if (imageIndex == 2){
            myPresentation.setImageView(R.drawable.photo2);
        } else if (imageIndex == 1){
            myPresentation.setImageView(R.drawable.photo1);
        } else {
            myPresentation.setImageView(R.drawable.title);
        }
        Log.d("lt", myPresentation.getImage().generateViewId() + "");
        myPresentation.show();

//       Imageview.getHolder().addCallback(new MySurfaceCallback());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}


