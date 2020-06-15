package com.fiek.projektiioc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends ArrayAdapter<Orders> {

    private static final String TAG="OrderListAdapter";
    private Context mContext;
    private int mResource;
    private int lastPosition=-1;

    static class ViewHolder{
        TextView emri;
        TextView data;
        TextView statusi;

    }

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

        final View result;
        ViewHolder holder;

        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(mContext);
            convertView=inflater.inflate(mResource,parent,false);

            holder =new ViewHolder();

            holder.emri=(TextView)convertView.findViewById(R.id.textView1);
            holder.data=(TextView)convertView.findViewById(R.id.textView2);
            holder.statusi=(TextView)convertView.findViewById(R.id.textView3);

            result=convertView;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
            result=convertView;
        }


        Animation animation= AnimationUtils.loadAnimation(mContext, (position>lastPosition) ? R.anim.load_down_anim : R.anim.load_down_anim);

        result.startAnimation(animation);
        lastPosition = position;

        holder.emri.setText(emri);
        holder.data.setText(data);
        holder.statusi.setText(statusi);

        return convertView;
    }
}
