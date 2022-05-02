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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText name,password,confirmpass,email;
    private Button register,returnlogin;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.username1);
        name = (EditText) findViewById(R.id.name1);
        password = (EditText) findViewById(R.id.password1);
        confirmpass = (EditText) findViewById(R.id.password2);
        register = (Button) findViewById(R.id.registerbutton1);
        returnlogin = (Button) findViewById(R.id.loginreturn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        password.setTransformationMethod(new MaskPassword());
        confirmpass.setTransformationMethod(new MaskPassword());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt = email.getText().toString();
                String fullName = name.getText().toString();
                String pass = password.getText().toString();
                String confirm = confirmpass.getText().toString();

                if(emailTxt.isEmpty() || fullName.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(RegisterActivity.this,"Please enter all fields.",Toast.LENGTH_SHORT).show();
                }

                else if(!pass.equals(confirm)) {
                    Toast.makeText(RegisterActivity.this,"Passwords do not match. Please try again.",Toast.LENGTH_SHORT).show();
                }

                else if(pass.length() < 6) {
                    Toast.makeText(RegisterActivity.this,"Passwords is less than 6 characters. Please try again.",Toast.LENGTH_SHORT).show();
                }

                else if(!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()) {
                    Toast.makeText(RegisterActivity.this,"Please provide valid email.",Toast.LENGTH_SHORT).show();
                }

                else {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(emailTxt, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                User user = new User(fullName,emailTxt);
                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this,"User successfully registered.",Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                            Intent intent = new Intent(getApplicationContext(), com.example.myapplication.MainActivity.class);
                                            startActivity(intent);
                                        }

                                        else {
                                            Toast.makeText(RegisterActivity.this,"Failed to register please try again.",Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                            }
                            else {
                                Toast.makeText(RegisterActivity.this,"Failed to register please try again.",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }

            }
        });

        returnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}