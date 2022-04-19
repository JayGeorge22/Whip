package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class MainActivity2 extends AppCompatActivity {
    Button qr;
    Button scan;
    Button contacts;
    Button settings;

    Button preset1;
    Button preset2;
    Button preset3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Whip");

        qr =(Button)findViewById(R.id.qr);
        scan =(Button)findViewById(R.id.scan);
        contacts =(Button)findViewById(R.id.contacts);
        settings =(Button)findViewById(R.id.settings);

        scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity2.this, scan.class);
                startActivity(intent);
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {

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

        contact infoPreset1 = new contact();
        contact infoPreset2 = new contact();
        contact infoPreset3 = new contact();

        setQr(infoPreset1.name);

        preset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQr(infoPreset1.name);
            }
        });

        preset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQr(infoPreset2.name);
            }
        });

        preset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQr(infoPreset3.name);
            }
        });


    }
    public void setQr(String content) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ((ImageView) findViewById(R.id.qrCodeImage)).setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}

