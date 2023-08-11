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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DifficultyListAdapter extends BaseAdapter implements ListAdapter {

    private Context context;
    private TextView difficultyName;
    private ArrayList<Map<String, String>> options;

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
        }

        //Handle TextView and display string from your list
        TextView tvContact= (TextView)view.findViewById(R.id.dfName);
        tvContact.setText(options.get(position).keySet().iterator().next() + " " + options.get(position).get("settings"));

        //Handle buttons and add onClickListeners
        Button callbtn = (Button) view.findViewById(R.id.btn);
        callbtn.setText("Test");

        callbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something

            }
        });

        return view;
    }
}


