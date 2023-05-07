package com.example.dailytask2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AggiungiEventoActivity extends AppCompatActivity {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchNotifica;
    @SuppressLint("SetTextI18n")
    boolean bool;
    final Calendar myCalendar= Calendar.getInstance();
    EditText dateText;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_evento);
        EditText editText = findViewById(R.id.editNome);
        dateText = findViewById(R.id.editTextDate);
        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        };
        dateText.setOnClickListener(view -> new DatePickerDialog(AggiungiEventoActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show());
        switchNotifica = findViewById(R.id.switchNotifica);
        switchNotifica.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b)     {
                switchNotifica.setText("ON");
                bool = true;
            }
            else    {
                switchNotifica.setText("OFF");
                bool = false;
            }
        });
        EditText descrizione = findViewById(R.id.editDescrizione);
        Button conferma = findViewById(R.id.conferma);
        conferma.setOnClickListener(view -> {
            finish();
            System.out.println(dateText.getText().toString());
            Utility.scriviDatabase(editText.getText().toString(), dateText.getText().toString(), bool, descrizione.getText().toString());
        });
        Button indietro = findViewById(R.id.indietro);
        indietro.setOnClickListener(view -> finish());
    }

    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.ITALY);
        dateText.setText(dateFormat.format(myCalendar.getTime()));
    }
}