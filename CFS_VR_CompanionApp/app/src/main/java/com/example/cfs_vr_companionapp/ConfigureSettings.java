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

    ListView difficultySettings;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = ConfigureDifficultyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    // TODO: figure out why it only allows up to 3 settings
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        difficultySettings = (ListView) getView().findViewById(R.id.difficulty_list);
        super.onViewCreated(view, savedInstanceState);
        jsonString = "{ \n" +
                "    \"app_version\": \"some\",\n" +
                "    \"settings\": [\n" +
                "        {\"setting_name\": \"Wind Speed\", \"options\": \"Low,Medium,High,Ultra\"},\n" +
                "        {\"setting_name\": \"Ground Moisture\", \"options\": \"Low,Medium,High\"},\n" +
                "        {\"setting_name\": \"Water Capacity\", \"options\": \"10000, 20000, 30000\"}\n" +
                "    ]\n" +
                "}";

        difficultyList = new ArrayList<Map<String, String>>();
        retrieveFromJson();
        difficultySettings.setAdapter(new DifficultyListAdapter(getActivity().getApplicationContext(), difficultyList));

    }

    private void retrieveFromJson() {
        try {
            JSONObject response = new JSONObject(jsonString);
            JSONArray mainNode = response.optJSONArray("settings");

            for (int i = 0; i < mainNode.length(); i++) {
                JSONObject childNode = mainNode.getJSONObject(i);
                String name = childNode.optString("setting_name");
                String subNode = childNode.optString("options");
                String out = name + ": " + subNode;
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

    public void setOptions(String options) {
        this.jsonString = options;
        retrieveFromJson();
        difficultySettings.setAdapter(new DifficultyListAdapter(getActivity().getApplicationContext(), difficultyList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
