package com.example.shaur.nimblenavigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Jubilate extends AppCompatActivity {

    ImageView snakesopen,rockopen;
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

    }
}
