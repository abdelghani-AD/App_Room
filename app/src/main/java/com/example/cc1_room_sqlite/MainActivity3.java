package com.example.cc1_room_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        if (intent != null){
            String name = intent.getStringExtra("name");
            int age = intent.getIntExtra("age",0);

            TextView txtName = findViewById(R.id.namePers);
            TextView txtAge = findViewById(R.id.agePers);
            txtName.setText("Name : "+name);
            txtAge.setText("Age : "+age);
        }
    }
}