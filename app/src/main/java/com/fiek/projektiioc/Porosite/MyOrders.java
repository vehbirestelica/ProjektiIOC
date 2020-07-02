package com.fiek.projektiioc.Porosite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fiek.projektiioc.R;
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

public class MyOrders extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private List<NewOrdersActivity> orders = new ArrayList<>();
    private ArrayList<String> arrayList = new ArrayList<String>();
    String value;
    private ListView mListView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        mListView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        mListView.setAdapter(arrayAdapter);

        FirebaseUser auth;
        auth = FirebaseAuth.getInstance().getCurrentUser();
        String currentUser = auth.getUid();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String currentItem = parent.getItemAtPosition(position).toString();
                Intent onClickintent = new Intent(MyOrders.this, ListViewOnClickListener.class);
                onClickintent.putExtra("myorders",currentItem);
                startActivity(onClickintent);

                Toast.makeText(MyOrders.this,"dwdwddwdwd",Toast.LENGTH_SHORT).show();
            }
        });

        Query query  = FirebaseDatabase.getInstance().getReference("addOrder")
                .orderByChild("userID")
                .equalTo(currentUser);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String emri = snapshot.child("derguesi").getValue(String.class);
                String data = snapshot.child("marresi").getValue(String.class);
                String statusi = snapshot.child("statusi").getValue(String.class);
                String ordersID = snapshot.getKey();
                String value = ordersID + " \t\t\t\t\t " + data + " \t\t\t\t" + statusi;
                NewOrders newOrders = new NewOrders();
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
