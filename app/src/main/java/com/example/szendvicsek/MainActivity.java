package com.example.szendvicsek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextArKereses;
    private Button buttonKereses, buttonUjFelvetele;
    private DBhelper adatbazis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        buttonUjFelvetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentInsert = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intentInsert);
                finish();
            }
        });

        buttonKereses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rogzitesEllenorzes();

            }
        });
    }

    public void rogzitesEllenorzes(){
        String arKereses = editTextArKereses.getText().toString().trim();
        if(arKereses.isEmpty()){
            Toast.makeText(this, "Nem maradhat üresen a mező", Toast.LENGTH_SHORT).show();
            editTextArKereses.setError("Nem maradhat üresen a mező");
        }else {
            Intent intentSearchResult = new Intent(MainActivity.this, SearchResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("ÁrKeresés", arKereses);
            intentSearchResult.putExtras(bundle);
            startActivity(intentSearchResult);
            finish();
        }
    }

    public void init(){
        editTextArKereses = findViewById(R.id.editTextArKereses);
        buttonKereses = findViewById(R.id.buttonKereses);
        buttonUjFelvetele = findViewById(R.id.buttonUjFelvetele);
        adatbazis = new DBhelper(MainActivity.this);
    }
}