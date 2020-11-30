package com.dji.uxsdkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button btn_droneUX = (Button) findViewById(R.id.btn_droneUX);
        Button btn_mediaManager = (Button) findViewById(R.id.btn_mediaManager);
        Button btn_analysis = (Button) findViewById(R.id.btn_analysis);
        Button btn_trackFly = (Button) findViewById(R.id.btn_trackFly);
        btn_droneUX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePageActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        btn_mediaManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePageActivity.this, MediaManagerActivity.class);
                startActivity(intent);

            }
        });

        btn_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePageActivity.this, StartActivity.class);
                startActivity(intent);

            }
        });

        btn_trackFly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePageActivity.this, TrackingTestActivity.class);
                startActivity(intent);

            }
        });

    }



}
