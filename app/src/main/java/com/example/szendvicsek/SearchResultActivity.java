package com.example.szendvicsek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResultActivity extends AppCompatActivity {

    private TextView textViewArKereses, textViewSzendvicsek;
    private Button buttonVissza;
    private DBhelper adatbazis;
    int keresSzam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        init();
        Bundle extras = getIntent().getExtras();
        String keres = extras.getString("ÁrKeresés");
        textViewArKereses.setText(getIntent().getExtras().getString("ÁrKeresés"));
        keresSzam = Integer.parseInt(keres);
        adatLekerdezes();

        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(SearchResultActivity.this, MainActivity.class);
                startActivity(intentMain);
                finish();
            }
        });


    }

    public void adatLekerdezes(){
        Cursor adatok = adatbazis.adatkerdezes();



        StringBuilder builder = new StringBuilder();
        while (adatok.moveToNext()){
            if (keresSzam >= adatok.getInt(4)){
                builder.append("ID: ").append(adatok.getInt(0)).append("\n");
                builder.append("Név: ").append(adatok.getString(1)).append("\n");
                builder.append("Leírás: ").append(adatok.getString(2)).append("\n");
                builder.append("Elkészítés: ").append(adatok.getInt(3)).append(" perc").append("\n");
                builder.append("Ár: ").append(adatok.getInt(4)).append(" Ft").append("\n\n");
            }

        }
        textViewSzendvicsek.setText(builder);
        Toast.makeText(this, "Sikeres adatlekérdezés", Toast.LENGTH_SHORT).show();
    }

    public void init(){
        textViewArKereses = findViewById(R.id.textViewArKereses);
        textViewSzendvicsek = findViewById(R.id.textViewSzendvicsek);
        buttonVissza = findViewById(R.id.buttonVissza);
        adatbazis = new DBhelper(SearchResultActivity.this);

        textViewSzendvicsek.setMovementMethod(new ScrollingMovementMethod());
    }
}