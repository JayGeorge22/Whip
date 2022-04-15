package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = (EditText) findViewById(R.id.email1);
        EditText password = (EditText) findViewById(R.id.password1);

        final Button button = (Button) findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String usernameString = username.getText().toString();
                /*String passwordString = password.getText().toString();

                Toast toast;
                if(usernameString.equals("abc@def.com") && passwordString.equals("123")) {
                    toast = Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT);*/
                    goToMenu(v, usernameString);
               /* }else {
                    toast = Toast.makeText(MainActivity.this, usernameString, Toast.LENGTH_SHORT);
                }
                toast.show();*/
            }
        });
    }
    public void goToMenu(View view, String usernameString) {
        Intent intent = new Intent(this, MainActivity2.class);
        //intent.putExtra(EXTRA_MESSAGE, usernameString);
        startActivity(intent);
    }
}
