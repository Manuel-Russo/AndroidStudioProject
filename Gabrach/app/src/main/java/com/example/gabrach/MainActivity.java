package com.example.gabrach;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    @Nullable
    @Override
    public ActionBar getActionBar() {
        return super.getActionBar();
    }

    static ArrayList<Prodotto> prodotti = new ArrayList<>(); //arrayList di prodotti
    static ProdottoAdapter prodottoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();//nascondo la barra superiore
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listViewProdotti = findViewById(R.id.lista); //oggetto ListView, formato da più view
        Utility.scaricaProdotti();
        //inizializzaProdotti(); //richiamo metodo inizializzaProdotti
        prodottoAdapter = new ProdottoAdapter(prodotti,this); //dichiaro oggetto prodottoAdapter, che mi permette di inserire le informazioni nella listView
        listViewProdotti.setAdapter(prodottoAdapter); //dico alla listView che il suo Adapter è prodottoAdapter
    }

    void inizializzaProdotti()  {
        prodotti.add(new Prodotto("p1", "Caffè", 1, Categoria.CAFFE));
        prodotti.add(new Prodotto("p2", "Pizza napoletana", 3, Categoria.PIZZA));
        prodotti.add(new Prodotto("p3", "Coca Cola", 2, Categoria.BEVANDE));
    }
}