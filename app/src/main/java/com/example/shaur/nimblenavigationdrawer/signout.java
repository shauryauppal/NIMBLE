package com.example.shaur.nimblenavigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class signout extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signout);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onLogout(View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "SIGNOUT", Toast.LENGTH_SHORT).show();
    }
}
