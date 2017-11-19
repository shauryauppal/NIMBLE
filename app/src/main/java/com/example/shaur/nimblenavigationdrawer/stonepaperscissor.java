package com.example.shaur.nimblenavigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class stonepaperscissor extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference gameRef = rootRef.child("gamerockpaper");

    ImageView rockpaperimage;
    Button rock,paper,scissor;
    TextView rockpapertext;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stonepaperscissor);


        rock = (Button) findViewById(R.id.rock);
        paper = (Button) findViewById(R.id.paper);
        scissor = (Button) findViewById(R.id.scissor);
        rockpapertext = (TextView) findViewById(R.id.rockpapertext);
        rockpaperimage = findViewById(R.id.rockpaperimage);


        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rockpaperimage.animate().alpha(0).setDuration(1200);
                //rockpaperimage.animate().alpha(1).setDuration(2000);
                rockpaperimage.setRotation(180);
                gameRef.setValue("rock");

            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rockpaperimage.animate().alpha(0).setDuration(1200);
                //rockpaperimage.animate().alpha(1).setDuration(1000);
                rockpaperimage.setRotation(300);
                gameRef.setValue("paper");

            }
        });
        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //rockpaperimage.animate().alpha(0).setDuration(1200);
                //rockpaperimage.animate().alpha(1).setDuration(1000);
                rockpaperimage.setRotation(60);
                gameRef.setValue("scissor");

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                text = dataSnapshot.getValue().toString();
                rockpapertext.setText(text);

                if(text=="scissor")
                    rockpaperimage.setRotation(60);
                else if(text=="rock")
                    rockpaperimage.setRotation(180);
                else rockpaperimage.setRotation(300);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Rockpaper","Error");
            }
        });
    }
}
