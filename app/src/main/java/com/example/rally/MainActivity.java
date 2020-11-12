package com.example.rally;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    Button btSignOut;
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user == null)
        {
            login();
        }
        btSignOut = findViewById(R.id.btSignOut);
        btSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void login() {
        Intent i = new Intent(MainActivity.this,Login.class);
        startActivity(i);
        finish();
    }

}
