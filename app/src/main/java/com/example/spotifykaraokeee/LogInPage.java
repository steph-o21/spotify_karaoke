package com.example.spotifykaraokeee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

public class LogInPage extends AppCompatActivity implements View.OnClickListener {

    Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets no title and makes splash screen into full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//hides the action bar with title
        setContentView(R.layout.activity_log_in_page);

        //our instance of logInPage button
        logIn = (Button)findViewById(R.id.logInPageButton);

        //sets onClickListener to these buttons
        logIn.setOnClickListener(this);
    }
    //overridden onClick method to allow transition to HomePage
    @Override
    public void onClick(View v) {
        final LogInPage logInPage = this;

        Intent intent = new Intent(logInPage, NavigationHomePage.class);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(logInPage, android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
        startActivity(intent, bundle);
    }
}