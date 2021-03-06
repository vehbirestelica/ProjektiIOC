package com.fiek.projektiioc.Porosite;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fiek.projektiioc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotPaidOrders extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ListView mListView;
    List<NewOrders> ordersList;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        mDatabase = FirebaseDatabase.getInstance();
        mListView = (ListView) findViewById(R.id.listView);
        ordersList = new ArrayList<>();

        FirebaseUser auth;
        auth = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    protected void onStart () {
        super.onStart();
        Query query = FirebaseDatabase.getInstance().getReference("addOrder").orderByChild("statusi").equalTo("Pa Paguar").limitToLast(50);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot) {
                ordersList.clear();
                for (DataSnapshot ordersSnapshot : snapshot.getChildren()) {
                    NewOrders orders = ordersSnapshot.getValue(NewOrders.class);

                    ordersList.add(orders);
                }

                NotPaidListAdapter adapter = new NotPaidListAdapter(NotPaidOrders.this, ordersList);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {

            }
        });
    }
}

