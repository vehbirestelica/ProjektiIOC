package com.fiek.projektiioc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewOnClickListener extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_on_click_listener);

        Intent intent = getIntent();
        NewOrders orders = intent.getParcelableExtra("orders");

        TextView sasia =  findViewById(R.id.sasia2);
        TextView porosia = findViewById(R.id.porosia2);
        TextView derguesi = findViewById(R.id.derguesi2);
        TextView marresi = findViewById(R.id.marresi2);







        try {
            sasia.setText(orders.getSasia());
            porosia.setText(orders.getPorosia());
            derguesi.setText(orders.getDerguesi());
            marresi.setText(orders.getMarresi());

        } catch (NullPointerException ignored){

        }

        }
    }