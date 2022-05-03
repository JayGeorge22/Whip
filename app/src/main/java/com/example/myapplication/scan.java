package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scan extends AppCompatActivity {
    Button qr;
    Button scan;
    Button contactsButton;
    Button settings;
    Button btScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_scan);
        setTitle("Scan");

        qr =(Button)findViewById(R.id.qr);
        scan =(Button)findViewById(R.id.scan);
        contactsButton =(Button)findViewById(R.id.contacts);
        settings =(Button)findViewById(R.id.settings);

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(scan.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(scan.this, contacts.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(scan.this, settings.class);
                startActivity(intent);
            }
        });

        btScan = findViewById(R.id.qrScan);

        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(scan.this);
                intentIntegrator.setPrompt("Volume up to turn flash on\nVolume down to turn flash off");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data );

        if(intentResult.getContents() != null){
            //what to do with info
            //intentREsult.getContents()
            String info = intentResult.getContents();
            String[] contact = info.split(",");

            contacts.addContact(contact);

            Toast toast = Toast.makeText(getApplicationContext(), "Contact "+contact[0]+"has been added", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}