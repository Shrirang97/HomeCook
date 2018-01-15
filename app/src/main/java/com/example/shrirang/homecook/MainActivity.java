package com.example.shrirang.homecook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthList;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.SignUp).setOnClickListener(this);
        findViewById(R.id.Login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.SignUp:
                startActivity(new Intent(this,SignUp.class));
                break;
            case R.id.Login:
                startActivity(new Intent(this,SignUp.class));
                break;
        }
    }
}
