package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private Button viewbutton;
    //ImageButton imgButton;
    private YouTubePlayerView youTubePlayerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewbutton = (Button) findViewById(R.id.view_button);
        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSecondActivity();
            }
        });


        /*imgButton = (ImageButton) findViewById(R.id.imageButton);
        imgButton.setOnClickListener(new view.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecondActivity();
            }
        });*/

        youTubePlayerView = findViewById(R.id.yt_view);
        getLifecycle().addObserver(youTubePlayerView);
    }




    public void openSecondActivity(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}