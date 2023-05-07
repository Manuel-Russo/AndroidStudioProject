package com.example.gabrach;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Prodotto {
    String id; //tabella prodotti serve id
    String nome;
    double prezzo;
    Categoria categoria;
    int quantita;

    public Prodotto(String id, String nome, double prezzo, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.categoria = categoria;
    }

    public Prodotto(HashMap<String, Object> hmProdotto){
        for (Map.Entry<String,Object> elemento:hmProdotto.entrySet())   { //entry: coppia e si chiama elemento, entrySet prende e mi restituisce la mia entry
            switch (elemento.getKey())  {
                case "nome": this.nome = elemento.getValue().toString();break;
                case "id": this.id = elemento.getValue().toString();break;
                case "prezzo": this.prezzo = Double.parseDouble(elemento.getValue().toString());break;
                case "categoria": this.categoria =Categoria.valueOf(elemento.getValue().toString());
            }
        }
    }
}
