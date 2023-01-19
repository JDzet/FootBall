package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MatchAddActivity extends AppCompatActivity {

    String[] Team = { "Зенит", "Спартак", "Рубин", "ЦСК"};


    DB bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_add);


        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Team);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);



        bd = new DB(this);

        Button buttonE = findViewById(R.id.buttonExit);
        Button buttonAdd = findViewById(R.id.buttonAdd);
     //   EditText Home = findViewById(R.id.editTextHome);
    //    EditText Guest = findViewById(R.id.editTextGuest);
        EditText GolHome = findViewById(R.id.editTextHomeGol);
        EditText GolGuest = findViewById(R.id.editTextGuestGol);



        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(MatchAddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String NameHome = spinner.getSelectedItem().toString();
                String NameGuest = spinner2.getSelectedItem().toString();
                int GolHom = Integer.parseInt(GolHome.getText().toString());
                int GolGuestt = Integer.parseInt(GolGuest.getText().toString());

                SQLiteDatabase database = bd.getWritableDatabase();

                ContentValues contentValues = new ContentValues();

                contentValues.put(bd.TEAM_HOME, NameHome);
                contentValues.put(bd.TEAM_GUEST, NameGuest);
                contentValues.put(bd.GOALS_HOME, GolHom);
                contentValues.put(bd.GOL_GUEST, GolGuestt);

                database.insert(DB.MATCH_G, null, contentValues);



                Intent i;
                i = new Intent(MatchAddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}