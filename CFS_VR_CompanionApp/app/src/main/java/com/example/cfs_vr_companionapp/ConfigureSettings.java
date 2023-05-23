package com.example.cfs_vr_companionapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cfs_vr_companionapp.databinding.ConfigureSettingsBinding;

public class ConfigureSettings extends Fragment {

    private Button selectedButton;

    private ConfigureSettingsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = ConfigureSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = binding.low;
                binding.low.setBackgroundColor(0x00FF00);
                MainActivity.difficultySettings.setQuickDifficulty("Low");
            }
        });

        binding.medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = binding.medium;
                binding.medium.setBackgroundColor(0x00FF00);
                MainActivity.difficultySettings.setQuickDifficulty("Medium");
            }
        });

        binding.high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = binding.high;
                binding.high.setBackgroundColor(0x00FF00);
                MainActivity.difficultySettings.setQuickDifficulty("High");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
