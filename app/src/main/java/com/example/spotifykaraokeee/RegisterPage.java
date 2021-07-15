package com.example.spotifykaraokeee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener{

    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets no title and makes splash screen into full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//hides the action bar with title
        setContentView(R.layout.activity_register_page);

        //our instance of our signUp button
        signUp = (Button)findViewById(R.id.registerPageButton);

        signUp.setOnClickListener(this);
    }
    //overridden onClick method to allow transition to HomePage
    @Override
    public void onClick(View v) {
        final RegisterPage registerPage = this;

        Intent intent = new Intent(registerPage, NavigationHomePage.class);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(registerPage, android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
        startActivity(intent, bundle);
    }
}