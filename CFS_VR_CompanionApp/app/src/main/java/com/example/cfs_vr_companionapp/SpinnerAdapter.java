package com.example.cfs_vr_companionapp;

import android.content.Context;
import android.widget.ArrayAdapter;


public class SpinnerAdapter extends ArrayAdapter<String> {

    public SpinnerAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
    }
}
