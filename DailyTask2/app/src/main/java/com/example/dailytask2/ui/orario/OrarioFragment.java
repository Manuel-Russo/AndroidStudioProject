package com.example.dailytask2.ui.orario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dailytask2.R;
import com.example.dailytask2.databinding.FragmentOrarioBinding;

public class OrarioFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInsranceState)   {
        View layoutO = inflater.inflate(R.layout.fragment_orario,container,false);
        return layoutO;
    }
}