package com.example.dailytask2;

import android.content.DialogInterface;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dailytask2.ui.eventi.EventiFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Utility    {

    public static void scaricaProdotti(ArrayList<Evento> eventi)     {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("/evento").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                eventi.add(new Evento((HashMap<String, Object>) Objects.requireNonNull(snapshot.getValue())));
                MainActivity.adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("xxxx","Evento modificato");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Log.d("xyxy","Evento rimosso");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("xxxx","Evento mosso");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("xxxx","Database rimosso");
            }
        });
    }

    public static void scaricaEvento(String data)   {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("/evento/");
        mDatabase.orderByChild("data").equalTo(data).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())  {
                    HashMap<String, HashMap<String, Object>> hashMapHashMap = (HashMap<String, HashMap<String, Object>>) Objects.requireNonNull(snapshot.getValue());
                    for (Map.Entry<String, HashMap<String, Object>> stringHashMapEntry : hashMapHashMap.entrySet()) {
                        HashMap<String, Object> hashMap = stringHashMapEntry.getValue();
                        EventiFragment.eventiGiornalieri.add(new Evento(String.valueOf(hashMap.get("nome")), String.valueOf(hashMap.get("data")) ,Boolean.getBoolean(String.valueOf(hashMap.get("notifica"))), String.valueOf(hashMap.get("descrizione"))));
                        MainActivity.adapterHome.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public static void scriviDatabase(String nome, String data, boolean notifica, String descrizione)     {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Evento p = new Evento(nome, data, notifica, descrizione);
        mDatabase.child("/evento").push().setValue(p.serializzaSuFirebase());
    }

    public static void rimuoviDatabase(int i)    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query queryEliminazione = ref.child("evento").orderByChild("nome").equalTo(EventiFragment.eventi.get(i).nome);

        queryEliminazione.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot delateSnap: dataSnapshot.getChildren()) {
                    delateSnap.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("xxxx", "onCancelled", databaseError.toException());
            }
        });
    }
}
