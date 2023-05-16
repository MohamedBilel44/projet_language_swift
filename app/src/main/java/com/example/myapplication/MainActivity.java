package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.ba);
        b2=findViewById(R.id.be);
        b1.setOnClickListener(this::onClick);
        b2.setOnClickListener(this::onClick2);
    }

    private void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, admin.class);
        startActivity(intent);
    }

    private void onClick2(View v) {
        Intent intent = new Intent(MainActivity.this, etudiant.class);
        startActivity(intent);
    }
}