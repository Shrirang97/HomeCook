package com.example.shrirang.homecook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Sign Up" ;
    private EditText First,Last,Email,Password,RePassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    Button SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        First = (EditText)findViewById(R.id.First);
        Last = (EditText)findViewById(R.id.Last);
        Email = (EditText)findViewById(R.id.Email);
        Password = (EditText)findViewById(R.id.Password);
        RePassword = (EditText)findViewById(R.id.RePassword);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        findViewById(R.id.SignUp).setOnClickListener(this);
        findViewById(R.id.Login).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.SignUp:
                registerUser();
                break;
            case R.id.Login:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

    private void registerUser()
    {
        String email = Email.getText().toString();
        String pwd = Password.getText().toString();
        String rpwd = RePassword.getText().toString();

        if(email.isEmpty())
        {
            Email.setError("Email is required");
            Email.requestFocus();
            return ;
        }
        if(pwd.isEmpty())
        {
            Password.setError("Please set your password");
            Password.requestFocus();
            return ;
        }
        if(rpwd.isEmpty())
        {
            RePassword.setError("Please reenter your password");
            RePassword.requestFocus();
            return ;
        }
        if(pwd.compareTo(rpwd)!=0)
        {
            Toast.makeText(getBaseContext(),"Password should be matched",Toast.LENGTH_LONG);
            RePassword.setText("");
            RePassword.requestFocus();
            return ;
        }

        if(pwd.length()<6)
        {
            Password.setError("Minimum length of the password should be 6.");
            Password.requestFocus();
            return ;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's informatio
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(getApplicationContext(),"Registered successfully.",Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException() );
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

        return;

    }

    private void updateUI(FirebaseUser user) {
        progressBar.setVisibility(View.GONE);

    }


}
