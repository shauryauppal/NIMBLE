package com.example.shaur.nimblenavigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        TextView email = (TextView) findViewById(R.id.email);
       // TextView chat = (TextView) findViewById(R.id.chat);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Contact.this,Contact_email.class);
                startActivity(intent);
            }
        });

    }
}
