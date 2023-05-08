package com.example.cfs_vr_companionapp;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothAdapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class DeviceListAdapter extends ArrayAdapter<BluetoothDevice> {

    private LayoutInflater layoutInflater;
    private ArrayList<BluetoothDevice> devices;
    private int viewResourceID;
    public Activity act;

    public DeviceListAdapter(Context context, Activity _act, int _resourceID, ArrayList<BluetoothDevice> _devices){
        super(context, _resourceID, _devices);
        this.devices = _devices;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceID = _resourceID;
        act = _act;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(viewResourceID, null);

        BluetoothDevice device = devices.get(position);

        if (device != null){
            TextView deviceName = (TextView) convertView.findViewById(R.id.deviceName);
            TextView deviceAddress = (TextView) convertView.findViewById(R.id.deviceAddress);

            if (deviceName != null) {
                if (ActivityCompat.checkSelfPermission(super.getContext(), android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        ActivityCompat.requestPermissions(
                                act,
                                new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                                2
                        );
                    }
                }
                deviceName.setText(device.getName());
                deviceAddress.setText(device.getAddress());

            }
        }
        return convertView;
    }


}
