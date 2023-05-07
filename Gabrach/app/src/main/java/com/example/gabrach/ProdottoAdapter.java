package com.example.gabrach;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProdottoAdapter extends BaseAdapter    {
    ArrayList<Prodotto> lista;
    Context contesto; //dichiaro variabile contesto per indicare dove mi trovo concon la creazione dell'oggetto

    public ProdottoAdapter(ArrayList<Prodotto> lista, Context contesto) {
        this.lista = lista;
        this.contesto = contesto;
    }
    //--------METODI IN OVERRIDE DALLA CLASSE BaseAdapter---------
    @Override
    public int getCount() {
        return lista.size(); //restitutisco la grandezza della lista
    }

    @Override
    public Prodotto getItem(int i) {
        return lista.get(i); //restituisco l'oggetto i della lista
    }

    @Override
    public long getItemId(int i) {
        return i; //restituisco la posizione dell'item
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) { //metodo per la visualizzazione
        if (view == null)   { //se la view è vuota
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext()); //dichiro nuovo Inflater dal contesto di viewGroup
            view = inflater.inflate(R.layout.layout_prodotto, viewGroup, false); //la visualizzazione è l'inflate del layout del prodotto
        }
        ImageView imgCategoria = view.findViewById(R.id.imgCategoria);  //immagine della categoria deve essere preso da view, perchè questa non è una activity
        TextView txtNome = view.findViewById(R.id.txtNome);
        txtNome.setOnClickListener(v -> { //lambda per il click
            Toast toast = new Toast(contesto); //dichiaro un nuovo toast per il contesto in cui sono
            //faccio apparire il toast al click
            if (lista.get(i).quantita == 0)     { //---------modificato---------
                toast.setText("Non puoi comprare 0 elementi");
            }
            else {
                toast.setText("Vuoi comprare " + lista.get(i).quantita + " " + txtNome.getText() + " ?"); //stampo nel toast se vuole comprare l'oggetto selezionato
            }
            toast.show();
        });
        TextView txtPrezzo = view.findViewById(R.id.txtPrezzo);
        Prodotto prodotto = lista.get(i); //il prodotto è uguale alla lista in posizione i
        txtNome.setText(prodotto.nome); //inserisco il nome del prodotto
        txtPrezzo.setText("€" + prodotto.prezzo);
        switch (prodotto.categoria)     { //switch per il set delle immagini nelle varie categorie
            case PANINI:
                imgCategoria.setImageResource(R.mipmap.panino); //inserirsco la risorsa dell'immagine che si trova in mipmap
                break;
            case CAFFE:
                imgCategoria.setImageResource(R.mipmap.coffee);
                break;
            case DOLCI:
                imgCategoria.setImageResource(R.mipmap.brioche);
                break;
            case PIZZA:
                imgCategoria.setImageResource(R.mipmap.pizza);
                break;
            case BEVANDE:
                imgCategoria.setImageResource(R.mipmap.bibita);
        }
        TextView quantita = view.findViewById(R.id.quantita); //-----------modificato------------
        quantita.setText(String.valueOf(prodotto.quantita));
        TextView bottonePiu = view.findViewById(R.id.bottonePiu);
        bottonePiu.setOnClickListener(v ->  {
            prodotto.quantita += 1;
            quantita.setText(String.valueOf(prodotto.quantita));
        });
        TextView bottoneMeno = view.findViewById(R.id.bottoneMeno);
        bottoneMeno.setOnClickListener(v -> {
            if (prodotto.quantita > 0)  {
                prodotto.quantita -= 1;
                quantita.setText(String.valueOf(prodotto.quantita));
            }
        });
        return view;
    }
}
