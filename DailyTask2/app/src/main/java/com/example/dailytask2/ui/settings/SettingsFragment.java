package com.example.dailytask2.ui.settings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.dailytask2.LoginActivity;
import com.example.dailytask2.R;
import com.firebase.ui.auth.AuthUI;

public class SettingsFragment extends Fragment {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutf = inflater.inflate(R.layout.fragment_setting,container,false);
        Switch switch1 = (Switch) layoutf.findViewById(R.id.notificheSwitch);
        switch1.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b)     {
                switch1.setText("ON");
            }
            else    {
                switch1.setText("OFF");
            }
        });

        Switch switch2 = (Switch) layoutf.findViewById(R.id.temaSwitch);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)    {
            switch2.setChecked(true);
        }
        else    {
            switch1.setChecked(false);
        }
        switch2.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b)     {
                switch2.setText("ON");
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            else    {
                switch2.setText("OFF");
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        Button bottone = (Button) layoutf.findViewById(R.id.supportare);
        bottone.setOnClickListener(view -> {
            Uri uri = Uri.parse("https://paypal.me/ManuRusso04");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });

        Button logout = (Button) layoutf.findViewById(R.id.logout);
        logout.setOnClickListener(view -> new AlertDialog.Builder(SettingsFragment.this.requireContext()).setMessage("Sei sicuro di voler uscire?")
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> AuthUI.getInstance()
                        .signOut(SettingsFragment.this.requireContext()).addOnCompleteListener(task -> {
                            Intent intent= new Intent(SettingsFragment.this.requireContext(), LoginActivity.class);
                            startActivity(intent);
                        }))
                .setNegativeButton("Annulla", (dialog, which) -> dialog.dismiss()).setIcon(android.R.drawable.ic_dialog_alert).show());
        return layoutf;
    }
}