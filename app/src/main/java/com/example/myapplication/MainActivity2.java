package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.content.SharedPreferences;

import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    Button qr;
    Button scan;
    Button contactsButton;
    Button settings;

    Button preset1;
    Button preset2;
    Button preset3;

    static contact infoPreset1 = new contact();
    static contact infoPreset2 = new contact();
    static contact infoPreset3 = new contact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Whip");

        qr = findViewById(R.id.qr);
        scan = findViewById(R.id.scan);
        contactsButton = findViewById(R.id.contacts);
        settings = findViewById(R.id.settings);

        scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, scan.class);
                startActivity(intent);
            }
        });

        contactsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity2.this, contacts.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity2.this, settings.class);
                startActivity(intent);
            }
        });

        preset1 = (Button) findViewById(R.id.preset1);
        preset2 = (Button) findViewById(R.id.preset2);
        preset3 = (Button) findViewById(R.id.preset3);

        SharedPreferences SP =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext() );

        infoPreset1.setContact(SP, "name1", "email1", "phone1", "instagram1", "snapchat1", "twitter1");
        infoPreset2.setContact(SP, "name2", "email2", "phone2", "instagram2", "snapchat2", "twitter2");
        infoPreset3.setContact(SP, "name3", "email3", "phone3", "instagram3", "snapchat3", "twitter3");

        setQr(infoPreset1.getLink());

        preset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQr(infoPreset1.getLink());
            }
        });

        preset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!infoPreset2.getLink().equals(" , , , , , ")) {
                    setQr(infoPreset2.getLink());
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "No preset has been made", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        preset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!infoPreset3.getLink().equals(" , , , , , ")) {
                    setQr(infoPreset3.getLink());
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "No preset has been made", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });



    }

    MultiFormatWriter writer = new MultiFormatWriter();

    public void setQr(String content) {
        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.MARGIN, 0);//changes white border from default to 0
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            ImageView imageViewQrCode = (ImageView) findViewById(R.id.qrCodeImage);
            imageViewQrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}

