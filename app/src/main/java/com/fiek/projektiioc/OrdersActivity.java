package com.fiek.projektiioc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {
    private static final String TAG = "OrdersActivity";
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_orders);
            Log.d(TAG, "onCreate: Started");
            ListView mListView = (ListView) findViewById(R.id.listView);

            Orders one = new Orders("Vehbi", "14/06/2020","Paid");
            Orders two = new Orders("Vehbi2", "14/06/2020","Not Paid");
            Orders three = new Orders("Vehbi3", "14/06/2020","In Progress");
            Orders four = new Orders("Vehbi4", "14/06/2020","Paid");
            Orders five = new Orders("Vehbi5", "14/06/2020","Paid");
            Orders six = new Orders("Vehbi6", "14/06/2020","Paid");
            Orders seven = new Orders("Vehbi7", "14/06/2020","Not Paid");
            Orders eight = new Orders("Vehbi8", "14/06/2020","Paid");
            Orders nine = new Orders("Vehbi9", "14/06/2020","Paid");
            Orders ten = new Orders("Vehbi10", "14/06/2020","In Progress");
            Orders eleven = new Orders("Vehbi", "14/06/2020","Paid");
            Orders twelve = new Orders("Vehbi2", "14/06/2020","Not Paid");
            Orders threeteen = new Orders("Vehbi3", "14/06/2020","In Progress");
            Orders fourteen = new Orders("Vehbi4", "14/06/2020","Paid");
            Orders fiveteen = new Orders("Vehbi5", "14/06/2020","Paid");
            Orders sixteen = new Orders("Vehbi6", "14/06/2020","Paid");
            Orders seventeen = new Orders("Vehbi7", "14/06/2020","Not Paid");
            Orders eighteen = new Orders("Vehbi8", "14/06/2020","Paid");
            Orders nineteen = new Orders("Vehbi9", "14/06/2020","Paid");
            Orders twenty = new Orders("Vehbi10", "14/06/2020","In Progress");

            ArrayList<Orders> ordersList = new ArrayList<>();
            ordersList.add(one);
            ordersList.add(two);
            ordersList.add(three);
            ordersList.add(four);
            ordersList.add(five);
            ordersList.add(six);
            ordersList.add(seven);
            ordersList.add(eight);
            ordersList.add(nine);
            ordersList.add(ten);
            ordersList.add(eleven);
            ordersList.add(twelve);
            ordersList.add(threeteen);
            ordersList.add(fourteen);
            ordersList.add(fiveteen);
            ordersList.add(sixteen);
            ordersList.add(seventeen);
            ordersList.add(eighteen);
            ordersList.add(nineteen);
            ordersList.add(twenty);

            OrderListAdapter adapter = new OrderListAdapter(this, R.layout.adapterview,ordersList);
            mListView.setAdapter(adapter);

        }
}