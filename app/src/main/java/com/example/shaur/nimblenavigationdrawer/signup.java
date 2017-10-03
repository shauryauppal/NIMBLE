package com.example.shaur.nimblenavigationdrawer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    EditText email;
    EditText password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = (EditText) findViewById(R.id.email_register);
        password = (EditText) findViewById(R.id.password_register);

        
        mAuth = FirebaseAuth.getInstance();
    }

    public void onRegister(View view) {
        final String myEmail = email.getText().toString();
        final String myPass = password.getText().toString();
        if (checkvalidation(myEmail, myPass)) {
            mAuth.createUserWithEmailAndPassword(myEmail, myPass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.i("TAG","createuser:successful");
                                Toast.makeText(signup.this, "SUCCESS", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(signup.this,signin.class);
                                startActivity(intent);
                            }
                            else{
                                Log.i("TAG","Authentication failed");
                                Toast.makeText(signup.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                            // ...
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
            Toast.makeText(signup.this, "Incorrect input", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
