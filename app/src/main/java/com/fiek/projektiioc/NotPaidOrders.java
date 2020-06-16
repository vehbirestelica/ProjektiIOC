package com.fiek.projektiioc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NotPaidOrders extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        ListView mListView = (ListView) findViewById(R.id.listView);

        Orders one = new Orders("Vehbi", "14/06/2020","Not Paid");
        Orders two = new Orders("Vehbi2", "14/06/2020","Not Paid");
        Orders three = new Orders("Vehbi3", "14/06/2020","Not Paid");
        Orders four = new Orders("Vehbi4", "14/06/2020","Not Paid");
        Orders five = new Orders("Vehbi5", "14/06/2020","Not Paid");
        Orders six = new Orders("Vehbi6", "14/06/2020","Not Paid");
        Orders seven = new Orders("Vehbi7", "14/06/2020","Not Paid");
        Orders eight = new Orders("Vehbi8", "14/06/2020","Not Paid");
        Orders nine = new Orders("Vehbi9", "14/06/2020","Not Paid");
        Orders ten = new Orders("Vehbi10", "14/06/2020","Not Paid");
        Orders eleven = new Orders("Vehbi", "14/06/2020","Not Paid");
        Orders twelve = new Orders("Vehbi2", "14/06/2020","Not Paid");
        Orders threeteen = new Orders("Vehbi3", "14/06/2020","Not Paid");
        Orders fourteen = new Orders("Vehbi4", "14/06/2020","Not Paid");
        Orders fiveteen = new Orders("Vehbi5", "14/06/2020","Not Paid");
        Orders sixteen = new Orders("Vehbi6", "14/06/2020","Not Paid");
        Orders seventeen = new Orders("Vehbi7", "14/06/2020","Not Paid");
        Orders eighteen = new Orders("Vehbi8", "14/06/2020","Not Paid");
        Orders nineteen = new Orders("Vehbi9", "14/06/2020","Not Paid");
        Orders twenty = new Orders("Vehbi10", "14/06/2020","Not Paid");

        ArrayList<Orders> npordersList = new ArrayList<>();
        npordersList.add(one);
        npordersList.add(two);
        npordersList.add(three);
        npordersList.add(four);
        npordersList.add(five);
        npordersList.add(six);
        npordersList.add(seven);
        npordersList.add(eight);
        npordersList.add(nine);
        npordersList.add(ten);
        npordersList.add(eleven);
        npordersList.add(twelve);
        npordersList.add(threeteen);
        npordersList.add(fourteen);
        npordersList.add(fiveteen);
        npordersList.add(sixteen);
        npordersList.add(seventeen);
        npordersList.add(eighteen);
        npordersList.add(nineteen);
        npordersList.add(twenty);

        OrderListAdapter npadapter = new OrderListAdapter(this, R.layout.adapterview,npordersList);
        mListView.setAdapter(npadapter);

    }
}