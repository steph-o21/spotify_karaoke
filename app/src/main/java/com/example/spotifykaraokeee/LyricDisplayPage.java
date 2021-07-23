package com.example.spotifykaraokeee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.spotifykaraoke.clientsdk.GetLyricsClient;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LyricDisplayPage extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric_display_page);

        String song_title = "Love never felt so good";
        String artist = "Michael Jackson";

        displayLyrics(song_title, artist);

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
}


