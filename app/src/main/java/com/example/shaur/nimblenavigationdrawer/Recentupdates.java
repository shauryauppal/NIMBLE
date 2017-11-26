package com.example.shaur.nimblenavigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Recentupdates extends AppCompatActivity {

    TextView result;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference notRef = rootRef.child("notification");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recentupdates);

        result = (TextView) findViewById(R.id.notificationtext);

        notRef.setValue("Hello Nimble");
        notRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue().toString();
                result.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("ERROR","Database firebase");
            }
        });
    }


}