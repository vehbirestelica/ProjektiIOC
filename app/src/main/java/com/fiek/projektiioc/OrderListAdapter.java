package com.fiek.projektiioc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends ArrayAdapter<Orders> {

    private static final String TAG="OrderListAdapter";
    private Context mContext;
    int mResource;

    public OrderListAdapter (@NonNull Context context, int resource, @NonNull ArrayList<Orders> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get order's info
        String emri=getItem(position).getEmri();
        String data=getItem(position).getData();
        String statusi=getItem(position).getStatusi();

        //create the orders object with infos
        Orders orders = new Orders(emri, data, statusi);

        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView tvEmri = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvData = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvStatusi = (TextView) convertView.findViewById(R.id.textView3);

        tvEmri.setText(emri);
        tvData.setText(data);
        tvStatusi.setText(statusi);

        return convertView;
    }
}
