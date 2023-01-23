package com.example.invataformule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button integrale, derivate, trigonometrie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main);


        Intent intent = new Intent(MainActivity.this, IntrebariActivity.class);
        integrale = findViewById(R.id.integraleId);
        integrale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("set", "integrale");
                startActivity(intent);
            }
        });
        integrale.setBackgroundColor(Color.parseColor("#d8d8d8"));
        derivate = findViewById(R.id.derivateId);
        derivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("set", "derivate");
                startActivity(intent);
            }
        });
        derivate.setBackgroundColor(Color.parseColor("#d8d8d8"));
        trigonometrie = findViewById(R.id.trigonometrieId);
        trigonometrie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("set", "trigo");
                startActivity(intent);
            }
        });
        trigonometrie.setBackgroundColor(Color.parseColor("#d8d8d8"));



    }
}