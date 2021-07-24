package com.example.spotifykaraokeee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;


import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyCallback;
import kaaes.spotify.webapi.android.SpotifyError;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.TracksPager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LyricDisplayPage extends AppCompatActivity {


    // Set up Spotify Credentials
    private static final int REQUEST_CODE = 1337;
    //our clientID from our developer dashboard
    private static final String CLIENT_ID = "b6cb228259164dabab54198bf1537a75";
    //also had to set this as our redirect URI in our developer dashboard
    private static final String REDIRECT_URI = "http://com.yourdomain.yourapp/callback";
    private SpotifyAppRemote mSpotifyAppRemote;

    private final OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric_display_page);

        String song_title = "Love never felt so good";
        String artist = "Michael Jackson";

        displayLyrics(song_title, artist);
        playSong();

        /*
        SpotifyApi api = new SpotifyApi();

        api.setAccessToken();

        SpotifyService spotify = api.getService();

        spotify.searchTracks(song_title, new SpotifyCallback<TracksPager>() {
            @Override
            public void success(TracksPager tracksPager, retrofit.client.Response response) {
               List<Track> songs = tracksPager.tracks.items;
                //System.out.print(songs);
            }

            @Override
            public void failure(SpotifyError spotifyError) {
                Log.d("Track failure", spotifyError.toString());
            }
        });
*/




    }
    //this method makes the http GET request to our api endpoint using OkHTTP library
    void displayLyrics(String song_title, String artist){

        Request request = new Request.Builder().url("https://36rls94ll3.execute-api.us-east-2.amazonaws.com/test/getlyrics?"+"song_title="+song_title+"&artist="+artist).build();

        client.newCall(request).enqueue(new Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    //saves lyrics in string format
                    final String lyrics_string = response.body().string();
                    //System.out.print(lyrics_string);

                    //runs method on this activity's thread
                    LyricDisplayPage.this.runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            //our lyrics textView object
                            TextView lyrics_display = (TextView) findViewById(R.id.lyrics_text);
                            //allow for scrolling movement
                            lyrics_display.setMovementMethod(new ScrollingMovementMethod());
                            //set desired text for our textView object
                            lyrics_display.setText(lyrics_string);
                        }
                    });
                }
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void playSong(){

        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams, new Connector.ConnectionListener() {
            @Override
            public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                mSpotifyAppRemote = spotifyAppRemote;
                Log.d("MainActivity", "Connected! Yay!");

                // Now you can start interacting with App Remote
                connected();

            }
            @Override
            public void onFailure(Throwable error) {
                // Something went wrong if you go here!
                Log.e("MyActivity", error.getMessage(), error); // edited from throwable.getMessage()
            }
        });
    }

    // Connected functionality for remote play
    private void connected() {

        //plays default song from playlist in quick start guide
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");

        //returns player state
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final com.spotify.protocol.types.Track track = playerState.track;
                    if (track != null) {
                        Log.d("MainActively", track.name + "by + " + track.artist.name);
                    }
                });
    }
}


