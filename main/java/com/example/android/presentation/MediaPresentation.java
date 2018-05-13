package com.example.android.presentation;

import android.app.Presentation;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.SurfaceView;
import android.view.View;
import android.widget.VideoView;
import com.example.android.presentation.R;

/**
 * Created by Think on 2018/4/20.
 */

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class MediaPresentation extends Presentation {
    private VideoView videoView;


    public MediaPresentation(Context outerContext, Display display, boolean vedioorimageflag) {
        super(outerContext, display);
        setContentView(R.layout.media);
        videoView = (VideoView) findViewById(R.id.surface_view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media);
        videoView = (VideoView) findViewById(R.id.surface_view);
    }

    public VideoView getVideoView(){
        return videoView;
    }
}
