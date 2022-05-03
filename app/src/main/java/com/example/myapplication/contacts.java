package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
    Button addContact;

    ExpandableListView list;
    static ArrayList<String> listGroup = new ArrayList<>();
    static HashMap<String,ArrayList<String>> listChild = new HashMap<>();
    static MainAdapter adapter;
    DBHelper DB;

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
        addContact = (Button)findViewById(R.id.add_contact);
        list = findViewById(R.id.list);
        //SQLite stuff
        DB = new DBHelper(this);

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

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(contacts.this, addContact.class);
                startActivity(intent);
            }
        });

        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b != null)
        {
            Intent startIntent = new Intent(contacts.this, MainActivity2.class);
            startActivity(startIntent);
        }

        DB.getdata();
        Cursor res = DB.getdata();
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append(res.getString(0)+',');
            buffer.append(res.getString(1)+',');
            buffer.append(res.getString(2)+',');
            buffer.append(res.getString(3)+',');
            buffer.append(res.getString(4)+',');
            buffer.append(res.getString(5)+',');
            buffer.append(res.getString(6)+'#');
        }

        String data = buffer.toString();
        String[] C = data.split("#");
        for (String a : C)

        addContact(a.split(","));

    }

    public static void addContact(String [] contact) {
        listGroup.add(contact[0]);
        ArrayList<String> arrayList = new ArrayList<>();
        /*for (int i=1; i<=5; i++){
            arrayList.add(contact[i]);
        }*/
        arrayList.add("Email: "+contact[1]);
        arrayList.add("Phone: "+contact[2]);
        arrayList.add("Instagram: "+contact[3]);
        arrayList.add("Snapchat: "+contact[4]);
        arrayList.add("Twitter: "+contact[5]);
        listChild.put(listGroup.get(listGroup.indexOf(contact[0])),arrayList);

        adapter.notifyDataSetChanged();
    }
}