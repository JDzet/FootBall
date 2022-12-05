package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DB(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton imageButton = findViewById(R.id.imageButton2);
        TextView textView = findViewById(R.id.textView5);

        LinkedList<Data> list = db.GetAll();
        String text = "";
        for(Data data:list){
            text = text + data.NameHome + " - " + data.NameGuest + "  |  " + data.GolHome + " : " +data.GolGuest + "\n";
        }

        textView.setText(text);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(MainActivity.this, MatchAddActivity.class);
                startActivity(i);
            }
        });

    }
}