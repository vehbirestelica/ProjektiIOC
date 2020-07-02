package com.fiek.projektiioc.Porosite;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fiek.projektiioc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListViewOnClickListener extends AppCompatActivity {
TextView statusi, porosia, derguesi, marresi, lokacioni;
FirebaseAuth firebaseAuth;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
private String currentItem;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_on_click_listener);

//        currentItem = getIntent().getExtras().get("myorders").toString();
        statusi = findViewById(R.id.statusi2);
        porosia = findViewById(R.id.porosia2);
        derguesi = findViewById(R.id.derguesi2);
        marresi = findViewById(R.id.marresi2);
        lokacioni = findViewById(R.id.lokacioni2);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        if (getIntent().getStringExtra("myorders") != null) {

            currentItem = getIntent().getStringExtra("myorders");
            String[] sstrin = currentItem.split(" \t",5);
            porosia.setText(sstrin[0]);
            derguesi.setText(sstrin[1]);
            marresi.setText(sstrin[2]);
//            porosia.setText(currentItem);
//            derguesi.setText(currentItem);
//            marresi.setText(currentItem);
//            Query query = FirebaseDatabase.getInstance().getReference("addOrder").equalTo(sstrin[0]);
//            query.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange (@NonNull DataSnapshot snapshot) {
//                    NewOrders orders = snapshot.getValue(NewOrders.class);
//                    ordersList.add(orders);
//
//                }
//
//                @Override
//                public void onCancelled (@NonNull DatabaseError error) {
//
//                }
//            });

        }
//        Intent intent = getIntent();
//        NewOrders newOrders = intent.getParcelableExtra("key");
            //        String orderSt = getIntent().getStringExtra("EXTRA_SESSION_ID");




//        sasia.setText(newOrders.getSasia());
//        porosia.setText(newOrders.getPorosia());
//        derguesi.setText(newOrders.getDerguesi());
//        marresi.setText(newOrders.getMarresi());

        }
    }