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

    List<Map<String, String>> difficultyList;

    String jsonString;
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
        jsonString = "{\"employee\":[{\"emp_name\":\"employee1\",\"emp_no\":\"101700\"},{\"emp_name\":\"employee2\",\"emp_no\":\"101701\"},{\"emp_name\":\"employee3\",\"emp_no\":\"101702\"},"+
                "{\"emp_name\":\"employee4\",\"emp_no\":\"101703\"},{\"emp_name\":\"employee5\",\"emp_no\":\"101704\"},{\"emp_name\":\"employee6\",\"emp_no\":\"101705\"},"+
                "{\"emp_name\":\"employee7\",\"emp_no\":\"101706\"},{\"emp_name\":\"employee8\",\"emp_no\":\"101707\"},{\"emp_name\":\"employee9\",\"emp_no\":\"101708\"},"+
                "{\"emp_name\":\"employee10\",\"emp_no\":\"101709\"},{\"emp_name\":\"employee11\",\"emp_no\":\"101710\"},{\"emp_name\":\"employee12\",\"emp_no\":\"101711\"},"+
                "{\"emp_name\":\"employee13\",\"emp_no\":\"101712\"},{\"emp_name\":\"employee14\",\"emp_no\":\"101713\"},{\"emp_name\":\"employee15\",\"emp_no\":\"101712\"}]}";

        difficultyList = new ArrayList<Map<String, String>>();
        retrieveFromJson();
        ListView difficultySettings = (ListView) getView().findViewById(R.id.difficulty_list);
        SimpleAdapter _adapter = new SimpleAdapter(getActivity(), difficultyList, android.R.layout.simple_list_item_1, new String[] {"employees"}, new int[] {android.R.id.text1}  );
        difficultySettings.setAdapter(_adapter);
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
            JSONArray mainNode = response.optJSONArray("employee");

            for (int i = 0; i < mainNode.length(); i++) {
                JSONObject childNode = mainNode.getJSONObject(i);
                String name = childNode.optString("emp_name");
                String number = childNode.optString("emp_no");
                String out = name + "_" + number;
                difficultyList.add(createEmployee("employees", out));
            }
        } catch(JSONException e) {
            Toast.makeText(getActivity().getApplicationContext(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private HashMap<String, String> createEmployee(String name, String number){
        HashMap<String, String> employeeNameNo = new HashMap<String, String>();
        employeeNameNo.put(name, number);
        return employeeNameNo;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
