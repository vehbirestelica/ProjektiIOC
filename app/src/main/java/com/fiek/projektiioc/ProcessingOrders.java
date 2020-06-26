package com.fiek.projektiioc;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class ProcessingOrders extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private List<Orders> orders = new ArrayList<>();
    private ArrayList<String> arrayList = new ArrayList<String>();

    private ListView mListView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Orders");

        mListView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        mListView.setAdapter(arrayAdapter);

        FirebaseUser auth;
        auth = FirebaseAuth.getInstance().getCurrentUser();
        String currentUser = auth.getUid();

        Query query  = FirebaseDatabase.getInstance().getReference("addOrder")
                .orderByChild("statusi")
                .equalTo("Ne Proces");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String emri = snapshot.child("derguesi").getValue(String.class);
                String data = snapshot.child("marresi").getValue(String.class);
                String statusi = snapshot.child("statusi").getValue(String.class);
                String value = emri + " \t\t\t\t\t\t\t\t\t\t " + data + " \t\t\t\t\t\t\t\t\t\t" + statusi;
                arrayList.add(value);
//                arrayList.add(data);
//                arrayList.add(statusi);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved (@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {

            }
        });

    }
}
//        Orders one = new Orders("Vehbi", "14/06/2020","In Process","drawable://"+R.drawable.mann);
//        Orders two = new Orders("Vehbi2", "14/06/2020","In Process","drawable://"+R.drawable.mann);
//        Orders three = new Orders("Vehbi3", "14/06/2020","In Process","drawable://"+R.drawable.mann);
//        Orders four = new Orders("Vehbi4", "14/06/2020","In Process","drawable://"+R.drawable.mann);
//        Orders five = new Orders("Vehbi5", "14/06/2020","In Process","drawable://"+R.drawable.mann);
//        Orders six = new Orders("Vehbi6", "14/06/2020","In Process","drawable://"+R.drawable.mann);

//        ArrayList<Orders> pordersList = new ArrayList<>();
////        pordersList.add(one);
////        pordersList.add(two);
////        pordersList.add(three);
////        pordersList.add(four);
////        pordersList.add(five);
////        pordersList.add(six);
//
//        OrderListAdapter padapter = new OrderListAdapter(this, R.layout.adapterview_processing,pordersList);
//        mListView.setAdapter(padapter);
