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

public class ProcessingOrders extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    List<NewOrders> ordersList;
    private ArrayList<String> arrayList = new ArrayList<String>();

    private ListView mListView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


        ordersList = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.listView);

        FirebaseUser auth;
        auth = FirebaseAuth.getInstance().getCurrentUser();
        String currentUser = auth.getUid();
    }

    @Override
    protected void onStart () {
        super.onStart();
        Query query = FirebaseDatabase.getInstance().getReference("addOrder").orderByChild("statusi").equalTo("Ne Proces").limitToLast(50);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot) {
                ordersList.clear();
                for(DataSnapshot ordersSnapshot : snapshot.getChildren()){
                    NewOrders orders = ordersSnapshot.getValue(NewOrders.class);

                    ordersList.add(orders);
                }

                ProcessingListAdapter adapter = new ProcessingListAdapter(ProcessingOrders.this, ordersList);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {

            }
        });
    }
}
