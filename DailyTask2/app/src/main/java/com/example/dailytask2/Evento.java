package com.example.dailytask2;

import java.util.HashMap;
import java.util.Map;

public class Evento {
    String nome;
    boolean notifica;
    String descrizione;
    String data;

    public Evento(String nome, String data, boolean notifica, String descrizione) {
        this.nome = nome;
        this.notifica = notifica;
        this.descrizione = descrizione;
        this.data = data;
    }

    public Evento(HashMap<String, Object> hmProdotto){
        for (Map.Entry<String,Object> elemento:hmProdotto.entrySet())   {
            switch (elemento.getKey())  {
                case "nome": this.nome = elemento.getValue().toString();break;
                case "data": this.data = elemento.getValue().toString();break;
                case "notifica": this.notifica = Boolean.parseBoolean(elemento.getValue().toString());break;
                case "descrizione": this.descrizione = elemento.getValue().toString();break;
            }
        }
    }

    HashMap<String, Object> serializzaSuFirebase()  {
        HashMap<String,Object> prodottoSerializzato = new HashMap<>();
        prodottoSerializzato.put("nome", this.nome);
        prodottoSerializzato.put("data", this.data);
        prodottoSerializzato.put("notifica", this.notifica);
        prodottoSerializzato.put("descrizione", this.descrizione);
        return prodottoSerializzato;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nome='" + nome + '\'' +
                ", notifica=" + notifica +
                ", descrizione='" + descrizione + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

