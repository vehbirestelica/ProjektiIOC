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

public class MyOrders extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private List<NewOrdersActivity> orders = new ArrayList<>();
    List<NewOrders> arrayList;
    private ListView mListView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        mListView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();

    }
    @Override
    protected void onStart () {
        super.onStart();
        FirebaseUser auth;
        auth = FirebaseAuth.getInstance().getCurrentUser();
        String currentUser = auth.getUid();
        Query query = FirebaseDatabase.getInstance().getReference("addOrder")
                .orderByChild("userID")
                .equalTo(currentUser);;
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot ordersSnapshot : snapshot.getChildren()){
                    NewOrders orders = ordersSnapshot.getValue(NewOrders.class);

                    arrayList.add(orders);
                }

                MyOrdersAdapter adapter = new MyOrdersAdapter(MyOrders.this, arrayList);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {

            }
        });
    }
}
