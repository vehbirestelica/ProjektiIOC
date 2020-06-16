package com.fiek.projektiioc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class ProcessingOrders extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        ListView mListView = (ListView) findViewById(R.id.listView);

        Orders one = new Orders("Vehbi", "14/06/2020","In Process");
        Orders two = new Orders("Vehbi2", "14/06/2020","In Process");
        Orders three = new Orders("Vehbi3", "14/06/2020","In Process");
        Orders four = new Orders("Vehbi4", "14/06/2020","In Process");
        Orders five = new Orders("Vehbi5", "14/06/2020","In Process");
        Orders six = new Orders("Vehbi6", "14/06/2020","In Process");

        ArrayList<Orders> pordersList = new ArrayList<>();
        pordersList.add(one);
        pordersList.add(two);
        pordersList.add(three);
        pordersList.add(four);
        pordersList.add(five);
        pordersList.add(six);

        OrderListAdapter padapter = new OrderListAdapter(this, R.layout.adapterview,pordersList);
        mListView.setAdapter(padapter);

    }
}