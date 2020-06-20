package com.fiek.projektiioc;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {
    ListView mListView;
    DatabaseReference dbRef;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    private static final String TAG = "OrdersActivity";
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_orders);
            Log.d(TAG, "onCreate: Started");
            mListView = (ListView) findViewById(R.id.listView);
            dbRef = FirebaseDatabase.getInstance().getReference("Orders");
            arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
            mListView.setAdapter(arrayAdapter);
            dbRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded (@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String value = dataSnapshot.getValue(Orders.class).toString();
                    arrayList.add(value);
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged (@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved (@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved (@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled (@NonNull DatabaseError databaseError) {

                }

            });

//            Orders one = new Orders("Vehbi", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders two = new Orders("Vehbi2", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders three = new Orders("Vehbi3", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders four = new Orders("Vehbi4", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders five = new Orders("Vehbi5", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders six = new Orders("Vehbi6", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders seven = new Orders("Vehbi7", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders eight = new Orders("Vehbi8", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders nine = new Orders("Vehbi9", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders ten = new Orders("Vehbi10", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders eleven = new Orders("Vehbi", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders twelve = new Orders("Vehbi2", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders threeteen = new Orders("Vehbi3", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders fourteen = new Orders("Vehbi4", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders fiveteen = new Orders("Vehbi5", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders sixteen = new Orders("Vehbi6", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders seventeen = new Orders("Vehbi7", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders eighteen = new Orders("Vehbi8", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders nineteen = new Orders("Vehbi9", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//            Orders twenty = new Orders("Vehbi10", "14/06/2020","Paid","drawable://"+R.drawable.mann);
//
//            ArrayList<Orders> ordersList = new ArrayList<>();
//            ordersList.add(one);
//            ordersList.add(two);
//            ordersList.add(three);
//            ordersList.add(four);
//            ordersList.add(five);
//            ordersList.add(six);
//            ordersList.add(seven);
//            ordersList.add(eight);
//            ordersList.add(nine);
//            ordersList.add(ten);
//            ordersList.add(eleven);
//            ordersList.add(twelve);
//            ordersList.add(threeteen);
//            ordersList.add(fourteen);
//            ordersList.add(fiveteen);
//            ordersList.add(sixteen);
//            ordersList.add(seventeen);
//            ordersList.add(eighteen);
//            ordersList.add(nineteen);
//            ordersList.add(twenty);
//
//            OrderListAdapter adapter = new OrderListAdapter(this, R.layout.adapterview,ordersList);
//            mListView.setAdapter(adapter);

        }
}