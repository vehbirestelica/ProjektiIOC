package com.fiek.projektiioc;

import android.os.Bundle;
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
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class NotPaidOrders extends AppCompatActivity {
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

        Query query  = FirebaseDatabase.getInstance().getReference("Orders")
                .orderByChild("Statusi")
                .equalTo("Pa Paguar");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String emri = snapshot.child("Emri").getValue(String.class);
                String data = snapshot.child("Data").getValue(String.class);
                String statusi = snapshot.child("Statusi").getValue(String.class);
                String value = emri + " \t\t\t\t\t\t\t\t\t\t " + data + " \t\t\t\t\t\t\t\t\t\t\t" + statusi;
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

