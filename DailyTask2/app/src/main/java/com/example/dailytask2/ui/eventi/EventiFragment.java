package com.example.dailytask2.ui.eventi;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dailytask2.Evento;
import com.example.dailytask2.MainActivity;
import com.example.dailytask2.ProdottoAdapter;
import com.example.dailytask2.R;
import com.example.dailytask2.Utility;

import java.util.ArrayList;

public class EventiFragment extends Fragment {
    public static ArrayList<Evento> eventi = new ArrayList<>();
    public static ArrayList<Evento> eventiGiornalieri = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {
        View layoutE = inflater.inflate(R.layout.fragment_eventi,container,false);
        Utility.scaricaProdotti(eventi);
        ListView lista = layoutE.findViewById(R.id.lista);
        MainActivity.adapter=new ProdottoAdapter(eventi);
        MainActivity.adapter.notifyDataSetChanged();
        lista.setAdapter(MainActivity.adapter);
        lista.setOnItemLongClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Vuoi eliminare questo evento?");
            builder.setPositiveButton("Si", (dialog, id) -> {
                Utility.rimuoviDatabase(i);
                eventi.remove(i);
                MainActivity.adapter.notifyDataSetChanged();
            })
                    .setNegativeButton("No", (dialog, id) -> dialog.cancel());
            builder.create().show();
            return true;
        });
        eventi.clear();
        return layoutE;
    }
}
