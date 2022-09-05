package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class AniActivity extends AppCompatActivity {

    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ani);

        getSupportActionBar().hide();

        lottie = findViewById(R.id.lottie);


        MediaPlayer mediaPlayer;

        mediaPlayer = MediaPlayer.create(AniActivity.this,R.raw.music);
        mediaPlayer.start();


        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AniActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);*/

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(AniActivity.this, MainActivity.class));
                finish();
            }
        } , 4000);
    }
}