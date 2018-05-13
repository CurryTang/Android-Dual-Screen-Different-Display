package com.example.android.presentation;

import com.example.android.presentation.R;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.widget.VideoView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MediaService extends Service {
    // the path to store the video

//    private MediaPlayer mediaPlayer;
    private VideoView presentSurface;
    private MediaPresentation myPresentation;

    private DisplayManager mDisplayManager;
    private String videoURI;
    private int videoId;
    private final static String SERVICE_ACTION = "com.example.android.action";


    MsgReceiver receiver;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate() {
        super.onCreate();
        videoId = R.raw.animation;
        videoURI = "android.resource://" + getPackageName() + "/" + videoId;
        updateContents();
        registMyReceiver();
        initViewSurface();


    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initViewSurface() {
        if(presentSurface != null){
            presentSurface.resume();
        }
        presentSurface = myPresentation.getVideoView();
        Log.d("lt", "Successfully find the file");
        presentSurface.setVisibility(View.VISIBLE);
        presentSurface.requestFocus();
        presentSurface.setVideoURI(Uri.parse(videoURI));
        presentSurface.start();
        presentSurface.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopSelf();
            }
        });
    }



    private void registMyReceiver() {
        receiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SERVICE_ACTION);
        registerReceiver(receiver, intentFilter);
    }

    class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && !presentSurface.isPlaying()) {
//                if (intent.getIntExtra("receiver_key", -1) == 0) {
                myPresentation.dismiss();
                unregisterReceiver(receiver);
                stopSelf();

//                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void updateContents() {
        mDisplayManager = (DisplayManager) getSystemService(
                Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays();
        Log.d("00000", "4343453nums===" + displays.length);
        showPresentation(displays[1]);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void showPresentation(Display display) {
        myPresentation = new MediaPresentation(this, display, true);
//        myPresentation.getWindow().setType(WindowManager.LayoutParams.TYPE_PHONE);
        presentSurface = myPresentation.getVideoView();
//        presentSurface.getHolder().addCallback(new MySurfaceCallback());
        myPresentation.show();
    }

//    class MySurfaceCallback implements SurfaceHolder.Callback{
//
//        @Override
//        public void surfaceCreated(SurfaceHolder surfaceHolder) {
//            mediaPlayer.setDisplay(presentSurface.getHolder());
//        }
//
//        @Override
//        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//
//        }
//    }
    public void onDestroy() {
        super.onDestroy();
    }
}
