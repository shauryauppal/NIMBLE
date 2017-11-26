package com.example.shaur.nimblenavigationdrawer;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageRequest;

public class Jubilate extends AppCompatActivity {

    ImageView snakesopen,rockopen,earthopen,tictacopen,timeropen,counteropen,weatheropen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jubilate);

        snakesopen = (ImageView) findViewById(R.id.snakesladdericon);
        snakesopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Jubilate.this,snakesladder.class));
            }
        });

        rockopen = (ImageView) findViewById(R.id.rockpaperscissorgame);
        rockopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Jubilate.this,stonepaperscissor.class));
            }
        });
        earthopen = (ImageView) findViewById(R.id.earthquakeicon);
        earthopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Jubilate.this,EarthquakeActivity.class ));
            }
        });

        tictacopen = (ImageView) findViewById(R.id.tictacicon);
        tictacopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Jubilate.this,Tictactoe.class));
            }
        });
        timeropen = (ImageView) findViewById(R.id.timericon);
        timeropen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Jubilate.this,TimerApp.class));
            }
        });

        counteropen = (ImageView) findViewById(R.id.counter);
        counteropen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Jubilate.this,courtcounter.class));
            }
        });

        weatheropen = (ImageView) findViewById(R.id.weathericon);
        weatheropen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Jubilate.this,Weather.class));
            }
        });

    }
}
