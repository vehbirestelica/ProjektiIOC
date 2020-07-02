package com.fiek.projektiioc.Porosite;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fiek.projektiioc.R;

import java.util.List;

public class MyOrdersAdapter extends ArrayAdapter<NewOrders> {

    private Activity context;
    private List<NewOrders> arrayList;

    public MyOrdersAdapter(Activity context, List<NewOrders> ordersList){
        super(context, R.layout.adapterview_my_orders,ordersList);
        this.context = context;
        this.arrayList = ordersList;
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.adapterview_my_orders, null, true);
        TextView tvDerguesi = (TextView) listViewItem.findViewById(R.id.textView1);
        TextView tvLokacioni = (TextView) listViewItem.findViewById(R.id.textView2);
        TextView tvStatusi = (TextView) listViewItem.findViewById(R.id.textView3);

        NewOrders orders = arrayList.get(position);
        tvDerguesi.setText(orders.getDerguesi());
        tvLokacioni.setText(orders.getLokacioni());
        tvStatusi.setText(orders.getStatusi());


        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        listViewItem.startAnimation(animation);

        return listViewItem;
    }
}
