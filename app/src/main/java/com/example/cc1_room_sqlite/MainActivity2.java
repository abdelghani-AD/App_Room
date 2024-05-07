package com.example.cc1_room_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
public class MainActivity2 extends AppCompatActivity {
    ListView listView;
    EditText editN, editA;
    Button btnAffiche;
    ArrayAdapter<String> adapter;
    PersonDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editA = findViewById(R.id.editAge);
        editN = findViewById(R.id.editName);
        btnAffiche = findViewById(R.id.btnAfiiche);
        listView = findViewById(R.id.listView2);

        database = PersonDatabase.getInstance(this);

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
        listView.setAdapter(adapter);

        updateListView();

        btnAffiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editN.getText().toString();
                int age = Integer.parseInt(editA.getText().toString());
                // Créer une instance de Person
                //Person person = new Person(name, age);
                // Insérer la personne dans la base de données
                //insertPerson(person);
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("name",name);
                intent.putExtra("age",age);
                startActivity(intent);
            }
        });
    }

    private void insertPerson(final Person person) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                database.personDao().insert(person);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Mettre à jour la ListView après l'insertion
                        updateListView();
                    }
                });
            }
        }).start();
    }
    private void updateListView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Person> persons = database.personDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        for (Person person : persons) {
                            adapter.add(person.nom + ", " + person.age);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
