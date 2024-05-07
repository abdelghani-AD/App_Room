package com.example.cc1_room_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText nameFormation, dureeFormation;
    Spinner typeFormation;

    ArrayAdapter<String> adapterSpinner;
    Button ajouter;
    ListView listView;
    ArrayAdapter<Formation> adapter;

    ArrayList<Formation> formations;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameFormation = findViewById(R.id.nameFormation);
        dureeFormation = findViewById(R.id.dureeFormation);
        typeFormation = findViewById(R.id.typeFormation);
        ajouter = findViewById(R.id.ajouter);
        listView = findViewById(R.id.listView);

        // Initialise le spinner
        ArrayList<String> types = new ArrayList<>();
        types.add("à distance");
        types.add("Présentiel");
        types.add("Hybride");
        adapterSpinner = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, types);
        typeFormation.setAdapter(adapterSpinner);

        formations = new ArrayList<>();
        adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, formations);

        dataBase = DataBase.getInstance(this);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameFormation.getText().toString();
                int duree = Integer.parseInt(dureeFormation.getText().toString());
                String type = typeFormation.getSelectedItem().toString();

                Formation formation = new Formation(name,duree,type);

                dataBase.getDAO().insert(formation);
                formations.add(formation);

                nameFormation.setText("");
                dureeFormation.setText("");
                afficherFormations();
            }
        });
    }
    private void afficherFormations() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Formation> formationList = dataBase.getDAO().getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        formations.clear();
                        formations.addAll(formationList);
                        listView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}
