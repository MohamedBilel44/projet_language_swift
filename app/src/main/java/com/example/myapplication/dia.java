package com.example.myapplication;



import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;



import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class dia extends AppCompatActivity {
    Button yes, no;
    EditText tt, ll, dd, ds;
    String titre;
    String lieu;
    String date;
    String disc;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dia);
        tt = findViewById(R.id.tt);
        ll = findViewById(R.id.ll);
        dd = findViewById(R.id.dd);
        ds = findViewById(R.id.ds);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        yes.setOnClickListener(this::onClick);
    }

    private void onClick(View v1) {
        titre = tt.getText().toString();
        lieu = ll.getText().toString();
        date = dd.getText().toString();
        disc = ds.getText().toString();
        Map<String, Object> even = new HashMap<>();
        even.put("titre", titre);
        even.put("lieu", lieu);
        even.put("date", date);
        even.put("disc", disc);
        db.collection("Event").add(even).addOnSuccessListener(unused -> Log.d(TAG," event  ajouter")).addOnFailureListener(e -> Log.w(TAG,"error",e));

    }


}
