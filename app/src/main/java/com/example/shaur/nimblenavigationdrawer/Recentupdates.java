package com.example.shaur.nimblenavigationdrawer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.ArrayList;
import java.util.List;

public class Recentupdates extends AppCompatActivity {

    TextView textView;
    private BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recentupdates);
        //textView = (TextView) findViewById(R.id.token_print);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
              //  textView.setText(SharedPreManager.getInstance(Recentupdates.this).getToken());
            }
        };
/*
        if(SharedPreManager.getInstance(this).getToken()!=null)
        {
            textView.setText(SharedPreManager.getInstance(Recentupdates.this).getToken());
            Log.i("MynimbleToken",SharedPreManager.getInstance(this).getToken());
        }
*/
        registerReceiver(broadcastReceiver,new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));


        MyFireBaseMessagingService message = new MyFireBaseMessagingService();
        String m = message.getMessage();


        String []mes={"dds"};

        ListView listView = (ListView) findViewById(R.id.notification_display);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mes);
        listView.setAdapter(adapter);
    }
}