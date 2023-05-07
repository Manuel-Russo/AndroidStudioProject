package com.example.dailytask2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProdottoAdapter extends BaseAdapter    {
    ArrayList<Evento> lista;

    public ProdottoAdapter(ArrayList<Evento> lista) {
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Evento getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)   {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.adapter_evento, viewGroup, false);
        }
        System.out.println("adapter "+lista.get(i).toString());
        TextView Data = view.findViewById(R.id.Data);
        TextView Nome = view.findViewById(R.id.Nome);
        TextView Descrizione = view.findViewById(R.id.Descrizione);
        Nome.setText(lista.get(i).nome);
        Data.setText(lista.get(i).data);
        Descrizione.setText(lista.get(i).descrizione);
        return view;
    }
}
