package com.example.cfs_vr_companionapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cfs_vr_companionapp.databinding.ConfigureDifficultyBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigureSettings extends Fragment {

    private Button selectedButton;

    private ConfigureDifficultyBinding binding;

    ArrayList<Map<String, String>> difficultyList;

    String jsonString;
    String outJsonString = "";
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = ConfigureDifficultyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jsonString = "{\"setting\":[{\"setting_name\":\"Wind Speed\",\"setting_options\":\"Low, mid, high\"}, " +
                "{\"setting_name\":\"Moisture Levels\",\"setting_options\":\"Low, mid, high\"}]}";

        ArrayList<String> vals = new ArrayList<String>();
        vals.add("Difficulty");
        vals.add("Weather");
        difficultyList = new ArrayList<Map<String, String>>();
        retrieveFromJson();
        ListView difficultySettings = (ListView) getView().findViewById(R.id.difficulty_list);
        SimpleAdapter _adapter = new SimpleAdapter(getActivity(), difficultyList, android.R.layout.simple_list_item_1, new String[] {"settings"}, new int[] {android.R.id.text1}  );
        difficultySettings.setAdapter(new DifficultyListAdapter(getActivity().getApplicationContext(), difficultyList));

        binding.low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = binding.low;
                MainActivity.difficultySettings.setQuickDifficulty("Low");
            }
        });

        binding.medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = binding.medium;
                MainActivity.difficultySettings.setQuickDifficulty("Medium");
            }
        });

        binding.high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = binding.high;
                //binding.high.setBackgroundColor(0x00FF00);
                MainActivity.difficultySettings.setQuickDifficulty("High");
                MainActivity.server.sendMessage(MainActivity.difficultySettings.setDifficulty());
            }
        });
    }

    private void retrieveFromJson() {
        try {
            JSONObject response = new JSONObject(jsonString);
            JSONArray mainNode = response.optJSONArray("setting");

            for (int i = 0; i < mainNode.length(); i++) {
                Button btn = new Button(getActivity().getApplicationContext());
                JSONObject childNode = mainNode.getJSONObject(i);
                String name = childNode.optString("setting_name");
                String options = childNode.optString("setting_options");
                String out = name + ": " + options;
                difficultyList.add(createOptions("settings", out));
            }
        } catch(JSONException e) {
            Toast.makeText(getActivity().getApplicationContext(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private HashMap<String, String> createOptions(String name, String options){
        HashMap<String, String> difficultyOptions = new HashMap<String, String>();
        difficultyOptions.put(name, options);
        return difficultyOptions;
    }

    public String exportJson(){
        return outJsonString;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
