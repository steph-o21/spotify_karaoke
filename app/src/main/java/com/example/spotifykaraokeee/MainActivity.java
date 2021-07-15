package com.example.spotifykaraokeee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.os.Bundle;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //our sliding top down animation object
    Animation topAnim;
    //our logo image
    ImageView logoImage;

    //allows us to transition to next screen
    private Thread mSplashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets no title and makes splash screen into full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//hides the action bar with title

        setContentView(R.layout.activity_main);

        //sliding top down animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        //logo image instantiated from xml by id
        logoImage = findViewById(R.id.ellipse_1);
        //sets our image to display with top down animation
        logoImage.setAnimation(topAnim);

        //instance of the splash screen
        final MainActivity splashScreen = this;
        //try catch method to allow for smooth transition between splash screen and entry page
        mSplashThread =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        wait(3000);
                    }
                }
                catch(InterruptedException ex){
                }
                finish();

                Intent intent = new Intent(splashScreen, EntryPage.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(splashScreen, android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle);
            }
        };
        mSplashThread.start();
    }

}