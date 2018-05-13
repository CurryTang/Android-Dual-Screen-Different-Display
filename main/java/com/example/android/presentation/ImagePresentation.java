package com.example.android.presentation;

import android.app.Presentation;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.widget.ImageView;

/**
 * Created by Think on 2018/4/20.
 */

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class ImagePresentation extends Presentation {
    private ImageView imageView;

    public ImagePresentation(Context outerContext, Display display) {
        super(outerContext, display);
        setContentView(R.layout.image);
        imageView = (ImageView) findViewById(R.id.imageview);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.image);
//        imageView = (ImageView) findViewById(R.id.imageview);
    }

    public ImageView getImage(){
        return imageView;
    }
    public void setImageView(int id){
        imageView.setImageDrawable(getResources().getDrawable(id));
    }
}
