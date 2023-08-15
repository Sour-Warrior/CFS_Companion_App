package com.example.cfs_vr_companionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class DifficultyListAdapter extends BaseAdapter implements ListAdapter {
    private Context context;
    private ArrayList<Map<String, String>> options;

    LinearLayout layout;

    public DifficultyListAdapter(Context context, ArrayList<Map<String, String>> _options) {
       this.options = _options;
       this.context = context;
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public long getItemId(int pos){
        return 0;
    }

    @Override
    public Object getItem(int pos) {
        return options.get(pos);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.difficulty_options, null);
            layout = (LinearLayout) view.findViewById(R.id.dfList);
        }

        String[] splitOptions = options.get(position).get("settings").split(":");
        String optionName = splitOptions[0];
        String[] optionValues = splitOptions[1].split(",");

        //Handle TextView and display string from your list
        TextView difficultyName = (TextView)view.findViewById(R.id.dfName);
        difficultyName.setText(optionName);

        Spinner spinner = (Spinner) view.findViewById(R.id.difficulty_options);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, optionValues);
        spinner.setAdapter(spinnerAdapter);

        return view;
    }
}


