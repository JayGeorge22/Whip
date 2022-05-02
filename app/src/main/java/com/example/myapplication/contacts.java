package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;

public class contacts extends AppCompatActivity {

    Button qr;
    Button scan;
    Button contacts;
    Button settings;
    ExpandableListView list;
    ArrayList<String> listGroup = new ArrayList<>();
    HashMap<String,ArrayList<String>> listChild = new HashMap<>();
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_contacts);
        setTitle("Contacts");

        qr =(Button)findViewById(R.id.qr);
        scan =(Button)findViewById(R.id.scan);
        contacts =(Button)findViewById(R.id.contacts);
        settings =(Button)findViewById(R.id.settings);

        list = findViewById(R.id.list);

        for(int i=0; i<=10; i++){
            listGroup.add("Group"+i);
            ArrayList<String> arrayList = new ArrayList<>();
            for (int j=0; j<=5; j++){
                arrayList.add("item"+j);
            }
            listChild.put(listGroup.get(i),arrayList);
        }

        adapter = new MainAdapter(listGroup, listChild);
        list.setAdapter(adapter);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(contacts.this, scan.class);
                startActivity(intent);
            }
        });

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(contacts.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(contacts.this, settings.class);
                startActivity(intent);
            }
        });
    }
}