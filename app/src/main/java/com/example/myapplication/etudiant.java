package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class etudiant extends AppCompatActivity
{
    ListView List,l1 ;
    Button bi,yes,no;
    EditText c,n;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etudiant);
        List=findViewById(R.id.list);
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

                        ArrayAdapter<String> adapter = new ArrayAdapter<>((Context) etudiant.this, android.R.layout.simple_list_item_1,l);
                        List.setAdapter(adapter);
                    }
                });

        List.setOnItemClickListener((parent, view, position, id) -> {

            Dialog dialog = new Dialog(etudiant.this);
            dialog.setContentView(R.layout.dial);
            l1 = findViewById(R.id.l1);


            final FirebaseFirestore dbb = FirebaseFirestore.getInstance();
            CollectionReference collectionReff;

            collectionReff = (CollectionReference) dbb.collection("Event").whereEqualTo("id",true);

            collectionReff.get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        ArrayList<String> l=new ArrayList<>();

                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            String titre = document.getString("titre");
                            String date = document.getString("date");
                            String lieu = document.getString("lieu");
                            String disc = document.getString("disc");
                            l.add(titre);
                            l.add(date);
                            l.add(lieu);
                            l.add(disc);

                            ArrayAdapter<String> adapter = new ArrayAdapter<>((Context) etudiant.this, android.R.layout.simple_list_item_1,l);
                            l1.setAdapter(adapter);
                        }
                    });
            dialog.show();
            bi = findViewById(R.id.bi);
            bi.setOnClickListener(v -> {
                Dialog dialog1 = new Dialog(etudiant.this);
                dialog1.setContentView(R.layout.dial3);
                c = findViewById(R.id.c);
                n = findViewById(R.id.n);

                yes = findViewById(R.id.oui);
                no = findViewById(R.id.non);
                yes.setOnClickListener(v1 -> {
                    Map<String, Object> even = new HashMap<>();

                    even.put("cin", c);
                    even.put("nom", n);

                    db.collection("Etudiant").add(even).addOnSuccessListener(unused -> Log.d(TAG," Inscription valide")).addOnFailureListener(e -> Log.w(TAG,"error",e));

                });
                dialog1.show();
            });

        });

    }
}