package com.example.shaur.nimblenavigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class snakesladder extends AppCompatActivity {

    int [] myDice={
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six
    };

    ImageView dice;
    public void rolltapped(View view){

        Random ran = new Random();
        int randomNumber = ran.nextInt(6);

        Log.i("Random","Random number is"+randomNumber);

        dice = (ImageView) findViewById(R.id.dice_roll);
        dice.setImageResource(myDice[randomNumber]);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snakesladder);
    }
}
