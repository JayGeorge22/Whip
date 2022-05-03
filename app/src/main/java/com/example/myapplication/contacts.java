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
    Button contactsButton;
    Button settings;
    DBHelper DB;
    static ExpandableListView list;
    static ArrayList<String> listGroup = new ArrayList<>();
    static HashMap<String,ArrayList<String>> listChild = new HashMap<>();
    static MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_contacts);
        setTitle("Contacts");

        qr =(Button)findViewById(R.id.qr);
        scan =(Button)findViewById(R.id.scan);
        contactsButton =(Button)findViewById(R.id.contacts);
        settings =(Button)findViewById(R.id.settings);
        list = findViewById(R.id.list);
        //SQLite stuff
        DB = new DBHelper(this);

        /*listGroup.add("bill");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("phone: 999");
        arrayList.add("email: gmail");
        listChild.put(listGroup.get(0), arrayList);*/

        /*for(int i=0; i<=10; i++){
            listGroup.add("Group"+i);
            ArrayList<String> arrayList = new ArrayList<>();
            for (int j=0; j<=5; j++){
                arrayList.add("item"+j);
            }
            listChild.put(listGroup.get(i),arrayList);
        }*/

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

        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b != null)
        {
            Intent startIntent = new Intent(contacts.this, MainActivity2.class);
            startActivity(startIntent);
        }
    }

    public static void addContact(String [] contact) {
        listGroup.add(contact[0]);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i=1; i<=5; i++){
            arrayList.add(contact[i]);
        }
        listChild.put(listGroup.get(listGroup.indexOf(contact[0])),arrayList);

        adapter.notifyDataSetChanged();
    }
}