package com.example.gabrach;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Utility    {

    public static void scaricaProdotti()     {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("prodotti").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //senza specificare se è stato aggiunto, modificato o tolto
                HashMap<String, HashMap<String, Object>> hmProdotti = (HashMap<String, HashMap<String, Object>>) snapshot.getValue();
                for (Map.Entry<String,HashMap<String,Object>> hmProdotto :hmProdotti.entrySet()) {
                    MainActivity.prodotti.add(new Prodotto(hmProdotto.getValue()));
                }
                MainActivity.prodottoAdapter.notifyDataSetChanged(); //comando per dire che ci sono cose nuove cose
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { //specificare se è stato tolto un elemento

            }
        });
    }
}
