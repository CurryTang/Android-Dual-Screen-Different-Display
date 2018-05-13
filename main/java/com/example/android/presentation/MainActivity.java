package com.example.android.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.display.DisplayManager;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private boolean isAirplay = false;

    private final static String SERVICE_ACTION = "com.android.example.action";
    private final static String Image_ACTION = "com.android.example.changeimage";


    private final static int STATE_IMAGE1 = 99876;
    private final static int STATE_IMAGE2 = 876;
    private final static int STATE_VIDEO = 12876;
    private final static int MAIN_MENU = 1927432;

    private int[] state = {STATE_IMAGE1, STATE_IMAGE2, STATE_VIDEO, MAIN_MENU};
    private int currentState;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // test whether single displays or multiple displays
        DisplayManager displayManager = (DisplayManager) getSystemService(
                Context.DISPLAY_SERVICE);
        Display[] mdisplays = displayManager.getDisplays();
        if (mdisplays.length <= 1){
            Log.d("Error", "Too few screens");
//            onDestroy();
        }
        currentState = MAIN_MENU;
        Button photo1 = (Button) findViewById(R.id.photo);
        Button photo2 = (Button) findViewById(R.id.photo2);
        Button media = (Button) findViewById(R.id.media);
        Button prev = (Button) findViewById(R.id.Prev);
        Button next = (Button) findViewById(R.id.Next);
//        Button change =(Button) findViewById(R.id.change);
//        change.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (!isAirplay) {
//                    currentState = STATE_VIDEO;
//                    Intent imageBroadcast = new Intent(Image_ACTION);
//                    sendBroadcast(imageBroadcast);
//                    Intent i = new Intent(MainActivity.this, MediaService.class);
//                    startService(i);
//                    isAirplay = true;
//                } else {
//                    currentState = STATE_IMAGE1;
//                    Intent i = new Intent(SERVICE_ACTION);
//                    sendBroadcast(i);
//                    Intent image = new Intent(MainActivity.this, ImageService.class);
//                    startService(image);
//                    isAirplay = false;
//                }
//
//            }
//        });
        photo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentState = STATE_IMAGE1;
                Intent i = new Intent(SERVICE_ACTION);
                sendBroadcast(i);
                Intent image = new Intent(MainActivity.this, ImageService.class);
                image.putExtra("parameter", 1);
                startService(image);
            }
        });
        photo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentState = STATE_IMAGE2;
                Intent i = new Intent(SERVICE_ACTION);
                sendBroadcast(i);
                Intent image = new Intent(MainActivity.this, ImageService.class);
                image.putExtra("parameter", 2);
                startService(image);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (currentState){
                    case STATE_VIDEO:
                        currentState = STATE_IMAGE2;
                        Intent i = new Intent(SERVICE_ACTION);
                        sendBroadcast(i);
                        Intent image = new Intent(MainActivity.this, ImageService.class);
                        Log.d("lt", "45678");
                        image.putExtra("parameter", 2);
                        startService(image);
                        break;
                    case STATE_IMAGE2:
                        currentState = STATE_IMAGE1;
                        Intent i2 = new Intent(SERVICE_ACTION);
                        sendBroadcast(i2);
                        Intent image2 = new Intent(MainActivity.this, ImageService.class);
                        Log.d("lt", "45678");
                        image2.putExtra("parameter", 1);
                        startService(image2);
                        break;
                    case STATE_IMAGE1:
                        currentState = MAIN_MENU;
                        Intent m =new Intent(SERVICE_ACTION);
                        sendBroadcast(m);
                        Intent media = new Intent(MainActivity.this, ImageService.class);
                        Log.d("lt", "45678");
                        media.putExtra("parameter", 3);
                        startService(media);
                        break;
                    case MAIN_MENU:
                        currentState = STATE_VIDEO;
                        Intent start = new Intent(SERVICE_ACTION);
                        sendBroadcast(start);
                        Intent menu = new Intent(MainActivity.this, MediaService.class);
                        Log.d("lt", "45678");
                        startService(menu);
                        break;
                    default:
                        Log.d("lt", "Unknown area");
                }

            }
        });
        media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("lt", "Start video ");
                currentState = STATE_VIDEO;
                Intent m =new Intent(SERVICE_ACTION);
                sendBroadcast(m);
                Intent media = new Intent(MainActivity.this, MediaService.class);
                startService(media);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (currentState){
                    case STATE_VIDEO:
                        currentState = MAIN_MENU;
                        Intent i = new Intent(SERVICE_ACTION);
                        sendBroadcast(i);
                        Intent image = new Intent(MainActivity.this, ImageService.class);
                        Log.d("lt", "45678");
                        image.putExtra("parameter", 3);
                        startService(image);
                        break;
                    case STATE_IMAGE1:
                        currentState = STATE_IMAGE2;
                        Intent i2 = new Intent(SERVICE_ACTION);
                        sendBroadcast(i2);
                        Intent image2 = new Intent(MainActivity.this, ImageService.class);
                        Log.d("lt", "45678");
                        image2.putExtra("parameter", 2);
                        startService(image2);
                        break;
                    case STATE_IMAGE2:
                        currentState = STATE_VIDEO;
                        Intent m =new Intent(SERVICE_ACTION);
                        sendBroadcast(m);
                        Intent media = new Intent(MainActivity.this, MediaService.class);
                        Log.d("lt", "45678");
                        startService(media);
                        break;
                    case MAIN_MENU:
                        currentState = STATE_IMAGE1;
                        Intent start = new Intent(SERVICE_ACTION);
                        sendBroadcast(start);
                        Intent intent = new Intent(MainActivity.this, ImageService.class);
                        intent.putExtra("parameter", 1);
                        startService(intent);
                        break;
                    default:
                        Log.d("lt", "Unknown area!");
                }
            }
        });
        Intent image = new Intent(MainActivity.this, ImageService.class);
        image.putExtra("parameter", 3);
        startService(image);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("lt", "create the menu tag");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item) {
            Log.d("lt", "correct menu item");
            Toast response = Toast.makeText(MainActivity.this, R.string.welcome, Toast.LENGTH_LONG);
            response.show();
            Intent i2 = new Intent(SERVICE_ACTION);
            sendBroadcast(i2);
            Intent image = new Intent(MainActivity.this, ImageService.class);
            image.putExtra("parameter", 3);
            startService(image);
            currentState = MAIN_MENU;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}