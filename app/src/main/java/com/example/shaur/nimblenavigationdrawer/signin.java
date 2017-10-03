package com.example.shaur.nimblenavigationdrawer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signin extends AppCompatActivity {

    EditText email;
    EditText pass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        email = (EditText) findViewById(R.id.loginemail);
        pass = (EditText) findViewById(R.id.loginpassword);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onLogin(View view) {
        final String myEmail = email.getText().toString();
        final String myPass = pass.getText().toString();
        if (checkvalidation(myEmail, myPass)) {
            mAuth.signInWithEmailAndPassword(myEmail, myPass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.i("TAG", "Login OK");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(signin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signin.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(signin.this, "Failed Login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private boolean checkvalidation(String myEmail,String myPass){

        if(!myEmail.isEmpty() && myEmail!=null && !myPass.isEmpty() && myPass!=null){
            return true;
        }
        else
        {
            Toast.makeText(signin.this, "Incorrect ID or Password", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
