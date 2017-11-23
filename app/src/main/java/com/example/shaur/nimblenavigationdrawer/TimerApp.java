package com.example.shaur.nimblenavigationdrawer;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TimerApp extends AppCompatActivity {

    TextView mnumber,done;
    Boolean flag=Boolean.FALSE;
    public void reset(View view){
        done.setText("Not Done Yet!");
        if(flag==Boolean.FALSE)
        {
            Toast.makeText(this, "Timer running already", Toast.LENGTH_SHORT).show();
        }
        else {
                flag=Boolean.FALSE;
            new CountDownTimer(100000, 1000) {

                @Override
                public void onTick(long l) {
                    mnumber.setText("Time:" + String.valueOf(l / 1000));
                }

                @Override
                public void onFinish() {
                    done.setText("Done!!!!");
                    flag=Boolean.TRUE;
                }
            }.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_app);
        mnumber = (TextView) findViewById(R.id.mnumber);
        done = (TextView) findViewById(R.id.done);

        new CountDownTimer(100000,1000){

            @Override
            public void onTick(long l) {
                mnumber.setText("Time:" + String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                done.setText("Done!!!!");
                flag=Boolean.TRUE;
            }
        }.start();
    }
}
