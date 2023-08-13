package com.example.cfs_vr_companionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
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

        for (int i = 0; i < optionValues.length; i++) {
            // Go through the list of options to create a button for each value
            String current = optionValues[i];
            Button btn = new Button(context);
            btn.setText(current);
            btn.setId(convertView.generateViewId());
            layout.addView(btn);
        }

        return view;
    }
}


