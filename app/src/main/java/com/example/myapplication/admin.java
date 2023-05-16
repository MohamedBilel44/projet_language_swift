package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class admin extends AppCompatActivity
{
    Button b;
    ListView List,l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        List = findViewById(R.id.list);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef;
        collectionRef = db.collection("Event");

        collectionRef.get()
        .addOnSuccessListener(queryDocumentSnapshots -> {
            ArrayList<String> l=new ArrayList<>();

            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                String titre = document.getString("titre");
                String date = document.getString("date");
                l.add(titre+" "+date);

                ArrayAdapter<String> adapter = new ArrayAdapter<>((Context) admin.this, android.R.layout.simple_list_item_1,l);
                List.setAdapter(adapter);
            }
        });
        List.setOnItemClickListener((parent, view, position, id) -> {

                    Dialog dialog = new Dialog(admin.this);
                    dialog.setContentView(R.layout.dial2);
                    l1 = findViewById(R.id.l3);


                    final FirebaseFirestore dbb = FirebaseFirestore.getInstance();
                    CollectionReference collectionReff;

                    collectionReff = (CollectionReference) dbb.collection("Event").whereEqualTo("id", true);

                    collectionReff.get()
                            .addOnSuccessListener(queryDocumentSnapshots -> {
                                ArrayList<String> l = new ArrayList<>();

                                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                    String titre = document.getString("titre");
                                    String date = document.getString("date");
                                    String lieu = document.getString("lieu");
                                    String disc = document.getString("disc");
                                    l.add(titre);
                                    l.add(date);
                                    l.add(lieu);
                                    l.add(disc);

                                    ArrayAdapter<String> adapter = new ArrayAdapter<>((Context) admin.this, android.R.layout.simple_list_item_1, l);
                                    l1.setAdapter(adapter);
                                }
                            });
dialog.show();
                });
            b = findViewById(R.id.bajout);
            b.setOnClickListener(this::onClick);
    }
        private void onClick(View v) {
        Intent intent = new Intent(admin.this, dia.class);
        startActivity(intent);
    }


}
