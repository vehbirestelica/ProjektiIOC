package com.fiek.projektiioc;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fiek.projektiioc.Porosite.NewOrders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListViewOnClickListener extends AppCompatActivity {
TextView sasia, porosia, derguesi, marresi;
FirebaseAuth firebaseAuth;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_on_click_listener);

//        Intent intent = getIntent();
//        NewOrders orders = intent.getParcelableExtra("orders");
        NewOrders newOrders = new NewOrders();

        String st = getIntent().getStringExtra("EXTRA_SESSION_ID");

         sasia =  findViewById(R.id.sasia2);
         porosia = findViewById(R.id.porosia2);
         derguesi = findViewById(R.id.derguesi2);
         marresi = findViewById(R.id.marresi2);

         firebaseAuth = FirebaseAuth.getInstance();
         firebaseDatabase = FirebaseDatabase.getInstance();


        sasia.setText(newOrders.getSasia());
        porosia.setText(newOrders.getPorosia());
        derguesi.setText(newOrders.getDerguesi());
        marresi.setText(newOrders.getMarresi());
//        databaseReference = firebaseDatabase.
//        Query query = FirebaseDatabase.getInstance().getReference("addOrder");
//        query.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////                NewOrders newOrders = snapshot.child("sasia").getValue(NewOrders.class);
////                String oSasia = newOrders.getSasia();
////                String oPorosia = newOrders.getPorosia();
//
//            }
//
//            @Override
//            public void onChildChanged (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved (@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved (@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled (@NonNull DatabaseError error) {
//
//            }
//        });

        }
    }