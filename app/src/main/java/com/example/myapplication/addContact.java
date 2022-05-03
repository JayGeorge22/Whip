package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class addContact  extends AppCompatActivity {
    Button qr;
    Button scan;
    Button contactsButton;
    Button settings;
    Button saveContact;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_add_contact);
        setTitle("Add Contact");

        qr =(Button)findViewById(R.id.qr);
        scan =(Button)findViewById(R.id.scan);
        contactsButton =(Button)findViewById(R.id.contacts);
        settings =(Button)findViewById(R.id.settings);
        saveContact = (Button)findViewById(R.id.save_contact);
        DB = new DBHelper(addContact.this);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(addContact.this, scan.class);
                startActivity(intent);
            }
        });

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(addContact.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(addContact.this, settings.class);
                startActivity(intent);
            }
        });

        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(addContact.this, contacts.class);
                startActivity(intent);
            }
        });

        saveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact c = new contact();
                c.name = ((EditText) findViewById(R.id.new_contact_name)).getText().toString();
                c.email = ((EditText) findViewById(R.id.new_contact_email)).getText().toString();
                c.phone = ((EditText) findViewById(R.id.new_contact_phone)).getText().toString();
                c.instagram = ((EditText) findViewById(R.id.new_contact_instagram)).getText().toString();
                c.twitter = ((EditText) findViewById(R.id.new_contact_twitter)).getText().toString();
                c.snapchat = ((EditText) findViewById(R.id.new_contact_snapchat)).getText().toString();

                contacts.addContact(c.getLink().split(","));
                Cursor res =DB.getdata();
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(res.getString(0));
                }
                String data = buffer.toString();
                DB.insertuserdata(data+"1", c.name, c.email, c.phone, c.instagram, c.twitter, c.snapchat);

                Intent intent =new Intent(addContact.this, contacts.class);
                startActivity(intent);
            }
        });
    }

}
