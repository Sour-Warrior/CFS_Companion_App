package com.example.cfs_vr_companionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cfs_vr_companionapp.databinding.ConfigureSettingsBinding;

public class ConfigureSettings extends Fragment {

    private Button selectedButton;

    private ConfigureSettingsBinding binding;

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = binding.low;
                binding.low.setBackgroundColor(0x00FF00);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
