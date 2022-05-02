package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText username,password;
    private Button login,register,forgetpass;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginbutton);
        register = (Button) findViewById(R.id.registerbutton);
        forgetpass = (Button) findViewById(R.id.forgetpass);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        mAuth = FirebaseAuth.getInstance();

        password.setTransformationMethod(new MaskPassword());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                String pass = password.getText().toString();

                if(email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please enter all fields.",Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(MainActivity.this,"Please enter a valid email.",Toast.LENGTH_SHORT).show();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                // Redirect to home activity
                                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(MainActivity.this,"Failed to login. Please try again.",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerActivity);
            }
        });

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgetPasswordActivity = new Intent(MainActivity.this,ForgetPasswordActivity.class);
                startActivity(forgetPasswordActivity);
            }
        });
    }
}